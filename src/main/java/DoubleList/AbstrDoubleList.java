package DoubleList;

import java.util.Iterator;

public class AbstrDoubleList<T> implements IAbstrDoubleList<T>{

    private Prvek<T> first;
    private Prvek<T> last;
    private Prvek<T> aktualni;

    private static class Prvek<T>{
        public Prvek<T> previous;
        public Prvek<T> next;
        public T data;
        public Prvek(Prvek<T> previous, T data, Prvek<T> next) {
            this.previous = previous;
            this.next = next;
            this.data = data;
        }
    }

    @Override
    public void zrus() {
        first = null;
        last = null;
        aktualni = null;
    }

    @Override
    public boolean jePrazdny() {
        return first == null;
    }

    @Override
    public void vlozPrvni(T data) {
        if(jePrazdny()){
               Prvek<T> prvni = new Prvek<>(null,data,null);
               prvni.previous = prvni;
               prvni.next = prvni;
               first = prvni;
               last = prvni;
               aktualni = first;
        }
        else {
            Prvek<T> prvni = new Prvek<>(last,data, first);
            last.next = prvni;
            first.previous = prvni;
            first = prvni;
        }
    }

    @Override
    public void vlozNaslednika(T data) throws DoubleListException {
        if(aktualni ==null) throw new DoubleListException("Aktualni prvek neni nastavlen");
        if(aktualni == last) vlozPosledni(data);
        else {
            Prvek<T> newActual = new Prvek<>(aktualni,data,aktualni.next);
            aktualni.next.previous = newActual;
            aktualni.next = newActual;
        }
    }


    @Override
    public void vlozPosledni(T data) {
        if(jePrazdny()) vlozPrvni(data);
        else if(first==first.next){
            Prvek<T> posledni = new Prvek<>(first,data, first);
            first.next = posledni;
            last = posledni;
            first.previous = posledni;
            aktualni = last;
        }
        else {
            Prvek<T> prvek1 = new Prvek<>(last,data, first);
            first.previous = prvek1;
            last.next = prvek1;
            last = prvek1;
        }
    }

    @Override
    public void vlozPredchudce(T data) throws DoubleListException {
        if(aktualni ==null) throw new DoubleListException("Aktualni prvek neni nastavlen");
        if(aktualni == first) vlozPrvni(data);
        else {
            Prvek<T> predchudce = new Prvek<>(aktualni.previous,data, aktualni);
            aktualni.previous.next = predchudce;
            aktualni.previous = predchudce;
            aktualni = predchudce;
        }
    }

    @Override
    public T zpristupniAktualni() throws DoubleListException {
        if(aktualni ==null) throw new DoubleListException("Aktualni prvek neni nastavlen");
        return aktualni.data;
    }

    @Override
    public T zpristupniPrvni() throws DoubleListException {
        if(first ==null) throw new DoubleListException("Prvni prvek neexistuje");
        aktualni = first;
        return aktualni.data;
    }

    @Override
    public T zpristupniPosledni() throws DoubleListException {
        if(last ==null) throw new DoubleListException("Posledni prvek neexistuje");
        aktualni = last;
        return aktualni.data;
    }

    @Override
    public T zpristupniNaslednika() throws DoubleListException {
        if(aktualni == null) throw new DoubleListException("Aktualni prvek neni nastavlen");
        aktualni = aktualni.next;
        if(aktualni == first) throw new DoubleListException("Seznam se skoncil");
        return aktualni.data;
    }

    @Override
    public T zpristupniPredchudce() throws DoubleListException {
        if(aktualni == null) throw new DoubleListException("Aktualni prvek neni nastavlen");
        aktualni = aktualni.previous;
        if(aktualni == last) throw new DoubleListException("Seznam se skoncil");
        return aktualni.data;
    }

    @Override
    public T odeberAktualni() throws DoubleListException {
        if(aktualni == null) throw new DoubleListException("Aktualni prvek neni nastavlen");
        Prvek<T> aktualni = this.aktualni;
        this.aktualni.previous.next = this.aktualni.next;
        this.aktualni.next.previous = this.aktualni.previous;
        if(last == aktualni) last = first.previous;
        else if(first == aktualni) first = last.next;
        this.aktualni = first;
        return aktualni.data;
    }

    @Override
    public T odeberPrvni() throws DoubleListException {
        if(first ==null) throw new DoubleListException("Prvni prvek neexistuje");
        Prvek<T> prvni = first;
        first = first.next;
        last.next= first;
        first.previous= last;
        return prvni.data;
    }

    @Override
    public T odeberPosledni() throws DoubleListException {
        if(last== null) throw new DoubleListException("Prvni prvek neexistuje");
        Prvek<T> posledi = last;
        if(last.next == last) {
            zrus();
            return posledi.data;
        }
        last = last.previous;
        last.next = first;
        first.previous = last;
        return posledi.data;
    }

    @Override
    public T odeberNaslednika() throws DoubleListException {
        if(aktualni ==null) throw new DoubleListException("Aktualni prvek neni nastavlen");
        Prvek<T> naslednik = aktualni.next;
        if(first ==naslednik) odeberPrvni();
        else if(last ==naslednik) odeberPosledni();
        else {
            aktualni.next = aktualni.next.next;
            aktualni.next.previous = aktualni;
        }
        return naslednik.data;
    }

    @Override
    public T odeberPredchudce() throws DoubleListException {
        if(aktualni == null) throw new DoubleListException("Aktualni prvek neni nastavlen");
        Prvek<T> predchudce = aktualni.previous;
        if(first == predchudce)odeberPrvni();
        else if(last == predchudce) odeberPosledni();
        else {
            aktualni.previous.previous.next = aktualni;
            aktualni.previous = aktualni.previous.previous;
        }
        return predchudce.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Prvek<T> localActual = last;
            Boolean check = true;
            @Override
            public boolean hasNext() {
                if(localActual == null) return false;
                if(check){
                    check = false;
                    return first != null;
                }
                return localActual.next != first;
            }

            @Override
            public T next() {
                localActual = localActual.next;
                return localActual.data;
            }
        };
    }




}
