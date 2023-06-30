package ParallelArrayConcurrency; 

import java.util.Random;

public class ParallelArrayConcurrency{
	
	
    public static void main(String[] args) {
        int[] array = generateRandomArray(200_000_000);

        long startTime = System.currentTimeMillis();
        long sumParallel = parallelArraySum(array);
        long endTime = System.currentTimeMillis();
        long parallelTime = endTime - startTime;

        startTime = System.currentTimeMillis();
        long sumSingleThread = singleThreadArraySum(array);
        endTime = System.currentTimeMillis();
        long singleThreadTime = endTime - startTime;

        System.out.println("Parallel Sum: " + sumParallel);
        System.out.println("Parallel Time: " + parallelTime + " milliseconds");

        System.out.println("Single Thread Sum: " + sumSingleThread);
        System.out.println("Single Thread Time: " + singleThreadTime + " milliseconds");
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(10) + 1;
        }
        return array;
    }

    public static long parallelArraySum(int[] array) {
        int processors = Runtime.getRuntime().availableProcessors();
        int chunkSize = array.length / processors;

        SumThread[] threads = new SumThread[processors];
        for (int i = 0; i < processors; i++) {
            threads[i] = new SumThread(array, i * chunkSize, (i + 1) * chunkSize);
            threads[i].start();
        }

        long sum = 0;
        try {
            for (int i = 0; i < processors; i++) {
                threads[i].join();
                sum += threads[i].getPartialSum();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static long singleThreadArraySum(int[] array) {
        long sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

    static class SumThread extends Thread {
        private final int[] array;
        private final int start;
        private final int end;
        private long partialSum;

        public SumThread(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        public long getPartialSum() {
            return partialSum;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                partialSum += array[i];
            }
        }
    }
}
