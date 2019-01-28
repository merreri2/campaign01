package edu.isu.cs.cs3308.structures.impl;

public class CircularlyLinkedList<E> extends SinglyLinkedList<E>


{
    public CircularlyLinkedList(){
        super();
    }



    //taken from book's implementation on page 131
    @Override
    public E first(){
        if (isEmpty()){
            return null;
        }
        return tail.getNext().getElement();
    }

    //taken from book's implementation on page 131
    public void rotate(){
        if (tail != null){
            tail = tail.getNext();
        }
    }
    //taken from book's implementation on page 131
    @Override
    public void addFirst(E e){
        if (size == 0){
            tail = new Node<E>(e, null);
                tail.setNext(tail);
            }
        else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    //taken from book's implementation on page 131
    @Override
    public void addLast(E e){
        addFirst(e);
        tail = tail.getNext();
    }

    //taken from book's implementation on page 131
    @Override
    public E removeFirst(){
        if (isEmpty()){
            return null;
        }
        Node<E> head = tail.getNext();
        if (head == tail){
            tail = null;
        }
        else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getElement();
    }

    @Override
    public void printList(){

        Node<E> toPrint = tail.getNext();
        while (toPrint != tail){
            System.out.println(toPrint.element.toString());
            toPrint = toPrint.getNext();
        }
        System.out.println(toPrint.element.toString());
    }

    /**
     * Identifies the 0-based index of the given item in the list. If the item does
     * not exist in the list or is null, then returns -1.
     *
     * @param item Item to search for
     * @return 0 based index of the provided item
     */
    @Override
    public int indexOf(E item){
        int index = 0;
        Node<E> toCheck = tail.getNext();

        if (item == null){
            return -1;
        }

        for (int count = 0; count < size; count++){
            if(toCheck.getElement() != item){
                toCheck = toCheck.getNext();
                index++;
            }
            else if (toCheck.getElement() == item){
                break;
            }
        }
        if (index >= size){
            index = -1;
        }
        return index;

    }

    /**
     Removes the element at the given index and returns the value.
     @param index Index of the element to remove
     @return The value of the element at the given index, or null if the index
     is greater than or equal to the size of the list or less than 0.
     */
    @Override
    public E remove(int index){
        if (index < 0 || index >= size){
            return null;
        }

        if (index == 1){
            removeFirst();
        }
        Node<E> temp = tail.getNext();
        Node<E> toJoin = tail.getNext();
        for (int count = 0; count < index; count++) {
            toJoin = temp;
            temp = temp.getNext();
        }
        /*// temp should now be the element to be removed
        while (toJoin.getNext() != temp){
            toJoin = toJoin.getNext();
        }*/
        // toJoin should now be the element before temp
        if (temp.getNext() != null){
            toJoin.setNext(temp.getNext());
            temp.setNext(null);
            size--;
            tail = toJoin;
        }
        return temp.element;
    }

    @Override
    public void insert(E element, int index) {

        if(element != null && index >= 0) {
            if (index >= size) {
                addLast(element);
            }
            else if (index == 0){
                addFirst(element);
            }
            else {
                Node<E> currentNode = tail.getNext();
                Node<E> temp = null;

                for (int count = 1; count < index; count++) {
                    currentNode = currentNode.getNext();
                }
                temp = currentNode.getNext();
                Node<E> newNode = new Node<E>(element, temp);
                currentNode.setNext(newNode);
                size++;
            }
        }
    }

    /**
     * Retrieves the value at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current size of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list size.
     */
    @Override
    public E get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        Node<E> temp = tail.getNext();
        for (int count = 0; count < index; count++){
            temp = temp.getNext();
        }
        return temp.element;
    }

    /*
    removes the last element of the list.  if the list is empty it returns null
    if the list is populated, it iterates through the list until it finds the element
    that points to last.  Once this is found the loop stops, the current element becomes
    tail, tail's pointer is set to null, and size is decremented to indicate the
    removal of an object.  Returns the tail element before removal
     */
    @Override
    public E removeLast(){
        if (isEmpty()){
            return null;
        }

        Node<E> toReturn = tail;
        Node<E> next = tail.getNext();
        while (next != tail){
            next = next.getNext();
        }

        next.setNext(tail.getNext());
        size--;
        return toReturn.element;
    }
}


