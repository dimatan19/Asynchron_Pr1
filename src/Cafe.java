import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Cafe {

    // Семафор для бариста, обмежує кількість замовлень, що готуються одночасно
    static final Semaphore barista = new Semaphore(2);

    // Флаг для визначення робочих годин кафе
    private static boolean isAvailableHours = true;

    // Лічильник активних клієнтів
    private static final AtomicInteger activeCustomers = new AtomicInteger(0);

    public static synchronized boolean isOpen() {
        return isAvailableHours;
    }

    public static synchronized void closeCafe() {
        isAvailableHours = false;
        System.err.println("============= Кав'ярню закрили ================");
    }

    public static void incrementCustomers() {
        activeCustomers.incrementAndGet();
    }

    public static void decrementCustomers() {
        activeCustomers.decrementAndGet();
    }

    public static int getActiveCustomers() {
        return activeCustomers.get();
    }

    public static void main(String[] args) throws InterruptedException {

        Runnable cafe = () -> {
            int i = 0;
            while (isOpen()) {
                Thread customer = new Thread(new Customer("Клієнт-" + i));
                customer.start();
                incrementCustomers();
                i++;

                try {
                    Thread.sleep(1000); // Інтервал між прихід клієнтів
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread cafeThread = new Thread(cafe, "Кав'ярня");
        cafeThread.start();
        Thread.sleep(10000); // Робочий час кафе
        closeCafe();

        // Очікуємо завершення обслуговування всіх клієнтів
        while (getActiveCustomers() > 0) {
            Thread.sleep(100); // Коротка пауза, щоб перевіряти чи всі клієнти пішли
        }

        System.err.println("============= Бариста пішов додому ================");
    }
}
