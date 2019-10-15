package ru.xfneo.concurrentfile;

import java.io.*;

public class IOUtil {
    public static void createFile(File file, int initialNumber) {
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeInt(initialNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int readInt(File file) {
        int i = -1;
        try (FileInputStream fis = new FileInputStream(file);
             DataInputStream dis = new DataInputStream(fis)) {
            i = dis.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }

    public static void writeInt(File file, int i) {
        try (FileOutputStream fos = new FileOutputStream(file);
             DataOutputStream dos = new DataOutputStream(fos)) {
            dos.writeInt(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
