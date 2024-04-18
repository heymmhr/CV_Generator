package com.cv.cvgenarator.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public interface CVGenerateService {


    ByteArrayOutputStream generatePdf(Short id);

    Map<String, String> getAllInformation(Short id) throws IOException;


}
