import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
public class Order implements Callable<Boolean>{
    private String id;
    private long processMs;
    private List<String> logs=new ArrayList<>();
    private AtomicInteger successCount;
    public Order(String id, long processMs, List logs, AtomicInteger successCount){
        this.id=id;
        this.processMs=processMs;
        this.logs=logs;
        this.successCount=successCount;
    }
    @Override
    public Boolean call(){
        System.out.println("Start "+id);
        try{
            Thread.sleep(processMs);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        boolean success=processMs <= 1500;

        synchronized(logs){
            if(success){
                logs.add("DONE "+id);
            }
            else{
                logs.add("FaIL "+id);
            }
        }
        if(success){
            successCount.incrementAndGet();
        }
        return success;
    }
}