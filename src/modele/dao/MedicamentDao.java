package modele.dao;

import modele.Medicament;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MedicamentDao {
	
	public static Medicament rechercher(String codeMedicament) {
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from MEDICAMENT where MED_DEPOTLEGAL ='"+codeMedicament+"'");
		Medicament unMedicament = null;
		try {
			if (reqSelection.next()) {
				//float prix = Float.parseFloat(reqSelection.getString(6));
				unMedicament = new Medicament(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5), 10, reqSelection.getString(7), reqSelection.getString(8) );	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return unMedicament;
	}
	
	public static ArrayList<Medicament> retournerCollectionDesMedicaments(){
		ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select MED_DEPOTLEGAL from MEDICAMENT");
		try{
			while (reqSelection.next()) {
				String codeMedicament = reqSelection.getString(1);
			    collectionDesMedicaments.add(MedicamentDao.rechercher(codeMedicament));
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("erreur retournerCollectionDesMedecins()");
			}
			return collectionDesMedicaments;
	}
	
	public static void ajouterQuantite(String codeVisiteur, String depotLegal, String strQuantite) {
		int quantite = Integer.parseInt(strQuantite);
		int bddQuantite;
		ResultSet reqQuantite = ConnexionMySql.execReqSelection("select quantite from stock where idVisiteur='"+codeVisiteur+"' and idMedicament='"+depotLegal+"' ;");
		try {
			if(reqQuantite.next()){
				bddQuantite = Integer.parseInt(reqQuantite.getString(1));
				int nouvelleQuantite = bddQuantite + quantite;
				ConnexionMySql.execReqMaj("update stock set quantite="+nouvelleQuantite+" where idVisiteur='"+codeVisiteur+"' and idMedicament='"+depotLegal+"' ;");
			}
			else{
				ConnexionMySql.execReqMaj("insert into stock (idVisiteur, idMedicament, quantite) values ('"+codeVisiteur+"','"+depotLegal+"',"+quantite+");");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> getLesCodeVisiteurs(){
		ArrayList<String> collectionCodesVisiteurs = new ArrayList<String>();
		ResultSet codesVisiteurs = ConnexionMySql.execReqSelection("select matricule from visiteur");
		try{
			while (codesVisiteurs.next()) {
				collectionCodesVisiteurs.add(codesVisiteurs.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur getLesCodeVisiteurs()");
		}
		return collectionCodesVisiteurs;
	}
	
	public static ArrayList<String> getLesCodeMedicaments(){
		ArrayList<String> collectionCodesMedicaments = new ArrayList<String>();
		ResultSet codesMedicaments = ConnexionMySql.execReqSelection("select med_depotlegal from medicament");
		try{
			while (codesMedicaments.next()) {
				collectionCodesMedicaments.add(codesMedicaments.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur getLesCodeMedicaments()");
		}
		return collectionCodesMedicaments;
	}
}
