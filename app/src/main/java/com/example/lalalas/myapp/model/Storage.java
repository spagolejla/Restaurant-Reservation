package com.example.lalalas.myapp.model;
import com.example.lalalas.myapp.R;
import com.example.lalalas.myapp.helper.MyObjects;

import java.util.ArrayList;
import java.util.List;

public class Storage {
  private static List<Restoran> restorani;

  public static List<Restoran> getRestorani(){

      int []imgs=getImages();
      if(restorani==null){
          restorani=new ArrayList<>();
          restorani.add(new Restoran(imgs[0],"Restoran Hindin han","Opis","Mostar","Brace Fejica br.34","036/511-456","hindin_han@gmail.com"));
          restorani.add(new Restoran(imgs[1],"Restoran Malta","Opis","Mostar","Lakisica br.23","036/511-456","malta@gmail.com"));
          restorani.add(new Restoran(imgs[2],"Restoran Marinero","Opis","Tuzla","Orka br.45 1","036/511-456","marrinero@gmail.com"));
          restorani.add(new Restoran(imgs[3],"Restoran Bella","Opis","Sarajevo","Trg Ivana Krndelja 23","036/511-456","bella@gmail.com"));
          restorani.add(new Restoran(imgs[4],"Restoran Han","Opis","Konjic","Gornje polje 11","036/511-456","han@gmail.com"));
          restorani.add(new Restoran(imgs[5],"Restoran Orlando","Opis","Ljubuski","Ale Cisica bb","036/511-456","orlando@gmail.com"));
          restorani.add(new Restoran(imgs[6],"Restoran italiano","Opis","Mostar","Stefanijino br.34","036/511-456","italiano@gmail.com"));
          restorani.add(new Restoran(imgs[7],"Restoran Kuluk","Opis","Konjic","Stari grad Konjic bb","036/511-456","sadrvan@gmail.com"));
          restorani.add(new Restoran(imgs[8],"Restoran Novalica Kula","Opis","Konjic","Nova carsija","036/511-456","novalica_kula@gmail.com"));

      }

      return restorani;
  }

    public static int[] getImages() {


        int[] images = {R.drawable.hindin_han_tn,R.drawable.malta_tn,R.drawable.marrinero_tn,R.drawable.bella_tn
                ,R.drawable.han_tn,R.drawable.orlando_tn,R.drawable.italiano_tn,R.drawable.kuluk_tn,R.drawable.novalica_kula_tn
        };

        return images;
    }


    private  static List<Korisnik> korisnici;
  public  static List<Korisnik> getKorisnici(){
      if (korisnici==null){
          korisnici=new ArrayList<>();
          korisnici.add(new Korisnik(1,"Lejla","Spago","Lejla","test","lejla.spago@edu.fit.ba"));
          korisnici.add(new Korisnik(2,"Melisa","test","Melisa","Dzeko","melisa.dzeko@edu.fit.ba"));
          korisnici.add(new Korisnik(3,"Šejla","test","Šejla","Špago","sejla.spago@edu.fit.ba"));
          korisnici.add(new Korisnik(4,"Jasmina","test","Jasmina","Špago","jejla.spago@edu.fit.ba"));
          korisnici.add(new Korisnik(5,"Dino","test","Dino","Fišić","dino.fisic@edu.fit.ba"));

      }

      return korisnici;
  }

   private static List<Rezervacija> rezervacije;
   public static List<Rezervacija> getRezervacije() {
      if (rezervacije==null){
         rezervacije=new ArrayList<>();
          rezervacije.add(new Rezervacija("Dorucak",getKorisnici().get(0),getRestorani().get(0),5,"17.5.2017","01:10"));
          rezervacije.add(new Rezervacija("Rucak",getKorisnici().get(0),getRestorani().get(1),10,"6.8.2017","15:00"));
          rezervacije.add(new Rezervacija("Vecera",getKorisnici().get(0),getRestorani().get(2),2,"2.9.2017","17:00"));
          rezervacije.add(new Rezervacija("Dorucak",getKorisnici().get(0),getRestorani().get(7),4,"13.12.2017","18:30"));
          rezervacije.add(new Rezervacija("Vecera",getKorisnici().get(0),getRestorani().get(7),6,"19.3.2017","19:00"));
          rezervacije.add(new Rezervacija("Dorucak",getKorisnici().get(0),getRestorani().get(1),8,"20.6.2017","20:00"));
          rezervacije.add(new Rezervacija("Vecera",getKorisnici().get(0),getRestorani().get(3),9,"17.8.2017","20:30"));
          rezervacije.add(new Rezervacija("Pice",getKorisnici().get(0),getRestorani().get(2),2,"2.8.2017","00:00"));


      }

      return rezervacije;
   }
public static void addRezervacija(Rezervacija rez)
{
    rezervacije.add(rez);

}
    public static List<Korisnik> getKorisniciByIme(String query) {

        List<Korisnik> rezultat = new ArrayList<>();

        for (Korisnik x: getKorisnici())
        {
            if ((x.getIme() + " " + x.getPrezime()).startsWith(query))
                rezultat.add(x);
        }

        return rezultat;
    }

    public static Korisnik LoginCheck(String username, String password)
    {
        for (Korisnik x : getKorisnici()) {
            if (MyObjects.equals(x.getUsername(), username) && MyObjects.equals(x.getPassword(), password))
                return x;
        }
        return null;
    }

}
