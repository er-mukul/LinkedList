public class SimpleLinkedList<T> {
    public class Node {
        public T data;
        public Node nextNode;
    }

    public int size;
    public Node headNode;

    public SimpleLinkedList() {
        headNode = null;
        size  = 0;
    }

    public boolean isEmpty(){
        return headNode == null;
    }

    public void insertAtHead(T data){
        Node newNode = new Node();
        newNode.data = data;
        newNode.nextNode = headNode;
        headNode = newNode;
        size++;
    }

    public void insertAtEnd(T data){
        if(isEmpty()){
            insertAtHead(data);
            return;
        }
        Node newNode = new Node();
        newNode.data = data;
        newNode.nextNode = null;

        Node tempNode = headNode;
        while(tempNode.nextNode!=null){
            tempNode = tempNode.nextNode;
        }
        tempNode.nextNode = newNode;
        size++;
    }

    public void insertAfter(T data, T previousData){
        if(isEmpty()){
            System.out.println("List is Empty");
            return;
        }

        Node newNode = new Node();
        newNode.data = data;


        Node currentNode = headNode;
        while(currentNode!=null && currentNode.data!=previousData){
            currentNode = currentNode.nextNode;
        }
        if(currentNode != null) {
            newNode.nextNode = currentNode.nextNode;
            currentNode.nextNode = newNode;
            size++;
        }
    }



    public void printList(){
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node tempNode = headNode;
        System.out.print("List : ");

        while(tempNode!=null){
            System.out.print(tempNode.data.toString()+" -> ");
            tempNode = tempNode.nextNode;
        }
        System.out.print(" null ");

    }

    public boolean searchNode(T data) {
        Node currentNode = headNode;
        while(currentNode != null){
            if(currentNode.data.equals(data)){
                return true;
            }
            currentNode = currentNode.nextNode;
        }

        return false;
    }

    public void deleteAtHead() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }
        headNode = headNode.nextNode;
        size--;
    }

    public void deleteAtEnd() {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }
        if(size==1){
            headNode = null;
        }

        Node currentNode  = headNode;
        while(currentNode.nextNode.nextNode!=null){
            currentNode = currentNode.nextNode;
        }
        currentNode.nextNode = null;
        size--;

    }

    public void deleteByValue(T data) {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node currentNode  = headNode;
        Node previousNode = null;

        if(data.equals(currentNode.data)){
            deleteAtHead();
            return;
        }

        while(currentNode!=null){
            if(data.equals(currentNode.data)){
                previousNode.nextNode = currentNode.nextNode;
                size--;
            }
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }
    }

    public void deleteAfterValue(T data) {
        if (isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node currentNode  = headNode;

        while(currentNode!=null){
            if(data.equals(currentNode.data) && currentNode.nextNode!=null){
                currentNode.nextNode = currentNode.nextNode.nextNode;
                size--;
                return;
            }
            currentNode = currentNode.nextNode;
        }
    }

    public void reverseList() {
        Node current = headNode;
        Node previous = null,next = null;

        while(current!=null){
            next = current.nextNode;
            current.nextNode = previous;
            previous = current;
            current = next;
        }
        headNode = previous;
    }

    public T findMid(){
        Node mid = headNode;
        Node current = headNode;

        while(current!=null && mid!=null && current.nextNode!=null){
            current = current.nextNode.nextNode;
            if (current!=null){
                mid = mid.nextNode;
            }
        }

        return mid.data;
    }

    public boolean detectLoop() {
        if(isEmpty()){
            System.out.println("List is Empty!");
            return false;
        }
        Node slow = headNode, fast = headNode;

        while(slow !=null && fast!=null && fast.nextNode!=null){
            slow = slow.nextNode;
            fast = fast.nextNode.nextNode;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    public void removeDuplicates(){
        Node current = headNode;
        Node compare = null;

        while(current!=null && current.nextNode!=null){
            compare = current;
            while(compare.nextNode!=null){
                if(current.data.equals(compare.nextNode.data)){
                    compare.nextNode = compare.nextNode.nextNode;
                } else {
                    compare = compare.nextNode;
                }
            }
            current = current.nextNode;
        }
    }


    public static void main(String...args){
        SimpleLinkedList<Integer> sll = new SimpleLinkedList<>();
        for(int i=0;i<12;i+=3){
            sll.insertAtEnd(i);
        }

        sll.insertAfter(7,6);
        System.out.println("Found:"+sll.searchNode(17));
        sll.printList();
        sll.deleteAtHead();
        System.out.println("After Delete at Head");
        sll.printList();
        sll.deleteAtEnd();
        System.out.println("After Delete at End");
        sll.deleteAtEnd();
        System.out.println("After Delete at End");
        sll.printList();
        for(int i=10;i<22;i+=3){
            sll.insertAtEnd(i);
        }
        System.out.println("After Delete By Value");
        sll.deleteByValue(16);
        sll.printList();
        System.out.println("After Delete By Value");
        sll.deleteByValue(26);
        sll.printList();

        System.out.println("After Delete After Value");
        sll.deleteAfterValue(10);
        sll.printList();
        System.out.println(sll.size);
        System.out.println("After reverse");
        sll.reverseList();
        sll.printList();
        System.out.println("Finding Mid");
        sll.insertAtEnd(21);
        sll.printList();
        System.out.println(sll.findMid());
        System.out.println("Removing Duplicates");
        sll.insertAfter(19,6);
        sll.insertAfter(10,3);
        sll.printList();
        System.out.println("After Removing Duplicates");
        sll.removeDuplicates();
        sll.printList();

        System.out.println("Checking Loop");
        System.out.println(sll.detectLoop());
        sll.headNode.nextNode.nextNode.nextNode = sll.headNode;
        System.out.println("Checking Loop");
        System.out.println(sll.detectLoop());
    }


}
