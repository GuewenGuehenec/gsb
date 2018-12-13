/*
 * Créé le 22 mars 2012
 *
 * TODO Pour changer le modèle de ce fichier généré, allez à :
 * Fenêtre - Préférences - Java - Style de code - Modèles de code
 */
package vue;

import modele.Localite;
import modele.Medecin;
import modele.dao.LocaliteDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

public class JIFMedecin extends JInternalFrame implements ActionListener  {
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel p;  
	protected JPanel pTexte;
	protected JPanel pBoutons;
	

	protected JLabel JLcode;
	protected JLabel JLnom;
	protected JLabel JLprenom;
	protected JLabel JLadresse;
	protected JLabel JLcp;
    protected JLabel JLville;
    protected JLabel JLtelephone;
    protected JLabel JLpotentiel;
    protected JLabel JLspecialite;
    
	protected static JTextField JTcode;
	protected static JTextField JTnom;
	protected static JTextField JTprenom;
	protected static JTextField JTadresse;
	protected static JTextField JTcp;
    protected static JTextField JTville;
    protected static JFormattedTextField JTtelephone;
    protected static JTextField JTpotentiel;
    protected static JTextField JTspecialite;
    
    @SuppressWarnings("rawtypes")
	protected static JComboBox JCBcp;
    protected int dataLength = 0;
    protected String data;
    protected String data1;
	
    @SuppressWarnings("rawtypes")
	public JIFMedecin() {    	
    	p = new JPanel();  // panneau principal de la fenêtre
        pBoutons = new JPanel();    // panneau supportant les boutons
        pTexte = new JPanel(new GridLayout(10,2));
        MaskFormatter mf1 = null;
		try {
			mf1 = new MaskFormatter("##-##-##-##-##");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
    	
    	 JLcode = new JLabel("Code");
         JLnom = new JLabel("Nom");
         JLprenom = new JLabel("Prénom");
         JLadresse = new JLabel("Adresse rue");
         JLcp = new JLabel("Code postal");
         JLville = new JLabel("Ville");
         JLtelephone = new JLabel("Téléphone");
         JLpotentiel = new JLabel("potentiel");
         JLspecialite = new JLabel("ASpecialite");
         
         Dimension  uneDimension = new Dimension();
         uneDimension.setSize(220, 25);
         
         JTcode = new JTextField();
         JTcode.setPreferredSize(uneDimension.getSize());
         JTcode.setMaximumSize(uneDimension.getSize());
         JTnom = new JTextField(20);
         JTprenom = new JTextField();
         JTadresse = new JTextField();    
         JTcp = new JTextField();
         JTville = new JTextField();
         JTtelephone = new JFormattedTextField(mf1);
         JTpotentiel = new JTextField();
         JTspecialite = new JTextField();
         JCBcp = new JComboBox();
         JCBcp.setPreferredSize(uneDimension);
         
         
         pTexte.add(JLcode);
         pTexte.add(JTcode);
         pTexte.add(JLnom);
         pTexte.add(JTnom);
         pTexte.add(JLprenom);
         pTexte.add(JTprenom);
         pTexte.add(JLtelephone);
         pTexte.add(JTtelephone);
         pTexte.add(JLpotentiel);
         pTexte.add(JTpotentiel);
         pTexte.add(JLspecialite);
         pTexte.add(JTspecialite);
         pTexte.add(JLadresse);
         pTexte.add(JTadresse);
         pTexte.add(JLcp);
         pTexte.add(JTcp);
         pTexte.add(JLville);
         pTexte.add(JTville);
         pTexte.add(JCBcp).setVisible(false);
         JTville.setEditable(false);
        // mise en forme de la fenêtre

         p.add(pTexte);
         p.add(pBoutons);
         Container contentPane = getContentPane();
         contentPane.add(p);
	}
    
    public void remplirText(Medecin unMedecin) 	
    {  // méthode qui permet de remplir les zones de texte à partir des valeurs passées en paramètres
        JTcode.setText(unMedecin.getCodeMed());        
        JTnom.setText(unMedecin.getNom());
        JTprenom.setText(unMedecin.getPrenom());
        JTadresse.setText(unMedecin.getAdresse());    
        JTcp.setText(unMedecin.getLaLocalite().getCodePostal());
        JTville.setText(unMedecin.getVille());
        JTtelephone.setText(unMedecin.getTelephone());
        JTpotentiel.setText(unMedecin.getPotentiel());
        JTspecialite.setText(unMedecin.getSpecialite());
     }
     
      public static void viderText() 	
    {  // méthode qui permet de vider les zones de texte 
        JTcode.setText("");        
        JTnom.setText("");
        JTprenom.setText("");
        JTadresse.setText("");    
        JTcp.setText("");
        JTville.setText("");
        JTtelephone.setText("");
        JTpotentiel.setText("");
        JTspecialite.setText("");
        JCBcp.removeAllItems();
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
