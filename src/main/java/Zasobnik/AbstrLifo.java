package Zasobnik;

import DoubleList.AbstrDoubleList;
import DoubleList.DoubleListException;
import DoubleList.IAbstrDoubleList;

public class AbstrLifo<T> implements IAbstrLifo<T>{
    private final IAbstrDoubleList<T> doubleList;

    public AbstrLifo() {
        doubleList = new AbstrDoubleList<>();
    }

    @Override
    public void zrus() {
        doubleList.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return doubleList.jePrazdny();
    }

    @Override
    public void vloz(T data) {
        doubleList.vlozPosledni(data);
    }

    @Override
    public T odeber()  {
        try {
            return doubleList.odeberPosledni();
        } catch (DoubleListException e) {
            throw new RuntimeException(e);
        }
    }
}
