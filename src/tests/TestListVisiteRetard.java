package tests;

import modele.dao.*;
import modele.*;

import java.util.ArrayList;

import modele.*;
public class TestListVisiteRetard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Medecin> listVisit = VisiteDao.retournerCollectionVisiteEnRetard();
		
		for (int i=0; i<listVisit.size();i++) {
			System.out.println(listVisit.get(i).getCodeMed());
			System.out.println(listVisit.get(i));
		}
		
		
	}

}
