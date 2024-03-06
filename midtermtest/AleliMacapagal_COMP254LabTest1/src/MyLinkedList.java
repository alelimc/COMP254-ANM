//Exercise 2
public class MyLinkedList {

    static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //SinglyLinkedList class
    static class SinglyLinkedList {
        SinglyLinkedListNode head;

        public void insert(int data) {

            SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
            if (head == null) {
                head = newNode;
            } else {
                SinglyLinkedListNode temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = newNode;
            }

        }

        public void display() {
            SinglyLinkedListNode current = head;
            while (current != null) {
                System.out.print(current.data + " ");
                current = current.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test SinglyLinkedList
        SinglyLinkedList L = new SinglyLinkedList();
        L.insert(1);
        L.insert(2);
        L.insert(3);
        System.out.println("Singly Linked List L:");
        L.display();

    }
}
