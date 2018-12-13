package vue;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import modele.Medecin;
import modele.Visite;
import modele.Visiteur;
import modele.dao.VisiteDao;
import service.VisiteService;
import tests.testDateVisite;
import modele.dao.VisiteDao;

public class JIFVisite extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
		
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	
	protected JLabel JLReference;
	protected JLabel JLCodMed;
	protected JLabel JLMatricule;
	protected JLabel JLCommentaire;
	protected JLabel JLDate;
	
	protected static JTextField JTReference;
	protected static JTextField JTCodMed;
	protected static JTextField JTMatricule;
	protected static JTextField JTCommentaire;
	protected static JDateChooser JDDate;
	
	protected JButton JBCreationVisite;
	protected MenuPrincipal fenetreContainer;
	protected Medecin unCodeMed;
	protected Visiteur unMatricule;
	
	protected String data =null;
	
	
	public JIFVisite(String unCodmed) {
		
		setTitle("Création Visite.");
		
		p = new JPanel();
		pBoutons = new JPanel();
		pTexte = new JPanel(new GridLayout(6,2));
		
		JLReference = new JLabel("Reference :");
		JLCodMed = new JLabel("Code médecin :");
		JLMatricule = new JLabel("Matricule :");
		JLCommentaire = new JLabel("Commentaire :");
		JLDate = new JLabel("Date :");
		
		JTReference = new JTextField(20);
		/*JTReference.setMaximumSize(JTReference.getPreferredSize());*/
		JTCodMed = new JTextField();
		JTMatricule = new JTextField();
		JTCommentaire = new JTextField();
		JDDate = new JDateChooser();

		pTexte.add(JLReference);
		pTexte.add(JTReference);
		pTexte.add(JLCodMed);
		pTexte.add(JTCodMed);
		pTexte.add(JLMatricule);
		pTexte.add(JTMatricule);
		pTexte.add(JLCommentaire);
		pTexte.add(JTCommentaire);
		pTexte.add(JLDate);
		pTexte.add(JDDate);
		
		JTCodMed.setText(unCodmed);
		
		JBCreationVisite = new JButton("Créer une visite");
		JBCreationVisite.addActionListener(this);
		/*JBCreationVisite.addActionListener(this); // source d'évenement*/
		pBoutons.add(JBCreationVisite);
        p.add(pTexte);
        p.add(pBoutons);
        
        Container contentPane = getContentPane();
        contentPane.add(p);
        JTReference.getDocument().addDocumentListener(new DocumentListener() {
     	    private void updateData() {
     	        // mise à jour de l'attribut data
     	    	data = null;
     	        data = JTReference.getText();
     	    }
     	 
     	    @Override
     	    public void changedUpdate(DocumentEvent e) {}
     	 
     	    @Override
     	    public void insertUpdate(DocumentEvent e) {
     	        // mise a jour quand du texte est inséré dans le champs
     	        updateData();
     	        if (data.length() >4) {
     	        	VisiteService.VisiteServiceReference(data);
     	        }   
     	    }
     	 
     	    @Override
     	    public void removeUpdate(DocumentEvent e)  {
     	        // mise a jour quand du texte est supprimé dans le champs
     	        updateData();
     	    }     	 
     	});
        JTMatricule.getDocument().addDocumentListener(new DocumentListener() {
     	    private void updateData() {
     	        // mise à jour de l'attribut data
     	    	data = null;
     	        data = JTMatricule.getText();
     	    }
     	    @Override
     	    public void insertUpdate(DocumentEvent e) {
     	        // mise a jour quand du texte est inséré dans le champs
     	        updateData();
     	        if (data.length() >2) {
     	        	VisiteService.VisiteServiceMatricule(data);
     	        }   
     	    }
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
     	});
        
	}
	
public JIFVisite(MenuPrincipal fenetre) {
		
		setTitle("Création Visite.");
		fenetreContainer = fenetre;
		p = new JPanel();
		pBoutons = new JPanel();
		pTexte = new JPanel(new GridLayout(6,2));
		
		JLReference = new JLabel("Reference :");
		JLCodMed = new JLabel("Code médecin :");
		JLMatricule = new JLabel("Matricule :");
		JLCommentaire = new JLabel("Commentaire :");
		JLDate = new JLabel("Date :");
		
		JTReference = new JTextField(20);
		/*JTReference.setMaximumSize(JTReference.getPreferredSize());*/
		JTCodMed = new JTextField();
		JTMatricule = new JTextField();
		JTCommentaire = new JTextField();
		JDDate = new JDateChooser();

		pTexte.add(JLReference);
		pTexte.add(JTReference);
		pTexte.add(JLCodMed);
		pTexte.add(JTCodMed);
		pTexte.add(JLMatricule);
		pTexte.add(JTMatricule);
		pTexte.add(JLCommentaire);
		pTexte.add(JTCommentaire);
		pTexte.add(JLDate);
		pTexte.add(JDDate);
		
		JBCreationVisite = new JButton("Créer une visite");
		JBCreationVisite.addActionListener(this);
		/*JBCreationVisite.addActionListener(this); // source d'évenement*/
		pBoutons.add(JBCreationVisite);
        p.add(pTexte);
        p.add(pBoutons);
        
        Container contentPane = getContentPane();
        contentPane.add(p);
        
        JTReference.getDocument().addDocumentListener(new DocumentListener() {
     	    private void updateData() {
     	        // mise à jour de l'attribut data
     	    	data = null;
     	        data = JTReference.getText();
     	    }
     	 
     	    @Override
     	    public void changedUpdate(DocumentEvent e) {}
     	 
     	    @Override
     	    public void insertUpdate(DocumentEvent e) {
     	        // mise a jour quand du texte est inséré dans le champs
     	        updateData();
     	        if (data.length() >4) {
     	        	VisiteService.VisiteServiceReference(data);
     	        }   
     	    }
     	 
     	    @Override
     	    public void removeUpdate(DocumentEvent e)  {
     	        // mise a jour quand du texte est supprimé dans le champs
     	        updateData();
     	    }     	 
     	});
        
        JTMatricule.getDocument().addDocumentListener(new DocumentListener() {
     	    private void updateData() {
     	        // mise à jour de l'attribut data
     	    	data = null;
     	        data = JTMatricule.getText();
     	    }
     	    @Override
     	    public void insertUpdate(DocumentEvent e) {
     	        // mise a jour quand du texte est inséré dans le champs
     	        updateData();
     	        if (data.length() >2) {
     	        	VisiteService.VisiteServiceMatricule(data);
     	        }   
     	    }
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
     	});
	}
	
    public static void viderText(){// méthode qui permet de vider les zones de texte //
      JTReference.setText("");
      JTCodMed.setText("");        
      JTCommentaire.setText("");
      JTMatricule.setText("");
      JDDate.setDate(null);
      
    }

    @Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object source = evt.getSource();
		if (source == JBCreationVisite) {
		Visite uneVisite = new Visite();
			uneVisite.setReference(JTReference.getText());
			uneVisite.setCommentaire(JTCommentaire.getText());
			uneVisite.setDate(JDDate.getDate());
			Medecin unCodMed = new Medecin();
			unCodMed.setCodeMed(JTCodMed.getText());
			Visiteur unMatricule = new Visiteur();
			unMatricule.setMatricule(JTMatricule.getText());
			VisiteDao.creer(uneVisite, unCodMed, unMatricule);
			
		}
		
		
	}
	
	
}
