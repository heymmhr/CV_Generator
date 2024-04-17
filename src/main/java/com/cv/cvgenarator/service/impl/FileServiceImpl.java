package com.cv.cvgenarator.service.impl;

import com.cv.cvgenarator.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();

        //to generate random name
        String randomID = UUID.randomUUID().toString();
//        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

        String finalFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        // Full path
        String filePath = path + File.separator + finalFileName;

        //create folder if not created
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return finalFileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
