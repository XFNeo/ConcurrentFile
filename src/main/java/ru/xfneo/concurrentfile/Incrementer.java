package ru.xfneo.concurrentfile;

public class Incrementer implements Runnable {
    private final int count;

    public Incrementer(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (Main.FILE) {
                Main.FILE.notifyAll();
                int intFromFile = IOUtil.readInt(Main.FILE);
                int newInt = intFromFile + 1;
                IOUtil.writeInt(Main.FILE, newInt);
                System.out.printf("Thread: %s, old number: %d, new number: %d\n", Thread.currentThread().getName(), intFromFile, newInt);
                try {
                    Main.FILE.wait(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
