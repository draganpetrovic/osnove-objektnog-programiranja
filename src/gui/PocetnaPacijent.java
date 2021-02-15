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
import entiteti.Osoba;
import entiteti.Pacijent;
import entiteti.Pregled;
import gui.izmena.entiteta.PreglediForma;
import gui.izmena.entiteta.PreglediLekaraForma;
import gui.izmena.entiteta.PreglediPacijentaAdd;
import gui.izmena.entiteta.PreglediPacijentaEdit;

public class PocetnaPacijent extends JFrame{
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();


	
	private DefaultTableModel tableModel;
	private JTable preglediPacijentaTabela;
	
	private Bolnica bolnica;
	private Osoba prijavljenPacijent;
	
	public PocetnaPacijent(Bolnica bolnica, Osoba prijavljenPacijent) {
		this.bolnica = bolnica;
		this.prijavljenPacijent = prijavljenPacijent; 
		setTitle("Lista pregleda pacijenta" + prijavljenPacijent.getIme());
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	

	private void initGUI() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		mainToolbar.add(btnAdd);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		mainToolbar.add(btnEdit);
		add(mainToolbar, BorderLayout.NORTH);
		String[] zaglavlje = new String [] {"Termin", "Soba", "Opis","Status"};
		Object [][] podaci = new Object[this.bolnica.getPregledi().size()][zaglavlje.length];
		String id = prijavljenPacijent.getIdentifikacioniKod();
		Pacijent pacijent = new Pacijent(bolnica.nadjiPacijentaID(id));
		for(int i=0; i<pacijent.getPregledi().size(); i++) {
			Pregled pregled = this.bolnica.getPregledi().get(i);
			podaci[i][0] = pregled.getTermin();
			podaci[i][1] = pregled.getSoba();
			podaci[i][2] = pregled.getOpis();
			podaci[i][3] = pregled.getStatus();
		}
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		preglediPacijentaTabela = new JTable(tableModel);
		preglediPacijentaTabela  = new JTable(tableModel);
		preglediPacijentaTabela.setRowSelectionAllowed(true);
		preglediPacijentaTabela.setColumnSelectionAllowed(false);
		preglediPacijentaTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		preglediPacijentaTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(preglediPacijentaTabela);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			Pacijent pacijent = bolnica.nadjiPacijentaID(prijavljenPacijent.getIdentifikacioniKod());
			PreglediPacijentaAdd ppa = new PreglediPacijentaAdd(bolnica, pacijent);
				ppa.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediPacijentaTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)preglediPacijentaTabela.getModel();
					String termin = model.getValueAt(red, 0).toString();
					Pregled pregled = bolnica.nadjiPregled(termin);
					if(pregled != null) {
						Pacijent pacijent = bolnica.nadjiPacijentaID(prijavljenPacijent.getIdentifikacioniKod());
						PreglediPacijentaEdit ppe = new PreglediPacijentaEdit(bolnica, pregled, pacijent);
						ppe.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
	}
}
