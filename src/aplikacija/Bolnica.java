package aplikacija;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entiteti.Lekar;
import entiteti.MedicinskaSestra;
import entiteti.Osoba;
import entiteti.Pacijent;
import entiteti.Pregled;
import entiteti.ZdravstvenaKnjizica;
import enumeracije.KategorijaOsiguranja;
import enumeracije.Pol;
import enumeracije.Sluzba;
import enumeracije.StatusPregleda;

public class Bolnica {
	private ArrayList<Osoba> osobe;
	private ArrayList<Lekar> lekari;
	private ArrayList<MedicinskaSestra> sestre;
	private ArrayList<Pacijent> pacijenti;
	private ArrayList<ZdravstvenaKnjizica> knjizice;
	private ArrayList<Pregled> pregledi;
	
	public Bolnica() {
		this.osobe = new ArrayList<Osoba>();
		this.lekari = new ArrayList<Lekar>();
		this.sestre = new ArrayList<MedicinskaSestra>();
		this.pacijenti = new ArrayList<Pacijent>();
		this.knjizice = new ArrayList<ZdravstvenaKnjizica>();
		this.pregledi = new ArrayList<Pregled>();
	}
	
	//Proverava ispravnost unetog korisnickog imena i lozinke prilikom logovanja na sistem.
	public Osoba login(String korisnickoIme, String lozinka) {
		for (Osoba osoba : osobe) {
			if(osoba.getKorisnickoIme().equals(korisnickoIme) &&
					osoba.getLozinka().equals(lozinka)) {
				return osoba;
			}
		}
		return null;
	}
	
