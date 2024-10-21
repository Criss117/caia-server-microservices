package com.solidos.caia.conferences.utils;

public class SlugGenerator {
  public static String generate(String name) {
    return name.toLowerCase().replace(" ", "-");
  }
}
