package gui.izmena.entiteta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import aplikacija.Bolnica;
import entiteti.Pacijent;
import entiteti.ZdravstvenaKnjizica;
import enumeracije.KategorijaOsiguranja;
import enumeracije.Pol;
import net.miginfocom.swing.MigLayout;

public class KnjiziceForma extends JFrame{

	private JLabel lblBroj = new JLabel("BrojKnjizice");
	private JTextField txtBroj = new JTextField(20);
	private JLabel lblDatumIsteka = new JLabel("Datum isteka");
	private JTextField txtDatumIsteka = new JTextField(20);
	private JLabel lblKategorijaOsiguranja = new JLabel("Osiguranje");
	private JComboBox<KategorijaOsiguranja> cbKategorijaOsiguranja = new JComboBox<KategorijaOsiguranja>(KategorijaOsiguranja.values());
	private JLabel lblVlasnik = new JLabel("Pacijent");
	private JComboBox<String> cbVlasnik = new JComboBox<String>();
	private JButton btnOK = new JButton("Ok");
	private JButton btnCancel = new JButton("Cancel");
	
	private Bolnica bolnica;
	private ZdravstvenaKnjizica knjizica;
	
	public KnjiziceForma(Bolnica bolnica, ZdravstvenaKnjizica knjizica) {
		this.bolnica = bolnica;
		this.knjizica = knjizica;
		if(this.knjizica == null) {
			setTitle("Dodavanje zdravstvene knjizice");
		}else {
			setTitle("Izmena podataka - " + this.knjizica.getBroj());
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
		
		if(this.knjizica != null) {
			popuniPolja();
		}
		
		for (Pacijent pacijent : this.bolnica.getPacijenti()) {
			cbVlasnik.addItem(pacijent.getIdentifikacioniKod());
		}
		
		add(lblBroj);
		add(txtBroj);
		add(lblDatumIsteka);
		add(txtDatumIsteka);
		add(lblKategorijaOsiguranja);
		add(cbKategorijaOsiguranja);
		add(lblVlasnik);
		add(cbVlasnik);
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
		
		
	}

	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija() == true) {
					String broj = txtBroj.getText().trim();
					String datumIsteka = txtDatumIsteka.getText().trim();
					KategorijaOsiguranja osiguranje = (KategorijaOsiguranja) cbKategorijaOsiguranja.getSelectedItem();
					String pacijentKod = cbVlasnik.getSelectedItem().toString();
					Pacijent pacijent = bolnica.nadjiPacijentaID(pacijentKod);
					
					if(knjizica == null) {
						knjizica = new ZdravstvenaKnjizica(broj, datumIsteka, osiguranje, pacijentKod);
						bolnica.getKnjizice().add(knjizica);
					}else {
						knjizica.setBroj(broj);
						knjizica.setDatumIsteka(datumIsteka);
						knjizica.setOsiguranje(osiguranje);
						knjizica.setPacijentKod(pacijentKod);
					}
					bolnica.snimiZdravstveneKnjizice();
					KnjiziceForma.this.dispose();
					KnjiziceForma.this.setVisible(false);
					
				}
			}
			
		});
		
	}
	
	private void popuniPolja() {
		txtBroj.setText(this.knjizica.getBroj());
		txtDatumIsteka.setText(this.knjizica.getDatumIsteka());
		cbKategorijaOsiguranja.setSelectedItem(this.knjizica.getOsiguranje());
		cbVlasnik.setSelectedItem(this.knjizica.getPacijentKod());
	}
	
	private boolean validacija() {
		boolean ok = true;
		
		String poruka = "Ispravite greske prilikom unosa: \n";
		if(txtBroj.getText().trim().equals("") || bolnica.jedinstveniBroj(txtBroj.getText().trim())) {
			poruka += " - Unesite jedinstveni broj knjizice \n";
			ok = false;
		}
		if(txtDatumIsteka.getText().trim().equals("")) {
			poruka += " - Unesite datum isteka knjizice \n";
			ok = false;
			
		}
		
		return ok;
		
	}
	
	
	
}
