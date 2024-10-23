package com.solidos.caia.authors.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FileServiceImpl implements FileService {
  public String uploadFile(MultipartFile file) {
    try {
      String fileName = UUID.randomUUID().toString();
      byte[] bytes = file.getBytes();
      String originalName = file.getOriginalFilename();

      long fileSize = file.getSize();
      long maxFileSize = 5 * 1024 * 1024;

      if (fileSize > maxFileSize) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File too large");
      }

      if (originalName == null || !originalName.endsWith(".pdf")) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File too large");
      }

      String fileExtension = originalName.substring(originalName.lastIndexOf("."));

      String newFileName = fileName + fileExtension;

      File folder = new File("src/main/resources/papers");

      if (!folder.exists()) {
        folder.mkdirs();
      }

      Path path = Paths.get("src/main/resources/papers/" + newFileName);

      Files.write(path, bytes);

      return newFileName;

    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error uploading file");
    }
  }
}
