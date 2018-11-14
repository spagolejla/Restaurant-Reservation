package com.example.lalalas.myapp.model;

import java.sql.Time;
import java.sql.Date;

public class RezervacijaAddVM {

    public String vrsta;
    public String datum;
    public String vrijeme;
    public int korsnikId;
    public int restoranId;
    public int brojOsoba;

    public RezervacijaAddVM(String vrsta, String datum, String vrijeme, int korsnikId, int restoranId, int brojOsoba) {
        this.vrsta = vrsta;
        this.datum = datum;
        this.vrijeme = vrijeme;
        this.korsnikId = korsnikId;
        this.restoranId = restoranId;
        this.brojOsoba = brojOsoba;
    }

    public RezervacijaAddVM() {
    }
}
