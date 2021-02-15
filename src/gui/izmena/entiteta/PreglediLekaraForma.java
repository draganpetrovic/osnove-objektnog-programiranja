package gui.izmena.entiteta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

public class PreglediLekaraForma extends JFrame{

	
	private JLabel lblTermin = new JLabel("Datum i vreme");
	private JTextField txtTermin = new JTextField(20);
	private JLabel lblSoba = new JLabel("Soba");
	private JTextField txtSoba = new JTextField(10);
	private JLabel lblOpis = new JLabel("Kratak opis");
	private JTextField txtOpis = new JTextField(100);
	private JLabel lblStatus = new JLabel("Status pregleda");
	private JComboBox<StatusPregleda> cbStatus = new JComboBox<StatusPregleda>(StatusPregleda.values());
	private JLabel lblPacijent = new JLabel("Pacijent");
	private JComboBox<String> cbPacijent = new JComboBox<String>();
	private JLabel lblLekar= new JLabel("Lekar");
	private JComboBox<String> cbLekar = new JComboBox<String>();
	
	private JButton btnOK = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	
	private Bolnica bolnica;
	private Pregled pregled;
	
	public PreglediLekaraForma(Bolnica bolnica, Pregled pregled) {
		this.bolnica = bolnica;
		this.pregled = pregled;
		setTitle("Izmena podataka za pregled - " + this.pregled.getTermin());
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
		
		if(this.pregled != null) {
			popuniPolja();
		}
		
		for (Pacijent pacijent : this.bolnica.getPacijenti()) {
			cbPacijent.addItem(pacijent.getIdentifikacioniKod());
		}
		
		for (Lekar lekar : this.bolnica.getLekari()) {
			cbLekar.addItem(lekar.getIdentifikacioniKod());
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
		add(cbPacijent);
		add(lblLekar);
		add(cbLekar);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
					String termin = txtTermin.getText().trim();
					String soba = txtSoba.getText().trim();
					String opis = txtOpis.getText().trim();
					StatusPregleda status = (StatusPregleda) cbStatus.getSelectedItem();
					String pacijentKod = cbPacijent.getSelectedItem().toString();
					Pacijent pacijent = bolnica.nadjiPacijentaID(pacijentKod);
					String lekarKod = cbLekar.getSelectedItem().toString();
					Lekar lekar = bolnica.nadjiLekaraID(lekarKod);
					
					if(pregled == null) {
						pregled = new Pregled(termin, soba, opis, status);
						if(pacijent != null && lekar != null) {
							pacijent.getPregledi().add(pregled);
							lekar.getPregledi().add(pregled);
						}
						bolnica.getPregledi().add(pregled);
					}else {
						pregled.setTermin(termin);
						pregled.setSoba(soba);
						pregled.setOpis(opis);
						pregled.setStatus(status);
						Pacijent stariPacijent = bolnica.pronadjiPacijenta(pregled);
						Lekar stariLekar = bolnica.pronadjiLekara(pregled);
						if(stariPacijent != null) {
							stariPacijent.getPregledi().remove(pregled);
						}
						if(pacijent != null) {
							pacijent.getPregledi().add(pregled);
						}
						if(stariLekar != null) {
							stariLekar.getPregledi().remove(pregled);
						}
						
						
						if(lekar != null) {
							lekar.getPregledi().add(pregled);
						}
						
						}
					
						bolnica.snimiPreglede();
						PreglediLekaraForma.this.dispose();
						PreglediLekaraForma.this.setVisible(false);
					
				}
			
			
		});
		
	}
	
	private void iskljuciPolja(boolean enable) {
		txtTermin.setEnabled(!enable);
		txtSoba.setEnabled(!enable);
		txtOpis.setEnabled(!enable);
		cbStatus.setEnabled(enable);
		cbPacijent.setEnabled(!enable);
		cbLekar.setEnabled(!enable);
	}
	
	private void popuniPolja() {
		iskljuciPolja(true);
		txtTermin.setText(this.pregled.getTermin());
		txtSoba.setText(this.pregled.getSoba());
		txtOpis.setText(this.pregled.getOpis());
		cbStatus.setSelectedItem(this.pregled.getStatus());
		Pacijent pacijent = bolnica.pronadjiPacijenta(this.pregled);
		cbPacijent.setSelectedItem(pacijent.getIdentifikacioniKod());
		Lekar lekar = bolnica.pronadjiLekara(this.pregled);
		cbLekar.setSelectedItem(lekar.getIdentifikacioniKod());
	}
	
	
	
	
	
}
