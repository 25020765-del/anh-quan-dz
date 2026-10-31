import java.util.*;
import java.util.concurrent.*;

public class Main06{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        sc.nextLine();
        ExecutorService executorService=Executors.newFixedThreadPool(n);
        List<Future<Integer>> future = new ArrayList<>();
        for(int i=0; i<n; i++){
            int m = sc.nextInt();
            int[] arr = new int[m];
            for (int j = 0; j < m; j++) {
                arr[j] = sc.nextInt();
            }
            Callable<Integer> task=() -> {
                if(arr.length<2){
                    return null;
                }
                Integer first=null;
                Integer second=null;

                for (int x: arr){
                    if(first==null || x>first){
                        second=first;
                        first=x;
                    }
                    else if(x<first && (second == null || x>second)){
                        second = x;
                    }
                }
                return second;
            };
            future.add(executorService.submit(task));
        }
        int totalSum=0;
        for (int i=0; i<future.size(); i++){
            try{
                Integer result=future.get(i).get();
                if(result!=null){
                    System.out.println("Array "+i+":"+" second largest = "+result);
                    totalSum+=result;
                }else{
                    System.out.println("Array "+i+": Not found");
                }
            }catch(InterruptedException | ExecutionException e){
                System.out.println("Error");
            }
        }
        System.out.println("Sum = "+totalSum);
        executorService.shutdown();
        sc.close();
    }
}