/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

/**
 *
 * @author Kenny Camba  
 * @param <E>  
 */
public interface List<E> {
    
    boolean addFirst(E element);
    boolean addLast(E element);
    boolean removeFirst();
    boolean removeLast();
    boolean isEmpty();
    boolean contains(E element);
    E get(int index);
    E getFirst();
    E getLast();
    int size();
    boolean remove(int index);
    boolean insert(E element, int index);
}
