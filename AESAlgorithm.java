
import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class AESAlgorithm {
	
	public static void AESEncrypt (SecretKey key, InputStream inputStream, OutputStream outputStream) throws NoSuchAlgorithmException, InvalidKeyException,
     NoSuchPaddingException, IOException   //AES encryption result showed in a text file
{
 Cipher cipher = Cipher.getInstance("AES");
 byte[] bytes = new byte[56];
 int numBytes;
 cipher.init(ENCRYPT_MODE,key);
 CipherInputStream cis = new CipherInputStream(inputStream, cipher);

 while ((numBytes = cis.read(bytes)) != -1) {
     outputStream.write(bytes, 0, numBytes);
 }
 outputStream.flush();
 outputStream.close();
 inputStream.close();

}
public static void main(String[] args) throws NoSuchAlgorithmException {
        long startTime;
        long finishTime; //capturing the time
        long elapsedTime;
       
        int key_size = 128;
        SecretKey AES_key = getKey(key_size); //AES key

        try {
            
           
          
            	FileInputStream doc = new FileInputStream("128text.txt"); //To be encrypted file
                FileOutputStream fileAES128 = new FileOutputStream("AES256.txt"); //The encrypted file
                startTime = System.currentTimeMillis();
                AESEncrypt(AES_key,doc,fileAES128); //AES encryption is done with key size 128 bits
                finishTime = System.currentTimeMillis();
                elapsedTime = finishTime-startTime;
                System.out.println("Processing time for AES with 256 bit key:"+elapsedTime +" milli seconds");
            
            
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

  
      public static SecretKey getKey (int size) throws NoSuchAlgorithmException { // generating public key with init size
       KeyGenerator keyCreate = KeyGenerator.getInstance("AES");
       keyCreate.init(size);
       SecretKey key = keyCreate.generateKey();
       return key;
   }
}
