import java.util.Random;

public class RedBlackTreeUsage {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Random random = new Random();
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10000);
        }

        // добавление элементов в дерево и вывод времени работы и количества операций для каждого добавления
        long startTime = System.currentTimeMillis();
        int addCount = 0;
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            long startAddTime = System.nanoTime();
            int count = tree.add(value);
            addCount += count;
            long endAddTime = System.nanoTime();
            System.out.println("Добавление " + value + " заняло " + (endAddTime - startAddTime) + " нс, " + count + " операций");
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Время выполнения добавления: " + (endTime - startTime) + " мс, " + addCount + " операций");

        // поиск случайно выбранных 100 элементов в дереве и вывод времени работы и количества операций для каждого поиска
        startTime = System.currentTimeMillis();
        int searchCount = 0;
        for (int i = 0; i < 100; i++) {
            int index = random.nextInt(arr.length);
            int value = arr[index];
            long startSearchTime = System.nanoTime();
            int count = tree.contains(value);
            searchCount += count;
            long endSearchTime = System.nanoTime();
            System.out.println("Поиск " + value + " занял " + (endSearchTime - startSearchTime) + " нс, " + count + " операций");
        }
        endTime = System.currentTimeMillis();

        System.out.println("Время выполнения поиска: " + (endTime - startTime) + " мс, " + searchCount + " операций");

        // удаление случайных 1000 элементов и вывод времени работы и количества операций для каждого удаления
        long startTime2 = System.currentTimeMillis();
        int removeCount = 0;
        for (int i = 0; i < 1000; i++) {
            int value = arr[random.nextInt(arr.length)];
            long startRemoveTime = System.nanoTime();
            int count = tree.remove(value);
            removeCount += count;
            long endRemoveTime = System.nanoTime();
            System.out.println("Удаление " + value + " заняло " + (endRemoveTime - startRemoveTime) + " нс, " + count + " операций");
        }
        long endTime2 = System.currentTimeMillis();

        System.out.println("Время выполнения удаления: " + (endTime2 - startTime2) + " мс, " + removeCount + " операций");
    }
}
