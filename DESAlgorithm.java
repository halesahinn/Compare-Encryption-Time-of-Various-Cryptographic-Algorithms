import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

import static javax.crypto.Cipher.ENCRYPT_MODE;


public class DESAlgorithm {



    public static void main(String[] args) throws NoSuchAlgorithmException {



        try {

            SecretKey DESkey = createKey(); //Public key
           //Calculating time
            long startTime;
            long finishTime;   
            long elapsedTime;


            FileInputStream doc1 = new FileInputStream("128text.txt"); //To be encrypted file
            FileOutputStream docDES = new FileOutputStream("DES128.txt"); //Encrypted file




          startTime = System.currentTimeMillis();
            DES_encrypt(DESkey,doc1, docDES);  //We encrypt and write to DES_encrypted.txt file
            finishTime = System.currentTimeMillis();
            elapsedTime = finishTime-startTime;
            System.out.println("Processing time for DES:"+elapsedTime +" milli seconds");

        } catch (Throwable e) {
            e.printStackTrace();
        }

    } 
    //Creating public key for DES encryption
    public static SecretKey createKey() throws NoSuchAlgorithmException {

        KeyGenerator publicKey = KeyGenerator.getInstance("DES");
        // Use it to generate a key
        return publicKey.generateKey();
    }
    
    public static void DES_encrypt(SecretKey key, InputStream inputStream, OutputStream outputStream) throws Throwable
    {

        Cipher cipher = Cipher.getInstance("DES"); 
        byte[] bytes = new byte[56]; 
        int numBytes;
            cipher.init(ENCRYPT_MODE,key);

            //Our cipher text
            CipherInputStream cipherStream = new CipherInputStream(inputStream, cipher);



        while ((numBytes = cipherStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, numBytes);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

    }
   
}



