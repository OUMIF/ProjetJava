module org.example.projetjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.dlsc.formsfx;

    opens org.example.projetjava to javafx.fxml;  // Ouvre le package principal pour javafx.fxml
    opens vues to javafx.fxml;  // Ouvre le package vues pour javafx.fxml
    exports org.example.projetjava;  // Exporte le package principal
    exports vues;  // Exporte le package vues
}
