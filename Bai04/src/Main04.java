public class Main04{
    public static void main(String[] args){
        BookStore store=new BookStore();
        Runnable reader=()->{
            for (int i=0; i<3; i++){
                System.out.println(Thread.currentThread().getName()+" doc Java: "+store.getStock("Java"));
                try{Thread.sleep(500);}catch(Exception e){}
            }
        };
         Runnable writer1 = () -> {
            store.borrow("Java", 2);
        };

        Runnable writer2 = () -> {
            store.addBook("Java", 5);
        };

        new Thread(reader, "Reader-1").start();
        new Thread(reader, "Reader-2").start();
        new Thread(reader, "Reader-3").start();

        new Thread(writer1, "Writer-1").start();
        new Thread(writer2, "Writer-2").start();
    }
}