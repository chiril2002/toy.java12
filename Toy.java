

    import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Toy {
    private int id;
    private String name;
    private int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }

    public static void main(String[] args) {
        Toy doll = new Toy(1, "Кукла", 3);
        Toy car = new Toy(2, "Машинка", 5);
        Toy ball = new Toy(3, "Мяч", 2);

        // Создаем приоритетную очередь для игрушек
        PriorityQueue<Toy> toyQueue = new PriorityQueue<>(new ToyComparator());
        toyQueue.add(doll);
        toyQueue.add(car);
        toyQueue.add(ball);

        // Вызываем Get 10 раз и записываем результат в файл
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))) {
            for (int i = 0; i < 10; i++) {
                Toy currentToy = toyQueue.poll();
                if (currentToy != null) {
                    writer.write("Выпала игрушка: " + currentToy.getName() + "\n");
                    toyQueue.add(currentToy); // Возвращаем игрушку обратно в очередь
                } else {
                    // Обработка случая, когда очередь пуста
                    writer.write("Очередь игрушек пуста\n");
                    break;
                }
            }
        } catch (IOException e) {
            // Логирование исключения
            e.printStackTrace();
        }
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    // Метод для вывода информации о игрушке
    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", frequency=" + frequency +
                '}';
    }
}

// Компаратор для сортировки игрушек по частоте выпадения
class ToyComparator implements Comparator<Toy> {
    @Override
    public int compare(Toy toy1, Toy toy2) {
        return toy2.getFrequency() - toy1.getFrequency();
    }
}

