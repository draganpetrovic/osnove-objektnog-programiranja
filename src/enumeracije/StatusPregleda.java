package enumeracije;

public enum StatusPregleda {
	ZATRAZEN, //1
	ZAKAZAN, //2
	OTKAZAN, //3
	ZAVRSEN; //4
	
	public static StatusPregleda fromInt(int a) {
		switch(a) {
		case 1:
			return ZATRAZEN;
		case 2:
			return ZAKAZAN;
		case 3:
			return OTKAZAN;
		default:
			return ZAVRSEN;
		}
	}
	
	public static int toInt(StatusPregleda status) {
		switch (status) {
		case ZATRAZEN:
			return 1;
		case ZAKAZAN:
			return 2;
		case OTKAZAN:
			return 3;
		default:
			return 4;
			}
		}
	
}
