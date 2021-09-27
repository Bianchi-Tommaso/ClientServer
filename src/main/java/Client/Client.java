package Client;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client
{
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket mioSocket;
    BufferedReader tastiera;
    String stringUtente;
    String stringRicevutaDalServer;
    DataOutput outVersoServer;
    BufferedReader inDalServer;

    public Socket Connetti()
    {
        System.out.println("2 CLIENT partito in esecuzione...");

        try
        {
            tastiera = new BufferedReader(new InputStreamReader(System.in));

            mioSocket = new Socket(nomeServer, portaServer);

            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));

        }
        catch(UnknownHostException e)
        {
            System.out.print("Host Sconosciuto");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }

        return mioSocket;
    }

    public void Comunica()
    {
        try
        {
            System.out.println("4... Inserisci La Stringa Da Trasmettere Al Server " + '\n');
            stringUtente = tastiera.readLine();

            System.out.println("5... Invio La Stringa Al Server E Attendo");
            outVersoServer.writeBytes(stringUtente + '\n');

            System.out.println("8 Ho La Risposta Dal Server... ");
            stringRicevutaDalServer = inDalServer.readLine();
            System.out.println(stringRicevutaDalServer);
            System.out.println("9 CLIENT: Termina Elaborazione E Chiude Connessione");
            mioSocket.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore Durante La Connessione");
            System.exit(1);
        }
    }

}