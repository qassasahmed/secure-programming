import java.util.Arrays;
import javax.crypto.SecretKey;

public class GradeReportEncryption {
    public static void main(String[] args) throws Exception {
        // Generate a secret key
        SecretKey key = KeyGeneration.generateKey();

        // Encrypt the grade report
        String gradeReport = "You passed";

        byte[] encryptedDataWithIV = Encryption.encrypt(gradeReport, key);
        System.out.println("Encrypted Grade Report:\n" + Arrays.toString(encryptedDataWithIV));

        //  Decrypt the data
        String decryptedReport = Decryption.decrypt(encryptedDataWithIV, key);
        System.out.println("Decrypted Grade Report:\n" + decryptedReport);

    }
}