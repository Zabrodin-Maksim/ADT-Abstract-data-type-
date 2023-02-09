package VyrobniProces;

import DoubleList.AbstrDoubleList;
import DoubleList.DoubleListException;
import DoubleList.IAbstrDoubleList;
import Model.Proces;
import Model.ProcesManualni;
import Model.ProcesRoboticky;
import Zasobnik.AbstrLifo;
import Zasobnik.IAbstrLifo;
import enums.enumPozice;
import enums.enumReorg;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class VyrobniProces implements IVyrobniProces{
    private final IAbstrDoubleList<Proces> list;

    public VyrobniProces() {
        list = new AbstrDoubleList<>();
    }
    @Override
    public int importDat(String soubor) {
        int pocet = 0;
        try {
            Scanner scanner = new Scanner(new File(soubor));
            scanner.next();
            while (scanner.hasNext()) {
                String[] result = scanner.next().split(";");
                Proces proces;
                if (result[0].startsWith("R")) proces = new ProcesRoboticky(result[0], Integer.parseInt(result[2]));
                else proces = new ProcesManualni(result[0], Integer.parseInt(result[1]), Integer.parseInt(result[2]));
                pocet++;
                list.vlozPosledni(proces);
            }
            } catch (FileNotFoundException e) {
                alert("Soubor nevyhledan");
            }
        return pocet;
    }


    @Override
    public void vlozProces(Proces proces, enumPozice pozice) {
        try {
            switch (pozice) {
                case PRVNI -> list.vlozPrvni(proces);
                case POSLEDNI -> list.vlozPosledni(proces);
                case NASLEDNIK -> list.vlozNaslednika(proces);
                case PREDCHUDCE -> list.vlozPredchudce(proces);
            }
        }catch (DoubleListException e){
            alert(e.getMessage());
        }
    }

    @Override
    public Proces zpristupniProces(enumPozice pozice) {
        Proces result = null;
        try {
            switch (pozice) {
                case PRVNI -> result = list.zpristupniPrvni();
                case POSLEDNI -> result = list.zpristupniPosledni();
                case NASLEDNIK -> result = list.zpristupniNaslednika();
                case PREDCHUDCE -> result = list.zpristupniPredchudce();
                case AKTUALNI -> result = list.zpristupniAktualni();
            }
        }catch (DoubleListException e){
            alert(e.getMessage());
        }
        return result;
    }

    @Override
    public Proces odeberProces(enumPozice pozice) {
        Proces result = null;
        try {
            switch (pozice) {
                case PRVNI -> result = list.odeberPrvni();
                case POSLEDNI -> result = list.odeberPosledni();
                case NASLEDNIK -> result = list.odeberNaslednika();
                case PREDCHUDCE -> result = list.odeberPredchudce();
                case AKTUALNI -> result = list.odeberAktualni();
            }
        }
        catch (DoubleListException e){
            alert(e.getMessage());
        }
        return result;
    }

    @Override
    public Iterator<Proces> iterator() {
        return list.iterator();
    }

    @Override
    public IAbstrLifo<Proces> vytipujKandidatiReorg(int cas, enumReorg reorgan) {
        switch (reorgan){
            case AGREGACE -> {
                IAbstrLifo<Proces> newList = new AbstrLifo<>();
                for (Proces proces : list) {
                    if (proces.getId().startsWith("O") && proces.getCas()<=cas) {
                        newList.vloz(proces);
                    }
                }
                return newList;
            }
            case DEKOMPOZICE -> {
                IAbstrLifo<Proces> newList = new AbstrLifo<>();
                for (Proces proces : list) {
                    if (proces.getId().startsWith("O") && proces.getCas()>=cas) {
                        newList.vloz(proces);
                    }
                }
                return newList;
            }
            default -> {
                alert("Neco se zlomilo");
                return null;
            }
        }
    }

    @Override
    public void reorganizace(enumReorg reorgan, IAbstrLifo<Proces> zasobnik) {
        try {
            switch (reorgan) {
                case AGREGACE -> {
                    IAbstrLifo<Proces> dublicate = new AbstrLifo<>();
                    Proces prechudce = null;
                    while (!zasobnik.jePrazdny()) {
                        if (prechudce == null) {
                            prechudce = zasobnik.odeber();
                            if (zasobnik.jePrazdny()) {
                                Proces proces = dublicate.odeber();
                                proces = new ProcesManualni(
                                        prechudce.getId(),
                                        ((ProcesManualni) proces).getPocetLide() + ((ProcesManualni) prechudce).getPocetLide(),
                                        proces.getCas() + prechudce.getCas());
                                dublicate.vloz(proces);
                            }
                        } else {
                            Proces now = zasobnik.odeber();
                            Proces proces = new ProcesManualni(
                                    now.getId(),
                                    ((ProcesManualni) prechudce).getPocetLide() + ((ProcesManualni) now).getPocetLide(),
                                    prechudce.getCas() + now.getCas());
                            dublicate.vloz(proces);
                            prechudce = null;
                        }
                    }
                    list.zrus();
                    while (!dublicate.jePrazdny()) {
                        list.vlozPosledni(dublicate.odeber());
                    }
                }
                case DEKOMPOZICE -> {
                    IAbstrLifo<Proces> lifo = new AbstrLifo<>();
                    while (!zasobnik.jePrazdny()) {
                        Proces proces = zasobnik.odeber();
                        Proces newProces = new ProcesManualni(proces.getId() + "_new", deleniNaDvaMin(((ProcesManualni) proces).getPocetLide()),
                                deleniNaDvaMin(proces.getCas()));
                        Proces staryProces = new ProcesManualni(proces.getId() + "_old", deleniNaDvaMax(((ProcesManualni) proces).getPocetLide()),
                                deleniNaDvaMax(proces.getCas()));
                        lifo.vloz(newProces);
                        lifo.vloz(staryProces);
                    }
                    list.zrus();
                    while (!lifo.jePrazdny()) {
                        list.vlozPosledni(lifo.odeber());
                    }
                }
            }
        }catch (DoubleListException e) {alert(e.getMessage());}
    }

    @Override
    public void zrus() {
        list.zrus();
    }
    private void alert(String message) {
        new Alert(Alert.AlertType.ERROR,message, ButtonType.OK).show();
    }
    private static int deleniNaDvaMin(int cas){
        return (int) (cas/2.0);
    }
    private static int deleniNaDvaMax(int cas){
        return (int) (Math.ceil(cas/2.0));
    }

    @Override
    public void ulozit(String soubor){
        File file = new File(soubor);
        try(PrintWriter printWriter = new PrintWriter(file)) {
            printWriter.write("IdProc;persons;time\n");
            for (Proces proces : list) {
                String result=proces.getId();
                if(proces.getId().startsWith("O")) result+=";"+((ProcesManualni)proces).getPocetLide();
                else result+=";0";
                result+=";"+proces.getCas();
                printWriter.write(result+"\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Soubor nevyhledan");
        }
    }

}
