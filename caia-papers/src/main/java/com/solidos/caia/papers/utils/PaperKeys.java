package com.solidos.caia.papers.utils;

import java.util.List;

public class PaperKeys {
  public static String toString(List<String> keys) {
    return keys.stream().reduce((a, b) -> a + "," + b).orElse("");
  }
}
