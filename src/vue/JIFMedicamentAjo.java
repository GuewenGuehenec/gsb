package vue;

import modele.Medicament;
import modele.dao.MedicamentDao;
import service.MedicamentService;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JIFMedicamentAjo extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel p;
	private JPanel pTexte;
	private GridLayout tableau;
	
	private JLabel JLplaceHolder0;
	private JLabel JLplaceHolder1;
	private JLabel JLplaceHolder2;
	
	private JLabel JLcodeVisiteur;
	private JLabel JLdepotLegal;
	private JLabel JLquantite;
	
	private ArrayList<String> codesVisiteurs;
	private ArrayList<String> codesMedicaments;
	
	private JComboBox JCBcodeVisiteur;
	private JComboBox JCBdepotLegal;
	private JTextField JTquantite;
	
	private JButton JBajouter;
	
	public JIFMedicamentAjo() {
		setTitle("Ajout échantillons pour un visiteur");
		p = new JPanel();
		tableau = new GridLayout(5,2);
		tableau.setVgap(20);
		pTexte = new JPanel(tableau);
		
		JLplaceHolder0 = new JLabel("PlaceHolder");
		JLplaceHolder1 = new JLabel("PlaceHolder");
		JLplaceHolder2 = new JLabel("PlaceHolder");
		JLplaceHolder0.setVisible(false);
		JLplaceHolder1.setVisible(false);
		JLplaceHolder2.setVisible(false);
		
		JLcodeVisiteur = new JLabel("Code visiteur");
		JLdepotLegal = new JLabel("Dépôt légal");
		JLquantite = new JLabel("Quantité");
		
		JCBcodeVisiteur = new JComboBox();
		JCBdepotLegal = new JComboBox();
		JTquantite = new JTextField(20);
		
		codesVisiteurs = MedicamentDao.getLesCodeVisiteurs();
		for(String code : codesVisiteurs) {
			JCBcodeVisiteur.addItem(code);
		}
		
		codesMedicaments = MedicamentDao.getLesCodeMedicaments();
		for(String code : codesMedicaments) {
			JCBdepotLegal.addItem(code);
		}
		
		JBajouter = new JButton("Ajouter quantité");
		JBajouter.addActionListener(this);
		
		pTexte.add(JLplaceHolder0);
		pTexte.add(JLplaceHolder1);
		pTexte.add(JLcodeVisiteur);
		pTexte.add(JCBcodeVisiteur);
		pTexte.add(JLdepotLegal);
		pTexte.add(JCBdepotLegal);
		pTexte.add(JLquantite);
		pTexte.add(JTquantite);
		pTexte.add(JLplaceHolder2);
		pTexte.add(JBajouter);
		
		p.add(pTexte);
		Container contentPane = getContentPane();
        contentPane.add(p);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object source = evt.getSource();
		if (source == JBajouter){
			MedicamentService.ajouterStockMedicament(JCBcodeVisiteur.getSelectedItem().toString(), JCBdepotLegal.getSelectedItem().toString(), JTquantite.getText());
		}
	}

}
