public class Main01{
    public static void main(String[] args){
        Task task1=new Task("Task 1", 2000);
        Task task2=new Task("Task 2", 5000);

        Thread t1=new Thread(task1);
        Thread t2=new Thread(task2);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("All task done");
    }
}