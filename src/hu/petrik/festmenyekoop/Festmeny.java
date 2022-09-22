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


    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
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

    public void licit(int mertek) {
        if (mertek < 10 || mertek > 100) {
            System.out.println("10 és 100 közötti szám kell!");
            if (elkelt == false && this.licitekSzama > 0) {
                int szam = (int)(((legmagasabbLicit * 1.1) + 5) / 10) *10;
                this.legmagasabbLicit = szam;
                this.licitekSzama++;
                this.legutolsoLicitIdeje = LocalDateTime.now();
            }
        }
        if (elkelt == true) {
            System.out.println("Elkelt már");
        } else if (this.licitekSzama == 0) {
            this.legmagasabbLicit = 100;
            this.licitekSzama++;
            this.legutolsoLicitIdeje = LocalDateTime.now();
        } else {
            int szam = (int)(((legmagasabbLicit * ((mertek / 100) + 1)) + 5) / 10) * 10;
            this.legmagasabbLicit = szam;
            this.licitekSzama++;
            this.legutolsoLicitIdeje = LocalDateTime.now();
        }


    }

    public void licit() {
        if (elkelt == true) {
            System.out.println("Elkelt már");
        } else if (this.licitekSzama == 0) {
            this.legmagasabbLicit = 100;
            this.licitekSzama++;
            this.legutolsoLicitIdeje = LocalDateTime.now();
        } else {
            this.legmagasabbLicit = (int) (legmagasabbLicit * 1.1);
            this.licitekSzama++;
            this.legutolsoLicitIdeje = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        String elkelte = "";
        if (elkelt == true) {
            elkelte = "elkelt";
            return festo + ":" + cim + "(" + stilus + ")\n" + elkelte + "\n" + legmagasabbLicit + "$ - " + legutolsoLicitIdeje + "(összesen: " + licitekSzama + " db)";
        } else {
            return festo + ":" + cim + "(" + stilus + ")\n" + "\n" + legmagasabbLicit + "$ - " + legutolsoLicitIdeje + "(összesen: " + licitekSzama + " db)";
        }

    }

}
