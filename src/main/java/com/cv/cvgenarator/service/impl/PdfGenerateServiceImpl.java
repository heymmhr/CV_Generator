package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.dto.BasicInformationDto;
import com.cv.cvgenarator.dto.EducationInformationDto;
import com.cv.cvgenarator.repo.BasicInformationRepo;
import com.cv.cvgenarator.service.BasicInformationService;
import com.cv.cvgenarator.service.PdfGenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

@Service
public class PdfGenerateServiceImpl implements PdfGenerateService {

    private final BasicInformationService basicInformationService;
    @Autowired
    private TemplateEngine templateEngine;

    public PdfGenerateServiceImpl(BasicInformationService basicInformationService, BasicInformationRepo basicInformationRepo) {
        this.basicInformationService = basicInformationService;
    }

    public ByteArrayOutputStream generatePdf(Short id) {

        BasicInformationDto basicInformationDto = basicInformationService.getBasicInformationById(id);
//        EducationInformationDto educationInformationDto =
        String htmlContent = generateHtmlContent(basicInformationDto);
        System.out.println("------------: " + htmlContent);
        String fileName = basicInformationDto.getFirstName()+".pdf";
        try {
            return generatePdfFromHtml(htmlContent, fileName);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
            return null;
        }
    }

    private String generateHtmlContent(BasicInformationDto basicInformationDto) {


        Context context = new Context();
        // context.setVariable("to", "Baeldung");

        context.setVariable("basicInfo", basicInformationDto);

        String html = templateEngine.process("index", context);

        return html;
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
