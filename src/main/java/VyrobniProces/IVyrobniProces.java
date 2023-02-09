package VyrobniProces;

import Model.Proces;
import Zasobnik.IAbstrLifo;
import enums.enumPozice;
import enums.enumReorg;

import java.util.Iterator;

public interface IVyrobniProces {

    int importDat(String soubor);

    void vlozProces(Proces proces, enumPozice pozice);

    Proces zpristupniProces(enumPozice pozice);

    Proces odeberProces(enumPozice pozice);

    Iterator<Proces> iterator();

    IAbstrLifo<Proces> vytipujKandidatiReorg(int cas, enumReorg reorgan);

    void reorganizace(enumReorg reorgan, IAbstrLifo<Proces> zasobnik);

    void zrus();
     void ulozit(String soubor);
}
