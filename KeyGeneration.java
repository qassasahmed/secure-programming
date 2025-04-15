import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGeneration {
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256); // Use 256-bit keys for maximum security
        return keyGen.generateKey();
    }
}