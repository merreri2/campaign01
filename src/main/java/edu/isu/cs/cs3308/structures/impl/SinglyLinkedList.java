package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;

public class SinglyLinkedList<E> implements List<E> {

    //nested node class taken from text book
    public static class Node<E>{
        protected E element;
        protected Node<E> next;
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }

        public E getElement(){
            return element;
        }

        public Node<E> getNext(){
            return next;
        }

        public void setNext(Node<E> n){
            next = n;
        }
    }


    protected Node<E> head = null;
    protected Node<E> tail = null;
    protected int size = 0;
    public SinglyLinkedList(){

    }


    public int size(){
        return size;
    }

    public E first(){
        if (isEmpty()){
            return null;
        }
        else {
            return head.getElement();
        }
    }

    public E last(){
        if (isEmpty()) {
            return null;
        }
        else {
            return tail.getElement();
        }
    }

    public void addFirst(E e){
        if (e != null) {
            head = new Node<>(e, head);
            if (size == 0) {
                tail = head;
            }
            size++;
        }
    }

    public void addLast(E e) {

        if (e != null) {
            Node<E> newest = new Node<>(e, null);
            if (isEmpty()) {
                head = newest;
            } else {
                tail.setNext(newest);
            }
            tail = newest;
            size++;
        }
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if (size == 0){
            tail = null;
        }
        return answer;
    }

    /*
    removes the last element of the list.  if the list is empty it returns null
    if the list is populated, it iterates through the list until it finds the element
    that points to last.  Once this is found the loop stops, the current element becomes
    tail, tail's pointer is set to null, and size is decremented to indicate the
    removal of an object.  Returns the tail element before removal
     */
    public E removeLast(){
        if (isEmpty()){
            return null;
        }

        Node<E> toReturn = tail;
        Node<E> toSeek = head;
        boolean count = true;

        while (toSeek.getNext() != tail) {
            toSeek = toSeek.getNext();
        }

        toSeek.setNext(null);
        size--;
        return toReturn.element;
    }
/*
        Node<E> answer = tail;
        Node<E> temp = head;
        boolean count = true;

        while (count == true){
            if (temp.getNext() == tail){
                tail = temp;
                count = false;
                size--;
                tail.getNext().element = null;
            }
            else {
                temp = temp.getNext();

            }*/

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current size of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index Index in the list where the element is to be inserted.
     */
    public void insert(E element, int index) {

        if(element != null && index >= 0) {
            if (index >= size) {
                addLast(element);
            }
            else if(index == 0) {
                addFirst(element);
            }
            else {
                Node<E> currentNode = head;
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
     Removes the element at the given index and returns the value.
     @param index Index of the element to remove
     @return The value of the element at the given index, or null if the index
     is greater than or equal to the size of the list or less than 0.
     */
    public E remove(int index){
        if (index < 0 || index >= size){
            return null;
        }

        if (index == 1){
            removeFirst();
        }
        Node<E> temp = head;
        Node<E> toJoin = head;
        for (int count = 1; count < index; count++) {
            toJoin = temp;
            temp = temp.getNext();
        }
        /*// temp should now be the element to be removed
        while (toJoin.getNext() != temp){
            toJoin = toJoin.getNext();
        }*/
        // toJoin should now be the element before temp
        if (temp.getNext() != null){
            toJoin.getNext().element = temp.getNext().element;
            temp.getNext().element = null;
            size--;
        }else if (temp.getNext() == null){

        }
        return temp.element;
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
    public E get(int index){
        if (index < 0 || index >= size){
            return null;
        }
        Node<E> temp = head;
        for (int count = 0; count < index; count++){
            temp = temp.getNext();
        }
        return temp.element;
    }

    /**
     * @return true if there are no items currently stored in the list, false
     * otherwise.
     */
    public boolean isEmpty(){
        if (size <= 0){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Prints the contents of the list in a single line separating each element
     * by a space to the default System.out
     */
    public void printList(){

        Node<E> toPrint = head;
        while (toPrint.getNext() != null){
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
    public int indexOf(E item){
        int index = 0;
        Node<E> toCheck = head;

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

}