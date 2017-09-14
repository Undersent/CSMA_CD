import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by rafal on 02.05.17.
 */
public class Code {
    public  static  String  code(String  input , int  frameSize, String flag) {
        String  data= "";
        String  partOfFrame;
        int i = 0;
        System.out.println ("Wiadomosc ma "+input.length ()+" bitów  .");
        while (i < input.length ()) {
            //tutaj tworze ramki o dlugosci frameSize
            partOfFrame= divideFrame(input, i, frameSize);
            System.out.print("Dane: "+partOfFrame+" ");
            //dodaje crc kod
            partOfFrame += Crc.crc(partOfFrame);
            // rozpychanie ramek
            partOfFrame = makeBitStuffing(partOfFrame);
            // tutaj dodaje flagi na poczatku i koncu wiadomosci
            partOfFrame = flag+partOfFrame+flag;
            i +=  frameSize;
            data += partOfFrame;
            System.out.println (" Otrzymana ramka: "+partOfFrame+" ");
        }


        PrintWriter writer = null;
        try {
            writer = new PrintWriter("outputOfCode.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(data);
        writer.close();
        return  data;
    }

    private static String divideFrame(String input, int i, int frameSize) {
        String partOfFrame="";
        if (input.length() - i > frameSize)
            partOfFrame = input.substring(i, i+frameSize );
        else
            partOfFrame = input.substring(i, input.length ());
        return partOfFrame;
    }

    private static String makeBitStuffing(String frame) {
        frame = frame.replace ("11111" ,  "111110");
        return frame;
    }
}
