package entiteti;

import java.util.ArrayList;

import enumeracije.Pol;
import enumeracije.Sluzba;

public class Lekar extends Osoba{
	private double plata;
	private Sluzba sluzba;
	private String specijalizacija;
	private ArrayList<Pregled> pregledi;

	public Lekar() {
		super();
		this.plata = 0;
		this.sluzba = Sluzba.SLUZBA_OPSTE_MEDICINE;
		this.specijalizacija = "";
		this.pregledi = new ArrayList<Pregled>();
	}
	
	
	public Lekar (String identifikacioniKod, String ime, String prezime, int jmbg, Pol pol, String adresa, int brojTelefona,
			String korisnickoIme, String lozinka, String uloga, double plata, Sluzba sluzba, String specijalizacija, ArrayList<Pregled> pregledi) {
		super(identifikacioniKod, ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga );
		this.plata = plata;
		this.sluzba = sluzba;
		this.specijalizacija = specijalizacija;
		this.pregledi = pregledi;
	}
	
	public Lekar(Lekar original) {
		super(original);
		this.plata = original.plata;
		this.sluzba = original.sluzba;
		this.specijalizacija = original.specijalizacija;
		this.pregledi = original.pregledi;
	}
	
	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public Sluzba getSluzba() {
		return sluzba;
	}

	public void setSluzba(Sluzba sluzba) {
		this.sluzba = sluzba;
	}

	public String getSpecijalizacija() {
		return specijalizacija;
	}

	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}

	public ArrayList<Pregled> getPregledi() {
		return pregledi;
	}

	public void setPregledi(ArrayList<Pregled> pregledi) {
		this.pregledi = pregledi;
	}
	
	@Override
	public String toString() {
		String s = super.toString() +
				"\nplata: " + this.plata +
				"\nspecijalizacija: " + this.specijalizacija;
		for (Pregled pregled : pregledi) {
			s += "\n" + pregled;
		}
		return s;
	}
}

