import java.util.Random;

/**
 * Created by rafal on 02.05.17.
 */
    class csma extends Thread
    {
        static int wait=1000;
        Random random = new Random();
        String thName;
        int T_max;
        static int data=0;

        csma(String name)
        {
            super(name);
            start();
        }

        int get(){
            return data;
        }
        void put(){
            //System.out.println("zapisuje ramke");
            data++;
        }

        public void run()
        {
            try
            {
                int data_1,data_2,i=0;
                while(true){
                    data_1 = get();
                    data_2 = get();
                    //nadaje co pewien czas
                    if(data_1 == data_2) {
                        put();
                        data_1 =get();
                        T_max=0;
                        System.out.println("data_1="+ data_1);
                        thName =Thread.currentThread().getName();
                        System.out.println("Nadaje od wątka "+ thName +" z "+i++);
                        Thread.sleep(wait);
                    }
                    //patrze czy ktoś inny nie nadaje jezeli tak to czekam
                    data_2=get();
                    System.out.println("data_2= "+data_2);
                    System.out.println("data_1 po czekaniu wynosi "+ data_1);
                    T_max = random.nextInt(1000);

                    if(data_1 != data_2){
                        System.out.println("watek "+ thName +" kolizja z "+(i-1));
                        System.out.println("czekam "+ 2*T_max);
                        Thread.sleep(2*T_max);
                        T_max *=2;

                    }
                    else
                    {
                        System.out.println("watek "+ thName +" udalo się zapisać "+i);
                    }

                }
            }
            catch(Exception e){}
        }
        public static void main(String[] args)
        {
            csma c1=new csma("A");
            csma c2=new csma("B");

            while(true);
        }
    }

