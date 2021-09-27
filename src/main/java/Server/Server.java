package Server;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
    ServerSocket server = null;
    Socket client = null;
    String stringRicevuta = null;
    String stringModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket Attendi()
    {
        try
        {
            System.out.println("1 Server partito in Esecuzione");

            server = new ServerSocket(6789);

            client = server.accept();

            server.close();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }

        return client;
    }

    public void Comunica()
    {
        try
        {
            System.out.println("3 Benvenuto Client, Scrivi Una Frase E la Trasformo In Maiuscolo. Attendo...");
            stringRicevuta = inDalClient.readLine();
            System.out.println("6 Ricevuta la Stringa Dal Client: " + stringRicevuta);

            stringModificata = stringRicevuta.toUpperCase();
            System.out.println("7 Invio La Stringa Modificata Al Client... ");
            outVersoClient.writeBytes(stringModificata + '\n');

            System.out.println("9 SERVER: Fine Elaborazione .... Buona Notte!!!");
            client.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }
    }
}
