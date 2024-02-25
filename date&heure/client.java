import java.net.*;

public class client {
    public static void main(String[] args) {
        try {
            // Création d'un socket UDP
            DatagramSocket socket = new DatagramSocket();

            // Définition de l'adresse IP et du port du serveur
            InetAddress serveurAdresse = InetAddress.getByName("localhost");
            int portServeur = 1250;

            // Envoi d'un datagramme vide au serveur
            byte[] buffer = new byte[0]; // Datagramme vide
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveurAdresse, portServeur);
            socket.send(packet);

            System.out.println("Datagramme envoyé au serveur.");

            // Attente de la réponse du serveur
            byte[] reponseBuffer = new byte[1024];
            DatagramPacket reponsePacket = new DatagramPacket(reponseBuffer, reponseBuffer.length);
            socket.receive(reponsePacket);

            // Conversion de la réponse en chaîne de caractères et affichage
            String reponse = new String(reponsePacket.getData(), 0, reponsePacket.getLength());
            System.out.println("Réponse du serveur : " + reponse);

            // Fermeture du socket
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
