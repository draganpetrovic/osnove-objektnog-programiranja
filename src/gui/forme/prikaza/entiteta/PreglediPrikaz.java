package gui.forme.prikaza.entiteta;

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

import entiteti.Pregled;
import entiteti.ZdravstvenaKnjizica;
import enumeracije.StatusPregleda;
import gui.Racun;
import gui.izmena.entiteta.KnjiziceForma;
import gui.izmena.entiteta.PreglediForma;

public class PreglediPrikaz extends JFrame{
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	private JButton btnDolar = new JButton();

	
	private DefaultTableModel tableModel;
	private JTable preglediTabela;
	
	private Bolnica bolnica;
	
	public PreglediPrikaz(Bolnica bolnica) {
		this.bolnica = bolnica;
		setTitle("Lista Pregleda");
		setSize(500, 300);
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
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		mainToolbar.add(btnDelete);
		ImageIcon dolarIcon = new ImageIcon(getClass().getResource("/slike/dollar.gif"));
		btnDolar.setIcon(dolarIcon);
		mainToolbar.add(btnDolar);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String [] {"Termin", "Soba", "Opis","Status"};
		Object [][] podaci = new Object[this.bolnica.getPregledi().size()][zaglavlje.length];
		
		for(int i=0; i<this.bolnica.getPregledi().size(); i++) {
			Pregled pregled = this.bolnica.getPregledi().get(i);
			podaci[i][0] = pregled.getTermin();
			podaci[i][1] = pregled.getSoba();
			podaci[i][2] = pregled.getOpis();
			podaci[i][3] = pregled.getStatus();
		}
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		preglediTabela = new JTable(tableModel);
		preglediTabela  = new JTable(tableModel);
		preglediTabela .setRowSelectionAllowed(true);
		preglediTabela .setColumnSelectionAllowed(false);
		preglediTabela .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		preglediTabela .setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(preglediTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			PreglediForma pf = new PreglediForma(bolnica, null);
				pf.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)preglediTabela.getModel();
					String termin = model.getValueAt(red, 0).toString();
					Pregled pregled = bolnica.nadjiPregled(termin);
					if(pregled != null) {
						PreglediForma pf = new PreglediForma(bolnica, pregled);
						pf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)preglediTabela.getModel();
					String termin = model.getValueAt(red, 0).toString();
					Pregled pregled = bolnica.nadjiPregled(termin);
					if(pregled != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete pregled?", pregled.getTermin() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							bolnica.getPregledi().remove(pregled);
							model.removeRow(red);
							bolnica.snimiPreglede();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabran pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDolar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = preglediTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)preglediTabela.getModel();
					String termin = model.getValueAt(red, 0).toString();
					Pregled pregled = bolnica.nadjiPregled(termin);
					if(pregled != null && pregled.getStatus().equals(StatusPregleda.ZAVRSEN)) {
						Racun r = new Racun(bolnica, pregled);
						r.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nema racuna za odabran pregled.", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
	}
	
	
	
}
