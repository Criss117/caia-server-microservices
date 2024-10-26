package com.solidos.caia.papers.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FileController {
  @GetMapping("/files/{filename}")
  public ResponseEntity<Resource> getPdf(@PathVariable String filename) {
    // Cargar el archivo PDF desde la carpeta resources/papers
    Resource resource = new ClassPathResource("papers/" + filename);

    // Verificar si el archivo existe
    if (!resource.exists()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Preparar los encabezados para descargar o mostrar el archivo
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"");

    return ResponseEntity
        .ok()
        .contentType(MediaType.APPLICATION_PDF)
        .headers(headers)
        .body(resource);
  }

}
