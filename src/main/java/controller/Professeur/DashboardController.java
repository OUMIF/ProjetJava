package controller.Professeur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class DashboardController {

    @FXML
    private Label welcomeText;

    @FXML
    private void onStudentManagementButtonClick() {
        try {
            // Charger la page de gestion des étudiants
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/ProfesseurInterface/ProfesseurPageInit.fxml"));
            BorderPane root = loader.load();
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCoursesButtonClick() {
        // Logique pour gérer les modules
        System.out.println("Gestion des modules button clicked");
    }

    @FXML
    private void onReportsButtonClick() {
        // Charger la page des rapports
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/ProfesseurInterface/ListesEtudiants.fxml"));
            BorderPane root = loader.load();
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onSettingsButtonClick() {
        // Logique pour gérer les paramètres ou déconnexion
        System.out.println("Gestion des paramètres button clicked");
    }

    @FXML
    private void onLogoutButtonClick() {
        // Logique pour déconnexion
        System.out.println("Logout button clicked");
    }
}
