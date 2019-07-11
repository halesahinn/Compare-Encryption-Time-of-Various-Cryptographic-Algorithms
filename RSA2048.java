import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;
import javax.crypto.Cipher;

class RSA2048 {
    
    public static void main(String[] args) throws Exception {
        Scanner textfile = new Scanner(new File("128text.txt")); //scanner is get text file for processing
        FileOutputStream outputStream = new FileOutputStream("RSA2048.txt");//  code that performs the save operation
        FileInputStream inputStream = new FileInputStream("128text.txt");
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream(); //Creates a new byte array output stream. The buffer capacity is initially though its size increases if necessary.
        String token = "";
        byte[] block = new byte[8];
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();

        ArrayList temp_textfile_array;
        for(temp_textfile_array = new ArrayList(); textfile.hasNext(); token = textfile.next()) {
            int num;
            while((num = inputStream.read(block)) != -1) {
                outputstream.write(block, 0, num);
            }

            temp_textfile_array.add(token);
        }

        textfile.close(); // we close the file that we had previously opened.
        inputStream.close();
        byte[] bytes = outputstream.toByteArray();
        String[] textfile_array = (String[])temp_textfile_array.toArray(new String[0]);
        long startTime = System.currentTimeMillis();
        String[] var21 = textfile_array;
        int var20 = textfile_array.length;

        for(int var19 = 0; var19 < var20; ++var19) {
            String s = var21[var19];
            outputStream.write(encrypt(pubKey, s));
        }

        long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;
        System.out.println("Processing time for RSA with 2048 bit key:" + elapsedTime + " milli seconds");
        outputStream.flush();
        outputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException { //building a key using keypair.
        int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PublicKey publicKey, String b) throws Exception { // building encription with using public key
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, publicKey);
        return cipher.doFinal(b.getBytes());
    }
}










