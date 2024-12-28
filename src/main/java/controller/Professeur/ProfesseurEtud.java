package controller.Professeur;
import dao.ProfesseurImp;
import dao.UserImp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Etudiant;
import model.Module;

import java.io.IOException;
import java.util.List;

public class ProfesseurEtud {

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
    private TableColumn<Etudiant, String> PrenomColumn;

    private ProfesseurImp professeurImp = new ProfesseurImp();
    private UserImp usImp = new UserImp();

    @FXML
    public void initialize() {
        // Initialisation des colonnes du tableau des étudiants
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));  // Assurez-vous que le getter s'appelle getNom()
        PrenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));  // Assurez-vous que le getter s'appelle getPrenom()

        loadModules();
    }

    private void loadModules() {
        List<Module> modules = professeurImp.getModuleAssigner(1);
        if (modules != null && !modules.isEmpty()) {
            moduleComboBox.getItems().addAll(modules);


            moduleComboBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Module module) {
                    return module != null ? module.getNomModule() : "";
                }

                @Override
                public Module fromString(String string) {

                    return null;
                }
            });


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
        // Logique pour revenir au tableau de bord
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/ProfesseurInterface/Dashboard.fxml"));
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
        System.out.println("Gestion des modules button clicked");
    }

    @FXML
    protected void onReportsButtonClick() {
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
    protected void onSettingsButtonClick() {
        System.out.println("Gestion des modules button clicked");
    }

    @FXML
    protected void onLogoutButtonClick() {
        System.out.println("Logout button clicked");
    }

    @FXML
    protected void onAddStudentButtonClick() {
        System.out.println("Add student button clicked");
    }

    @FXML
    protected void onEditStudentButtonClick() {
        System.out.println("Edit student button clicked");
    }

    @FXML
    protected void onDeleteStudentButtonClick() {
        System.out.println("Delete student button clicked");
    }

    @FXML
    protected void onSearchButtonClick() {
        System.out.println("Search student button clicked");
    }

    @FXML
    protected void onModuleSelected() {

        Module selectedModule = moduleComboBox.getValue();
        if (selectedModule != null) {

            int moduleId = selectedModule.getId();
            List<Etudiant> students = professeurImp.listeEtudiants(moduleId);
            System.out.println("Étudiants du module : " + students);


            studentTable.getItems().clear();

            // Ajouter les étudiants à la TableView
            studentTable.getItems().addAll(students);


            for (Etudiant etudiant : students) {
                Integer id = etudiant.getId();
                String studentName = etudiant.getNom();
                String studentPrenom = etudiant.getPrenom();
                System.out.println(studentName + " " + studentPrenom + " - " + studentPrenom);
            }
        }
    }





}