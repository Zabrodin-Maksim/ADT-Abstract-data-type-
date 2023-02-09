package Pamatky;

import enums.eTypKey;

public class Zamek implements Comparable<Zamek> {
    private String id;
    private String nazev;
    private GPS gps;
    private eTypKey typ;


    public Zamek(String id, String nazev, GPS gps, eTypKey typ) {
        this.id = id;
        this.nazev = nazev;
        this.gps = gps;
        this.typ = typ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public GPS getGps() {
        return gps;
    }

    public void setGps(GPS gps) {
        this.gps = gps;
    }

    public eTypKey getTyp() {
        return typ;
    }

    public void setTyp(eTypKey typ) {
        this.typ = typ;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", nazev=" + nazev + ", GPS=" + gps;
    }


    @Override
    public int compareTo(Zamek o) {
        if(typ==eTypKey.NAZEV)return nazev.compareTo(o.getNazev());
        else return gps.compareTo(o.getGps());
    }

}
