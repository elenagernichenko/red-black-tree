public class RedBlackTree {
    private Node root; // корень дерева
    int countAdd = 0;
    int countRemove = 0;
    int countContains = 0;

    // Вспомогательный класс для представления узла дерева
    private static class Node {
        int value; // Значение узла
        boolean isRed; // Цвет узла: true - красный, false - черный
        Node left; // Левый потомок
        Node right; // Правый потомок

        public Node(int value, boolean isRed) {
            this.value = value;
            this.isRed = isRed;
        }
    }

    // Метод для добавления элемента в дерево
    public int add(int value) {
        root = add(root, value);
        root.isRed = false; // корень всегда черный
        return countAdd;
    }

    private Node add(Node node, int value) {
        countAdd = 0;
        if (node == null) {
            countAdd++;
            return new Node(value, true); // создаем красный узел
        }
        countAdd++;
        // Если значение меньше текущего узла, то добавляем в левое поддерево
        if (value < node.value) {
            node.left = add(node.left, value);
        }
        // Иначе добавляем в правое поддерево
        else if (value > node.value) {
            node.right = add(node.right, value);
        }
        // Если значение уже есть в дереве, то ничего не делаем
        else {
            return node;
        }

        // Проверяем и исправляем свойства красно-черного дерева
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
            countAdd += 4; // 4 операции при повороте
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
            countAdd += 4; // 4 операции при повороте
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
            countAdd += 5; // 5 операций при перекрашивании
        }

        return node;
    }

    // Метод для удаления элемента из дерева
    public int remove(int value) {
        root = remove(root, value);
        root.isRed = false; // корень всегда черный
        return countRemove;
    }

    private Node remove(Node node, int value) {
        countRemove = 0;
        if (node == null) {
            return null;
        }
        countRemove++;
        // Если значение меньше текущего узла, то удаляем из левого поддерева
        if (value < node.value) {
            countRemove++;
            node.left = remove(node.left, value);
        }
        // Иначе удаляем из правого поддерева
        else if (value > node.value) {
            countRemove += 2; // операции сравнения value > node.value и логическое ИЛИ
            node.right = remove(node.right, value);
        }
        // Если значение равно текущему узлу, то удаляем текущий узел
        else {
            countRemove += 2; // операции сравнения value > node.value и логическое ИЛИ
            // Если удаляемый узел имеет только одного потомка или нет потомков
            if (node.left == null || node.right == null) {
                countRemove++;
                countRemove++;
                // Находим непустого потомка или null
                Node temp = node.left != null ? node.left : node.right;
                countRemove += 3;
                // Если удаляемый узел не корень дерева, то заменяем его потомком
                if (temp != null) {
                    temp.isRed = node.isRed;
                    countRemove++;
                }

                // Возвращаем непустого потомка или null
                countRemove++;
                return temp;
            }
            countRemove++;
            countRemove++;
            // Если удаляемый узел имеет двух потомков, то находим максимальный элемент в левом поддереве
            Node maxNode = getMaxNode(node.left);
            countRemove++;
            node.value = maxNode.value;
            countRemove++;
            node.left = remove(node.left, maxNode.value);
            countRemove++;
        }

        // Проверяем и исправляем свойства красно-черного дерева
        if (isRed(node.right) && !isRed(node.left)) {
            countRemove += 2;
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            countRemove += 3;
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            countRemove += 3;
            flipColors(node);
            countRemove++;
        }

        countRemove++;
        return node;
    }

    // Метод для поиска элемента в дереве
    public int contains(int value) {
        return contains(root, value);
    }

    private int contains(Node node, int value) {
        countContains = 0;
        if (node == null) {
            countContains++;
            return countContains;
        }
        countContains++;
        if (value < node.value) {
            return contains(node.left, value);
        } else if (value > node.value) {
            return contains(node.right, value);
        } else {
            countContains++;
            return countContains;
        }
    }


    // Метод для проверки, является ли узел красным
    private boolean isRed(Node node) {
        return node != null && node.isRed;
    }

    // Метод для поворота дерева влево
    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        temp.isRed = node.isRed;
        node.isRed = true;
        return temp;
    }

    // Метод для поворота дерева вправо
    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        temp.isRed = node.isRed;
        node.isRed = true;
        return temp;
    }

    // Метод для перекраски узлов
    private void flipColors(Node node) {
        node.left.isRed = !node.left.isRed;
        node.right.isRed = !node.right.isRed;
        node.isRed = !node.isRed;
    }


    // Метод для нахождения максимального элемента в дереве
    private Node getMaxNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
}