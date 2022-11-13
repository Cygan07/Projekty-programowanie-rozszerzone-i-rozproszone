package com.company.Monte_Carlo;

import java.util.Random;

class Monte_Carlo extends Thread {
    double xStart, xStop, yStart, yStop;
    int licznik_prob;
    double wynik;
    Random losowe;

    public Monte_Carlo(double xStart, double xStop, double yStop, double yStart, int licznik) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xStop = xStop;
        this.yStop = yStop;
        this.wynik = 0;
        this.losowe = new Random();
        this.licznik_prob = licznik;

    }

    public void run() {
        int trafienie = 0;

        for (int i = 0; i < this.licznik_prob; i++) {
            double x = Math.random();
            double y = Math.random();

            if ((x * x + y * y) <= 1)
                trafienie++;
        }

        this.wynik = trafienie;
    }

    public double Wynik() {
        return this.wynik;
    }

}

public class Main {

    public static void main(String[] args) {
        Monte_Carlo w1, w2, m3, w4;
        int proby = 1000;
        double a = 10;

        w1 = new Monte_Carlo(0,0, a/2, a/2, proby);
        w2 = new Monte_Carlo(a/2,0, 1, a/2, proby);
        m3 = new Monte_Carlo(0, a/2, a/2, a, proby);
        w4 = new Monte_Carlo(a/2,a/2, a, a, proby);

        w1.run();
        w2.run();
        m3.run();
        w4.run();

        try {
            w1.join();
            w2.join();
            m3.join();
            w4.join();
        }catch (Exception e){

        }
        double poleKola = w1.Wynik() + w2.Wynik() + m3.Wynik() + w4.Wynik();
        poleKola = poleKola / ((double)proby * 4) * (a * a);
        System.out.println("Pole twojego kola wynosi = " + poleKola);
    }
}