	public void ucitajKorisnike() {
		try {
			File korisniciFile = new File("src/podaci/korisnici.txt");
			BufferedReader br = new BufferedReader(new FileReader(korisniciFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] split = linija.split("\\|");
				String identifikacioniKod = split[0];
				String ime = split[1];
				String prezime = split[2];
				String jmbgString = split[3];
				int jmbg = Integer.parseInt(jmbgString);
				int polInt = Integer.parseInt(split[4]);
				Pol pol = Pol.fromInt(polInt);
				String adresa = split[5];
				String brojTelefonaString = split[6];
				int brojTelefona = Integer.parseInt(brojTelefonaString);
				String korisnickoIme = split[7];
				String lozinka =split[8];
				String uloga = split[9];
				Osoba osoba = new Osoba (identifikacioniKod, ime, prezime,jmbg, pol, adresa, brojTelefona,
							korisnickoIme, lozinka, uloga);
				osobe.add(osoba);
				if(uloga.equals("Lekar")) {
					String plataString = split[10];
					double plata = Double.parseDouble(plataString);
					int sluzbaInt = Integer.parseInt(split[11]);
					Sluzba sluzba = Sluzba.fromInt(sluzbaInt);
					String specijalizacija = split[12];
					ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
					Lekar lekar = new Lekar(identifikacioniKod, ime, prezime,jmbg, pol, adresa, brojTelefona,
							korisnickoIme, lozinka, uloga, plata, sluzba, specijalizacija, pregledi);
					lekari.add(lekar);
				}else if(uloga.equals("MedicinskaSestra")) {
					String plataString = split[10];
					double plata = Double.parseDouble(plataString);
					int sluzbaInt = Integer.parseInt(split[11]);
					Sluzba sluzba = Sluzba.fromInt(sluzbaInt);
					MedicinskaSestra sestra = new MedicinskaSestra(identifikacioniKod, ime, prezime, jmbg, pol, adresa, brojTelefona,
							korisnickoIme, lozinka, uloga, plata, sluzba);
					sestre.add(sestra);
				}else if (uloga.equals("Pacijent")) {
					String identifikacioniKodLekara = split[10];
					String brojKnjizice = split[11];
					ArrayList<Pregled> pregledi = new ArrayList<Pregled>();
					Pacijent pacijent = new Pacijent(identifikacioniKod, ime, prezime,jmbg, pol, adresa, brojTelefona,
							korisnickoIme, lozinka, uloga, identifikacioniKodLekara, brojKnjizice, pregledi);
					pacijenti.add(pacijent);
				}
			}br.close();	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void snimiKorisnike() {
		try {
			File korisniciFile = new File("src/podaci/korisnici.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(korisniciFile));
			String sadrzaj = "";
			for (Lekar lekar : lekari) {
				Pol polStr = lekar.getPol();
				int pol = Pol.toInt(polStr);
				Sluzba sluzbaStr = lekar.getSluzba();
				int sluzba = Sluzba.toInt(sluzbaStr);
				sadrzaj += lekar.getIdentifikacioniKod() + "|" +
							lekar.getIme() + "|" +
							lekar.getPrezime()  + "|" +
							lekar.getJmbg()  + "|" +
							pol  + "|" +
							lekar.getAdresa()  + "|" +
							lekar.getBrojTelefona()  + "|" +
							lekar.getKorisnickoIme()  + "|" +
							lekar.getLozinka()  + "|" +
							lekar.getUloga() + "|" +
							lekar.getPlata()  + "|" +
							sluzba + "|" +
							lekar.getSpecijalizacija() + "\n";
			}
			
			for (MedicinskaSestra sestra : sestre) {
				Pol polStr = sestra.getPol();
				int pol = Pol.toInt(polStr);
				Sluzba sluzbaStr = sestra.getSluzba();
				int sluzba = Sluzba.toInt(sluzbaStr);
				sadrzaj += sestra.getIdentifikacioniKod() + "|" +
						sestra.getIme() + "|" +
						sestra.getPrezime()  + "|" +
						sestra.getJmbg()  + "|" +
						pol + "|" +
						sestra.getAdresa()  + "|" +
						sestra.getBrojTelefona()  + "|" +
						sestra.getKorisnickoIme()  + "|" +
						sestra.getLozinka()  + "|" +
						sestra.getUloga() + "|" +
						sestra.getPlata()  + "|" +
						sluzba + "\n";
			}
			
			for (Pacijent pacijent : pacijenti) {
				Pol polStr = pacijent.getPol();
				int pol = Pol.toInt(polStr);
				sadrzaj += pacijent.getIdentifikacioniKod() + "|" +
						pacijent.getIme() + "|" +
						pacijent.getPrezime()  + "|" +
						pacijent.getJmbg()  + "|" +
						pol  + "|" +
						pacijent.getAdresa()  + "|" +
						pacijent.getBrojTelefona()  + "|" +
						pacijent.getKorisnickoIme()  + "|" +
						pacijent.getLozinka()  + "|" +
						pacijent.getUloga() + "|" +
						pacijent.getIdentifikacioniKodLekara() + "|" +
						pacijent.getBrojKnjizice() + "\n";
			}
			writer.write(sadrzaj);
			writer.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			}
		}
	
	
	public Osoba nadjiKorisnika(String identifikacioniKod) {
		for (Pacijent pacijent : pacijenti) {
			if(pacijent.getIdentifikacioniKod().equals(identifikacioniKod)) {
				return pacijent;
			}
		}
		for(Lekar lekar: lekari) {
			if(lekar.getIdentifikacioniKod().equals(identifikacioniKod)) {
				return lekar;
			}
		}
		for(MedicinskaSestra sestra: sestre) {
			if(sestra.getIdentifikacioniKod().equals(identifikacioniKod)) {
				return sestra;
			}
		}
		return null;
	}
	
	public void ucitajZdravstveneKnjizice() {
		try {
			File knjiziceFile = new File("src/podaci/knjizice.txt");
			BufferedReader br = new BufferedReader(new FileReader(knjiziceFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] split = linija.split("\\|");
				String broj = split[0];
				String datumIsteka = split[1];
				int koInt = Integer.parseInt(split[2]);
				KategorijaOsiguranja osiguranje = KategorijaOsiguranja.fromInt(koInt);
				String pacijentKod = split[3];
				ZdravstvenaKnjizica knjizica = new ZdravstvenaKnjizica(broj, datumIsteka, osiguranje, pacijentKod);
				knjizice.add(knjizica);
			}
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	
	
	public void snimiZdravstveneKnjizice() {
		try {
			File knjiziceFile = new File("src/podaci/knjizice.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(knjiziceFile));
			String sadrzaj = "";
			for (ZdravstvenaKnjizica knjizica : knjizice) {
				KategorijaOsiguranja osiguranjeStr = knjizica.getOsiguranje();
				int osiguranje = KategorijaOsiguranja.toInt(osiguranjeStr);
				sadrzaj += knjizica.getBroj() + "|" +
							knjizica.getDatumIsteka() + "|" +
							osiguranje + "|" +
							knjizica.getPacijentKod() + "\n";
			}
			writer.write(sadrzaj);
			writer.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	
	
	
	
	public void ucitajPreglede() {
		try {
			File preglediFile = new File("src/podaci/pregledi.txt");
			BufferedReader br = new BufferedReader(new FileReader(preglediFile));
			String linija = null;
			while ((linija = br.readLine()) != null) {
				String[] split = linija.split("\\|");
				String termin = split[0];
				String soba = split[1];
				String opis = split[2];
				int statusInt = Integer.parseInt(split[3]);
				StatusPregleda status = StatusPregleda.fromInt(statusInt);
				String pacijentKod = split[4];
				String lekarKod = split[5];
				Pregled pregled = new Pregled(termin, soba, opis, status);
				Pacijent pacijent = (Pacijent) nadjiKorisnika(pacijentKod);
				Lekar lekar = (Lekar) nadjiKorisnika(lekarKod);
				if(pacijent != null && lekar != null) {
					pacijent.getPregledi().add(pregled);
					lekar.getPregledi().add(pregled);
				}
				pregledi.add(pregled);
			}
			br.close();
			
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	
	
	
	public void snimiPreglede() {
		try {
			File preglediFile = new File("src/podaci/pregledi.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(preglediFile));
			String sadrzaj = "";
			for (Pregled pregled : pregledi) {
				StatusPregleda statusString = pregled.getStatus();
				int status = StatusPregleda.toInt(statusString);
				sadrzaj += pregled.getTermin() + "|" +
							pregled.getSoba() + "|" +
							pregled.getOpis() + "|" +
							status + "|";
				Pacijent pacijent = pronadjiPacijenta(pregled);
				Lekar lekar = pronadjiLekara(pregled);
				if(pacijent != null && lekar != null) {
					sadrzaj += pacijent.getIdentifikacioniKod() + "|" +
								lekar.getIdentifikacioniKod() + "\n";
				}
			}
			writer.write(sadrzaj);
			writer.close();
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	
	public Pacijent pronadjiPacijenta(Pregled pregled) {
		for (Pacijent pacijent : pacijenti) {
			if(pacijent.getPregledi().contains(pregled)) {
				return pacijent;
			}
		}
		return null;
	}
	
	
	public Lekar pronadjiLekara(Pregled pregled) {
		for(Lekar lekar : lekari) {
			if(lekar.getPregledi().contains(pregled)) {
				return lekar;
			}
		}
		return null;
	}
	
	public Lekar nadjiLekara(String korisnickoIme) {
		for (Lekar lekar : lekari) {
			if(lekar.getKorisnickoIme().equals(korisnickoIme)) {
				return lekar;
			}
		}
		return null;
	}
	
	
	public Lekar nadjiLekaraID(String identifikacioniKod) {
		for (Lekar lekar : lekari) {
			if(lekar.getIdentifikacioniKod().equals(identifikacioniKod)) {
				return lekar;
			}
		}
		return null;
	}
	
	
	
	public boolean jedinstvenoKorisnickoIme(String korisnickoIme) {
		for(Osoba osoba : osobe) {
			if(osoba.getKorisnickoIme().equals(korisnickoIme)) {
				return true;
			}
		}return false;
	}
	
	public boolean jedinstveniID(String identifikacioniKod) {
		for(Osoba osoba : osobe) {
			if(osoba.getIdentifikacioniKod().equals(identifikacioniKod)) {
				return true;
			}
		}return false;
	}

	public MedicinskaSestra nadjiMedicinskuSestru(String korisnickoIme) {
		for(MedicinskaSestra sestra : sestre) {
			if(sestra.getKorisnickoIme().equals(korisnickoIme)) {
				return sestra;
			}
		}return null;
	}
	
	public Pacijent nadjiPacijenta(String korisnickoIme) {
		for(Pacijent pacijent : pacijenti) {
			if(pacijent.getKorisnickoIme().equals(korisnickoIme)) {
				return pacijent;
			}
		}return null;
	}
	
	public Pacijent nadjiPacijentaID(String identifikacioniKod) {
		for (Pacijent pacijent : pacijenti) {
			if(pacijent.getIdentifikacioniKod().equals(identifikacioniKod)) {
				return pacijent;
			}
		}
		return null;
	}
	
	public ZdravstvenaKnjizica nadjiKnjizicu(String broj) {
		for(ZdravstvenaKnjizica knjizica: knjizice) {
			if(knjizica.getBroj().equals(broj)) {
				return knjizica;
			}
		}return null;
	}
	
	public Pregled nadjiPregled(String termin) {
		for(Pregled pregled: pregledi) {
			if(pregled.getTermin().equals(termin)) {
				return pregled;
			}
		}return null;
	}
	
	public boolean jedinstveniBroj(String broj) {
		for(ZdravstvenaKnjizica knjizica : knjizice) {
			if(knjizica.getBroj().equals(broj)) {
				return true;
			}
		}return false;
	}
	
	
	
	public ArrayList<Lekar> getLekari() {
		return lekari;
	}
	
	
	public ArrayList<Pacijent> getPacijenti() {
		return pacijenti;
	}

	public ArrayList<ZdravstvenaKnjizica> getKnjizice() {
		return knjizice;
	}

	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}

	public ArrayList<Osoba> getOsobe() {
		return osobe;
	}

	public ArrayList<MedicinskaSestra> getSestre() {
		return sestre;
	}
	
	
	public String datumFormat(String termin, String sifraLekara) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd HH:mm");
		LocalDateTime vreme = LocalDateTime.parse(termin, formatter);
		try {
			Lekar lekar = nadjiLekaraID(sifraLekara);
			for(Pregled pregled : lekar.getPregledi()) {
				LocalDateTime datum = LocalDateTime.parse(pregled.getTermin(),formatter);
				LocalDateTime vremeDodato= vreme.plusMinutes(15);
				LocalDateTime vremeOduzeto = vreme.minusMinutes(15);
				
				if(datum.isAfter(vremeOduzeto) && datum.isBefore(vremeDodato)) {
					JOptionPane.showMessageDialog(null, "Preklapanje termina pregleda.", "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
					return null;		
					}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			}
		return termin;
	}
		
	public String racunPregleda(String broj) {
		
		ZdravstvenaKnjizica knjizica = nadjiKnjizicu(broj);
		if(knjizica.getOsiguranje().equals(KategorijaOsiguranja.PRVA)) {
			String cena = "300";
			return cena;
		}else if(knjizica.getOsiguranje().equals(KategorijaOsiguranja.DRUGA)) {
			String cena = "50";
			return cena;
		}else {
			String cena = "Besplatan";
			return cena;
		}
	}
	
	
	
}
	
	

	
	
	
	
	
	

	
	