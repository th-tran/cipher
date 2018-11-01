package cryptography;

import java.lang.Math;

public class RSA {
  
  public int d, e, n;
  public RSA() {
    // Two random prime numbers
    int p = 3;
    int q = 7;

    // First part of public key
    n = p * q;
    // Second part of public key
    e = 2;
    int phi = (p - 1) * (q - 1);
    while (e < phi) {
      // e must be between 1 and phi (exclusive) and coprime to phi
      if (gcd(e, phi) == 1) {
        break;
      } else {
        e++;
      }
    }

    // Private key
    // d must satisty: d * e = 1 + (k * phi)
    int k = 2;
    d = (1 + (k * phi)) / e;

    System.out.println(String.format("Public: %d , %d", n, e));
    System.out.println(String.format("Private: %d", d));
  }

  private static int gcd(int e, int phi) {
    int temp;
    while (true) {
      temp = (e % phi);
      if (temp == 0) {
        return phi;
      }
      e = phi;
      phi = temp;
    }
  }

  public double encrypt(double msg, int e, int n) {
    // Encryption c = (msg ^ e) % n
    double c = Math.pow(msg, e);
    c = (c % n);
    return c;
  }

  public double decrypt(double msg, int d, int n) {
    // Decryption c = (msg ^ d) % n
    double c = Math.pow(msg, d);
    c = (c % n);
    return c;
  }
}
