package controller.Admin;

import dao.ModuleImp;
import dao.ProfesseurImp;
import dao.UserImp;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Module;
import model.Professeur;
import model.User;

import java.io.IOException;
import java.util.List;

public class GestionProfesseur {

    @FXML
    private TableView<Professeur> professeursTable;

    @FXML
    private TableColumn<Professeur, Integer> idColumn;

    @FXML
    private TableColumn<Professeur, String> nameColumn;

    @FXML
    private TableColumn<Professeur, String> PrenomColumn;

    @FXML
    private TableColumn<Professeur, String> emailColumn;

    @FXML
    private TableColumn<Professeur, String> spColumn;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<Module> moduleComboBox;

    private ProfesseurImp professeurImp = new ProfesseurImp();
    private UserImp userImp = new UserImp();
    private ObservableList<Professeur> professeursList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        spColumn.setCellValueFactory(new PropertyValueFactory<>("specialite"));

        emailColumn.setCellValueFactory(param -> {
            Professeur prof = param.getValue();
            String email = userImp.getemailUser(prof.getId());
            if (email == null) {
                System.out.println("Email non trouvé pour le professeur avec ID: " + prof.getId());
            }
            return new SimpleStringProperty(email != null ? email : "Email non disponible");
        });

        loadModules();
        loadProfesseurs();
    }


    @FXML
    private void loadModules() {
        ModuleImp moduleImp = new ModuleImp();
        List<Module> modules = moduleImp.getAll();

        if (modules != null) {
            moduleComboBox.setItems(FXCollections.observableArrayList(modules));
            moduleComboBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Module module) {
                    return module != null ? module.getNomModule() : "";
                }

                @Override
                public Module fromString(String string) {
                    return moduleComboBox.getItems()
                            .stream()
                            .filter(module -> module.getNomModule().equals(string))
                            .findFirst()
                            .orElse(null);
                }
            });
        }
    }


    private void loadProfesseurs() {
        Module selectedModule = moduleComboBox.getSelectionModel().getSelectedItem();
        if (selectedModule != null) {
            Integer idmodule = selectedModule.getId();
            List<Professeur> professeurs = professeurImp.getProfesseurByIdMod(idmodule);
            if (professeurs != null) {
                professeursList.setAll(professeurs);
                professeursTable.setItems(professeursList);
            } else {
                showWarning("Aucun professeur trouvé", "La liste des professeurs est vide.");
            }
        } else {
            showWarning("Module non sélectionné", "Veuillez sélectionner un module.");
        }
    }

    @FXML
    private void onContactButtonClick() {
        loadProfesseurs();
    }
    @FXML
    private void onAjouterModuleButtonClick() {
        loadProfesseurs();
    }   @FXML
    private void onDeleteButtonClick() {
        loadProfesseurs();
    }   @FXML
    private void onModuleSelected() {
        loadProfesseurs();
    }




    @FXML
    public void onSearchButtonClick() {
     System.out.println("button clicked ");
    }

    @FXML
    public void onAddButtonClick() {
        loadScene("/vues/ADMIN/GestionProfesseurs/AjoutProfesseur.fxml");
    }

    @FXML
    public void onEditButtonClick() {
         System.out.println("button clicked ");
    }


    private void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void onStatistiqueButtonClick() {
        loadScene("/vues/ADMIN/AdminPageInit.fxml");
    }
    @FXML
    public void onGestiondesProfesseurButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/GestionProfesseurs/gestionProf.fxml");
    }
    @FXML
    public void onGestiondesSecretaireButtonClick(ActionEvent actionEvent) {
        System.out.println("button clicked");
    }
    @FXML
    public void onGestiondesEtudiantButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/etudiantmanagment.fxml");

    }
    @FXML
    private void onDesconnected(ActionEvent event) {
        try {
            // Assuming you're trying to load the login screen after logout
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
    @FXML
    private void onGestiondesModulesButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/GestionModule/GestionModule.fxml");
    }
    private void loadScene(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            BorderPane root = loader.load();
            Stage stage = (Stage) professeursTable.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}


