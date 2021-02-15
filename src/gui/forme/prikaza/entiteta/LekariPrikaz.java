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
import entiteti.Lekar;
import gui.izmena.entiteta.LekariForma;

public class LekariPrikaz extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable lekariTabela;
	
	private Bolnica bolnica;
	
	public LekariPrikaz(Bolnica bolnica) {
		this.bolnica = bolnica;
		setTitle("Spisak lekara");
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
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String [] {"ID", "Ime", "Prezime", "Sluzba" ,"Broj Telefona", "Korisnicko ime"};
		Object [][] podaci = new Object[this.bolnica.getLekari().size()][zaglavlje.length];
		//ucitava se lista lekara
		for(int i=0; i<this.bolnica.getLekari().size(); i++) {
			Lekar lekar = this.bolnica.getLekari().get(i);
			podaci[i][0] = lekar.getIdentifikacioniKod();
			podaci[i][1] = lekar.getIme();
			podaci[i][2] = lekar.getPrezime();
			podaci[i][3] = lekar.getSluzba();
			podaci[i][4] = lekar.getBrojTelefona();
			podaci[i][5] = lekar.getKorisnickoIme();
		}
		
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		lekariTabela = new JTable(tableModel);
		lekariTabela = new JTable(tableModel);
		lekariTabela.setRowSelectionAllowed(true);
		lekariTabela.setColumnSelectionAllowed(false);
		lekariTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lekariTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(lekariTabela);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LekariForma lf = new LekariForma(bolnica, null);
				lf.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = lekariTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)lekariTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 5).toString();
					Lekar lekar = bolnica.nadjiLekara(korisnickoIme);
					if(lekar != null) {
						LekariForma lf = new LekariForma(bolnica, lekar);
						lf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog lekara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = lekariTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)lekariTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 5).toString();
					Lekar lekar = bolnica.nadjiLekara(korisnickoIme);
					if(lekar != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete lekara?", lekar.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							bolnica.getLekari().remove(lekar);
							model.removeRow(red);
							bolnica.snimiKorisnike();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog lekara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
	}


}
