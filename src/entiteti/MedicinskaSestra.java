package entiteti;

import enumeracije.Pol;
import enumeracije.Sluzba;

public class MedicinskaSestra extends Osoba {
	
	private double plata;
	private Sluzba sluzba;

	public MedicinskaSestra() {
		super();
		this.plata = 0;
		this.sluzba = Sluzba.SLUZBA_OPSTE_MEDICINE;
	}
	
	public MedicinskaSestra (String identifikacioniKod, String ime, String prezime, int jmbg, Pol pol, String adresa, int brojTelefona,
			String korisnickoIme, String lozinka, String uloga, double plata, Sluzba sluzba) {
		super (identifikacioniKod, ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga);
		this.plata = plata;
		this.sluzba = sluzba;
	}
	
	public MedicinskaSestra(MedicinskaSestra original) {
		super(original);
		this.plata = original.plata;
		this.sluzba = original.sluzba;
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

	@Override
	public String toString() {
		return super.toString() +
				"\nplata: " + plata;
	}
	
}
