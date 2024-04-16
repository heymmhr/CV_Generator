package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.dto.ExperienceInformationDto;
import com.cv.cvgenarator.dto.ProjectInformationDto;
import com.cv.cvgenarator.repo.*;
import com.cv.cvgenarator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

    public CVGenerateServiceImpl(BasicInformationRepo basicInformationRepo, EducationInformationRepo educationInformationRepo, ExperienceInformationRepo experienceInformationRepo, ProjectInformationRepo projectInformationRepo, AddressInformationRepo addressInformationRepo, CountryRepo countryRepo, ProvinceRepo provinceRepo, DistrictRepo districtRepo, LocalLevelRepo localLevelRepo, BasicInformationService basicInformationService, AddressInformationService addressInformationService, EducationInformationService educationInformationService, ExperienceInformationService experienceInformationService, ProjectInformationService projectInformationService) {
        this.basicInformationService = basicInformationService;
        this.addressInformationService = addressInformationService;
        this.educationInformationService = educationInformationService;
        this.experienceInformationService = experienceInformationService;
        this.projectInformationService = projectInformationService;
    }

    public ByteArrayOutputStream generatePdf(Short id) {

        BasicInformationDto basicInformationDto = basicInformationService.getBasicInformationById(id);

        /*String htmlContent = generateHtmlContent(basicInformationDto);*/
        String htmlContent = "";
        System.out.println("------------: " + htmlContent);
        String fileName = basicInformationDto.getFirstName() + ".pdf";
        try {
            return generatePdfFromHtml(htmlContent, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
            return null;
        }
    }

    @Override
    public String getAllInformation(Short id) {
        BasicInformationDto basicInformationDto = basicInformationService.getBasicInformationById(id);
        List<EducationInformationDto> educationInformationDtos = educationInformationService.getEducationInfoByBasicInfoId(id);
        List<ExperienceInformationDto> experienceInformationDtos = experienceInformationService.getExperienceInfoByBasicInfoId(id);
        List<ProjectInformationDto> projectInformationDtos = projectInformationService.getProjectInfoByBasicInfoId(id);

        Context context = new Context();
        context.setVariable("basicInformationDto", basicInformationDto);
        context.setVariable("educationInformationDtos", educationInformationDtos);
        context.setVariable("experienceInformationDtos", experienceInformationDtos);
        context.setVariable("projectInformationDtos", projectInformationDtos);

        return templateEngine.process("index", context);
    }

    private ByteArrayOutputStream generatePdfFromHtml(String html, String fileName) throws Exception {
        String outputFilePath = System.getProperty("user.home") + File.separator + fileName;
        OutputStream outputStream = new FileOutputStream(outputFilePath);

        //TODO remove this code when test success

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(byteArrayOutputStream);

        //TODO remove this code when test success
        renderer.createPDF(outputStream);
        outputStream.close();


        byteArrayOutputStream.close();

        System.out.println("here");

        return byteArrayOutputStream;
    }


}
