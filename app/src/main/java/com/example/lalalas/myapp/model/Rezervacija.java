package com.example.lalalas.myapp.model;

import java.io.Serializable;
import java.util.Date;
import com.example.lalalas.myapp.model.Korisnik;
import com.example.lalalas.myapp.model.Restoran;

public class Rezervacija implements Serializable{
    private int rezervacijaID;
    private String vrstaRezervacije;
     private Korisnik korisnik;
    private Restoran restoran;
    private int brojOsoba;
    private String datum;
    private String vrijeme;
   private static int id=0;

    public Rezervacija() {
    }

    public Rezervacija(String vrstaRezervacije, Korisnik korisnik, Restoran restoran, int brojOsoba, String datum,String vrijeme) {
        this.rezervacijaID = id++;
        this.vrstaRezervacije = vrstaRezervacije;
        this.korisnik = korisnik;
        this.restoran = restoran;
        this.brojOsoba = brojOsoba;
        this.datum = datum;
        this.vrijeme=vrijeme;
    }

    public Rezervacija( String vrstaRezervacije, Korisnik korisnik, Restoran restoran, int brojOsoba) {
        this.rezervacijaID = id++;
        this.vrstaRezervacije = vrstaRezervacije;
        this.korisnik = korisnik;
        this.restoran = restoran;
        this.brojOsoba = brojOsoba;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getVrijeme() {
        return vrijeme;
    }

    public void setVrijeme(String vrijeme) {
        this.vrijeme = vrijeme;
    }

    public String getVrstaRezervacije() {
        return vrstaRezervacije;
    }

    public void setVrstaRezervacije(String vrstaRezervacije) {
        this.vrstaRezervacije = vrstaRezervacije;
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }


    public int getBrojOsoba() {
        return brojOsoba;
    }

    public void setBrojOsoba(int brojOsoba) {
        this.brojOsoba = brojOsoba;
    }


}