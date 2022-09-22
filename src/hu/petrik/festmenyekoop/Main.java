package hu.petrik.festmenyekoop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.*;

public class Main {

    public static List<Festmeny> festmenyek = new ArrayList<>();

    public static void main(String[] args) {
        festmenyek = new ArrayList<>();
        kettoa();
        kettob();
        bekeres();
        kettod();
        kettoe();
        //out.println(festmenyek);
        haroma();
        haromb();
        haromc();
        haromd();
    }

    //BEOLVASÁS --------------------------------------------------------------------

    public static void bekeres() {
        String fajlNev = "festmenyek.csv";
        try {
            beolvas(fajlNev);
        } catch (FileNotFoundException e) {
            err.printf("Hiba miatt nem található az %s fájl\n", fajlNev);
        } catch (IOException e) {
            err.println("Ismeretlen hiba történt a fájl beolvasása során");
        }
    }

    public static void beolvas(String fajlNev) throws IOException {
        FileReader fr = new FileReader(fajlNev);
        BufferedReader br = new BufferedReader(fr);
        String sor = br.readLine();
        while (sor != null && !sor.equals("")) {
            String[] tomb = sor.split(";");
            Festmeny festmeny = new Festmeny(tomb[0], tomb[1], tomb[2]);
            festmenyek.add(festmeny);
            sor = br.readLine();
        }
        br.close();
        fr.close();
    }


    //MÁSODIK FELADAT ------------------------------------------------------------------------

    public static void kettoa() {
        Festmeny f1 = new Festmeny("A java szépségei", "Sárándi Bálint", "Expresszionista");
        f1.licit(50);
        festmenyek.add(f1);
        festmenyek.add(new Festmeny("A java csúnyaságai", "Sárándi Bálint", "Kubizmus"));
    }

    public static void kettob() {
        Scanner sc = new Scanner(in);
        out.print("Hány festményt szeretne hozzáadni a listához?\nVálasz: ");
        int darabszam = sc.nextInt();
        for (int i = 0; i < darabszam; i++) {
            out.print("Cím: ");
            String cim = sc.nextLine();
            out.print("Név: ");
            String nev = sc.nextLine();
            out.print("Stílus: ");
            String stilus = sc.nextLine();
            festmenyek.add(new Festmeny(cim, nev, stilus));
        }
    }

    public static void kettod() {
        Random r = new Random();
        for (int i = 0; i < 20; i++) {
            festmenyek.get((int) (Math.random() * festmenyek.size())).licit(r.nextInt(100 - 10) + 10);
        }
    }

    public static void kettoe() {
        Scanner sc = new Scanner(in);
        int index = -1;
        double licitertek = 0;
        while (true) {
            out.print("Melyik festményre licitálna: ");
            index = sc.nextInt();
            sc.nextLine();
            if (index == 0) {
                break;
            } else if (festmenyek.get(index - 1).getElkelt()) {
                out.println("Elkelt!");
            } else if (index > festmenyek.size() || index < 0) {
                out.println("Nem létező sorszám!");
            } else {
                out.print("Licit érték (%-ban 10-100): ");
                licitertek = sc.nextInt();
                if (licitertek < 0 || licitertek > 100) {
                    while (licitertek < 0 || licitertek > 100) {
                        out.println("Érvénytelen szám!");
                        out.print("Licit érték: ");
                        licitertek = sc.nextInt();
                    }
                } else {
                    festmenyek.get(index - 1).licit(licitertek);
                }
            }
        }
        for (Festmeny festmeny : festmenyek) {
            if (festmeny.getLicitekSzama() > 0) {
                festmeny.setElkelt(true);
            }
        }
    }

    //HARMADIK FELADAT -------------------------------------------------------
    public static void haroma() {
        int legdragabb = 0;
        for (Festmeny festmeny : festmenyek) {
            if (festmeny.getLegmagasabbLicit() > legdragabb) {
                legdragabb = festmeny.getLegmagasabbLicit();
            }
        }
        out.printf("\nLegdrágábban elkelt festmény: %d", legdragabb);
    }

    public static void haromb() {
        boolean vane = false;
        for (Festmeny festmeny : festmenyek) {
            if (festmeny.getLicitekSzama() > 10) {
                vane = true;
                break;
            }
        }
        out.printf("\nVan 10-nél többször licitált dolog?: %b\n", vane);
    }

    public static void haromc() {
        int szamlalo = 0;
        for (Festmeny festmeny : festmenyek) {
            if (!festmeny.getElkelt()) {
                szamlalo++;
            }
        }
        out.printf("%d db festmény nem kelt el!\n", szamlalo);
    }

    public static void haromd(){
        festmenyek.sort((x, y) -> (x.getLegmagasabbLicit() > y.getLegmagasabbLicit()) ? -1 : 1);
        for (var x : festmenyek){
            out.println(x);
        }
    }

}