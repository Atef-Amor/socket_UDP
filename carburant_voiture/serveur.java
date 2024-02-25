import java.io.*;
import java.net.*;


public class serveur {
    public static void main(String[] args) {
        try {
            // Création d'un socket UDP à l'écoute sur le port 9999
            DatagramSocket socket = new DatagramSocket(9999);
            byte[] buffer = new byte[1024];

            while (true) {
                // Préparation d'un paquet pour recevoir des données
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                
                // Réception du paquet
                socket.receive(packet);

                // Lecture de l'objet voiture depuis le flux reçu
                ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
                voiture car = (voiture) inputStream.readObject();

                // Modification de la quantité de carburant
                car.setCarburant(60); // Exemple: fixer la quantité de carburant à 60 litres

                // Affichage de la quantité de carburant reçue
                System.out.println("Voiture reçue du client : " + car.getCarburant() + " litres de carburant");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

