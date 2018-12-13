package tests;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import modele.Medecin;
import modele.Visite;
import modele.Visiteur;
import modele.dao.VisiteDao;
import vue.JIFVisite;

public class testDateVisite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	/*	Visite laVisite = JIFVisite.getText(laVisite);*/
		
		Date date = new Date("2018/12/12 00:00:00");
		
		Instant instant = date.toInstant();
		
		ZoneId zoneId = ZoneId.of ( "America/Montreal" );
		ZonedDateTime zdt = ZonedDateTime.ofInstant ( instant , zoneId );
		LocalDate localDate = zdt.toLocalDate();
		
		java.sql.Date datesql = java.sql.Date.valueOf( localDate );
		
		Visite uneNouvelleVisite;
		uneNouvelleVisite = new Visite();
		uneNouvelleVisite.setCommentaire("test");
		uneNouvelleVisite.setDate(datesql);
		uneNouvelleVisite.setReference("test");
		Medecin unMedecin = new Medecin();
		unMedecin.setCodeMed("m001");
		Visiteur unVisiteur = new Visiteur();
		unVisiteur.setMatricule("b59");
		VisiteDao.creer(uneNouvelleVisite, unMedecin, unVisiteur);
	}

}
