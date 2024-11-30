# Практичне завдання №1

### Опис
Цей проєкт є симуляцією роботи кав'ярні з багатопотоковою обробкою замовлень. У ньому моделюється поведінка клієнтів та баристи, який виконує замовлення в умовах обмеженого ресурсу (кількість замовлень, що виконує бариста одночасно - обмежена). 

Система демонструє використання таких механізмів Java, як:
- Семафори для синхронізації потоків.
- Багатопотоковість для роботи з клієнтами.
- Атомарні операції для управління станом.

---

### Особливості
- Одночасно бариста може обслуговати двох клієнтів по замовчуванню. (це число можна змінити у коді).
- Нові клієнти заходять у кав'ярню з певним інтервалом. (інтервал також можна змінити у коді)
- Кав'ярня працює протягом обмеженого часу, після чого зачиняється, але бариста завершує обслуговування всіх клієнтів перед тим, як піти додому.
- Якщо клієнт приходить до кав'ярні і намагається зробити замовлення після закриття, бариста відмовляє йому в обслуговуванні.

---

### Структура проєкту
- Cafe – головний клас, що запускає симуляцію.
- Customer – клас, який представляє клієнта кав'ярні.