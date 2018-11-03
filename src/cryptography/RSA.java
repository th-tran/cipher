package cryptography;

import java.lang.Math;
import java.math.BigInteger;
import java.util.Random;

public class RSA {

  public BigInteger d, e, N, p, q, phi;
  public Random r;
  public int bitLength;

  public RSA() {
    // Two random unique prime numbers (p and q)
    // NOTE: If the message is larger than the modulus (N), the message recieved
    //       after decryption will be inaccurate. Use large prime numbers to avoid this.
    bitLength = 1024;
    r = new Random();
    p = BigInteger.probablePrime(bitLength, r);
    q = BigInteger.probablePrime(bitLength, r);

    // First part of public key (modulus N)
    N = p.multiply(q);
    // The totient (phi)
    phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
    // Second part of public key (encryption exponent e)
    // (1 < e < phi) AND (e is coprime to phi)
    e = BigInteger.valueOf(2);
    // e = BigInteger.probablePrime(bitLength / 2, r);
    while ((e.compareTo(phi) < 0) && (e.gcd(phi).compareTo(BigInteger.ONE) > 0)) {
        e = e.add(BigInteger.ONE);
    }

    // Private key (decryption exponent d)
    // d * e = 1 + (k * phi)
    // TODO: Try to solve for d by implementing the extended Euclid algorithm
    d = e.modInverse(phi);

    // TODO: Generate files to store key values instead of printing to screen
    System.out.println(String.format("Public: %d , %d", e, N));
    System.out.println(String.format("Private: %d", d));
  }

  public byte[] encrypt(byte[] message) {
    // x = (message ^ e) % n
    return (new BigInteger(message)).modPow(e, N).toByteArray();
  }

  public byte[] decrypt(byte[] message) {
    // x = (message ^ d) % n
    return (new BigInteger(message)).modPow(d, N).toByteArray();
  }
}
