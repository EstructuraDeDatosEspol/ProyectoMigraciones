/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.simplylinkedlistcircular;

/**
 *
 * @author author Kenny Camba
 * @param <E>
 */
public class Node<E> {
    
    private E data;
    private Node<E> next;
    
    public Node(E data) {
        this.data = data;
        next = null;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    } 
}
