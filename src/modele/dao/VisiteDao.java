package modele.dao;

import java.lang.ref.Reference;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modele.dao.ConnexionMySql;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import modele.Medecin;
import modele.Visite;
import modele.Visiteur;
import vue.JIFVisite;

public class VisiteDao {

	static Connection cnx = null;
	static java.sql.PreparedStatement prepared = null;
	static ResultSet resultat = null;
	
	
	// RETOURNER DATE DERNIERE VISITE
	public static String retournerDateDerniereVisite(String codeMedecin) {
		String dateVisite = null;
		
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select max(DATEVISITE) from visite where CODEMED = '"+codeMedecin+"';");
		try {
			while (reqSelection.next()) {
				dateVisite = reqSelection.getString(1);
				
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select max(DATEVISITE) from visite where CODEMED ='"+codeMedecin+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return dateVisite;
	}
	// RETOURNER COLLECTION VISITE EN RETARD
	public static ArrayList<Medecin> retournerCollectionVisiteEnRetard() {
		ArrayList<Medecin> collectionVisiteEnRetard = new ArrayList<Medecin>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select medecin.codemed, max(ifnull(visite.DATEVISITE,null)) as datevisite from medecin left join visite on medecin.codemed = visite.codemed group by codemed having (max(ifnull(visite.DATEVISITE,0))=0) or (timestampdiff(MONTH, max(ifnull(visite.DATEVISITE,0)), curdate()) > 8 )");
		/*ResultSet reqSelection = ConnexionMySql.execReqSelection("select medecin.codemed, max(visite.DATEVISITE) from medecin left join visite on medecin.codemed = visite.codemed where (timestampdiff(MONTH, datevisite, curdate()) > 8 ) OR (isnull(datevisite) = 1) group by codemed;");*/
		try{
		while (reqSelection.next()) {
			String codeMedecin = reqSelection.getString(1);
		    collectionVisiteEnRetard.add(MedecinDao.rechercher(codeMedecin));		    
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedecinsEnRetard()");
		}
		return collectionVisiteEnRetard;
	}
	
	public static void creer(Visite uneVisite, Medecin unCodeMed, Visiteur unMatricule) {
		String Rsql = "insert into visite (reference, datevisite, commentaire, matricule, codemed) values (? , ? , ? , ? , ? )";
		java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date date = uneVisite.getDate();
		String uneDate = dateFormat.format(date);
			cnx = ConnexionMySql.connecterBd();
		try {
			if (!uneVisite.getCommentaire().equals("") && !uneVisite.getDate().equals("") && !uneVisite.getReference().equals("") && !unCodeMed.getCodeMed().equals("") && !unMatricule.getMatricule().equals("")) {
				prepared = cnx.prepareStatement(Rsql);
				prepared.setString(1, uneVisite.getReference().toString());
				prepared.setString(2, uneDate.toString());
				prepared.setString(3, uneVisite.getCommentaire().toString());
				prepared.setString(4, unMatricule.getMatricule().toString());
				prepared.setString(5, unCodeMed.getCodeMed().toString());
				prepared.execute();
				JOptionPane.showMessageDialog(null," La création à été effectuée !");
				JIFVisite.viderText();
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
	
	public static boolean rechercher(String data) {
		String requete = "select * from visite where REFERENCE ='"+data+"';";
		ResultSet reqSelection = ConnexionMySql.execReqSelection(requete);
		boolean resultReference = false;
		try {
			if (reqSelection.next()) {
				String reference = reqSelection.getString(1);
				if (!(data == reference)) {
					resultReference = true;
				}
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Une erreur est survenue lors de la recherche de la référence ! ","Alert",JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}	
		return resultReference;
		
	}
}
