public class Main10{
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread thread = new Thread(worker);

        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        worker.stop();

        try {
            thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Main finished.");
        // volatile giúp đảm bảo mọi luồng đều thấy giá trị mới nhất của biến
        // Nếu không có volatile, thread có thể dùng giá trị cached (running = true)
        // và không bao giờ thoát khỏi vòng lặp -> gây lặp vô hạn
    }
}