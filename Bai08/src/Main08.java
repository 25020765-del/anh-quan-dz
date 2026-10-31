import java.util.*;
import java.util.concurrent.*;

public class Main08 {

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        List<int[]> arrays = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            int[] arr = new int[m];
            for (int j = 0; j < m; j++) arr[j] = sc.nextInt();
            arrays.add(arr);
        }

        ExecutorService pool1 = Executors.newFixedThreadPool(3);
        ExecutorService pool2 = Executors.newFixedThreadPool(3);

        CompletionService<Map.Entry<Integer, List<Integer>>> cs1 =
                new ExecutorCompletionService<>(pool1);

        for (int i = 0; i < n; i++) {
            final int idx = i;
            cs1.submit(() -> {
                List<Integer> primes = new ArrayList<>();
                for (int x : arrays.get(idx)) {
                    if (isPrime(x)) primes.add(x);
                }
                System.out.println("Stage 1 - Array " + idx + ": " + primes);
                return new AbstractMap.SimpleEntry<>(idx, primes);
            });
        }

        CompletionService<Long> cs2 =
                new ExecutorCompletionService<>(pool2);

        for (int i = 0; i < n; i++) {
            Future<Map.Entry<Integer, List<Integer>>> f = cs1.take();
            Map.Entry<Integer, List<Integer>> entry = f.get();

            int idx = entry.getKey();
            List<Integer> primes = entry.getValue();

            cs2.submit(() -> {
                long result = 0;

                if (primes.size() % 2 == 0) {
                    for (int x : primes) result += x * x;
                    System.out.println("Stage 2 - Array " + idx +
                            ": sum of squares = " + result);
                } else {
                    for (int x : primes) result += x * x * x;
                    System.out.println("Stage 2 - Array " + idx +
                            ": sum of cubes = " + result);
                }
                return result;
            });
        }

        long total = 0;

        for (int i = 0; i < n; i++) {
            total += cs2.take().get();
        }

        System.out.println("Total = " + total);

        pool1.shutdown();
        pool2.shutdown();
    }
}