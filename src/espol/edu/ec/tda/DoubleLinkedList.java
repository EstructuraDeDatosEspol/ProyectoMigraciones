/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * @author MiguelPS
 */
public class DoubleLinkedList<E> implements List<E>, Stack<E>{
     
    
    private int size =0;
    Node<E> first;
    private Node<E> last;
  

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean push(E e) {
        if(e==null) return false;
        addLast(e);
        return true;
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;

        E item= this.last.item;
        removeLast();
        return item;
    }

    @Override
    public E peek() {

        if (isEmpty()) return null;
        return this.last.item;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        if(!checkIndex(i)){
            throw new IndexOutOfBoundsException("Indice incorrecto i="+i);
        }
        return node(i).item;
    }

    @Override
    public E getFirst() {
        return this.first.item;
    }

    @Override
    public E getLast() {
        return  this.last.item;
    }

    @Override
    public boolean insert( E e,int i) throws IndexOutOfBoundsException {

        if(!checkIndex(i)) throw new IndexOutOfBoundsException("Index Incorrecto i="+i);

        if (i == size){
            addLast(e);
        }
        else{
            Node<E> succ=node(i);
            
            final Node<E> pred = succ.prev;
            final Node<E> newNode = new Node<>(pred, e, succ);
                succ.prev = newNode;
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            }
        size++;
        return true;
    }

    @Override
    public boolean remove(int i) throws IndexOutOfBoundsException {
        Node<E> n = node(i);
        
        final Node<E> prev = n.prev;
        final E element = n.item;
        final Node<E> next = n.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            n.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            n.next = null;
        }

        n.item = null;
        size--;
        return true;
    }

    @Override
    public boolean addFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;   
        return true;
    }

    @Override
    public boolean addLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;  
        return true;
    }

    @Override
    public boolean removeFirst() {
        final Node<E> f = first;
        if (f == null) return false;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) 
            last = null;
        else
            next.prev = null;
        
        size--;
        return true;
    }

    @Override
    public boolean removeLast() {
        final Node<E> l = last;
        if (l == null) return false;
         final E element = l.item;
        final Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        last = prev;
        if (prev == null) 
            first = null;
        else
            prev.next = null;
        size--;
        return true;
    }
    
    //comprueba si en indice es válido.
    private boolean checkIndex(int i){
        return i >= 0 && i <= size;
    }
    
    //busca el nodo en el index determinado
    Node<E> node(int index) {

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    @Override
    public String toString() {
        
        if(size==0) return "[]";
        
        StringBuilder m =new StringBuilder();
        m.append("[");
        for(Node<E> temp = first; temp!=null; temp=temp.next){
            m.append(temp.item);
            if(temp.next!=null) m.append(",");
            else m.append("]");
        }
        return m.toString();
    }

    @Override
    public boolean equals(Object obj) {
       
        if (obj == null)return false; 
        if (getClass() != obj.getClass())  return false; 
        
        final DoubleLinkedList<E> other = (DoubleLinkedList<E>) obj;
        if(size!=other.size()) return false;
        
        Node<E> posThis=first;
        Node<E> posOther=other.first;
        
        while(posThis!=null){
            if(!(posThis.item.equals(posOther.item))) return false;
            posThis=posThis.next;
            posOther=posOther.next;
        }
        return true;
        
    }
    
    public ListIterator<E> iterator(){
        return new DoubleLLIterator();
    }
    
    public ListIterator<E> iterator(int i) {
        checkIndex(i);
        return new DoubleLLIterator(i);
    }
    private class DoubleLLIterator implements ListIterator<E>{
        private Node<E> lastReturned;
        private Node<E> temp;
        private int nextIndex;

        DoubleLLIterator() {
            temp=first;
            nextIndex=0;
        }

        DoubleLLIterator(int index) {
       
            temp = (index == size) ? null : node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size; 
        }
         @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastReturned = temp;
            temp = temp.next;           
            nextIndex++;
            return lastReturned.item;
        }
        
        @Override
        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            
            if(temp==null){
                temp=last;
                lastReturned=temp;
                
            }else {
                temp=temp.prev;
                lastReturned=temp;  
            }

            nextIndex--;
            return lastReturned.item;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void set(E e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
   

    
    
}
