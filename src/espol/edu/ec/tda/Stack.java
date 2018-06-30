package espol.edu.ec.tda;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MiguelPS
 */
public interface Stack<E> {
    int size();
    boolean push(E e);
    E pop();
    E peek();
    boolean isEmpty();
}
