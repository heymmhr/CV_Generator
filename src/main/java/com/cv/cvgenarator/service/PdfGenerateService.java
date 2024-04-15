package com.cv.cvgenarator.service;

import com.cv.cvgenarator.dto.BasicInformationDto;

import java.io.ByteArrayOutputStream;

public interface PdfGenerateService {

  /*  // get
    CVGenerateInfoDto getCVGenerateInfoByEmail(String email);

    // get all
    List<CVGenerateInfoDto> getAllCVGenerateInfo();*/

    ByteArrayOutputStream generatePdf(Short id);
}
