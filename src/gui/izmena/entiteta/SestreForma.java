package gui.izmena.entiteta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import aplikacija.Bolnica;
import entiteti.MedicinskaSestra;
import enumeracije.Pol;
import enumeracije.Sluzba;
import net.miginfocom.swing.MigLayout;

public class SestreForma extends JFrame {

	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(10);
	private JLabel lblIme = new JLabel("Ime");
	private JTextField txtIme  = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime  = new JTextField(20);
	private JLabel lblJmbg = new JLabel("JMBG");
	private JTextField txtJmbg  = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(40);
	private JLabel lblTelefon = new JLabel("Broj telefona");
	private JTextField txtTelefon = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField pfLozinka = new JPasswordField(20);
	private JLabel lblUloga = new JLabel("Uloga");
	private JTextField txtUloga = new JTextField(20);
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblSluzba = new JLabel("Sluzba");
	private JComboBox<Sluzba> cbSluzba = new JComboBox<Sluzba>(Sluzba.values());
	
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Bolnica bolnica;
	private MedicinskaSestra sestra;
	
	public SestreForma(Bolnica bolnica, MedicinskaSestra sestra) {
		this.bolnica = bolnica;
		this.sestra = sestra;
		if(this.sestra == null) {
			setTitle("Dodavanje medicinske sestre");
		}else {
			setTitle("Izmena podataka - " + this.sestra.getKorisnickoIme());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(this.sestra != null) {
			popuniPolja();
		}
		add(lblID);
		add(txtID);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJmbg);
		add(txtJmbg);
		add(lblPol);
		add(cbPol);
		add(lblAdresa);
		add(txtAdresa);
		add(lblTelefon);
		add(txtTelefon);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(pfLozinka);
		add(lblUloga);
		add(txtUloga);
		add(lblPlata);
		add(txtPlata);
		add(lblSluzba);
		add(cbSluzba);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					String id = txtID.getText().trim();
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					int jmbg = Integer.parseInt(txtJmbg.getText().trim());
					Pol pol = (Pol) cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					int brojTelefona = Integer.parseInt(txtTelefon.getText().trim());
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(pfLozinka.getPassword()).trim();
					String uloga = txtUloga.getText().trim();
					double plata = Double.parseDouble(txtPlata.getText().trim());
					Sluzba sluzba = (Sluzba) cbSluzba.getSelectedItem();
					if(sestra == null) {
						sestra = new MedicinskaSestra(id, ime, prezime, jmbg, pol, adresa, brojTelefona,
							korisnickoIme, lozinka, uloga, plata, sluzba);
						bolnica.getSestre().add(sestra);
					}else {
						sestra.setIdentifikacioniKod(id);
						sestra.setIme(ime);
						sestra.setPrezime(prezime);
						sestra.setJmbg(jmbg);
						sestra.setPol(pol);
						sestra.setAdresa(adresa);
						sestra.setBrojTelefona(brojTelefona);
						sestra.setKorisnickoIme(korisnickoIme);
						sestra.setLozinka(lozinka);
						sestra.setUloga(uloga);
						sestra.setPlata(plata);
						sestra.setSluzba(sluzba);
					}
					bolnica.snimiKorisnike();
					SestreForma.this.dispose();
					SestreForma.this.setVisible(false);
				}
			}
		});
	}
	
	private void popuniPolja() {
		txtID.setText(this.sestra.getIdentifikacioniKod());
		txtIme.setText(this.sestra.getIme());
		txtPrezime.setText(this.sestra.getPrezime());
		txtJmbg.setText(String.valueOf(this.sestra.getJmbg()));
		cbPol.setSelectedItem(this.sestra.getPol());
		txtAdresa.setText(this.sestra.getAdresa());
		txtTelefon.setText(String.valueOf(this.sestra.getBrojTelefona()));
		txtKorisnickoIme.setText(this.sestra.getKorisnickoIme());
		pfLozinka.setText(this.sestra.getLozinka());
		txtUloga.setText(this.sestra.getUloga());
		txtPlata.setText(String.valueOf(this.sestra.getPlata()));
		cbSluzba.setSelectedItem(this.sestra.getSluzba());
	}
	
	private boolean validacija() {
		boolean ok = true;
		
		String poruka = "Ispravite greske prilikom unosa: \n";
		if(txtID.getText().trim().equals("") ) {
			poruka += "- Unesite jedinstveni ID \n";
			ok = false;
		}
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Unesite ime\n";
			ok = false;
		}
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Unesite prezime\n";
			ok = false;
		}
		try {
			Integer.parseInt(txtJmbg.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- JMBG mora biti sastavljen od brojeva \n";
			ok = false;
		}
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Unesite adresu\n";
			ok = false;
		}
		try {
			Integer.parseInt(txtTelefon.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Broj telefona mora biti sastavljen od brojeva \n";
			ok = false;
		}
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Unesite jedinstveno korisnicko ime \n";
			ok = false;
		}
		String lozinka = new String(pfLozinka.getPassword()).trim();
		if(lozinka.trim().equals("")) {
			poruka += "- Unesite lozinku\n";
			ok = false;
		}
		if(txtUloga.getText().trim().equals("")) {
			poruka += "- Unesite Ulogu\n";
			ok = false;
		}
		try {
			Double.parseDouble(txtPlata.getText().trim());
		}catch (NumberFormatException e) {
			poruka += "- Plata mora biti broj\n";
			ok = false;
		}
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
	
	
}
