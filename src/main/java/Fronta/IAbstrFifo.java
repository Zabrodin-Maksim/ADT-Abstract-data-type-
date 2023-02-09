package Fronta;

import DoubleList.DoubleListException;

public interface IAbstrFifo<T> {

    void zrus();

    boolean jePrazdny();

    void vloz(T data);

    T odeber() throws DoubleListException;
}
