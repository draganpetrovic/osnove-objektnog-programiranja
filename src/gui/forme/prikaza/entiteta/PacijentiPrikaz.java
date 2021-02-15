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
import entiteti.Pacijent;
import gui.izmena.entiteta.PacijentiForma;


public class PacijentiPrikaz extends JFrame{

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable pacijentiTabela;
	
	private Bolnica bolnica;
	
	public PacijentiPrikaz(Bolnica bolnica) {
		this.bolnica = bolnica;
		setTitle("Spisak Pacijenata");
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
		
		String[] zaglavlje = new String [] {"ID", "Ime", "Prezime","Izabrani Lekar", "Broj knjizice", "Broj Telefona", "Korisnicko ime"};
		Object [][] podaci = new Object[this.bolnica.getPacijenti().size()][zaglavlje.length];
		//ucitava se lista lekara
		for(int i=0; i<this.bolnica.getPacijenti().size(); i++) {
			Pacijent pacijent = this.bolnica.getPacijenti().get(i);
			podaci[i][0] = pacijent.getIdentifikacioniKod();
			podaci[i][1] = pacijent.getIme();
			podaci[i][2] = pacijent.getPrezime();
			podaci[i][3] = pacijent.getIdentifikacioniKodLekara();
			podaci[i][4] = pacijent.getBrojKnjizice();
			podaci[i][5] = pacijent.getBrojTelefona();
			podaci[i][6] = pacijent.getKorisnickoIme();
		}
		
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		pacijentiTabela = new JTable(tableModel);
		pacijentiTabela = new JTable(tableModel);
		pacijentiTabela.setRowSelectionAllowed(true);
		pacijentiTabela.setColumnSelectionAllowed(false);
		pacijentiTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pacijentiTabela.setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(pacijentiTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			PacijentiForma pf = new PacijentiForma(bolnica, null);
				pf.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = pacijentiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)pacijentiTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					Pacijent pacijent = bolnica.nadjiPacijenta(korisnickoIme);
					if(pacijent != null) {
						PacijentiForma pf = new PacijentiForma(bolnica, pacijent);
						pf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = pacijentiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)pacijentiTabela.getModel();
					String korisnickoIme = model.getValueAt(red, 6).toString();
					Pacijent pacijent = bolnica.nadjiPacijenta(korisnickoIme);
					if(pacijent != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete pacijenta?", pacijent.getKorisnickoIme() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							bolnica.getPacijenti().remove(pacijent);
							model.removeRow(red);
							bolnica.snimiKorisnike();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});	
		
	}

}
