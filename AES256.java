
import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class AES256 {
	
	private static CipherInputStream cis;


	public static void AESEncrypt (SecretKey key, InputStream inputStream, OutputStream outputStream) throws NoSuchAlgorithmException, InvalidKeyException,
     NoSuchPaddingException, IOException   //AES encryption result showed in a text file
{
 Cipher cipher = Cipher.getInstance("AES");
 byte[] bytes = new byte[128];
 int numBytes;
 cipher.init(ENCRYPT_MODE,key);
 cis = new CipherInputStream(inputStream, cipher);

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
       
        int key_size = 256;
        SecretKey AES_key = getKey(key_size); //AES key

        try {
            
           
          
            	FileInputStream doc = new FileInputStream("128text.txt"); //To be encrypted file
                FileOutputStream fileAES256 = new FileOutputStream("AES256.txt"); //The encrypted file
                startTime = System.currentTimeMillis();
                AESEncrypt(AES_key,doc,fileAES256); //AES encryption is done with key size 256 bits
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
