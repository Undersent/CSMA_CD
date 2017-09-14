import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * Created by rafal on 02.05.17.
 */
public class Crc {
    /*
/ crc to jest sposob kodowania i po prostu jak dostanie fragment tekstu
+ flage to koduje to przez crc ze nikt nie odczyta

*/
    public static String crc(String piece)
    {
        Checksum crc=new CRC32();
        long sumval;
        byte[] bytes =piece.getBytes();
        crc.update (bytes, 0, bytes.length);
        sumval=crc.getValue();
        String binaryValue = Integer.toBinaryString((int)sumval);

        return binaryValue;
    }
}
