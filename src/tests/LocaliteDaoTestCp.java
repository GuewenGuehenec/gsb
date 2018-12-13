package tests;

import java.util.ArrayList;

import modele.Localite;
import modele.dao.*;

public class LocaliteDaoTestCp {

	
	public static void main(String[] args) {
		
		String cp = "56330";
		
		ArrayList<Localite> uneLocalite = LocaliteDao.retournerListeVilleCp(cp);
		
		int nbLigne = LocaliteDao.retournerListeVilleCp(cp).size();
		
		for(int i = 0; i < nbLigne; i++) {
			System.out.println(uneLocalite.get(i).getVille());
		}
		
	}
	
}
