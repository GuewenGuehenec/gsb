package vue;

import modele.Medicament;
import modele.dao.MedicamentDao;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class JIFMedicamentCons extends JInternalFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel p;
	private JScrollPane scrollPane;
	private JTextField JTcodeMedicament;
	private JPanel pBouton;
	private JButton JBafficherFiche;
	private MenuPrincipal fenetreContainer;
	
	public JIFMedicamentCons(MenuPrincipal uneFenetreContainer) {
		fenetreContainer = uneFenetreContainer;
		setTitle("Liste des médicaments");
		p = new JPanel();
		JTcodeMedicament = new JTextField(20);
		JTcodeMedicament.setEditable(false);
		pBouton = new JPanel();
		JBafficherFiche = new JButton("Afficher fiche");
		JBafficherFiche.addActionListener(this);
		ArrayList<Medicament> lesMedicaments = MedicamentDao.retournerCollectionDesMedicaments();
		int nbLignes = MedicamentDao.retournerCollectionDesMedicaments().size();
		
		JTable table;
		int i=0;
		String[][] data = new String[nbLignes][3] ;
		for(Medicament unMedicament : lesMedicaments){
			data[i][0] = unMedicament.getDepotLegal();
			data[i][1] = unMedicament.getNomCommercial();
			data[i][2] = unMedicament.getLibelleFamille();
			i++;
			}
		String[] columnNames = {"Code", "Nom","Famille"};
		table = new JTable(data, columnNames);
		table.addMouseListener(
			new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					int ligne = table.getSelectedRow();
							JTcodeMedicament.setText(table.getValueAt(ligne, 0).toString());
				}
            }
		);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(450, 250));
		p.add(scrollPane);
		pBouton.add(JTcodeMedicament);
		pBouton.add(JBafficherFiche);
		p.add(pBouton);
		
		Container contentPane = getContentPane();
		contentPane.add(p);
		table.getColumnModel().getColumn(0).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(125);
		table.getColumnModel().getColumn(1).setPreferredWidth(125);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Object source = evt.getSource();
		if (source == JBafficherFiche){
			Medicament unMedicament = MedicamentDao.rechercher(JTcodeMedicament.getText());
			if(unMedicament!=null) {
				fenetreContainer.ouvrirFenetre(new JIFMedicamentFiche(unMedicament));
			}
		}
	}

}
