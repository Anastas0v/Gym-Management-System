package com.gymbuddy.web.api.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageHandler
{
    @Value("${upload.directory}")
    private String uploadDir;

    public String handleFileUpload(MultipartFile file, String entityName) throws IOException
    {
        Path uploadPath = Paths.get(uploadDir);

        if (!uploadPath.toFile().exists())
        {
            uploadPath.toFile().mkdirs();
        }

        String fileName = entityName + "-" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        file.transferTo(filePath.toFile());

        return "/images/" + fileName;
    }
}
