package enumeracije;

public enum Sluzba {
	SLUZBA_ZDRAVSTVENE_ZASTITE_DECE, //1
	STOMATOLOSKA_SLUZBA, //2
	SLUZBA_ZDRAVSTVENE_ZASTITE_RADNIKA, //3
	SLUZBA_ZA_PRAVNE_I_EKONOMSKE_POSLOVE, //4
	SLUZBA_ZA_TEHNICKE_POSLOVE, //5
	SLUZBA_OPSTE_MEDICINE; //6
	
	public static Sluzba fromInt(int a) {
		switch(a) {
		case 1:
			return SLUZBA_ZDRAVSTVENE_ZASTITE_DECE;
		case 2:
			return STOMATOLOSKA_SLUZBA;
		case 3:
			return SLUZBA_ZDRAVSTVENE_ZASTITE_RADNIKA;
		case 4:
			return SLUZBA_ZA_PRAVNE_I_EKONOMSKE_POSLOVE;
		case 5:
			return SLUZBA_ZA_TEHNICKE_POSLOVE;
		default:
			return SLUZBA_OPSTE_MEDICINE;
		}
	}
	
	public static int toInt(Sluzba sluzba) {
		switch (sluzba) {
		case SLUZBA_ZDRAVSTVENE_ZASTITE_DECE:
			return 1;
		case STOMATOLOSKA_SLUZBA:
			return 2;
		case SLUZBA_ZDRAVSTVENE_ZASTITE_RADNIKA:
			return 3;
		case SLUZBA_ZA_PRAVNE_I_EKONOMSKE_POSLOVE: 
			return 4;
		case SLUZBA_ZA_TEHNICKE_POSLOVE:
			return 5;
		default:
			return 6;
		}
		
	}
	
}
