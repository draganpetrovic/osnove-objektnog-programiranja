package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import aplikacija.Bolnica;
import entiteti.Lekar;
import entiteti.Osoba;
import entiteti.Pregled;
import enumeracije.StatusPregleda;
import gui.izmena.entiteta.PreglediLekaraForma;

public class PocetnaLekar extends JFrame{

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnEdit = new JButton();


	
	private DefaultTableModel tableModel;
	private JTable preglediLekaraTabela;
	
	private Bolnica bolnica;
	private Osoba prijavljenLekar;
	
	
	public PocetnaLekar(Bolnica bolnica, Osoba prijavljenLekar) {
		this.bolnica = bolnica;
		this.prijavljenLekar = prijavljenLekar; 
		setTitle("Lista pregleda lekara" + prijavljenLekar.getIme());
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}


	private void initGUI() {
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		add(mainToolbar, BorderLayout.NORTH);
		String[] zaglavlje = new String [] {"Termin", "Soba", "Opis","Status"};
		Object [][] podaci = new Object[this.bolnica.getPregledi().size()][zaglavlje.length];
		String id = prijavljenLekar.getIdentifikacioniKod();
		Lekar lekar = bolnica.nadjiLekaraID(id);
		for(int i=0; i<lekar.getPregledi().size(); i++) {
			Pregled pregled = this.bolnica.getPregledi().get(i);
			podaci[i][0] = pregled.getTermin();
			podaci[i][1] = pregled.getSoba();
			podaci[i][2] = pregled.getOpis();
			podaci[i][3] = pregled.getStatus();
			
		}
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		preglediLekaraTabela = new JTable(tableModel);
		preglediLekaraTabela  = new JTable(tableModel);
		preglediLekaraTabela.setRowSelectionAllowed(true);
		preglediLekaraTabela.setColumnSelectionAllowed(false);
		preglediLekaraTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		preglediLekaraTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(preglediLekaraTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediLekaraTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)preglediLekaraTabela.getModel();
					String termin = model.getValueAt(red, 0).toString();
					Pregled pregled = bolnica.nadjiPregled(termin);
					if(pregled != null) {
						if(pregled.getStatus().equals(StatusPregleda.OTKAZAN) || pregled.getStatus().equals(StatusPregleda.ZAVRSEN) ) {
							JOptionPane.showMessageDialog(null, "Pregled otkazan ili zavrsen, nisu moguce izmene!", "Greska", JOptionPane.ERROR_MESSAGE);
							
						}else {
							PreglediLekaraForma plf = new PreglediLekaraForma(bolnica, pregled);
							plf.setVisible(true);}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
}
