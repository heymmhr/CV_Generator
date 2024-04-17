package com.cv.cvgenarator.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface CVGenerateService {


    ByteArrayOutputStream generatePdf(Short id);

    String getAllInformation(Short id) throws IOException;


}
