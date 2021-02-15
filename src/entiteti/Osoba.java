package entiteti;

import enumeracije.Pol;

public class Osoba {
	
	protected String identifikacioniKod;
	protected String ime;
	protected String prezime;
	protected int jmbg;
	protected  Pol pol;
	protected String adresa;
	protected int brojTelefona;
	protected String korisnickoIme;
	protected String lozinka;
	protected String uloga;
	
	public Osoba() {
		this.identifikacioniKod = "";
		this.ime = "";
		this.prezime = "";
		this.jmbg = 0;
		this.pol = Pol.ZENSKI;
		this.adresa = "";
		this.brojTelefona = 0;
		this.korisnickoIme = "";
		this.lozinka = "";
		this.uloga = "";
	}
	
	public Osoba(String identifikacioniKod, String ime, String prezime, int jmbg, Pol pol, String adresa, int brojTelefona, String korisnickoIme, String lozinka, String uloga) {
		super();
		this.identifikacioniKod = identifikacioniKod;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.adresa = adresa;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.uloga = uloga;
	}

	public Osoba(Osoba original) {
		this.identifikacioniKod = original.identifikacioniKod;
		this.ime = original.ime;
		this.prezime = original.prezime;
		this.jmbg = original.jmbg;
		this.pol = original.pol;
		this.adresa = original.adresa;
		this.brojTelefona = original.brojTelefona;
		this.korisnickoIme = original.korisnickoIme;
		this.lozinka = original.lozinka;
		this.uloga = original.uloga;
	}

	public String getIdentifikacioniKod() {
		return identifikacioniKod;
	}

	public void setIdentifikacioniKod(String identifikacioniKod) {
		this.identifikacioniKod = identifikacioniKod;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getJmbg() {
		return jmbg;
	}

	public void setJmbg(int jmbg) {
		this.jmbg = jmbg;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(int brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	
	@Override
	public String toString() {
		return  "\nidentifikacioniKod: " + identifikacioniKod
				+ "\nime: " + ime
				+ "\nprezime=" + prezime
				+ "\njmbg=" + jmbg 
				+ "\nadresa=" + adresa
				+ "\nbrojTelefona=" + brojTelefona
				+ "\nkorisnickoIme=" + korisnickoIme
				+ "\nlozinka=" + lozinka
				+ "\nuloga=" + uloga;
	}
}
