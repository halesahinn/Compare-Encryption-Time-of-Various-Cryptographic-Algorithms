import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class CreatText {
   
    private static String makingText(int textSize) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(textSize);
        final String alph = "0123456789ABCDEF";
        final int N = alph.length();

        Random r = new Random();
        for (int i=0; i<textSize; i++) {
            stringBuilder.append(alph.charAt(r.nextInt(N)));
            if (i%100==0)
                stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }
    public static void main(String[] args) throws IOException {


        String dataSize = makingText(1024*128);
        FileOutputStream doc = new FileOutputStream("128Text.txt");
        byte [] strToBytes = dataSize.getBytes();
        doc.write(strToBytes);
        doc.close();
    }

}
