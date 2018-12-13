/*
 * Créé le 22 févr. 2015
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package modele.dao;

import modele.Localite;
import modele.Medecin;
import modele.Visite;
import modele.Visiteur;
import vue.JIFMedecin;
import vue.JIFVisite;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;



/**
 * @author Isabelle
 * 22 févr. 2015
 * TODO Pour changer le modèle de ce commentaire de type généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
public class MedecinDao {
	
	static Connection cnx = null;
	static java.sql.PreparedStatement prepared = null;
	static ResultSet resultat = null;
	
	public static Medecin rechercher(String codeMedecin){
		Medecin unMedecin=null;
		Localite uneLocalite= null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
		try {
			if (reqSelection.next()) {
				uneLocalite = LocaliteDao.rechercher(reqSelection.getString(5));
				unMedecin = new Medecin(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), uneLocalite, reqSelection.getString(6), reqSelection.getString(7), reqSelection.getString(8), reqSelection.getString(9));
			}
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from MEDECIN where CODEMED ='"+codeMedecin+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return unMedecin;	
	}
	public static ArrayList<Medecin> retournerCollectionDesMedecins(){
		ArrayList<Medecin> collectionDesMedecins = new ArrayList<Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN");
		try{
		while (reqSelection.next()) {
			String codeMedecin = reqSelection.getString(1);
		    collectionDesMedecins.add(MedecinDao.rechercher(codeMedecin));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedecins()");
		}
		return collectionDesMedecins;
	}
	
	public static HashMap<String,Medecin> retournerDictionnaireDesMedecins(){
		HashMap<String, Medecin> diccoDesMedecins = new HashMap<String, Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select CODEMED from MEDECIN order by codemed asc");
		try{
		while (reqSelection.next()) {
			String codeMedecin = reqSelection.getString(1);
		    diccoDesMedecins.put(codeMedecin, MedecinDao.rechercher(codeMedecin));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerDiccoDesMedecins()");
		}
		return diccoDesMedecins;
	}
	
	public static void creer(Medecin unMedecin, Localite uneLocalite) {
		String Rsql = "insert into medecin (codemed, nom, prenom, adresse, codepostal, ville, telephone, potentiel, specialite) values (? , ? , ? , ? , ?, ?, ?, ?, ?)";
			cnx = ConnexionMySql.connecterBd();
		try {
			if (!unMedecin.getCodeMed().equals("") && !unMedecin.getNom().equals("") && !unMedecin.getPrenom().equals("") && !unMedecin.getAdresse().equals("") && !uneLocalite.getCodePostal().equals("") && !uneLocalite.getVille().equals("") && !unMedecin.getTelephone().equals("") && !unMedecin.getSpecialite().equals("")) {
				prepared = cnx.prepareStatement(Rsql);
				prepared.setString(1, unMedecin.getCodeMed().toString());
				prepared.setString(2, unMedecin.getNom().toString());
				prepared.setString(3, unMedecin.getPrenom().toString());
				prepared.setString(4, unMedecin.getAdresse().toString());
				prepared.setString(5, uneLocalite.getCodePostal().toString());
				prepared.setString(6, uneLocalite.getVille().toString());
				prepared.setString(7, unMedecin.getTelephone().toString());
				prepared.setString(8, unMedecin.getPotentiel().toString());
				prepared.setString(9, unMedecin.getSpecialite().toString());
				prepared.execute();
				JOptionPane.showMessageDialog(null," La création à été effectuée !");
				JIFMedecin.viderText();
			}else
			{
				JOptionPane.showMessageDialog(null," Tout les Champs ne sont pas remplis ! ","Alert",JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(null," La Création à échouée ! ","Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}		
	}
	public static boolean rechercherCodMed(String data) {
		String requete = "select codemed from medecin where codemed ='"+data+"';";
		ResultSet reqSelection = ConnexionMySql.execReqSelection(requete);
		boolean ResultCodMed = false;
		try {
			if (reqSelection.next()) {
				String matricule = reqSelection.getString(1);
				if (data.equals(matricule)) {
					ResultCodMed = true;
				}
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la recherche du Matricule ! ","Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}	
		return ResultCodMed;

		
	}
	

}
