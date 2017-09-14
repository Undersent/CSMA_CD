import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by rafal on 02.05.17.
 */
public class Decode {

    public static int lengthOfCrc = 32;

    // ta metoda koduje input dzieli na fragmenty zawiera flage . z ta flaga to chodzi o to ze zna ja tylko ty i odbiorca i to wykonuje jakies tam mnozenia i dlatego ktos kto przechwyci wiad nie umie jej odczytac.

    // dekodowanie usuwamy najpierw flagi,
    // oraz w kodzie jest ze 11111 jest zamienione na 111110 przy 5 jedynkach
    // co daje gwarancje ze program nie pomyli kodu z flaga, flaga ma 6 jedynek

    public  static  String  decode(String  input, String flag) {


        String  output = "";
        //nasz kod jest flaga xxxx flaga+flaga xxx + flaga
        input = deleteFlags(flag, input);
        //dzielimy dane
        String  data[] = input.split(" ");
        //usuwamy dodatkowe bity
        data = deleteBitStuffing(data);
        //usuwam ramki
        for (String s: data) {
            System.out.println("przed "+s);
            String  crc = s.substring(s.length () - lengthOfCrc);
            String  frame = s.substring(0, s.length () - lengthOfCrc);

            if (crc.equals(Crc.crc(frame))) {
                System.out.println ("Ramka po odkodowaniu "+
                        frame + " a nasz kod crc : "+crc);
                output += frame;
            } else {
                System.out.println ("Nie odkodowalo sie :( "+
                        frame+ " crc: "+crc);
            }
        }



        PrintWriter writer = null;
        try {
            writer = new PrintWriter("W.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println(output);
        writer.close();
        return  output;
    }

    private static String[] deleteBitStuffing(String[] data) {
        for (int i=0; i<data.length; i++) {
            data[i] = data[i]. replace ("111110" ,  "11111");
        }

        return data;
    }

    private static String deleteFlags(String flag, String input) {
        input = input.replace(flag+flag , " ");
        input = input.replace(flag , "");
        return input;
    }
}
