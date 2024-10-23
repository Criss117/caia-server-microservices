package com.solidos.caia.authors.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  public String uploadFile(MultipartFile file);
}
