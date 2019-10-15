package ru.xfneo.concurrentfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final File FILE = new File("out.txt");
    private static final int INITIAL_NUMBER = 0;

    public static void main(String[] args) {
        int n = readNumber();
        IOUtil.createFile(FILE, INITIAL_NUMBER);
        Thread thread1 = new Thread(new Incrementer(n / 2));
        Thread thread2 = new Thread(new Incrementer(n / 2));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Result: " + IOUtil.readInt(FILE));
    }

    private static int readNumber() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = 1;
        do {
            System.out.println("Enter a number multiple of 2:");
            try {
                n = Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("NaN");
            }
        } while (n % 2 != 0 && n > 0);
        return n;
    }
}