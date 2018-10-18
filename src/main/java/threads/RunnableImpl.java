package threads;

public class RunnableImpl implements Runnable {

    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("From RunnableImpl: " + i );
        }
    }
}
