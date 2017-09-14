/**
 * Created by rafal on 22.05.17.
 */
public class User {
    String id;
    int position;
    String message;
    char[] messageToSend = message.toCharArray();
    int index = 0;

    User(String id, int postion, String message){
        this.id = id;
        if(CSMA.lengthOfCabble < postion) throw new IndexOutOfBoundsException();

        this.position=postion;
        this.message = message;

    }

    public String getMessage(){
        return message;
    }

    public char getBit(){
        char bit = messageToSend[index];
        index ++;
        return bit;
    }
}
