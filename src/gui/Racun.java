package gui;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import aplikacija.Bolnica;
import entiteti.Lekar;
import entiteti.Pacijent;
import entiteti.Pregled;
import enumeracije.StatusPregleda;
import net.miginfocom.swing.MigLayout;

public class Racun extends JFrame {
	
	private JLabel lblTermin = new JLabel("Datum i vreme");
	private JTextField txtTermin = new JTextField(20);
	private JLabel lblSoba = new JLabel("Soba");
	private JTextField txtSoba = new JTextField(10);
	private JLabel lblOpis = new JLabel("Kratak opis");
	private JTextField txtOpis = new JTextField(50);
	private JLabel lblStatus = new JLabel("Status pregleda");
	private JComboBox<StatusPregleda> cbStatus = new JComboBox<StatusPregleda>(StatusPregleda.values());
	private JLabel lblPacijent = new JLabel("Pacijent");
	private JTextField txtPacijent = new JTextField(5);
	private JLabel lblLekar= new JLabel("Lekar");
	private JTextField txtLekar = new JTextField(5);
	private JLabel lblCena = new JLabel("Cena pregleda");
	private JTextField txtCena = new JTextField(10);
	
	private Bolnica bolnica;
	private Pregled pregled;
	
	public Racun(Bolnica bolnica, Pregled pregled) {
		this.bolnica = bolnica;
		this.pregled = pregled;
		setTitle("Racun");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2");
		setLayout(layout);
		
		if(this.pregled != null) {
			popuniPolja();
		}
		
		
		add(lblTermin);
		add(txtTermin);
		add(lblSoba);
		add(txtSoba);
		add(lblOpis);
		add(txtOpis);
		add(lblStatus);
		add(cbStatus);
		add(lblPacijent);
		add(txtPacijent);
		add(lblLekar);
		add(txtLekar);
		add(lblCena);
		add(txtCena);
		add(new JLabel());

	}
	
	private void iskljuciPolja(boolean enable) {
		txtTermin.setEnabled(!enable);
		txtSoba.setEnabled(!enable);
		txtOpis.setEnabled(!enable);
		cbStatus.setEnabled(!enable);
		txtPacijent.setEnabled(!enable);
		txtLekar.setEnabled(!enable);
		txtCena.setEnabled(!enable);
	}
	
	
	private void popuniPolja() {
		//iskljuciPolja(true);
		txtTermin.setText(this.pregled.getTermin());
		txtSoba.setText(this.pregled.getSoba());
		txtOpis.setText(this.pregled.getOpis());
		cbStatus.setSelectedItem(this.pregled.getStatus());
		Pacijent pacijent = bolnica.pronadjiPacijenta(this.pregled);
		txtPacijent.setText(pacijent.getIdentifikacioniKod());
		Lekar lekar = bolnica.pronadjiLekara(this.pregled);
		txtLekar.setText(lekar.getIdentifikacioniKod());
		txtCena.setText(bolnica.racunPregleda(pacijent.getBrojKnjizice()));
	}
	
	private boolean validacija() {
		boolean ok = true;
		
		String poruka = "Ispravite greske prilikom unosa: \n";
		if(txtTermin.getText().trim().equals("")) {
			poruka += " - Unesite termin pregleda \n";
			ok = false;
		}
		if(txtSoba.getText().trim().equals("")) {
			poruka += " - Unesite Sobu u kojoj ce se pregledati pacijent \n";
			ok = false;
			
		}
		if(txtOpis.getText().trim().equals("")) {
			poruka += " - Unesite opis pregleda \n";
			ok = false;
		}
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
		
	}

}
