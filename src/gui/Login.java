package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entiteti.Osoba;
import net.miginfocom.swing.MigLayout;
import aplikacija.Bolnica;

public class Login extends JFrame {

	private JLabel lblPoruka;
	private JLabel lblKorisnickoIme;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOK;
	private JButton btnCancel;
	
	private Bolnica bolnica;
	
	public Login(Bolnica bolnica) {
		this.bolnica = bolnica;
		setTitle("Prijava");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}
	
	private void initGUI() {
		/*
		 * 	Malo detaljnije podesavanje MigLayout-a:
		 * 	Drugi parametar (string) sadrzi 2 prazne uglaste zagrade jer imamo 2 kolone (ovde nista nismo podesili)
		 *  Treci parametar ima onoliko uglastih zagrada koliko imamo redova (u nasem slucaju 4)
		 *  Unutar zagrada mozemo detaljnije podesavati kolone i redove, dok vrednosti izmedju njih predstavljaju
		 *  razmake u pikselima.
		 *  Ovde smo postavili razmak od 20px izmedju 1. i 2. i izmedju 3. i 4. reda.
		 */
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
		setLayout(layout);
		
		this.lblPoruka = new JLabel("Dobrodošli. Molimo da se prijavite.");
		this.lblKorisnickoIme = new JLabel("Korisničko ime");
		this.txtKorisnickoIme = new JTextField(20);
		this.lblSifra = new JLabel("Šifra");
		this.pfSifra = new JPasswordField(20);
		this.btnOK = new JButton("OK");
		this.btnCancel = new JButton("Cancel");
		
		// Ako postavimo dugme 'btnOK' kao defaul button, onda ce svaki pritisak tastera Enter na tastaturi
		// Izazvati klik na njega
		this.getRootPane().setDefaultButton(btnOK);
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	
	private void initActions() {
		// Klik na Login dugme
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword()).trim();
				// Ukoliko nesto nije uneseno, obavestimo korisnika
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");
				}else {
					Osoba osoba = bolnica.login(korisnickoIme, sifra);
					if(osoba != null) {
						// Uspesno logovanje:
						// 1. Sakrijemo login prozor
						Login.this.setVisible(false);
						Login.this.dispose();
						// 2. Prikazemo glavni prozor i prosledimo mu bolnicu i prijavljenog korisnika
						if (osoba.getUloga().equals("Lekar")) {
							PocetnaLekar prozorL = new PocetnaLekar(bolnica, osoba);
							prozorL.setVisible(true);
						}
						else if (osoba.getUloga().equals("Pacijent")) {
							PocetnaPacijent prozorP = new PocetnaPacijent(bolnica,osoba);
							prozorP.setVisible(true);
						}
						else if (osoba.getUloga().equals("MedicinskaSestra")) {
							PocetnaMedicinskaSestra prozorS = new PocetnaMedicinskaSestra(bolnica,osoba);
							prozorS.setVisible(true);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Pogrešni login podaci!");
					}
				}
			}
		});
		// Cancel dugme samo sakriva trenutni prozor
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Login.this.setVisible(false);
				Login.this.dispose();
			}
		});
		
	}
}