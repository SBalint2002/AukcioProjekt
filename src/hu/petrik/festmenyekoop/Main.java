package hu.petrik.festmenyekoop;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Festmeny f1;
        String fajlNev = "festmenyek.csv";
        try{
            Festmenyek festmenyek = new Festmenyek(fajlNev);
        }catch (FileNotFoundException e){
            System.err.printf("Hiba miatt nem található az %s fájl\n", fajlNev);
        } catch (IOException e) {
            System.err.println("Ismeretlen hiba történt a fájl beolvasása során");
        }
    }
}