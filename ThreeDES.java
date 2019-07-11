import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class ThreeDES {
   
    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecretKey ThreeDES_key = createKey();

        try {
            ThreeDES_key = saveKey(ThreeDES_key);
            FileInputStream fis = new FileInputStream("128text.txt"); // We take a 128-bit file and process it here.
            FileOutputStream fos3DES = new FileOutputStream("3DES128.txt");// encrypt 128bit DES
            long startTime = System.currentTimeMillis(); // time start
            TripleDES_encrypt(ThreeDES_key, fis, fos3DES);
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("Processing time for 3DES:" + elapsedTime + " milli seconds"); // print screen.
        } catch (Throwable var10) {
            var10.printStackTrace();
        }

    }

    
    public static SecretKey saveKey(SecretKey sk) {
        return sk;
    }

    public static SecretKey createKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("TripleDES");
        return keygen.generateKey();
    }


public static void TripleDES_encrypt(SecretKey key, InputStream inputs, OutputStream outputs) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IOException {
        Cipher cipher = Cipher.getInstance("TripleDES");
        byte[] bytes = new byte[56];
        cipher.init(1, key);
        CipherInputStream cis = new CipherInputStream(inputs, cipher);

        int numBytes;
        while((numBytes = cis.read(bytes)) != -1) {
            outputs.write(bytes, 0, numBytes);
        }

        outputs.flush();
        outputs.close();
        inputs.close();
    }

}