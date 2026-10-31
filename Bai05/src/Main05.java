import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
public class Main05{
    public static void main(String[] args) throws Exception{
        System.out.println("Nhap so don hang:");
        Scanner sc=new Scanner(System.in);

        int m=sc.nextInt();
        sc.nextLine();

        List<String> logs=new ArrayList<>();
        AtomicInteger successCount=new AtomicInteger(0);

        ExecutorService executor=Executors.newFixedThreadPool(3);
        List<Future<Boolean>>futures=new ArrayList<>();
        for(int i=0; i<m;i++){
            System.out.println("Nhap don hang thu: "+(i+1));
            System.out.println("Nhap id:");
            String id=sc.next();
            System.out.println("Nhap processMs:");
            long processMs=sc.nextLong();
        
            Callable<Boolean> task=new Order(id, processMs, logs, successCount); 
            futures.add(executor.submit(task));
        }
        for(Future<Boolean> f:futures){
            f.get();
        }
        System.out.println("Success = "+successCount.get());
        for (String log: logs){
            System.out.println(log);
        }

        executor.shutdown();
    }
}