import DoubleList.DoubleListException;
import Zasobnik.AbstrLifo;
import Zasobnik.IAbstrLifo;

public class Main {
    public static void main(String[] args) throws DoubleListException {
        IAbstrLifo<String> lifo = new AbstrLifo<>();
        lifo.vloz("Hello");
        lifo.vloz("Hello1");
        lifo.vloz("Hello3");
        while (!lifo.jePrazdny()){
            System.out.println(lifo.odeber());
        }
    }
}
