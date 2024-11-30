public class Customer implements Runnable {

    private final String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s прийшов у кав'ярню і хоче зробити замовлення\n", name);

            // Бариста починає обробляти замовлення
            Cafe.barista.acquire();
            try {
                // Повторна перевірка: якщо кафе закрите, клієнт не обслуговується
                if (!Cafe.isOpen()) {
                    Thread.sleep(400);
                    System.out.printf("Бариста відмовив в обслуговуванні %s, бо кав'ярня вже зачинилась\n", name);
                    return;
                }

                System.out.printf("Бариста почав готувати замовлення для %s\n", name);
                Thread.sleep(4000); // Час приготування замовлення
                System.out.printf("Бариста закінчив готувати замовлення для %s\n", name);
            } finally {
                Cafe.barista.release(); // Бариста закінчив замовлення
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Cafe.decrementCustomers(); // Клієнт уходить
        }
    }
}
