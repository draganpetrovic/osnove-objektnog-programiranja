package aplikacija;

import gui.Login;

public class BolnicaMain {

	public static void main(String[] args) {
		Bolnica bolnica = new Bolnica();
		bolnica.ucitajKorisnike();
		bolnica.ucitajPreglede();
		bolnica.ucitajZdravstveneKnjizice();
		
		Login login = new Login(bolnica);
		login.setVisible(true);
	}

}
