import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import javax.crypto.SecretKey;

public class Encryption {
    public static byte[] encrypt(String plaintext, SecretKey key) throws Exception {
        // Generate a random IV
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Initialize cipher for encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        // Encrypt the data
        byte[] encryptedData = cipher.doFinal(plaintext.getBytes());

        // Combine IV and encrypted data for storage/transmission
        byte[] result = new byte[iv.length + encryptedData.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encryptedData, 0, result, iv.length, encryptedData.length);
        return result;
    }
}