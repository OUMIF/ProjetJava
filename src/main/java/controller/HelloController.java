package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label welcomeText;

    // Méthode pour gérer l'action sur le bouton "Hello"
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    // Exemple de méthode pour d'autres boutons
    @FXML
    protected void onDashboardButtonClick() {
        // Ajouter votre logique ici pour l'action du bouton Dashboard
        System.out.println("Dashboard button clicked");
    }

    @FXML
    protected void onStudentManagementButtonClick() {
        // Ajouter votre logique ici pour l'action du bouton gestion des étudiants
        System.out.println("Gestion des étudiants button clicked");
    }

    @FXML
    protected void onCoursesButtonClick() {
        // Ajouter votre logique ici pour l'action du bouton gestion des professeurs
        System.out.println("Gestion des professeurs button clicked");
    }

    @FXML
    protected void onReportsButtonClick() {
        // Ajouter votre logique ici pour l'action du bouton gestion des secrétaires
        System.out.println("Gestion des secrétaires button clicked");
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
}
