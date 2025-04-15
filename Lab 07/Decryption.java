import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Decryption {
    public static String decrypt(byte[] encryptedDataWithIV, SecretKey key) throws Exception {
        // Extract IV and ciphertext
        byte[] iv = new byte[16];
        byte[] encryptedData = new byte[encryptedDataWithIV.length - 16];
        System.arraycopy(encryptedDataWithIV, 0, iv, 0, 16);
        System.arraycopy(encryptedDataWithIV, 16, encryptedData, 0, encryptedData.length);

        // Initialize cipher for decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

        // Decrypt the data
        byte[] decryptedData = cipher.doFinal(encryptedData);
        return new String(decryptedData);
    }
}