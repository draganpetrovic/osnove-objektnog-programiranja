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
import entiteti.ZdravstvenaKnjizica;
import gui.izmena.entiteta.KnjiziceForma;
import gui.izmena.entiteta.PacijentiForma;

public class KnjizicePrikaz extends JFrame {
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable knjiziceTabela;
	
	private Bolnica bolnica;
	
	public KnjizicePrikaz(Bolnica bolnica) {
		this.bolnica = bolnica;
		setTitle("Lista Knjizica");
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
		String[] zaglavlje = new String [] {"Broj knjizice", "Datum isteka", "Osiguranje"};
		Object [][] podaci = new Object[this.bolnica.getKnjizice().size()][zaglavlje.length];
		
		for(int i=0; i<this.bolnica.getKnjizice().size(); i++) {
			ZdravstvenaKnjizica knjizica = this.bolnica.getKnjizice().get(i);
			podaci[i][0] = knjizica.getBroj();
			podaci[i][1] = knjizica.getDatumIsteka();
			podaci[i][2] = knjizica.getOsiguranje();
		}
		tableModel = new DefaultTableModel(podaci, zaglavlje);
		knjiziceTabela = new JTable(tableModel);
		knjiziceTabela  = new JTable(tableModel);
		knjiziceTabela .setRowSelectionAllowed(true);
		knjiziceTabela .setColumnSelectionAllowed(false);
		knjiziceTabela .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		knjiziceTabela .setDefaultEditor(Object.class, null);
		
		JScrollPane scrollPane = new JScrollPane(knjiziceTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}

	private void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			KnjiziceForma kf = new KnjiziceForma(bolnica, null);
				kf.setVisible(true);
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)knjiziceTabela.getModel();
					String broj = model.getValueAt(red, 0).toString();
					ZdravstvenaKnjizica knjizica = bolnica.nadjiKnjizicu(broj);
					if(knjizica != null) {
						KnjiziceForma kf = new KnjiziceForma(bolnica, knjizica);
						kf.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu knjizicu!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = knjiziceTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					DefaultTableModel model = (DefaultTableModel)knjiziceTabela.getModel();
					String broj = model.getValueAt(red, 0).toString();
					ZdravstvenaKnjizica knjizica = bolnica.nadjiKnjizicu(broj);
					if(knjizica != null) {
						int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete knjizicu?", knjizica.getBroj() + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_OPTION) {
							bolnica.getKnjizice().remove(knjizica);
							model.removeRow(red);
							bolnica.snimiZdravstveneKnjizice();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu knjizicu!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
	}
	
	
	
}
