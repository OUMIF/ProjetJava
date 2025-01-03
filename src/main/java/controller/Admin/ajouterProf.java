package controller.Admin;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class ajouterProf {

    @FXML
    private void onStatistiqueButtonClick() {
        loadScene("/vues/ADMIN/AdminPageInit.fxml");
    }

    @FXML
    public void onGestiondesProfesseurButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/GestionProfesseur.fxml");
    }

    @FXML
    public void onGestiondesSecretaireButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/GestionSecretaire.fxml");
    }

    @FXML
    public void onGestiondesEtudiantButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/GestionEtudiant.fxml");
    }

    @FXML
    private void onGestiondesModulesButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/GestionModule/GestionModule.fxml");
    }

    @FXML
    private void onDesconnected(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) Stage.getWindows().filtered(window -> window.isShowing()).get(0);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
