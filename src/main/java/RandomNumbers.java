import java.util.concurrent.ThreadLocalRandom;

public class RandomNumbers extends Thread {

    @Override
    public void run() {
        try {
            int bound = 100;
            int result = ThreadLocalRandom.current().nextInt(bound);
            System.out.println("Thread " + Thread.currentThread().getId() + " generated " + result);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public static void main(String[] args) {
        String sd = "\uD83D\uDC3B";
        System.out.println(sd);
        int a = 8 ;
        System.out.println(a & 16);
        System.out.println(a & 2);
        System.out.println(a & 4);
        System.out.println(a & 8);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 10; i++) {
            RandomNumbers randomNumbers = new RandomNumbers();
            randomNumbers.start();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime));
    }

}
