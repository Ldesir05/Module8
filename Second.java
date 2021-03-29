package com.limbert.www;

public class Second extends Thread {
    private int[] arr;

    private int low, high, partial;

    public Second(int[] arr, int low, int high)

    {
        this.arr = arr;
        this.low = low;
        this.high = Math.min(high, arr.length);
    }

    public int getPartialSum() {
        return partial;
    }

    public void run() {
        partial = sum(arr, low, high);
    }

    public static int sum(int[] arr) {
        return sum(arr, 0, arr.length);
    }

    public static int sum(int[] arr, int low, int high) {
        int total = 0;
        for (int i = low; i < high; i++) {
            total += arr[i];
        }
        return total;
    }
    public static int parallelSum(int[] arr) {
        return parallelSum(arr, Runtime.getRuntime().availableProcessors());
    }

    public static int parallelSum(int[] arr, int threads) {
        int size = (int) Math.ceil(arr.length * 1.0 / threads);
        Second[] sums = new Second[threads];
        for (int i = 0; i < threads; i++) {
            sums[i] = new Second(arr, i * size, (i + 1) * size);
            sums[i].start();
        }
        try {
            for (Second sum : sums) {
                sum.join();
            }
        } catch (InterruptedException e) { }
        int total = 0;
        for (Second sum : sums) {
            total += sum.getPartialSum();
        }
        return total;
    }
}
