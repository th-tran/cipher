package cryptography;

public class Shift {
  
  private int s;

  public Shift(int shift) {
    s = shift;
  }
  
  public String encrypt(String msg) {
    String encrypted = "";
    int len = msg.length();
    for(int x = 0; x < len; x++){
      char c = (char)(msg.charAt(x) + s);
      char start = 'Z';
      if (('a' <= c) && (c <= 'z')) {
        start = 'z';
      } else if (!(('A' <= c) && (c <= 'Z'))) {
        encrypted += c;
        continue;
      }
      if (c > start){
        encrypted += (char)(msg.charAt(x) - (26-s));
      } else {
        encrypted += (char)(msg.charAt(x) + s);
      }
    }
    return encrypted;
  }

  public String decrypt(String msg) {
    String decrypted = "";
    int len = msg.length();
    for(int x = 0; x < len; x++){
      char c = (char)(msg.charAt(x) - s);
      char start = 'A';
      if (('a' <= c) && (c <= 'z')) {
        start = 'a';
      } else if (!(('A' <= c) && (c <= 'Z'))) {
        decrypted += c;
        continue;
      }
      if (c < start) {
        decrypted += (char)(msg.charAt(x) + (26-s));
      } else {
        decrypted += (char)(msg.charAt(x) - s);
      }
    }
    return decrypted;
  }
}
