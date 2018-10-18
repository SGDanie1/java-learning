package threads;

public class ThreadsLearning {

    /*
    * It's better not to use Threads as it is.
    * ExecutorService is safer and more convenient
    *
    * */

    public static void main(String[] args) {
        ExtentionOfThread extention = new ExtentionOfThread();
        Thread implementation = new Thread(new RunnableImpl());
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i =0; i < 10; i++) {
                    System.out.println("Threads: " + i);
                }
            }
        });

        extention.start();
        implementation.start();
        thread.start();

    }
}


