//class ThreadN extends Thread { public void run() {
//    try {
//        for (int i = 0; i < 10; i++) { Thread.sleep(1000); System.out.println("ThreadN"); }
//    }
//    catch (InterruptedException ex) { ex.printStackTrace();
//    }
//}
//}
//class ThreadM extends Thread {
//    public void run() {
//        try {
//            for (int i = 0; i < 10; i++) {
//                Thread.sleep(1000); System.out.println("ThreadM");
//            }
//        }
//        catch (InterruptedException ex) { ex.printStackTrace();
//        }
//    }
//}
//class JoinDemo1 {
//    public static void main(String args[]) {
//        ThreadM tm = new ThreadM();
//        tm.start();
//        ThreadN tn = new ThreadN();
//        tn.start();
//        try {
//            tm.join();
//            tn.join();
//            System.out.println("Both threads havfinished"); }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}







//class CalcPI1 {
//    public static void main(String[] args) {
//        MyThread mt = new MyThread();
//        mt.start();
//        try {
//            Thread.sleep(10); // uśpienie wątku na 10 millisekund
//        } catch (InterruptedException e) {
//        }
//        System.out.println("pi = " + mt.pi);
//    }
//}
//class MyThread extends Thread {
//    boolean negative = true;
//    double pi;
//
//    public void run() {
//        for (int i = 3; i < 100000; i += 2) {
//            if (negative)
//                pi -= (1.0 / i);
//            else
//                pi += (1.0 / i);
//            negative = !negative;
//        }
//        pi += 1.0;
//        pi *= 4.0;
//        System.out.println("Koniec obliczeń PI");
//    }
//}





//class CalcPI2 {
//    public static void main (String [] args) {
//        MyThread mt = new MyThread (); mt.start ();
//        while (mt.isAlive ())
//            try {
//                Thread.sleep (10); // uśpienie wątku na 10 millisekund
//            }catch (InterruptedException e) {
//                }
//        System.out.println ("pi = " + mt.pi);
//    }
//}
//    class MyThread extends Thread {
//        boolean negative = true;
//        double pi; public void run ()
//        {
//            for (int i = 3; i < 100000; i += 2) {
//                if (negative)
//                    pi -= (1.0 / i);
//                else
//                    pi += (1.0 / i);
//                negative = !negative; }
//            pi += 1.0;
//            pi *= 4.0;
//            System.out.println (" Koniec obliczeń PI ");
//        }
//    }

//class CalcPI3 {
//    public static void main (String [] args) {
//        MyThread mt = new MyThread (); mt.start ();
//        try
//        {
//            mt.join ();
//        }
//        catch (InterruptedException e)
//        {
//        }
//        System.out.println ("pi = " + mt.pi);
//    } }
//class MyThread extends Thread {
//    boolean negative = true;
//    double pi; // Initializes to 0.0, by default public void run ()
//    {
//        for (int i = 3; i < 100000; i += 2) {
//            if (negative)
//                pi -= (1.0 / i);
//            else
//                pi += (1.0 / i);
//            negative = !negative;
//        }
//        pi += 1.0;
//        pi *= 4.0;
//        System.out.println (" Koniec obliczeń PI ");
//    } }


