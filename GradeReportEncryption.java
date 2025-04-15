import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GradeReportEncryption {
    public static void main(String[] args) throws Exception {
        // Step 1: Generate a secret key
        SecretKey key = KeyGeneration.generateKey();

        // Step 2: Encrypt the grade report
        String gradeReport = """
                             Student Name: John Doe
                             Student ID: 123456
                             Course: Math 101 - Grade: A
                             Course: Physics 201 - Grade: B+
                             Course: History 101 - Grade: A-""";
        byte[] encryptedDataWithIV = Encryption.encrypt(gradeReport, key);

        // Step 3: Store the encrypted data in a file
        try (FileOutputStream fos = new FileOutputStream("encrypted_grade_report.dat")) {
            fos.write(encryptedDataWithIV);
        }

        // Step 4: Read the encrypted data from the file
        byte[] storedData;
        try (FileInputStream fis = new FileInputStream("encrypted_grade_report.dat")) {
            storedData = fis.readAllBytes();
        }

        // Step 5: Decrypt the data
        String decryptedReport = Decryption.decrypt(storedData, key);
        System.out.println("Decrypted Grade Report:\n" + decryptedReport);



        // Step 6: Verify the decryption
        if (gradeReport.equals(decryptedReport)) {
            System.out.println("Decryption successful: Data matches original.");
        } else {
            System.out.println("Decryption failed: Data does not match.");
        }
    }
}