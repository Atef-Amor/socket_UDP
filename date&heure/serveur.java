import java.net.*;
import java.util.Date;

public class serveur {
    public static void main(String[] args) {
        try {
            // Création d'un socket UDP à l'écoute sur le port 1250
            DatagramSocket socket = new DatagramSocket(1250);

            System.out.println("Serveur UDP démarré. En attente de datagrammes...");

            // Boucle infinie pour recevoir les datagrammes
            while (true) {
                // Création d'un paquet pour recevoir des données
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                // Réception du datagramme
                socket.receive(packet);

                // Récupération de l'adresse IP et du port de l'émetteur
                InetAddress adresseClient = packet.getAddress();
                int portClient = packet.getPort();

                // Obtention de la date et de l'heure courante
                String dateHeure = new Date().toString();
                byte[] dateHeureBytes = dateHeure.getBytes();

                // Création d'un nouveau datagramme contenant la date et l'heure courante
                DatagramPacket reponsePacket = new DatagramPacket(dateHeureBytes, dateHeureBytes.length, adresseClient, portClient);

                // Envoi de la réponse au client
                socket.send(reponsePacket);

                System.out.println("Réponse envoyée à " + adresseClient + ":" + portClient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
