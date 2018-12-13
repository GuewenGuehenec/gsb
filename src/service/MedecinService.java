/*
 * Créé le 23 févr. 2015
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package service;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import modele.Medecin;
import modele.dao.ConnexionMySql;
import modele.dao.MedecinDao;
import modele.dao.VisiteurDao;

/**
 * @author Isabelle
 * 23 févr. 2015
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class MedecinService {
	
	public static Medecin rechercherMedecin(String unCodeMedecin){
		Medecin unMedecin = null;
		try{
		if (unCodeMedecin==null) {
            throw new Exception("Donnée obligatoire : code");
        }
		unMedecin = MedecinDao.rechercher(unCodeMedecin);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unMedecin;
	}

	public static void VisiteServiceCodeMed(String data) {
		boolean codMed = MedecinDao.rechercherCodMed(data);
		try {
			if(codMed == true) {
				JOptionPane.showMessageDialog(null,"Le Code médecin existe déjà !","Alert",JOptionPane.WARNING_MESSAGE);
			}
		}catch(Exception e) {

		}	
		
	}
	
		
}

