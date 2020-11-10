package ciclistas;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OpenedBarrierAction implements Runnable {

    private final DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());
    private static int stage=0;

    @Override
    public void run() {
        switch (stage){
            case 0:
                System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + " starts the stage.");
                stage++;
                break;
            case 1:
                System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + " returning home.");
                stage++;
                break;
            case 2:
                System.out.println(LocalTime.now().format(dateTimeFormatter) + " -> " + Thread.currentThread().getName() + " ended the stage.");
                stage++;
                break;
        }
    }
}
