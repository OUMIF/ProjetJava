package controller.Admin;

import dao.ModuleImp;
import model.Module;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import model.User;
import util.Session;

import java.awt.*;

public class ajouterModule {


    @FXML
    private TextField nomModule;

    @FXML
    private TextField codeModule;

    @FXML
    private Button addModule;

    private ModuleImp moduleImp = new ModuleImp();
    private User ue = Session.getCurrentUser();
    private Module m = new Module();

    @FXML
    private void onSubmitForm(ActionEvent event) {
        // Assurer que les champs sont remplis
        m.setNomModule(nomModule.getText());
        m.setCodeModule(codeModule.getText());

        if (nomModule.getText().isEmpty() || codeModule.getText().isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis!", Alert.AlertType.ERROR);
            return;
        }

        // Ajouter le module
        if (moduleImp.AjouterModule(m, ue)) {
            showAlert("Succès", "Module ajouté avec succès!", Alert.AlertType.INFORMATION);
            clearFields();  // Effacer les champs après l'ajout
        } else {
            showAlert("Erreur", "Une erreur est survenue lors de l'ajout du module.", Alert.AlertType.ERROR);
        }
    }
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        nomModule.clear();
        codeModule.clear();
    }


    public void onStatistiqueButtonClick(ActionEvent actionEvent) {
    }

    public void onGestiondesProfesseurButtonClick(ActionEvent actionEvent) {
    }

    public void onGestiondesSecretaireButtonClick(ActionEvent actionEvent) {
    }

    public void onGestiondesEtudiantButtonClick(ActionEvent actionEvent) {
    }

    public void onGestiondesModulesButtonClick(ActionEvent actionEvent) {
    }

    public void onDesconnected(ActionEvent actionEvent) {
    }
}
