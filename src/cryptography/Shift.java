package cryptography;

public class Shift {
  
  public Shift() {
  }
  
  public String encrypt(String msg, int shift) {
    String encrypted = "";
    int len = msg.length();
    for(int x = 0; x < len; x++){
      char c = (char)(msg.charAt(x) + shift);
      if (c > 'z')
        encrypted += (char)(msg.charAt(x) - (26-shift));
      else
        encrypted += (char)(msg.charAt(x) + shift);
    }
    return encrypted;
  }
}
