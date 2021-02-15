package entiteti;

import java.util.ArrayList;

import enumeracije.Pol;

public class Pacijent extends Osoba {
	private String identifikacioniKodLekara;
	private String brojKnjizice;
	private ArrayList <Pregled> pregledi;
	
	public Pacijent() {
		super();
		this.identifikacioniKodLekara = "";
		this.brojKnjizice = "";
		this.pregledi = new ArrayList<Pregled>();
	}
	
	public Pacijent(String identifikacioniKod, String ime, String prezime, int jmbg, Pol pol, String adresa, int brojTelefona,
			String korisnickoIme, String lozinka, String uloga, String identifikacioniKodLekara, String brojKnjizice, ArrayList<Pregled> pregledi) {
		super(identifikacioniKod, ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, uloga );
		this.identifikacioniKodLekara = identifikacioniKodLekara;
		this.brojKnjizice = brojKnjizice;
		this.pregledi = pregledi;
	}
	
	public Pacijent(Pacijent original) {
		super(original);
		this.identifikacioniKodLekara = original.identifikacioniKodLekara;
		this.brojKnjizice = original.brojKnjizice;
		this.pregledi = original.pregledi;
	}

	public String getIdentifikacioniKodLekara() {
		return identifikacioniKodLekara;
	}

	public void setIdentifikacioniKodLekara(String identifikacioniKodLekara) {
		this.identifikacioniKodLekara = identifikacioniKodLekara;
	}

	public String getBrojKnjizice() {
		return brojKnjizice;
	}

	public void setBrojKnjizice(String brojKnjizice) {
		this.brojKnjizice = brojKnjizice;
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
				"\nidentifikacioniKodLekara: " + this.identifikacioniKodLekara +
				"\nbrojKnjizice: " + this.brojKnjizice;
		for (Pregled pregled : pregledi) {
			s += "\n" + pregled;
		}
		return s;
	}

}
