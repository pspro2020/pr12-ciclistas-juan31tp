package ciclistas;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Cyclist implements Runnable {

    /*
    Duda para el profesor:
    Para los números aleatorios he tenido que usar un ThreadLocalRandom.current().nextInt, ya que si no, me daba un NullPointerException, es decir
    no podía utilizar el objeto Random que llevo usando desde siempre, ¿a qué se debe esto?
     */
    //Random rnd;
    CyclicBarrier barrier;
    private final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());

    public Cyclist(CyclicBarrier barrier) {
        this.barrier=barrier;
    }

    @Override
    public void run() {

        goToFuelStation();
        try {
            barrier.await();
        } catch (InterruptedException e) {
            System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": I was interrupted while waiting at the fuel station");
            return;
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        goToShop();
        try {
            barrier.await();
        } catch (InterruptedException e) {
            System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": I was interrupted while waiting at the shop");
            return;
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        backToFuelStation();
        try {
            barrier.await();
        } catch (InterruptedException e) {
            System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": I was interrupted while waiting at the fuel station in the returning.");
            return;
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        backToHome();


    }

    private void backToHome() {
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": Im going home");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": I'm at home!");
    }

    private void backToFuelStation() {
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": Im returning to the fuel station");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10)+5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": I've just arrived to the fuel station again");
    }

    private void goToShop() {
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": Im going to the shop");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10)+5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": I've just arrived to the shop");
    }

    private void goToFuelStation() {
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": Im going to the fuel station");
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3)+1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + ": I've just arrived to the fuel station");
    }
}
