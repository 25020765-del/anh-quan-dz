import java.util.concurrent.locks.ReentrantLock;
class Counter {
    private int value = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increment() {
        if (lock.tryLock()) {
            try {
                value++;
            } finally {
                lock.unlock(); 
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " khong lay duoc lock");
        }
    }

    public int getValue() {
        return value;
    }
}
