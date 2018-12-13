package vue;

import modele.Medicament;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JIFMedicamentFiche extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JPanel p;
	private JPanel pTexte;
	private Dimension dimension;
	
	private JLabel JLdepotLegal;
	private JLabel JLnomCommercial;
	private JLabel JLcomposition;
	private JLabel JLeffets;
	private JLabel JLcontreIndications;
	private JLabel JLcodeFamille;
	private JLabel JLlibelleFamille;
	
	private JTextField JTdepotLegal;
	private JTextField JTnomCommercial;
	private JTextField JTcomposition;
	private JTextField JTeffets;
	private JTextField JTcontreIndications;
	private JTextField JTcodeFamille;
	private JTextField JTlibelleFamille;
	
	public JIFMedicamentFiche(Medicament unMedicament) {
		setTitle("Fiche médicament");
		p = new JPanel();
		pTexte = new JPanel(new GridLayout(7,2));
		
		JLdepotLegal = new JLabel("Dépôt légal");
		JLdepotLegal.setPreferredSize(new Dimension(5,5));
		JLnomCommercial = new JLabel("Nom commercial");
		JLcomposition = new JLabel("Composition");
		JLeffets = new JLabel("Effets");
		JLcontreIndications = new JLabel("Contre indications");
		JLcodeFamille = new JLabel("Code famille");
		JLlibelleFamille = new JLabel("Libellé famille");
		
		Dimension dimension = new Dimension();
		dimension.setSize(220,25);
		
		JTdepotLegal = new JTextField();
		JTdepotLegal.setPreferredSize(dimension.getSize());
		JTdepotLegal.setMaximumSize(dimension.getSize());
		JTnomCommercial = new JTextField();
		JTnomCommercial.setPreferredSize(dimension.getSize());
		JTnomCommercial.setMaximumSize(dimension.getSize());
		JTcomposition = new JTextField();
		JTcomposition.setPreferredSize(dimension.getSize());
		JTcomposition.setMaximumSize(dimension.getSize());
		JTeffets = new JTextField();
		JTeffets.setPreferredSize(dimension.getSize());
		JTeffets.setMaximumSize(dimension.getSize());
		JTcontreIndications = new JTextField();
		JTcontreIndications.setPreferredSize(dimension.getSize());
		JTcontreIndications.setMaximumSize(dimension.getSize());
		JTcodeFamille = new JTextField();
		JTcodeFamille.setPreferredSize(dimension.getSize());
		JTcodeFamille.setMaximumSize(dimension.getSize());
		JTlibelleFamille = new JTextField();
		JTlibelleFamille.setPreferredSize(dimension.getSize());
		JTlibelleFamille.setMaximumSize(dimension.getSize());
		
		JTdepotLegal.setText(unMedicament.getDepotLegal());
		JTnomCommercial.setText(unMedicament.getNomCommercial());
		JTcomposition.setText(unMedicament.getComposition());
		JTeffets.setText(unMedicament.getEffets());
		JTcontreIndications.setText(unMedicament.getContreIndication());
		JTcodeFamille.setText(unMedicament.getCodeFamille());
		JTlibelleFamille.setText(unMedicament.getLibelleFamille());
		
		pTexte.add(JLdepotLegal);
		pTexte.add(JTdepotLegal);
		pTexte.add(JLnomCommercial);
		pTexte.add(JTnomCommercial);
		pTexte.add(JLcomposition);
		pTexte.add(JTcomposition);
		pTexte.add(JLeffets);
		pTexte.add(JTeffets);
		pTexte.add(JLcontreIndications);
		pTexte.add(JTcontreIndications);
		pTexte.add(JLcodeFamille);
		pTexte.add(JTcodeFamille);
		pTexte.add(JLlibelleFamille);
		pTexte.add(JTlibelleFamille);
		
		p.add(pTexte);
        Container contentPane = getContentPane();
        contentPane.add(p);
	}
}