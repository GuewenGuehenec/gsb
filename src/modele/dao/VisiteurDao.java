package modele.dao;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class VisiteurDao {

	public static boolean rechercher(String data) {
		String requete = "select matricule from visiteur where matricule ='"+data+"';";
		ResultSet reqSelection = ConnexionMySql.execReqSelection(requete);
		boolean resultMatricule = false;
		try {
			if (reqSelection.next()) {
				String matricule = reqSelection.getString(1);
				if (data != matricule) {
					resultMatricule = true;
				}
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la recherche du Matricule ! ","Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}	
		return resultMatricule;
		
	}
	
}
