package parser;

public enum AlgoEnum {
  
  SHIFT,
  RSA;

  // converter
  public static AlgoEnum fromString(String code) {
    for (AlgoEnum algo : AlgoEnum.values()) {
      if (algo.toString().equalsIgnoreCase(code)) {
        return algo;
      }
    }
    return null;
  }
}
