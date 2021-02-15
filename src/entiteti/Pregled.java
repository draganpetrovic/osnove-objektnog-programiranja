package entiteti;

import enumeracije.StatusPregleda;

public class Pregled {

	private String termin;
	private String soba;
	private String opis;
	private StatusPregleda status;
	
	public Pregled() {
		this.termin = "";
		this.soba = "";
		this.opis = "";
		this.status = StatusPregleda.ZAKAZAN;
	}
	
	public Pregled( String termin, String soba, String opis, StatusPregleda status) {
		super();
		this.termin = termin;
		this.soba = soba;
		this.opis = opis;
		this.status = status;
	}
	
	public Pregled(Pregled original) {
		this.termin = original.termin;
		this.soba = original.soba;
		this.opis = original.opis;
		this.status = original.status;
	}

	public String getTermin() {
		return termin;
	}

	public void setTermin(String termin) {
		this.termin = termin;
	}

	public String getSoba() {
		return soba;
	}

	public void setSoba(String soba) {
		this.soba = soba;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public StatusPregleda getStatus() {
		return status;
	}

	public void setStatus(StatusPregleda status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return 	"\ntermin: " + termin 
				+ "\nsoba: " + soba
				+ "\nopis: " + opis 
				+ "\nstatus: " + status;
	}
	
	
}
