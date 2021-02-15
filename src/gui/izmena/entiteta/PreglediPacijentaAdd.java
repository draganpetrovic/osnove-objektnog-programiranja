package gui.izmena.entiteta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import aplikacija.Bolnica;
import entiteti.Lekar;
import entiteti.Pacijent;
import entiteti.Pregled;
import enumeracije.StatusPregleda;
import net.miginfocom.swing.MigLayout;

public class PreglediPacijentaAdd extends JFrame{

	private JLabel lblOpis = new JLabel("Kratak opis");
	private JTextField txtOpis = new JTextField(100);
	
	private JButton btnOK = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	
	private Bolnica bolnica;
	private Pacijent prijavljenPacijent;
	private Pregled pregled;
	
	public PreglediPacijentaAdd(Bolnica bolnica, Pacijent prijavljenPacijent) {
		this.bolnica = bolnica;
		this.prijavljenPacijent = prijavljenPacijent;
		setTitle("Dodavanje pregleda");
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
		
		add(lblOpis);
		add(txtOpis);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String termin = "";
				String soba = "";
				String opis = txtOpis.getText().trim();
				StatusPregleda status = StatusPregleda.ZATRAZEN;
				String pacijentKod = prijavljenPacijent.getIdentifikacioniKod();
				Pacijent pacijent = bolnica.nadjiPacijentaID(pacijentKod);
				String lekarKod = prijavljenPacijent.getIdentifikacioniKodLekara();
				Lekar lekar = bolnica.nadjiLekaraID(lekarKod);
				pregled = new Pregled(termin, soba, opis, status);
				if(pacijent != null && lekar != null) {
					pacijent.getPregledi().add(pregled);
					lekar.getPregledi().add(pregled);
				}
				bolnica.getPregledi().add(pregled);
				bolnica.snimiPreglede();
				PreglediPacijentaAdd.this.dispose();
				PreglediPacijentaAdd.this.setVisible(false);
			}	
		});
	}
}
