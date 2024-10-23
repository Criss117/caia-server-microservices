package com.solidos.caia.conferences.utils;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse<T> {

  /**
   * Código de estado HTTP (ej. 200 para éxito, 400 para error de cliente, etc.).
   */
  private int status;

  /**
   * Mensaje de error, si ocurre alguno. Será null si no hay error.
   */
  private String error;

  /**
   * Mensaje informativo o de éxito.
   */
  private String message;

  /**
   * Datos asociados con la respuesta, puede ser de cualquier tipo.
   */
  private T data;

  /**
   * Método de fábrica para una respuesta exitosa.
   */
  public static <T> CommonResponse<T> success(String message) {
    return CommonResponse.<T>builder()
        .status(HttpStatus.OK.value()) // o cualquier otro código de éxito
        .message(message)
        .build();
  }

  /**
   * Método de fábrica para una respuesta exitosa.
   */
  public static <T> CommonResponse<T> success(String message, T data) {
    return CommonResponse.<T>builder()
        .status(HttpStatus.OK.value()) // o cualquier otro código de éxito
        .message(message)
        .data(data)
        .build();
  }

  public static <T> CommonResponse<T> success(String message, T data, int status) {
    return CommonResponse.<T>builder()
        .status(status) // o cualquier otro código de éxito
        .message(message)
        .data(data)
        .build();
  }

  /**
   * Método de fábrica para una respuesta con error.
   */
  public static <T> CommonResponse<T> errorResponse(String error, int status) {
    return CommonResponse.<T>builder()
        .status(status)
        .error(error)
        .build();
  }
}
