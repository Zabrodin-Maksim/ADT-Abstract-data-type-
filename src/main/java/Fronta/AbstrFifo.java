package Fronta;

import DoubleList.AbstrDoubleList;
import DoubleList.DoubleListException;
import DoubleList.IAbstrDoubleList;

public class AbstrFifo<T> implements IAbstrFifo<T> {

    private final IAbstrDoubleList<T> doubleList;

    public AbstrFifo() {
        this.doubleList = new AbstrDoubleList<>();
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
            return doubleList.odeberPrvni();
        } catch (DoubleListException e) {
            throw new RuntimeException(e);
        }
    }
}
