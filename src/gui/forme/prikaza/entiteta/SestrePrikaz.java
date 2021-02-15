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
import entiteti.MedicinskaSestra;
import gui.izmena.entiteta.SestreForma;

public class SestrePrikaz extends JFrame{

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable sestreTabela;
	
	private Bolnica bolnica;
	
	public SestrePrikaz(Bolnica bolnica) {
		this.bolnica = bolnica;
		setTitle("Spisak Medicinskih sestara");
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
		Object [][] podaci = new Object[this.bolnica.getSestre().size()][zaglavlje.length];
		//ucitava se lista lekara
		for(int i=0; i<this.bolnica.getSestre().size(); i++) {
			MedicinskaSestra sestra = this.bolnica.getSestre().get(i);
			podaci[i][0] = sestra.getIdentifikacioniKod();
			podaci[i][1] = sestra.getIme();
			podaci[i][2] = sestra.getPrezime();
			podaci[i][3] = sestra.getSluzba();
			podaci[i][4] = sestra.getBrojTelefona();
			podaci[i][5] = sestra.getKorisnickoIme();
		}
		
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		sestreTabela = new JTable(tableModel);
		sestreTabela = new JTable(tableModel);
		sestreTabela.setRowSelectionAllowed(true);
		sestreTabela.setColumnSelectionAllowed(false);
		sestreTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sestreTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(sestreTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SestreForma sf = new SestreForma(bolnica, null);
				sf.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = sestreTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)sestreTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 5).toString();
					MedicinskaSestra sestra = bolnica.nadjiMedicinskuSestru(korisnickoIme);
					if(sestra != null) {
						SestreForma sf = new SestreForma(bolnica, sestra);
						sf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu medicinsku sestru!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = sestreTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)sestreTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 3).toString();
					MedicinskaSestra sestra = bolnica.nadjiMedicinskuSestru(korisnickoIme);
					if(sestra != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete medicinsku sestru?", sestra.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							bolnica.getSestre().remove(sestra);
							model.removeRow(red);
							bolnica.snimiKorisnike();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu medicinsku sestru!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
	}

}
