package controller.Admin;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.*;






public class EnvoyerMessageController {

    @FXML
    private TextArea champMessage;

    @FXML
    private TextField champEmailDestinataire;

    @FXML
    private Button sendButton;

    @FXML
    private Button cancelButton;

    private static final String EMAILJS_SERVICE_ID = "service_h410d6b";
    private static final String EMAILJS_TEMPLATE_ID = "template_bg32h3q";
    private static final String EMAILJS_USER_ID = "skeQt12FrPchtW2We";




    @FXML
    private void envoyerMessage() {
        String message = champMessage.getText();
        String nomDestinataire = champEmailDestinataire.getText();
        String emailExpediteur = "oumifad49@gmail.com"; // Email de l'expéditeur

        if (message.isEmpty()) {
            showAlert(AlertType.WARNING, "Champ de message vide", "Veuillez entrer un message avant d'envoyer.");
        } else if (nomDestinataire.isEmpty()) {
            showAlert(AlertType.WARNING, "Nom du destinataire vide", "Veuillez entrer un nom pour le destinataire.");
        } else if (emailExpediteur.isEmpty()) {
            showAlert(AlertType.WARNING, "Email de l'expéditeur vide", "Veuillez entrer votre email.");
        } else {
            try {
                // URL de l'API EmailJS
                URL url = new URL("https://api.emailjs.com/api/v1.0/email/send");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Authorization", "Bearer " + "WFEgUGVcXRqRqetkUvGhw"); // Inclure la clé privée ici

                String EMAILJS_USER_ID = "5dga8E5uMM7IgtlQI"; // Remplacez par votre clé publique EmailJS
                String EMAILJS_SERVICE_ID = "service_h410d6b"; // Remplacez par votre service ID
                String EMAILJS_TEMPLATE_ID = "template_bg32h3q"; // Remplacez par votre template ID

                // Échapper les caractères spéciaux dans le message
                String messageEscaped = message.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");

                // Créer le corps de la requête JSON
                String jsonInputString = String.format(
                        "{\"service_id\":\"%s\",\"template_id\":\"%s\",\"user_id\":\"%s\",\"template_params\":{\"to_email\":\"%s\",\"from_name\":\"%s\",\"from_email\":\"%s\",\"message\":\"%s\"}}",
                        EMAILJS_SERVICE_ID, EMAILJS_TEMPLATE_ID, EMAILJS_USER_ID, nomDestinataire, emailExpediteur, emailExpediteur, messageEscaped
                );

                // Écrire les données dans la requête
                try (OutputStream os = connection.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                // Lire la réponse de l'API
                int responseCode = connection.getResponseCode();
                InputStream responseStream = (responseCode == HttpURLConnection.HTTP_OK)
                        ? connection.getInputStream()
                        : connection.getErrorStream();

                String responseMessage = new String(responseStream.readAllBytes(), StandardCharsets.UTF_8);

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    showAlert(AlertType.INFORMATION, "Message envoyé", "Votre message a été envoyé avec succès.");
                    champMessage.clear();
                } else {
                    showAlert(AlertType.ERROR, "Erreur d'envoi", "Une erreur est survenue lors de l'envoi de l'email. Détails : " + responseMessage);
                }

            } catch (Exception e) {
                e.printStackTrace();
                showAlert(AlertType.ERROR, "Erreur d'envoi", "Une erreur est survenue lors de l'envoi de l'email.");
            }
        }
    }

    public void setEmailDestinataire(String email) {
        champEmailDestinataire.setText(email);
    }

    @FXML
    private void onCancelButtonClick() {
        champMessage.clear();
    }

    @FXML
    private void onMouseEnterSendButton(MouseEvent event) {
        sendButton.setStyle("-fx-background-color: #45A049;");
    }

    @FXML
    private void onMouseExitSendButton(MouseEvent event) {
        sendButton.setStyle("-fx-background-color: #4CAF50;");
    }

    @FXML
    private void onMouseEnterCancelButton(MouseEvent event) {
        cancelButton.setStyle("-fx-background-color: #F44336;");
    }

    @FXML
    private void onMouseExitCancelButton(MouseEvent event) {
        cancelButton.setStyle("-fx-background-color: #f44336;");
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onDesconnected() {
        System.out.println("Utilisateur déconnecté.");
    }

    @FXML
    private void onStatistiqueButtonClick() {
        System.out.println("Statistiques");
    }

    @FXML
    private void onGestiondesProfesseurButtonClick() {
        System.out.println("Gestion des Professeurs");
    }

    @FXML
    private void onGestiondesSecretaireButtonClick() {
        System.out.println("Gestion des Secrétaires");
    }

    @FXML
    private void onGestiondesEtudiantButtonClick() {
        System.out.println("Gestion des Étudiants");
    }

    @FXML
    private void onGestiondesModulesButtonClick() {
        System.out.println("Gestion des Modules");
    }
}
