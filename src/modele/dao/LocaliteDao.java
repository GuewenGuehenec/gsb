/*
 * Créé le 22 févr. 2015
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package modele.dao;

import modele.Localite;
import modele.Medecin;

import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
 * @author Isabelle
 * 22 févr. 2015
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class LocaliteDao {
	
	public static Localite rechercher(String codeLocalite){
		Localite uneLocalite=null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from LOCALITE where CODEPOSTAL='"+codeLocalite+"'");
		try {
			if (reqSelection.next()) {
				uneLocalite = new Localite(reqSelection.getString(2), reqSelection.getString(3));	
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from LOCALITE where CODEPOSTAL='"+codeLocalite+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return uneLocalite;
	}
	
	public static ArrayList<Localite> retournerListeVilleCp(String unCodeLocalite){
		ArrayList<Localite> collectionDesLocaliteCp = new ArrayList<Localite>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from LOCALITE where CODEPOSTAL='"+unCodeLocalite+"'");
		try {
			while (reqSelection.next()) {
				Localite ville = new Localite();
				ville.setVille(reqSelection.getString(3));
				collectionDesLocaliteCp.add(ville);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return collectionDesLocaliteCp;		
	}
}

