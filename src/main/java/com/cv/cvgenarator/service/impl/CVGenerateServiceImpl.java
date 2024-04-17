package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.*;
import com.cv.cvgenarator.repo.*;
import com.cv.cvgenarator.service.*;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.layout.BoxBuilder;
import org.xhtmlrenderer.layout.LayoutContext;
import org.xhtmlrenderer.pdf.ITextFontContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.render.BlockBox;
import org.xhtmlrenderer.render.ViewportBox;

import java.io.*;
import java.util.Base64;
import java.util.List;

@Service
public class CVGenerateServiceImpl implements CVGenerateService {

    private final BasicInformationService basicInformationService;
    private final AddressInformationService addressInformationService;
    private final EducationInformationService educationInformationService;
    private final ExperienceInformationService experienceInformationService;
    private final ProjectInformationService projectInformationService;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${project.image}")
    private String path;

    public CVGenerateServiceImpl(BasicInformationRepo basicInformationRepo, EducationInformationRepo educationInformationRepo, ExperienceInformationRepo experienceInformationRepo, ProjectInformationRepo projectInformationRepo, AddressInformationRepo addressInformationRepo, CountryRepo countryRepo, ProvinceRepo provinceRepo, DistrictRepo districtRepo, LocalLevelRepo localLevelRepo, BasicInformationService basicInformationService, AddressInformationService addressInformationService, EducationInformationService educationInformationService, ExperienceInformationService experienceInformationService, ProjectInformationService projectInformationService) {
        this.basicInformationService = basicInformationService;
        this.addressInformationService = addressInformationService;
        this.educationInformationService = educationInformationService;
        this.experienceInformationService = experienceInformationService;
        this.projectInformationService = projectInformationService;
    }

    public ByteArrayOutputStream generatePdf(Short id) {

        String htmlContent = null;
        try {
            htmlContent = getAllInformation(id);
            String fileName = "test.pdf";
            return generatePdfFromHtml(htmlContent, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
            return null;
        }
    }

    @Override
    public String getAllInformation(Short id) throws IOException {
        BasicInformationDto basicInformationDto = basicInformationService.getBasicInformationById(id);
        basicInformationDto.setProfileImage(imgToBase64(basicInformationDto.getProfileImage()));

        List<EducationInformationDto> educationInformationDtos = educationInformationService.getEducationInfoByBasicInfoId(id);
        List<ExperienceInformationDto> experienceInformationDtos = experienceInformationService.getExperienceInfoByBasicInfoId(id);
        List<ProjectInformationDto> projectInformationDtos = projectInformationService.getProjectInfoByBasicInfoId(id);
        List<AddressInformationDto> addressInformationDtos = addressInformationService.getAddressInfoByBasicInfoId(id);

        Context context = new Context();
        context.setVariable("basicInformationDto", basicInformationDto);
        context.setVariable("educationInformationDtos", educationInformationDtos);
        context.setVariable("experienceInformationDtos", experienceInformationDtos);
        context.setVariable("projectInformationDtos", projectInformationDtos);
        context.setVariable("addressInformationDtos", addressInformationDtos);

        return templateEngine.process("index", context);
    }

    private ByteArrayOutputStream generatePdfFromHtml(String html, String fileName) throws Exception {
        String outputFilePath = System.getProperty("user.home") + File.separator + fileName;

        OutputStream outputStream = new FileOutputStream(outputFilePath);

        //TODO remove this code when test success

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();



        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlToXhtml(html));
        renderer.layout();
        renderer.createPDF(byteArrayOutputStream);

        //TODO remove this code when test success
        renderer.createPDF(outputStream);

        File outputFile = new File(outputFilePath);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        }
        outputStream.close();


        byteArrayOutputStream.close();

        System.out.println("here");

        return byteArrayOutputStream;
    }

    private static String htmlToXhtml(String html) {
        Document document = Jsoup.parse(html);
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document.html();
    }

    private String imgToBase64(String imgName) throws IOException {
        String filePath = path + File.separator + imgName;
        String encodedString = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(new File(filePath)));
        return "data:image/png;base64,".concat(encodedString);
    }

}
