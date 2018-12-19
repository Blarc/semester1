import java.util.Arrays;
import java.util.Scanner;

public class projekt_ver3 {
	
	
	public static class Prodaja {
		String ime;
		int idProdaja;
		int zeljeni_cas;
		int kolicina;
		
		public Prodaja(String ime, int idProdaja, int zeljeni_cas, int kolicina) {
			this.ime = ime;
			this.idProdaja = idProdaja;
			this.zeljeni_cas = zeljeni_cas;
			this.kolicina = kolicina;
		}
		
	}
	
	public static class Izdelek {
		String ime;
		int idIzdelek;
		int cas;
		int sarza;
		int zaloga;
		
		public Izdelek(String ime, int id, int cas, int sarza) {
			this.idIzdelek = idIzdelek;
			this.ime = ime;
			this.cas = cas;
			this.sarza = sarza;
			this.zaloga = 0;
		}
	}
	
	
	public static void izpisProizvodnja(Izdelek[] proizvodnja) {
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Izdelek[] izdelki = new Izdelek[10];
		Prodaja[] prodaje = new Prodaja[10];
		
		
		prodaje[1] = new Prodaja("cokolada", 1, 8, 5000);
		prodaje[2] = new Prodaja("marmelada", 2, 5, 10000);
		prodaje[3] = new Prodaja("sok", 3, 3, 25000);
		
		izdelki[1] = new Izdelek("cokolada", 1, 1, 5000);
		izdelki[2] = new Izdelek("marmelada", 2, 2, 2000);
		izdelki[3] = new Izdelek("sok", 3, 3, 10000);
		
		Izdelek[] proizvodnja = new Izdelek[20];
		
		System.out.println("---------------------- UKAZI ------------------------");
		System.out.println("Kuhanje izdelka: skuhaj id_izdelka");
		System.out.println("Sprintaj proizvodnjo: proizvodnja");
		System.out.println("Sprintaj prodajo: prodaja");
		System.out.println("-----------------------------------------------------");
		
		int count = 0;
		int ura = 0;
		
		while(true) {
			String[] line = sc.nextLine().split(" ");
			String ukaz = line[0];
			switch(ukaz) {
            case "skuhaj":
            	int id = Integer.parseInt(line[1]);
            	proizvodnja[count] = izdelki[id];
            	System.out.println(izdelki[id].ime + " uspesno dodan v proizvodnjo!");
                break;
            case "proizvodnja":
                break;
            case "prodaja":
                break;
            default: System.out.println("neveljaven ukaz");
			}
		}
	}
}
