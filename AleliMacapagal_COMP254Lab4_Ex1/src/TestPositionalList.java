import java.io.PrintStream;

public class TestPositionalList {
    public TestPositionalList() {
    }

    public static void main(String[] args) {
        PositionalList<Integer> list = new LinkedPositionalList();
        Position<Integer> p1 = list.addFirst(5);
        Position<Integer> p2 = list.addLast(15);
        Position<Integer> p3 = list.addAfter(p1, 25);

        try {
            System.out.println("List of elements: " + String.valueOf(list));
            PrintStream var10000 = System.out;
            int var10001 = list.indexOf(p1);
            var10000.println("Index of 5: " + var10001);
            var10000 = System.out;
            var10001 = list.indexOf(p2);
            var10000.println("Index of 15: " + var10001);
            var10000 = System.out;
            var10001 = list.indexOf(p3);
            var10000.println("Index of 25: " + var10001);
        } catch (IllegalArgumentException var6) {
            System.out.println(var6.getMessage());
        }

    }
}
