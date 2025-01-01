package main;

import dao.ModuleImp;
import model.Module;
import model.User;

import java.util.List;

public class test {

    public static void main(String[] args) {
        // Créer une instance de ModuleImp
        ModuleImp moduleImp = new ModuleImp();
        User u =   new User(1,"mehdi@gmail.com","lkjalkjdmaj",3);

        // Créer un nouvel objet Module
        Module nouveauModule = new Module();
        nouveauModule.setNomModule("Programmation Java");
        nouveauModule.setCodeModule("M336");

        // Appeler la méthode AjouterModule pour insérer le module
        //boolean isAdded = moduleImp.AjouterModule(nouveauModule,u);
        boolean iki = moduleImp.deleteModule(16);

        // Afficher le résultat
//        if (isAdded) {
//            System.out.println("Le module a été ajouté avec succès.");
//        } else {
//            System.out.println("L'ajout du module a échoué.");
//        }
    }
}
