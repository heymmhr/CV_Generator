package com.cv.cvgenarator.service;

import java.io.ByteArrayOutputStream;

public interface CVGenerateService {


    ByteArrayOutputStream generatePdf(Short id);

    String getAllInformation(Short id);





}
