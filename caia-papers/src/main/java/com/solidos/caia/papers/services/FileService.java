package com.solidos.caia.papers.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
  public String uploadFile(MultipartFile file);
}
