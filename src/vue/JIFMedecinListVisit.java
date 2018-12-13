package vue;

import java.awt.Container;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;

import modele.Medecin;
import modele.dao.MedecinDao;
import modele.dao.VisiteDao;
import service.MedecinService;

public class JIFMedecinListVisit extends JInternalFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JPanel p;
	protected JScrollPane scrollPane;
	protected JPanel pSaisie;
	protected static JTextField JTcodeMedecin;
	protected JButton JBCreationVisite;
	protected MenuPrincipal fenetreContainer;
	protected JTable table;
	public JIFMedecinListVisit(MenuPrincipal uneFenetreContainer) {
		
		// TODO Auto-generated constructor stub
		fenetreContainer = uneFenetreContainer;
		setTitle("Liste Médecin n'ayant pas eu de visite depuis au moins 8 mois");
		p = new JPanel(); // panneau principal de la fenêtre
		
		//ArrayList permettant de récupérer la liste des médecins en retard
		ArrayList<Medecin> listVisit = VisiteDao.retournerCollectionVisiteEnRetard();
		// Permet de récupérer le nombre de ligne de visiteEnRetard pour parcourir listVisit
		int nbLignes = VisiteDao.retournerCollectionVisiteEnRetard().size();
		//Initialisation du String pour le tableau
		String[][] data = new String[nbLignes][4] ;
		
		// For permettant de d'insérer toute les ligne dans data //
		for (int i=0; i<listVisit.size();i++) {
			String codeMed = listVisit.get(i).getCodeMed();
			data[i][0] = listVisit.get(i).getCodeMed();
			data[i][1] = listVisit.get(i).getPrenom();
			data[i][2] = listVisit.get(i).getNom();
			//Code permettant de récupérer les dates en fonction du codMed
			data[i][3] = VisiteDao.retournerDateDerniereVisite(codeMed);
		}
		//code permettant la mise en forme du tableau d'affichage
		String[] columnNames = {"Code", "Prénom","Nom","Derniere Visite"};
		table = new JTable(data, columnNames);
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
			int ligne = table.getSelectedRow();
				JTcodeMedecin.setText(table.getValueAt(ligne, 0).toString());	
			}
			} );
		table.getSelectionModel().addListSelectionListener(table);		
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(418, 250));
		p.add(scrollPane);
		
		//code de saisie du code médecin et bouton de la validation
		pSaisie = new JPanel();
		JTcodeMedecin = new JTextField(20);
		JTcodeMedecin.setMaximumSize(JTcodeMedecin.getPreferredSize());
		JBCreationVisite = new JButton("Créer une visite");
		JBCreationVisite.addActionListener(this); // source d'évenement
		pSaisie.add(JTcodeMedecin);
		pSaisie.add(JBCreationVisite);
		p.add(pSaisie);
		// mise en forme de la fenêtre
		Container contentPane = getContentPane();
		contentPane.add(p);
		// Code permettant de prédéfinir la taille d'une collone
		TableColumn col = table.getColumnModel().getColumn(0);
        col.setPreferredWidth(50);
		TableColumn col1 = table.getColumnModel().getColumn(1);
        col1.setPreferredWidth(90);
		TableColumn col2 = table.getColumnModel().getColumn(2);
        col2.setPreferredWidth(110);
		TableColumn col3 = table.getColumnModel().getColumn(3);
        col3.setPreferredWidth(150);
       		
	}
    public static void viderText() {
    	JTcodeMedecin.setText("");
    }
    
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object source = evt.getSource();
		if (source == JBCreationVisite) {
			String codMed = JTcodeMedecin.getText();
			
			try {
				String unCodMed = MedecinService.rechercherMedecin(codMed).getCodeMed();
				if (unCodMed != null) {
					fenetreContainer.ouvrirFenetre(new JIFVisite(codMed));
				}
				
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null," La Médecin choisis n'existe pas ! ","Alert",JOptionPane.WARNING_MESSAGE);
				viderText();
			}
		}
		
		
	}

}
