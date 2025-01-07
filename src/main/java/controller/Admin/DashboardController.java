package controller.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardController {

    @FXML
    private Label welcomeText;
    @FXML
    private Label studentCountLabel;
    @FXML
    private Label professorCountLabel;
    @FXML
    private Label secretaryCountLabel;
    @FXML
    private Label moduleCountLabel;

    @FXML
    private PieChart modulePieChart;

    @FXML
    private BarChart<String, Number> moduleBarChart;

    @FXML
    private CategoryAxis moduleCategoryAxis;

    @FXML
    private NumberAxis studentCountYAxis;

    private Connection connection;

    public DashboardController() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    @FXML
    private void onStatistiqueButtonClick() {
        loadScene("/vues/ADMIN/AdminPageInit.fxml");
    }

    @FXML
    public void initialize() {
        loadCounts();

    }

    private void loadCounts() {
        try {
            studentCountLabel.setText(getCount("SELECT COUNT(*) FROM etudiants"));
            professorCountLabel.setText(getCount("SELECT COUNT(*) FROM professeurs"));
            secretaryCountLabel.setText(getCount("SELECT COUNT(*) FROM secretaire"));
            moduleCountLabel.setText(getCount("SELECT COUNT(*) FROM modules"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getCount(String query) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            return rs.next() ? String.valueOf(rs.getInt(1)) : "0";
        }
    }



    @FXML
    public void onGestiondesProfesseurButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/GestionProfesseurs/gestionProf.fxml");
    }

    @FXML
    public void onGestiondesSecretaireButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/gestiondessecretaire.fxml");
    }

    @FXML
    public void onGestiondesEtudiantButtonClick(ActionEvent actionEvent) {
        loadScene("/vues/ADMIN/etudiantmanagment.fxml");
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
            BorderPane root = loader.load();
            Stage stage = (Stage) welcomeText.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
