package gui.izmena.entiteta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import aplikacija.Bolnica;
import entiteti.Lekar;
import entiteti.Pacijent;
import entiteti.Pregled;
import enumeracije.StatusPregleda;
import net.miginfocom.swing.MigLayout;

public class PreglediPacijentaEdit extends JFrame {
	
	private JLabel lblStatus = new JLabel("Status pregleda");
	private JComboBox<StatusPregleda> cbStatus = new JComboBox<StatusPregleda>(StatusPregleda.values());

	private JButton btnOK = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	private Bolnica bolnica;
	private Pregled pregled;
	private Pacijent prijavljenPacijent;
	
	public PreglediPacijentaEdit(Bolnica bolnica, Pregled pregled, Pacijent prijavljenPacijent) {
		this.prijavljenPacijent = prijavljenPacijent;
		this.bolnica = bolnica;
		this.pregled = pregled;
		setTitle("Izmena podataka za pregled " + this.pregled.getTermin());
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
		
		add(lblStatus);
		add(cbStatus);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					int jedan = 1;
					int dva = 2;
					if(pregled.getStatus().equals(jedan) || pregled.getStatus().equals(dva)) {
						String termin = pregled.getTermin();
						String soba = pregled.getSoba();
						String opis = pregled.getOpis();
						StatusPregleda status = (StatusPregleda) cbStatus.getSelectedItem();
						String pacijentKod = prijavljenPacijent.getIdentifikacioniKod();
						Pacijent pacijent = bolnica.nadjiPacijentaID(pacijentKod);
						String lekarKod = prijavljenPacijent.getIdentifikacioniKodLekara();
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
					}
				
				bolnica.snimiPreglede();
				PreglediPacijentaEdit.this.dispose();
				PreglediPacijentaEdit.this.setVisible(false);
				
				}
			}
				
		});

	}
	
	private void popuniPolja() {
		cbStatus.setSelectedItem(this.pregled.getStatus());
		}
		
	private boolean validacija() {
		boolean ok = true;
		if(cbStatus.getSelectedItem().equals(3) || cbStatus.getSelectedItem().equals(4)) {
			JOptionPane.showMessageDialog(null, "Nije moguce otkazati zavrsen ili vec otkazan pregled!");
			ok = false;
		}
		
		return ok;
	}	
	
}
