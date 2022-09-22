package hu.petrik.festmenyekoop;

import javax.swing.plaf.basic.BasicMenuBarUI;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static List<Festmeny> festmenyek = new ArrayList<>();

    public static void main(String[] args) {
        festmenyek = new ArrayList<>();
        Festmeny f1 = new Festmeny("A java szépségei", "Sárándi Bálint", "Expresszionista");
        f1.licit(50);
        festmenyek.add(f1);
        festmenyek.add(new Festmeny("A java csúnyaságai", "Sárándi Bálint", "Kubizmus"));

        Scanner sc = new Scanner(System.in);

        System.out.print("Hány festményt szeretne hozzáadni a listához?\nVálasz: ");
        int darabszam = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < darabszam; i++) {
            System.out.print("Cím: ");
            String cim = sc.nextLine();
            System.out.print("Név: ");
            String nev = sc.nextLine();
            System.out.print("Stílus: ");
            String stilus = sc.nextLine();
            festmenyek.add(new Festmeny(cim, nev, stilus));
        }


        String fajlNev = "festmenyek.csv";
        try {
            beolvas(fajlNev);
        } catch (FileNotFoundException e) {
            System.err.printf("Hiba miatt nem található az %s fájl\n", fajlNev);
        } catch (IOException e) {
            System.err.println("Ismeretlen hiba történt a fájl beolvasása során");
        }

        System.out.println(festmenyek);

        for (int i = 0; i < 20; i++) {
            festmenyek.get((int) (Math.random() * festmenyek.size())).licit((int) (Math.random() * 100) + 10);
        }

        /*for (int i = 0; i < 20; i++) {
            int feldob = (int)(Math.random() * festmenyek.size());
            festmenyek.get(feldob).licit();
        }*/
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
}