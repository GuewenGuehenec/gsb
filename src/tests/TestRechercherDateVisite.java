package tests;

import java.util.ArrayList;

import modele.Medecin;
import modele.dao.*;

@SuppressWarnings("unused")
public class TestRechercherDateVisite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			String codeMed = "m001";

			System.out.println( VisiteDao.retournerDateDerniereVisite(codeMed));
		
		
		
		
	}

}
