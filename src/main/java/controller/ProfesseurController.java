package controller;

import dao.ProfesseurImp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Etudiant;
import model.Module;

import java.io.IOException;
import java.util.List;

public class ProfesseurController {
    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<Module> moduleComboBox;

    @FXML
    private TableView<Etudiant> studentTable;

    @FXML
    private TableColumn<Etudiant, Integer> idColumn;
    @FXML
    private TableColumn<Etudiant, String> nameColumn;
    @FXML
    private TableColumn<Etudiant, String> emailColumn;
    @FXML
    private TableColumn<Etudiant, String> courseColumn;
    @FXML
    private TableColumn<Etudiant, String> statusColumn;

    private ProfesseurImp professeurImp = new ProfesseurImp();

    @FXML
    public void initialize() {
        // Initialisation des colonnes du tableau des étudiants
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));


        loadModules();
    }

    private void loadModules() {
        List<Module> modules = professeurImp.getModuleAssigner(1);
        if (modules != null && !modules.isEmpty()) {
            moduleComboBox.getItems().addAll(modules);

            // Configurer le StringConverter pour afficher les noms des modules
            moduleComboBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Module module) {
                    return module != null ? module.getNomModule() : "";
                }

                @Override
                public Module fromString(String string) {
                    // Ce cas est rarement utilisé, donc retourner null est acceptable
                    return null;
                }
            });

            // Configurer le CellFactory pour afficher les noms des modules dans la liste déroulante
            moduleComboBox.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(Module module, boolean empty) {
                    super.updateItem(module, empty);
                    if (empty || module == null) {
                        setText(null);
                    } else {
                        setText(module.getNomModule());
                    }
                }
            });
        }
    }






    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }


    @FXML
    protected void onDashboardButtonClick() {

        System.out.println("Dashboard button clicked");
    }

    @FXML
    protected void onStudentManagementButtonClick() {
        try {
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
    protected void onCoursesButtonClick() {
        System.out.println("Gestion des professeurs button clicked");
    }


    @FXML
    protected void onReportsButtonClick() {
        try {
            // Charger le fichier FXML de la nouvelle scène
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/ProfesseurInterface/ListesEtudiants.fxml"));
            BorderPane root = loader.load();  // Changer AnchorPane en BorderPane

            // Récupérer le stage de l'un des composants visibles
            Stage stage = (Stage) welcomeText.getScene().getWindow();  // Si welcomeText est bien attaché à la scène

            // Créer une nouvelle scène avec le contenu chargé
            Scene scene = new Scene(root);

            // Appliquer la nouvelle scène à la fenêtre actuelle
            stage.setScene(scene);
            stage.show(); // Afficher la scène
        } catch (IOException e) {
            e.printStackTrace(); // Gérer les erreurs de chargement du fichier FXML
        }
    }

    @FXML
    protected void onSettingsButtonClick() {
        // Ajouter votre logique ici pour l'action du bouton gestion des modules
        System.out.println("Gestion des modules button clicked");
    }

    @FXML
    protected void onLogoutButtonClick() {
        // Ajouter votre logique ici pour l'action du bouton logout
        System.out.println("Logout button clicked");
    }

    // Exemple d'autres méthodes pour la gestion des étudiants (ajouter, modifier, supprimer)
    @FXML
    protected void onAddStudentButtonClick() {
        // Ajouter votre logique ici pour l'ajout d'un étudiant
        System.out.println("Add student button clicked");
    }

    @FXML
    protected void onEditStudentButtonClick() {
        // Ajouter votre logique ici pour la modification d'un étudiant
        System.out.println("Edit student button clicked");
    }

    @FXML
    protected void onDeleteStudentButtonClick() {
        // Ajouter votre logique ici pour la suppression d'un étudiant
        System.out.println("Delete student button clicked");
    }

    @FXML
    protected void onSearchButtonClick() {
        // Ajouter votre logique ici pour la recherche d'un étudiant
        System.out.println("Search student button clicked");
    }

    @FXML
    protected void  onModuleSelected()  {
        Module selectedModule = moduleComboBox.getValue();
        if (selectedModule != null) {
            // Récupérer les étudiants associés au module sélectionné
            List<Etudiant> students = professeurImp.listeEtudiants(selectedModule.getId());
            // Afficher les étudiants dans le tableau
            studentTable.getItems().clear();
            studentTable.getItems().addAll(students);
        }
    }




}
