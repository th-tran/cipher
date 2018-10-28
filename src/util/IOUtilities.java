package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public final class IOUtilities {
  public static String getUserInput() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    br.close();
    return input;
  }
}