//import java.util.Random;
//public class Lotnisko {
//    static int LOTNISKO = 1;
//    static int START = 2;
//    static int LOT = 3;
//    static int KONIEC_LOTU = 4;
//    static int KATASTROFA = 5;
//    int ilosc_pasow;
//    int ilosc_zajetych;
//    int ilosc_samolotow;
//
//    Lotnisko(int ilosc_pasow, int ilosc_samolotow) {
//        this.ilosc_pasow = ilosc_pasow;
//        this.ilosc_samolotow = ilosc_samolotow;
//        this.ilosc_zajetych = 0;
//    }
//
//    synchronized int start(int numer) {
//        ilosc_zajetych--;
//        System.out.println("Pozwolenie na start samolotowi " + numer);
//        return START;
//    }
//
//    synchronized int laduj() {
//        try {
//            Thread.currentThread().sleep(1000);
//            // sleep for 1000ms
//        } catch (Exception ie) {
//
//        }
//        if (ilosc_zajetych < ilosc_pasow) {
//            ilosc_zajetych++;
//            System.out.println("Pozwolenie ladowanie na pasie " + ilosc_zajetych);
//            return LOTNISKO;
//        } else {
//            return KONIEC_LOTU;
//        }
//    }
//
//    synchronized void zmniejsz() {
//        ilosc_samolotow--;
//        System.out.println("ZABILEM");
//        if (ilosc_samolotow == ilosc_pasow)
//            System.out.println("ILOSC SAMOLOTOW TAKA SAMA JAK ILOSC PASOW ______________");
//    }
//
//    public class Glowna {
//        static int ilosc_samolotow = 100000;
//        static int ilosc_pasow = 5;
//        static Lotnisko lotnisko;
//
//        public Glowna() {
//        }
//
//        public void main(String[] args) {
//            lotnisko = new Lotnisko(ilosc_pasow, ilosc_samolotow);
//            for (int i = 0; i < ilosc_samolotow; i++)
//                new Samolot(i, 2000, lotnisko).start();
//        }
//    }
//
//    public class Samolot extends Thread {
//        //definicja stanˇw samolotu
//        static int LOTNISKO = 1;
//        static int START = 2;
//        static int LOT = 3;
//        static int KONIEC_LOTU = 4;
//        static int KATASTROFA = 5;
//        static int TANKUJ = 1000;
//        static int REZERWA = 500;
//        //zmienne pomocnicze
//        int numer;
//        int paliwo;
//        int stan;
//        Lotnisko l;
//        Random rand;
//
//        public Samolot(int numer, int paliwo, Lotnisko l) {
//            this.numer = numer;
//            this.paliwo = paliwo;
//            this.stan = LOT;
//            this.l = l;
//            rand = new Random();
//        }
//
//        public void run() {
//            while (true) {
//                if (stan == LOTNISKO) {
//                    if (rand.nextInt(2) == 1) {
//                        stan = START;
//                        paliwo = TANKUJ;
//                        System.out.println("prosze o pozwolenie na start, samolot " + numer);
//                        stan = l.start(numer);
//                    } else {
//                        System.out.println("Postoje sobie jeszcze troche");
//                    }
//                } else if (stan == START) {
//                    System.out.println("Wystartowalem, samolot " + numer);
//                    stan = LOT;
//                } else if (stan == LOT) {
//                    paliwo -= rand.nextInt(500);
//                    if (paliwo <= REZERWA) {
//                        stan = KONIEC_LOTU;
//                    } else try {
//                        sleep(rand.nextInt(1000));
//                    } catch (Exception e) {
//                    }
//                } else if (stan == KONIEC_LOTU) {
//                    System.out.println("Prosze o pozowolenie na ladowanie " + numer + " ilosc paliwa " + paliwo);
//                    stan = l.laduj();
//                    if (stan == KONIEC_LOTU) {
//                        paliwo -= rand.nextInt(500);
//                        System.out.println("REZERWA " + paliwo);
//                        if (paliwo <= 0) stan = KATASTROFA;
//                    }
//                } else if (stan == KATASTROFA) {
//                    System.out.println("KATASTROFA samolot " + numer);
//                    l.zmniejsz();
//                }
//            }
//        }
//    }
//}




import java.net.*; import java.io.*; import java.util.*;
class jHTTPSMulti extends Thread {
    private Socket socket = null;
    String getAnswer() {
        InetAddress adres; String name = ""; String ip = "";
        try {
            adres = InetAddress.getLocalHost(); name = adres.getHostName();
            ip = adres.getHostAddress();
        }
        catch (UnknownHostException ex) { System.err.println(ex); } String document = "<html>\r\n" +
                "<body><br>\r\n" +
                "<h2><font color=red>jHTTPApp demo document\r\n" + "</font></h2>\r\n" +
                "<h3>Serwer na watkach" +
                "czesc czesc</h3><hr>\r\n" +
                "Data: <b>" + new Date() + "</b><br>\r\n" +
                "Nazwa hosta: <b>" + name + "</b><br>\r\n" +
                "IP hosta: <b>" + ip + "</b><br>\r\n" +
                "<hr>\r\n" +
                "</body>\r\n" +
                "</html>\r\n";
        String header = "HTTP/1.1 200 OK\r\n" +
                "Server: jHTTPServer ver 1.1\r\n" +
                "Last-Modified: Fri, 28 Jul 2000 07:58:55 GMT\r\n" + "Content-Length: " + document.length() + "\r\n" + "Connection: close\r\n" +
                "Content-Type: text/html; charset=iso-8859-2"; return header + "\r\n\r\n" + document;
    }
    public jHTTPSMulti(Socket socket){ System.out.println("Nowy obiekt jHTTPSMulti..."); this.socket = socket;
        start();
    }
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())); System.out.println("---------------- Pierwsza linia zapytania ----------------"); System.out.println(in.readLine());
            System.out.println("---------------- Wysylam odpowiedz -----------------------"); System.out.println(getAnswer());
            System.out.println("---------------- Koniec odpowiedzi -----------------------"); out.println(getAnswer());
            out.flush();
        } catch (IOException e) {
            System.out.println("Blad IO danych!");
        }
        finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.out.println("Blad zamkniecia gniazda!");
            }
        } // finally
    }
}
public class jHTTPApp {
    public static void main(String[] args) throws IOException { ServerSocket server = new ServerSocket(80);
        try {
            while (true) {
                Socket socket = server.accept();
                new jHTTPSMulti(socket);
            } // while
        } // try
        finally { server.close();}
    } // main
}
