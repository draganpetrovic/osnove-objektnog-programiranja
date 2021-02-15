package enumeracije;

public enum KategorijaOsiguranja {
	PRVA,
	DRUGA,
	TRECA;
	
	public static KategorijaOsiguranja fromInt(int a ) {
		switch(a) {
		case 1:
			return PRVA;
		case 2:
			return DRUGA;
		default:
			return TRECA;
		}
	}
	
	public static int toInt(KategorijaOsiguranja osiguranje) {
	switch (osiguranje) {
	case PRVA:
		return 1;
	case DRUGA:
		return 2;
	default:
		return 3;
		}
	}
	
}
