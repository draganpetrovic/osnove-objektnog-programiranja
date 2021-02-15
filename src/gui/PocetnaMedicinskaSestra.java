package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import aplikacija.Bolnica;
import entiteti.MedicinskaSestra;
import entiteti.Osoba;
import gui.forme.prikaza.entiteta.KnjizicePrikaz;
import gui.forme.prikaza.entiteta.LekariPrikaz;
import gui.forme.prikaza.entiteta.PacijentiPrikaz;
import gui.forme.prikaza.entiteta.PreglediPrikaz;
import gui.forme.prikaza.entiteta.SestrePrikaz;


public class PocetnaMedicinskaSestra extends JFrame{
	
	private JMenuBar mainMenu;
	private JMenu zaposleniMenu;
	private JMenuItem lekariItem;
	private JMenuItem medicinskeSestreItem;
	private JMenu pacijentiMenu;
	private JMenuItem pacijentiItem;
	private JMenuItem preglediItem;
	private JMenuItem knjiziceItem;
	
	private Bolnica bolnica;
	private Osoba prijavljenaSestra;
	
	public PocetnaMedicinskaSestra(Bolnica bolnica, Osoba prijavljenaSestra) {
		this.bolnica = bolnica;
		this.prijavljenaSestra = prijavljenaSestra;
		setTitle("DOM ZDRAVLJA - " + prijavljenaSestra.getIme());
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		this.mainMenu = new JMenuBar();
		this.zaposleniMenu = new JMenu("Zaposleni");
		this.lekariItem = new JMenuItem("Prikazi lekare");
		this.medicinskeSestreItem = new JMenuItem("Prikazi medicinske sestre");
		this.pacijentiMenu = new JMenu("Pacijenti");
		this.pacijentiItem = new JMenuItem("Prikazi pacijente");
		this.preglediItem = new JMenuItem("Prikazi preglede");
		this.knjiziceItem = new JMenuItem("Prikazi knjizice");
		
		this.zaposleniMenu.add(lekariItem);
		this.zaposleniMenu.add(medicinskeSestreItem);
		this.pacijentiMenu.add(pacijentiItem);
		this.pacijentiMenu.add(preglediItem);
		this.pacijentiMenu.add(knjiziceItem);
		
		this.mainMenu.add(zaposleniMenu);
		this.mainMenu.add(pacijentiMenu);
		
		setJMenuBar(this.mainMenu);
		
	}

	private void initActions() {
		lekariItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LekariPrikaz lp = new LekariPrikaz(bolnica);
				lp.setVisible(true);
			}
		});
		medicinskeSestreItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SestrePrikaz sp = new SestrePrikaz(bolnica);
				sp.setVisible(true);
			}
		});
		pacijentiItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentiPrikaz pp = new PacijentiPrikaz(bolnica);
				pp.setVisible(true);
			}
		});
		preglediItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PreglediPrikaz prp = new PreglediPrikaz(bolnica);
				prp.setVisible(true);
			}
		});
		knjiziceItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				KnjizicePrikaz kp = new KnjizicePrikaz(bolnica);
				kp.setVisible(true);
			}
		});
		
	}


	

}
