package com.limbert.www;
import java.util.Random;

public class Main extends Thread{

    public static void main(String[] args) {

        Random rand = new Random();

        int[] arr = new int[200000000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10) + 1;
        }

        long start = System.currentTimeMillis();
        System.out.println(Second.sum(arr));
        System.out.println("Single: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println(Second.parallelSum(arr));
        System.out.println("Parallel: " + (System.currentTimeMillis() - start));




    }

}
