package vue;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

import modele.Localite;
import modele.Medecin;
import modele.dao.LocaliteDao;
import modele.dao.MedecinDao;
import service.MedecinService;
import service.VisiteService;

public class JIFMedecinCreation extends JIFMedecin implements ActionListener{
	
	private JButton JBCreationMedecins = new JButton("Ajouter un Médecin");
	
	public JIFMedecinCreation(MenuPrincipal menuPrincipal) throws ParseException {
		// TODO Auto-generated constructor stub
		super();
		JBCreationMedecins.addActionListener(this); // source d'évenement
		p.add(JBCreationMedecins);
    	setTitle("Ajout médecin");
		Container contentPane = getContentPane();
		contentPane.add(p);
		JTcp.getDocument().addDocumentListener(new DocumentListener() {
     	    private void updateData() {
     	        // mise à jour de l'attribut data
     	    	data = JTcp.getText();

     	    }
     	    @Override
     	    public void changedUpdate(DocumentEvent e) {}
     	 
     	    @SuppressWarnings("unchecked")
			@Override
     	    public void insertUpdate(DocumentEvent e) {
     	        // mise a jour quand du texte est inséré dans le champs
     	        updateData();
     	        if (data.length() == 5) {
     	        	data1 = data;
         	        ArrayList<Localite> uneLocalite = LocaliteDao.retournerListeVilleCp(data);
         	        int nbLigne = LocaliteDao.retournerListeVilleCp(data).size();
         	        try {
         	        	if (nbLigne ==1) {
         	        		JTville.setText(uneLocalite.get(0).getVille());
         	        		JTville.setEditable(false);
         	        	}else if (nbLigne > 1) {
         	                JCBcp.setVisible(true);
         	                JTville.setVisible(false);
         	                pTexte.remove(JTville);
         	                for(int i = 0; i < nbLigne; i++) {
         	                	String laLocalite = uneLocalite.get(i).getVille();
         	                	JCBcp.addItem(laLocalite);
         	                }
         	        	}else {
         	        		JTville.setText(uneLocalite.get(0).getVille());
         	        	}
         	        }catch(Exception e1)
         	        {
         	        	JOptionPane.showMessageDialog(null,"Code postal inconnu !","Alert",JOptionPane.WARNING_MESSAGE);
         	        }
     	        }
     	        if (data.length() > 5) {
     	        	JOptionPane.showMessageDialog(null,"Code trop long !","Alert",JOptionPane.WARNING_MESSAGE);
     	        }
     	    }
     	    @Override
     	    public void removeUpdate(DocumentEvent e)  {
     	        // mise a jour quand du texte est supprimé dans le champs
     	        updateData();
     	        if (data.length() < 5) {
     	        	JTville.setText("");
 	                JCBcp.setVisible(false);
 	                pTexte.add(JTville);
 	                pTexte.remove(JCBcp);
 	                pTexte.add(JCBcp);
 	                JTville.setVisible(true);
 	                JCBcp.removeAllItems();
 	                JTville.setEditable(false);     
     	        }
     	    }
     	 
     	});
        JTcode.getDocument().addDocumentListener(new DocumentListener() {
     	    private void updateData() {
     	        // mise à jour de l'attribut data
     	    	data = null;
     	        data = JTcode.getText();
     	    }
     	 
     	    @Override
     	    public void changedUpdate(DocumentEvent e) {}
     	 
     	    @Override
     	    public void insertUpdate(DocumentEvent e) {
     	        // mise a jour quand du texte est inséré dans le champs
     	        updateData();
     	        if (data.length() > 3) {
     	        	MedecinService.VisiteServiceCodeMed(data);
     	        }
     	        
     	    }
     	 
     	    @Override
     	    public void removeUpdate(DocumentEvent e)  {
     	        // mise a jour quand du texte est supprimé dans le champs
     	        updateData();
     	    }
     	 
     	});
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object source = evt.getSource();
		if (source == JBCreationMedecins) {
			Medecin unMedecin = new Medecin();
			unMedecin.setCodeMed(JTcode.getText());
			unMedecin.setNom(JTnom.getText().toUpperCase());
			unMedecin.setPrenom(JTprenom.getText().toUpperCase());
			unMedecin.setTelephone(JTtelephone.getText());
			unMedecin.setPotentiel(JTpotentiel.getText());
			unMedecin.setSpecialite(JTspecialite.getText());
			unMedecin.setAdresse(JTadresse.getText());
			Localite uneLocalite = new Localite();
			uneLocalite.setCodePostal(JTcp.getText());
			ArrayList<Localite> uneLocalite1 = LocaliteDao.retournerListeVilleCp(JTcp.getText());
			int nbLigne = LocaliteDao.retournerListeVilleCp(JTcp.getText()).size();
			if(nbLigne == 1) {
				uneLocalite.setVille(JTville.getText());
			}else if (nbLigne > 1) {
				uneLocalite.setVille(JCBcp.getSelectedItem().toString());
			}else if (JTcp.getText().length() > 5) {
				JOptionPane.showMessageDialog(null,"Code postal trop long !","Alert",JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null,"Veuillez renseigner un Code Postal !","Alert",JOptionPane.WARNING_MESSAGE);
			}
			MedecinDao.creer(unMedecin, uneLocalite);
		}
	}

}
