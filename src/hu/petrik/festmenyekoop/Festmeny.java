package hu.petrik.festmenyekoop;

import java.time.LocalDateTime;

public class Festmeny {
    private String cim;
    private String festo;
    private String stilus;
    private int licitekSzama;
    private int legmagasabbLicit;
    private LocalDateTime legutolsoLicitIdeje;
    private boolean elkelt;
    private Festmeny[] festmenyek;


    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
        this.licitekSzama = 0;
        this.legmagasabbLicit = 0;
        this.elkelt = false;
    }

    public String getFesto() {
        return festo;
    }

    public String getStilus() {
        return stilus;
    }

    public int getLicitekSzama() {
        return licitekSzama;
    }

    public int getLegmagasabbLicit() {
        return legmagasabbLicit;
    }

    public LocalDateTime getLegutolsoLicitIdeje() {
        return legutolsoLicitIdeje;
    }

    public boolean getElkelt() {
        return elkelt;
    }

    public void setElkelt(boolean elkelt) {
        this.elkelt = elkelt;
    }

    public void licit(double mertek) {
        if (elkelt) {
            System.out.println("Elkelt már");
        } else if (licitekSzama != 0) {
            legmagasabbLicit = (int)(100 +((legmagasabbLicit*mertek)/100));
            licitekSzama++;
            legutolsoLicitIdeje = LocalDateTime.now();
        } else if (mertek < 10 || mertek > 100) {
            System.out.println("Hiba!!!! 10 és 100 közötti szám kell!");
        } else {
            legmagasabbLicit = 100;
            licitekSzama++;
            legutolsoLicitIdeje = LocalDateTime.now();
        }
    }

    public void licit() {
        if (elkelt) {
            System.out.println("Elkelt már");
        } else if (licitekSzama == 0) {
            legmagasabbLicit = 100;
            licitekSzama++;
            legutolsoLicitIdeje = LocalDateTime.now();
        } else {
            legmagasabbLicit *= 1.10;
            licitekSzama++;
            legutolsoLicitIdeje = LocalDateTime.now();
        }
    }


    @Override
    public String toString() {
        return String.format("\n\nFestő: %s(%s)\n%b\nLegmagasabb licit: %d$ - "
                + legutolsoLicitIdeje + " (összesen: %d db)", festo, stilus, elkelt, legmagasabbLicit, licitekSzama);

    }

}
