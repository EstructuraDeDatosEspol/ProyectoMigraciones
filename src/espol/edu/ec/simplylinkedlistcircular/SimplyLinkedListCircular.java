/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.simplylinkedlistcircular;

import java.util.Iterator;

/**
 *
 * @author Kenny Camba
 * @param <E>
 */
public class SimplyLinkedListCircular<E> implements List<E>, Iterable<E> {
    
    private Node<E> last;
    private int efectivo;
    
    public SimplyLinkedListCircular() {
        last = null;
        efectivo = 0;
    }

    @Override
    public boolean addFirst(E element) {
        if(element == null)
            return false;
        Node<E> node = new Node<>(element);
        if(isEmpty()){
            last = node;
            node.setNext(last); 
        }else{
            Node<E> n = last.getNext();
            node.setNext(n);
            last.setNext(node); 
        }
        efectivo++;
        return true;
    }

    @Override
    public boolean addLast(E element) {
        if(element == null)
            return false;
        Node<E> node = new Node<>(element);
        if(isEmpty()){
            last = node;
            node.setNext(last); 
        }else{
            Node<E> n = last.getNext();
            last.setNext(node);
            node.setNext(n); 
            last = node; 
        }
        efectivo++;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty())
            return false;
        else if(last == last.getNext())
            last = null;
        else {
            Node<E> tmp = last.getNext();
            last.setNext(tmp.getNext()); 
            tmp.setNext(null);
            tmp.setData(null); 
        }
        efectivo--;
        return true;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty())
            return false;
        else if(last == last.getNext())
            last = null;
        else {
            Node<E> prev = getPrevius(last);
            last.setData(null);
            prev.setNext(last.getNext()); 
            last.setNext(null);
            last = prev;
            
        }
        efectivo--;
        return true;
    }
    
    private Node<E> getPrevius(Node<E> node){
        Node<E> p = last.getNext();
        do{
            if(node == p.getNext())
                return p;
            p = p.getNext();
        }while(p != last.getNext());
        return null;
    }

    @Override
    public boolean isEmpty() {
        return efectivo == 0;
    }

    @Override
    public boolean contains(E element) {
        if(isEmpty() || element == null)
            return false;
        Node<E> p = last.getNext();
        do{
            if(p.getData().equals(element))
                return true;
            p = p.getNext();
        }while(p != last.getNext());
        return false;
    }

    @Override
    public E get(int index) {
        if(isEmpty() || index < 0 || index >= efectivo)
            throw new IndexOutOfBoundsException("Index out of range");
        if(index == 0)
            return last.getNext().getData();
        else if(index == efectivo-1)
            return last.getData();
        int i = 1;
        Node<E> p = last.getNext().getNext();
        while(i++ < index)
            p = p.getNext();
        return p.getData();
    }

    @Override
    public E getFirst() {
        if(isEmpty())
            throw new RuntimeException("List is empty");
        return last.getNext().getData();
    }

    @Override
    public E getLast() {
        if(isEmpty())
            throw new RuntimeException("List is empty");
        return last.getData();
    }

    @Override
    public int size() {
        return efectivo;
    }

    @Override
    public boolean remove(int index) {
        if(isEmpty() || index < 0 || index >= efectivo)
            return false;
        if(index == 0)
            return removeFirst();
        else if(index == efectivo - 1)
            return removeLast();
        Node<E> prev = getNode(index - 1);
        Node<E> tmp = prev.getNext();
        prev.setNext(tmp.getNext());
        tmp.setNext(null);
        tmp.setData(null);
        efectivo--;
        return true;
    }

    @Override
    public boolean insert(E element, int index) {
        if(isEmpty() || index < 0 || index >= efectivo)
            return false;
        if(index == 0)
            return addFirst(element);
        Node<E> node = new Node<>(element);
        Node<E> prev = getNode(index - 1);
        node.setNext(prev.getNext());
        prev.setNext(node);
        efectivo++;
        return true;
    }
    
    private Node<E> getNode(int index){
        int i = 0;
        Node<E> p = last.getNext();
        while(i++ < index)
            p = p.getNext();
        return p;
    }
    
    @Override
    public String toString() {
        if(isEmpty())
            return "[]";
        StringBuilder string = new StringBuilder();
        string.append('[');
        Node<E> p = last.getNext();
        int i = 0;
        do{
            string.append(p.getData());
            p = p.getNext();
            if(i++ < efectivo-1)
                string.append(", ");
        }while(p!= last.getNext());
        return string.append(']').toString();
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof SimplyLinkedListCircular))
            return false;
        SimplyLinkedListCircular list = (SimplyLinkedListCircular)o;
        if(list.efectivo != this.efectivo)
            return false;
        Node<E> p = last.getNext();
        Node<E> q = list.last.getNext();
        do{
            if(!p.getData().equals(q.getData()))
                return false;
            p = p.getNext();
            q = q.getNext();
        }while(p != last.getNext());
        return true;
    }
    
    @Override
    public Iterator<E> iterator(){
        Iterator<E> i = new Iterator<E>(){
            
            private Node<E> p = last.getNext();
            private int index = 0;
            
            @Override
            public boolean hasNext() {
                return index < efectivo;
            }

            @Override
            public E next() {
                E data = p.getData();
                p = p.getNext();
                index++;
                return data;
            }
        };
        return i;
    }
    
}
