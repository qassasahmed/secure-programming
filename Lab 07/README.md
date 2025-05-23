# Secure-Programming
| **Term**                | **Definition**                                                                                     |
|-------------------------|----------------------------------------------------------------------------------------------------|
| **AES**                 | A method to scramble data so only someone with the right key can unscramble it. It's a widely used encryption algorithm. |
| **CBC**                 | A way to use AES where each piece of data depends on the previous one, making it harder to crack. It stands for Cipher Block Chaining. |
| **PKCS5Padding**        | Adds extra bits to the data so it fits perfectly into the encryption process. This ensures the data is a multiple of the block size required by AES. |
| **SecretKey**           | The key used to lock (encrypt) and unlock (decrypt) the data. It's like the key to a safe. |
| **KeyGenerator**        | A tool that creates a new, random secret key for encryption. It's like a key-making machine. |
| **Cipher**              | The engine that does the actual encrypting and decrypting. It follows the instructions provided by the encryption algorithm and mode. |
| **IvParameterSpec**     | Holds the initialization vector (IV), which is a random value used to start the encryption process in CBC mode. It ensures that the same plaintext encrypts differently each time. |
| **ByteBuffer**          | A container that holds a sequence of bytes. It's used to combine the IV and encrypted data into a single byte array. |
| **Base64**              | A way to turn binary data (like encrypted bytes) into text so it can be easily shared or displayed. It's used here to print the encrypted data in a readable format. |
| **StandardCharsets.UTF_8** | A standard way to convert text to bytes and vice versa, ensuring consistency across different systems. It's used to handle the message encoding and decoding. |