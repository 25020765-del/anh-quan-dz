import java.util.*;
import java.util.concurrent.*;
public class Main02{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("nhap n:");
        int n=sc.nextInt();
        sc.nextLine();
        int[] arr=new int[n];
        System.out.println("Nhap mang so nguyen:");
        for(int i=0; i<n; i++){
            arr[i]=sc.nextInt();
        }
        System.out.println("Nhap k:");
        int k=sc.nextInt();
        if(k>n){
            System.out.println("Chia doan khong thanh cong");
        }
        else{
            ExecutorService executor=Executors.newFixedThreadPool(k);
            List<Future<Integer>> future=new ArrayList<>();
            int s=0;
            int remainder=n%k;
            for (int i = 0; i<k; i++){
                int size = n/k;
                if(i<remainder){
                    size++;
                }
                int start=s;
                int e=s+size;
                Callable<Integer> task= () -> {
                    int sum=0;
                    for (int j = start; j<e; j++){
                        sum+=arr[j];
                    }
                    System.out.println("Thread xu ly tu "+start+" den "+(e-1)+" -> sum = "+sum);
                    return sum;
                };
                future.add(executor.submit(task));
                s=e;
            }
            int total=0;
            for (Future<Integer> f: future){
                try{
                    total+= f.get();
                }catch(InterruptedException | ExecutionException e){
                    e.printStackTrace();
                }
            }
            System.out.println("Tong= "+total);
        }

    }
}