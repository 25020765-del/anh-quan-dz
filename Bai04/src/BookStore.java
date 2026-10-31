import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.locks.*;
public class BookStore{
    private Map<String, Integer> stock=new HashMap<>();
    private final ReadWriteLock lock=new ReentrantReadWriteLock();
    public BookStore(){
        stock.put("Java",10);
        stock.put("Python",8);
        stock.put("C++",5);
    }
    public int getStock(String title){
        if(stock.containsKey(title)){
            lock.readLock().lock();
            try{
                return stock.get(title);
            }finally{
                lock.readLock().unlock();
            }
        }
        else{
            return 0;
        }
    }
    public void addBook(String title, int qty){
        lock.writeLock().lock();
        try {
            stock.merge(title, qty, Integer::sum);
            System.out.println("Da them sach: "+title+" - So luong: "+qty);
        } finally {
            lock.writeLock().unlock();
        }
    }
    public void borrow(String title, int qty){
        lock.writeLock().lock();
        try{
            if(!stock.containsKey(title) || stock.get(title)<qty){
                System.out.println("Khong du so luong");
            }
            else{
                stock.merge(title, qty, (oldValue, newValue)->oldValue-newValue);
                System.out.println("Da muon sach: "+title+" - So luong: "+qty);
            }
        }finally{
            lock.writeLock().unlock();
        }
    }
}