import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class RSAAlgorithm {


    public static void main(String [] args) throws Exception {


        long startTime;
        long finishTime;    //For measuring time
        long elapsedTime;

        Scanner textfile = new Scanner(new File("128text.txt"));  //To be encrypted file
        FileOutputStream outputStream = new FileOutputStream("RSA1024.txt");  //Encrypted file
        FileInputStream inputStream = new FileInputStream("128text.txt");
        ByteArrayOutputStream boutputStream = new ByteArrayOutputStream(); //To separate blocks of the file plaintext

        String token = "";
        byte [] block = new byte[8];
        KeyPair keyPair = buildKeyPair();   //A key was generated
        PublicKey pubKey = keyPair.getPublic(); //Public key for encryption


        List <String> temp_textfile_array = new ArrayList<String>();

        while (textfile.hasNext())
        {
            for (int readNum;(readNum=inputStream.read(block))!=-1;)
                boutputStream.write(block,0,readNum);
            temp_textfile_array.add(token);
            token = textfile.next();
        }
        textfile.close();
        inputStream.close();
        byte [] bytes = boutputStream.toByteArray();
      String [] textfile_array = temp_textfile_array.toArray(new String[0]);

        startTime = System.currentTimeMillis();
        for (String s: textfile_array)
        {

            outputStream.write(encrypt(pubKey,s)); //We encrypt and write to RSA2014Encrypted.txt file
        }
        finishTime = System.currentTimeMillis();
        elapsedTime = finishTime-startTime;
        System.out.println("Elapsed time for RSA with 1024 bit key:"+elapsedTime +" milli seconds");
        outputStream.flush();
        outputStream.close();

        outputStream.flush();
        outputStream.close();

    }
//1024 bytes size key inputStream generated
    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 1024;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }
//We do encryption and returns encrypted byte array
    public static byte[] encrypt(PublicKey publicKey, String b) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(b.getBytes());


    }


}	