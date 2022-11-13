package com.company.Samochod;

import java.util.concurrent.atomic.AtomicBoolean;


class Samochod extends Thread {

    private String nrRej;
    private int pojZbiornika;
    private int paliwo;

    private final AtomicBoolean czyDziala = new AtomicBoolean();


    public Samochod(String nr, int pojZbiornika, int paliwo) {
        this.nrRej = nr;
        this.pojZbiornika = pojZbiornika;
        this.paliwo = paliwo;
    }

    public void tankowanie() {
        paliwo = 100;
        System.out.println("Tankowanie " + nrRej);

    }

    public void start() {
        super.start();
    }

    public final void stopp() {
        System.out.println("Zatrzymuje sie " + nrRej);
        czyDziala.set(false);
        tankowanie();
    }

    public void uruchom() throws InterruptedException {
        czyDziala.set(true);

        while (czyDziala.get()) {

            Thread.sleep(1000);

            System.out.println("Jedziemy samochodem " + nrRej + " i mamy " + paliwo + " paliwa");
            paliwo -= 3;
            if (paliwo < 20) {
                System.out.println("Potrzeba zatankowac " + nrRej);
            }
            if (paliwo < 0) {
                stopp();
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Samochod nr: "+nrRej);

        try {
            uruchom();
        } catch (InterruptedException e) {
            stopp();
            e.printStackTrace();
        }
    }

}

class TestSamochod{

    public static void main(String[] args)  {
        var mercedes = new Samochod("BKL",100,100);
        var bmw = new Samochod("ST",100,40);
        var audi = new Samochod("WWA",300,200);

        mercedes.start();
        bmw.start();
        audi.start();

    }
}