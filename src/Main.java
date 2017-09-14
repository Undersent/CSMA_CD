import java.io.*;

/**
 * Created by rafal on 02.05.17.
 */
public class Main {

    public static void main(String[] args) throws IOException
    {
        String  flag = "01111110";
        //File Z = new File("Z.txt");
        BufferedReader brR = new BufferedReader( new FileReader("Z.txt") );
        String dataToCode=brR.readLine();
        Code.code(dataToCode,8,flag);
        brR = new BufferedReader( new FileReader("outputOfCode.txt") );
        String dataToDecode = brR.readLine();
        Decode.decode(dataToDecode, flag);
        brR.close();

    }
}
