package AbstrTable;

import Pamatky.GPS;
import Pamatky.Zamek;
import enums.eTypKey;
import enums.eTypProhl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;

public class Perzistance {
    public static int nacist(IAbstrTable<Zamek, Zamek> strom, String nazev, eTypKey typ) throws FileNotFoundException {
        int getBack = 0;
        Scanner scanner = new Scanner(new File("src\\main\\resources\\data.txt"));
        while (scanner.hasNext()){
            getBack++;
            String result = scanner.nextLine();
            String[] array = result.split("\\s+");
            Integer[] gpsSourad = coord(array[2]+" " + array[3] + " " + array[4] + " "+ array[5]);
            Zamek zamek = new Zamek(String.valueOf(getBack),array[1],new GPS(gpsSourad[0],gpsSourad[1]),null);
            switch (typ){
                case NAZEV -> {
                    zamek.setTyp(eTypKey.NAZEV);
                    strom.vloz(zamek,zamek);
                }
                case GPS -> {
                    zamek.setTyp(eTypKey.GPS);
                    strom.vloz(zamek,zamek);
                }
            }
        }
        return getBack;
    }
    public static void ulozit(IAbstrTable<String, Zamek> strom,String nazev) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("src\\main\\resources\\"+nazev));
        Iterator<Zamek> iterator = strom.vytvorIterator(eTypProhl.HLOUBKA);
        while (iterator.hasNext()){
            Zamek zamek = iterator.next();
            String ar = "W" + zamek.getNazev() + " " + zamek.getGps();
        }
    }
    private static Integer[] coord(String klic) {
        String[] array = klic.split(" ");
        String[] xSlit = array[1].split("\\.");
        String[] ySlit = array[3].split("\\.");
        int x = Integer.parseInt(array[0].substring(1) + xSlit[0] + xSlit[1].charAt(0));
        int y = Integer.parseInt(array[2].substring(1) + ySlit[0] + ySlit[1].charAt(0));
        return new Integer[]{x, y};
    }
}
