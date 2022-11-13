package com.company.Serwer;

import java.net.*; import java.io.*; import java.util.*;
class jHTTPSMulti extends Thread {
    private Socket socket = null;
    String getAnswer() {
        InetAddress adres;
        String name = "";
        String ip = "";
        String Mac = null;
        try {
            adres = InetAddress.getLocalHost();
            name = adres.getHostName();
            ip = adres.getHostAddress();

            InetAddress localHost = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(localHost);
            byte[] hardwareAddress = ni.getHardwareAddress();

            String[] hexadecimal = new String[hardwareAddress.length];
            for (int i = 0; i < hardwareAddress.length; i++) {
                hexadecimal[i] = String.format("%02X", hardwareAddress[i]);
            }
            String macAddress = String.join("-", hexadecimal);
            Mac = macAddress;
        } catch (UnknownHostException | SocketException ex) {
            System.err.println(ex);
        }


        String document = "<html>\r\n" +
                "<body><br>\r\n" +
                "<h2><font color=red>jHTTPApp demo document\r\n" + "</font></h2>\r\n" +
                "<h3>Serwer na watkach" +
                "</h3><hr>\r\n" +
                "Data: <b>" + new Date() + "</b><br>\r\n" +
                "Nazwa hosta: <b>" + name + "</b><br>\r\n" +
                "IP hosta: <b>" + ip + "</b><br>\r\n" +
                "adres Mac hosta: <b>" + Mac + "</b><br>\r\n" +
                "<hr>\r\n" +
                "</body>\r\n" +
                "</html>\r\n";
        String header = "HTTP/1.1 200 OK\r\n" +
                "Server: jHTTPServer ver 1.1\r\n" +
                "Last-Modified: Fri, 28 Jul 2000 07:58:55 GMT\r\n" + "Content-Length: " + document.length() + "\r\n" + "Connection: close\r\n" +
                "Content-Type: text/html; charset=iso-8859-2";
        return header + "\r\n\r\n" + document;
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
