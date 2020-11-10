package ciclistas;

import java.util.concurrent.CyclicBarrier;

public class Main {

    private static final int NUM_OF_CYLIST= 10;

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(10, new OpenedBarrierAction());

        for (int i=0; i<NUM_OF_CYLIST; i++){
            new Thread(new Cyclist(barrier), "Cyclist #" + i).start();
        }
    }
}
