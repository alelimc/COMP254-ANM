import java.util.Stack;

public class StackTransferClass {
    public StackTransferClass() {
    }

    public static <E> void transfer(Stack<E> S, Stack<E> T) {
        while(!S.isEmpty()) {
            T.push(S.pop());
        }

    }

    public static void main(String[] args) {
        Stack<Integer> stackS = new Stack();
        stackS.push(5);
        stackS.push(10);
        stackS.push(15);
        stackS.push(20);
        Stack<Integer> stackT = new Stack();
        transfer(stackS, stackT);

        while(!stackT.isEmpty()) {
            System.out.println(stackT.pop());
        }

    }
}
