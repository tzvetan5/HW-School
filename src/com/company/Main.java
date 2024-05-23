package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args) {

        PriorityBlockingQueue<product> productQueue = new PriorityBlockingQueue<>(10, new ProductComparator());

        try {
            ProductThread thread1 = new ProductThread("CalvinKleinThread", "ck.txt", productQueue);
            ProductThread thread2 = new ProductThread("GuessThread", "guess.txt", productQueue);
            ProductThread thread3 = new ProductThread("TrussardiThread", "trussardi.txt", productQueue);

            thread1.start();
            thread2.start();
            thread3.start();

            List<Thread> threads = new ArrayList<>();
            threads.add(thread1);
            threads.add(thread2);
            threads.add(thread3);

            for (Thread t : threads) {
                t.join();
            }

        } catch (Exception e) {
            System.out.println("Error processing the data.");
            System.exit(1);
        }

        ArrayList<product> extractedProducts = new ArrayList<>();
        productQueue.drainTo(extractedProducts);

        File outputFile = new File("result_products.txt");
        if (outputFile.exists()) {
            System.out.println("Already exists");
            System.exit(1);
        }

        try (PrintWriter writer = new PrintWriter(outputFile)) {
            for ( product product : extractedProducts) {
                writer.println(product + " ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
