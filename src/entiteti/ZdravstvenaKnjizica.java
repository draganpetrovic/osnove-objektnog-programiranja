package entiteti;

import enumeracije.KategorijaOsiguranja;

public class ZdravstvenaKnjizica {
	private String broj;
	private String datumIsteka;
	private KategorijaOsiguranja osiguranje;
	private String pacijentKod;
	
	public ZdravstvenaKnjizica() {
		this.broj = "";
		this.datumIsteka = "";
		this.osiguranje = KategorijaOsiguranja.PRVA;
	}
	
	public ZdravstvenaKnjizica(String broj, String datumIsteka, KategorijaOsiguranja osiguranje, String pacijentKod) {
		super();
		this.broj = broj;
		this.datumIsteka = datumIsteka;
		this.osiguranje = osiguranje;
		this.pacijentKod = pacijentKod;
	}
	
	public ZdravstvenaKnjizica(ZdravstvenaKnjizica original) {
		this.broj = original.broj;
		this.datumIsteka = original.datumIsteka;
		this.osiguranje = original.osiguranje;
		this.pacijentKod = original.pacijentKod;
	}

	public String getPacijentKod() {
		return pacijentKod;
	}

	public void setPacijentKod(String pacijentKod) {
		this.pacijentKod = pacijentKod;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getDatumIsteka() {
		return datumIsteka;
	}

	public void setDatumIsteka(String datumIsteka) {
		this.datumIsteka = datumIsteka;
	}

	public KategorijaOsiguranja getOsiguranje() {
		return osiguranje;
	}

	public void setOsiguranje(KategorijaOsiguranja osiguranje) {
		this.osiguranje = osiguranje;
	}


	
	@Override
	public String toString() {
		return "\nbroj: " + broj
				+ "\ndatumIsteka: " + datumIsteka
				+ "\nosiguranje: " + osiguranje
				+ "\npacijentKod: " + pacijentKod;
	}
	
}
