package service;

import javax.swing.JOptionPane;

import modele.Visite;
import modele.dao.VisiteDao;
import modele.dao.VisiteurDao;

public class VisiteService {

	public static void VisiteServiceReference(String data) {
		boolean reference = VisiteDao.rechercher(data);
		try {
			if(reference == true) {
				JOptionPane.showMessageDialog(null,"La référence saisie existe déjà !","Alert",JOptionPane.WARNING_MESSAGE);
			}
		}catch(Exception e) {

		}	
	}
	
	public static void VisiteServiceMatricule(String data) {
		boolean matricule = VisiteurDao.rechercher(data);
		try {
			if(matricule == false) {
				JOptionPane.showMessageDialog(null,"Le Matricule saisis n'existe pas !","Alert",JOptionPane.WARNING_MESSAGE);
			}
		}catch(Exception e) {

		}	
	}
	
}
