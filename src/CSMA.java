import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rafal on 22.05.17.
 */
public class CSMA extends Thread {
    public final static int lengthOfCabble = 1000;
    public final static int speedOfSending = 1; //ms
    public final static int numerOfUsers = 4;
    ///public final static int message = 32; //uzupelnienie zerami
    public boolean isTransmiting = false;
    public static ReentrantLock lock = new ReentrantLock();

    void sendMessage(User sender, User recipent, String message){
        new Thread(() -> {
            lock.lock();
            try {
                isTransmiting = true;
            } finally {
                lock.unlock();
            }
            for(int i=0; i<=lengthOfCabble; i++){
                moveBits();
            }
        }).start();
    }

    private void moveBits() {

    }

    public static void main(String[] args)
    {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("1",0));
        users.add(new User("2",250));
        users.add(new User("3",500));
        users.add(new User("4", 750));
        users.add(new User("5", 1000));



    }
}


/*
import java.util.ArrayList;
        import java.util.Random;
        import java.util.concurrent.TimeUnit;
        import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by rafal on 10.05.17.
 */
/*
public class Zad2 extends Thread {
    public static int users = 0;
    public static boolean isCollision = false;
    public static ReentrantLock lock = new ReentrantLock();

    int time = 1;
    boolean buildData = true;
    int dataSize = 32;
    int id;

    Zad2(int id) {
        // super();
        this.id = id;
        //start();
    }

    public  String generateBits(int n) {
        String bits = "";
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            if (rand.nextInt(2) == 1) {
                bits += "1";
            } else {
                bits += "0";
            }
        }
        return bits;
    }

    void simulation(){
        new Thread(() -> {
            // while (true) {
            String data = "";
            if (buildData) {
                //dataSize =32;
                data = generateBits(dataSize);
            }
            //zobacz czy ktos uzywa kanalu
            // czekamy dopoki nie jest wolny
            while (users != 0) {
                try {
                    System.out.println("numer id " + id + " czeka " + time + " sec");
                    TimeUnit.SECONDS.sleep(time);
                    time*=2;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // teraz to my uzywamy kanalu

            lock.lock();
            try {
                System.out.println("liczba uzytkownikow "+ users);
                users++;
            } finally {
                lock.unlock();
            }

            int sec;
            int endTime = data.length() / 8;

            //kazdy bajt wysyla  sie jedna sekunde
            System.out.println("Wysyla pakiet danych " + id);
            for (sec = 0; sec < endTime; sec++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                if (users > 1) {// wykrywa kolizje
                    isCollision = true; //powiadom o kolizji
                }
                lock.unlock();

                lock.lock();
                if (isCollision) {//wychodzimy z kanalu
                    users--;

                    if (users == 0) {//jezeli nikt nie uzywa to powiedz ze nie ma kolizji
                        isCollision = false;
                    }
                    lock.unlock();

                    System.out.println("Wykryto kolizje, id przestaje wysylac swoje pakiety " + id);
                    buildData = false; //bedziemy wysylac jeszcze raz ten sam pakiet

                    try {
                        TimeUnit.SECONDS.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(time <32) {
                        time *= 2;
                    }
                    break;
                }

                lock.unlock();
            }




            lock.lock();
            if (users != 0) {
                users--; // skonczylismy uzywac kanalu
            }
            lock.unlock();

            if (sec == endTime) {
                System.out.println("Pomyslnie wyslano pakiet danych id: " + id);
                time = 1;
            }



        }).start();
    }


    public static void main(String[] args)
    {
        ArrayList<Zad2> users = new ArrayList<Zad2>();
        for(int i=1;i<10;i++){
            users.add(new Zad2(i));
        }
        for(Zad2 user : users){
            user.simulation();
        }

    }
}*/
//dlugosc ramki = 2x kabla