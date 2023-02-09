package DoubleList;

public interface IAbstrDoubleList<T> extends Iterable<T>{

    void zrus();

    boolean jePrazdny();

    void vlozPrvni(T data);

    void vlozNaslednika(T data) throws DoubleListException;

    void vlozPosledni(T data);

    void vlozPredchudce(T data) throws DoubleListException;

    T zpristupniAktualni() throws DoubleListException;

    T zpristupniPrvni() throws DoubleListException;

    T zpristupniPosledni() throws DoubleListException;

    T zpristupniNaslednika() throws DoubleListException;

    T zpristupniPredchudce() throws DoubleListException;

    T odeberAktualni() throws DoubleListException;

    T odeberPrvni() throws DoubleListException;

    T odeberPosledni() throws DoubleListException;

    T odeberNaslednika() throws DoubleListException;

    T odeberPredchudce() throws DoubleListException;

}
