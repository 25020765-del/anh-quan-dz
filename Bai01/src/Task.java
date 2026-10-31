public class Task implements Runnable{
    public String name;
    public long durationMs;
    public Task(String name, long durationMs){
        this.name=name;
        this.durationMs=durationMs;
    }
    @Override
    public void run(){
        System.out.println("Start: "+name);
        try{
            Thread.sleep(durationMs);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("End: "+name);
    }
}