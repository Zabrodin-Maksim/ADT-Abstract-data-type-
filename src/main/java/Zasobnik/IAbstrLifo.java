package Zasobnik;

import DoubleList.DoubleListException;

public interface IAbstrLifo<T> {

    void zrus();

    boolean jePrazdny();

    void vloz(T data);

    T odeber() throws DoubleListException;

}
