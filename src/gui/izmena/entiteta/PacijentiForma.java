package gui.izmena.entiteta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import aplikacija.Bolnica;
import entiteti.Lekar;
import entiteti.Pacijent;
import entiteti.Pregled;
import entiteti.ZdravstvenaKnjizica;
import enumeracije.Pol;
import net.miginfocom.swing.MigLayout;

public class PacijentiForma extends JFrame{
	
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
	private JLabel lblIzabraniLekar = new JLabel("Izabrani Lekar");
	private JComboBox<String> cbIzabraniLekar = new JComboBox<String>();
	private JLabel lblBrojKnjizice = new JLabel("Knjizica");
	private JComboBox<String> cbBrojKnjizice = new JComboBox<String>();
	private JButton btnOK = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");

	private Bolnica bolnica;
	private Pacijent pacijent;
	
	public PacijentiForma(Bolnica bolnica, Pacijent pacijent) {
		this.bolnica = bolnica;
		this.pacijent = pacijent;
		if(this.pacijent == null) {
			setTitle("Dodavanje pacijenta");
		}else {
			setTitle("Izmena podataka - " + this.pacijent.getKorisnickoIme());
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
		
		if(this.pacijent != null) {
			popuniPolja();
		}
		
		for (Lekar lekar : this.bolnica.getLekari()) {
			cbIzabraniLekar.addItem(lekar.getIdentifikacioniKod());
		}
		
		for (ZdravstvenaKnjizica knjizica : this.bolnica.getKnjizice()) {
			cbBrojKnjizice.addItem(knjizica.getBroj());
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
		add(lblIzabraniLekar);
		add(cbIzabraniLekar);
		add(lblBrojKnjizice);
		add(cbBrojKnjizice);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
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
					String izabraniLekar = cbIzabraniLekar.getSelectedItem().toString();
					String brojKnjizice = cbBrojKnjizice.getSelectedItem().toString();
					
					if(pacijent == null) {
						pacijent = new Pacijent(id, ime, prezime, jmbg, pol, adresa, brojTelefona,
							korisnickoIme, lozinka, uloga, izabraniLekar, brojKnjizice, new ArrayList<Pregled>());
						bolnica.getPacijenti().add(pacijent);
					}else {
						pacijent.setIdentifikacioniKod(id);
						pacijent.setIme(ime);
						pacijent.setPrezime(prezime);
						pacijent.setJmbg(jmbg);
						pacijent.setPol(pol);
						pacijent.setAdresa(adresa);
						pacijent.setBrojTelefona(brojTelefona);
						pacijent.setKorisnickoIme(korisnickoIme);
						pacijent.setLozinka(lozinka);
						pacijent.setUloga(uloga);
						pacijent.setIdentifikacioniKodLekara(izabraniLekar);
						pacijent.setBrojKnjizice(brojKnjizice);
					
					}
					bolnica.snimiKorisnike();
					PacijentiForma.this.dispose();
					PacijentiForma.this.setVisible(false);
				}
			}
		});
	}
		
	private void popuniPolja() {
			txtID.setText(this.pacijent.getIdentifikacioniKod());
			txtIme.setText(this.pacijent.getIme());
			txtPrezime.setText(this.pacijent.getPrezime());
			txtJmbg.setText(String.valueOf(this.pacijent.getJmbg()));
			cbPol.setSelectedItem(this.pacijent.getPol());
			txtAdresa.setText(this.pacijent.getAdresa());
			txtTelefon.setText(String.valueOf(this.pacijent.getBrojTelefona()));
			txtKorisnickoIme.setText(this.pacijent.getKorisnickoIme());
			pfLozinka.setText(this.pacijent.getLozinka());
			txtUloga.setText(this.pacijent.getUloga());
			cbIzabraniLekar.setSelectedItem(this.pacijent.getIdentifikacioniKodLekara());
			cbBrojKnjizice.setSelectedItem(this.pacijent.getBrojKnjizice());
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
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
		
}
	
		
		
		
	
	

