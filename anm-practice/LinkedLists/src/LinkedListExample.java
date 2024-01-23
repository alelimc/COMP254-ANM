import java.util.List;
import java.util.LinkedList;

class LinkedListExample {
    public static void main(String[] args) {
        // Create a LinkedList of Strings
        // Use List to ensure we can use any collection such as ArrayList, LinkedList
        List<String> car = new LinkedList<>();

        // Add some cars to the LinkedList
        car.add("Honda");
        car.add("Toyota");

        System.out.println("The Cars List Contains: " + car);

        //SinglyLinkedList linkedList = new SinglyLinkedList();

        // Using our own linked list class
        /*
        SinglyLinkedList linkedList = new SinglyLinkedList();

        linkedList.insertAtEnd(10);
        linkedList.insertAtEnd(20);
        linkedList.insertAtEnd(30);

        linkedList.display(); // Output: 10 20 30

        linkedList.insertAtBeginning(5);
        linkedList.display(); // Output: 5 10 20 30

        linkedList.insertInBetween(15, 3);
        linkedList.display(); // Output: 5 10 15 20 30

        linkedList.removeFromEnd();
        linkedList.display(); // Output: 5 10 15 20

        linkedList.removeFromBeginning();
        linkedList.display(); // Output: 10 15 20

        linkedList.removeFromInBetween(2);
        linkedList.display(); // Output: 10 20
        */
    }
}