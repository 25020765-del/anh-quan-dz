import java.util.*;
import java.util.concurrent.*;

public class Main07{

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ExecutorService executor = Executors.newFixedThreadPool(n);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                list.add(sc.nextInt());
            }
            Callable<Integer> task = () -> {
                int count = 0;
                for (int num : list) {
                    if (isPrime(num)) count++;
                }
                return count;
            };
            futures.add(executor.submit(task));
        }

        int[] results = new int[n];
        int maxPrimes = -1;
        for (int i = 0; i < n; i++) {
            results[i] = futures.get(i).get();
            System.out.println("Array " + i + ": " + results[i]);
            if (results[i] > maxPrimes) maxPrimes = results[i];
        }

        System.out.print("Most primes: ");
        for (int i = 0; i < n; i++) {
            if (results[i] == maxPrimes) {
                System.out.print("Array " + i + " ");
            }
        }
        System.out.println("with " + maxPrimes + " primes");

        executor.shutdown();
        sc.close();
    }
}