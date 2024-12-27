package main;

import dao.ProfesseurImp;
import model.Etudiant;
import model.Module;

import java.util.List;

public class main {
    public static void main(String[] args) {

        ProfesseurImp professeurDAO = new ProfesseurImp();

        List<Etudiant> etus = professeurDAO.listeEtudiants(1);

        System.out.println("Liste des Etudiants :");
        for (Etudiant f : etus) {
            System.out.println(f.getNom() + " - " + f.getNom());
        }

        List<Module>modules = professeurDAO.getModuleAssigner(1);
        System.out.println("Liste des Modules :");
        for (Module m : modules) {
            System.out.println(m.getCodeModule() + "-" + m.getNomModule());
        }
    }
}
