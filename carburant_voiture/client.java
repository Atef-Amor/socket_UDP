import java.io.*;
import java.net.*;

public class client{
    public static void main(String[] args) {
        try {
            // Création d'un socket UDP
            DatagramSocket socket = new DatagramSocket();
            
            // Définition de l'adresse du serveur et du port
            InetAddress serveurAdresse = InetAddress.getByName("localhost");
            int port = 9999;

            // Création d'un objet voiture
            voiture car = new voiture("Essence", "golf");
            
            // Envoi de l'objet voiture au serveur
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(car);
            
            byte[] data = outputStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(data, data.length, serveurAdresse, port);
            socket.send(packet);

            // Affichage de la quantité de carburant envoyée
            System.out.println("Voiture envoyée au serveur : " + car.getCarburant() + " litres de carburant");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
