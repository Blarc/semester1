import java.util.Arrays;
import java.util.Scanner;

public class projekt_ver1 {
	
	
	public static class Prodaja {
		String ime;
		int id;
		int id_izdelka;
		int zeljeni_cas;
		int kolicina;
		
		public Prodaja(String ime, int id, int id_izdelka, int zeljeni_cas, int kolicina) {
			this.ime = ime;
			this.id = id;
			this.id_izdelka = id_izdelka;
			this.zeljeni_cas = zeljeni_cas;
			this.kolicina = kolicina;
		}
		
		public String toString() {
			return this.ime + " " + this.kolicina + " ob " + this.zeljeni_cas;
		}
	}
	
	public static class Izdelek {
		String ime;
		int id;
		int cas;
		int sarza;
		int zaloga;
		
		public Izdelek(String ime, int id, int cas, int sarza) {
			this.id = id;
			this.ime = ime;
			this.cas = cas;
			this.sarza = sarza;
			this.zaloga = 0;
		}
		
		public int skuhaj(int zac_cas) {
			System.out.println("Zacel kuhati " + ime + " ob " + zac_cas + " |RU: " + cas + " |predviden konec ob " + (zac_cas + cas) + " |sarza: " + sarza);
			zaloga += sarza;
			return cas;
		}
	}
	
	public static Prodaja preveriProdajo(Prodaja[] prodaje, Izdelek atm, int cas) {
		for (int i = 1; i < prodaje.length && prodaje[i] != null; i++) {
			if (prodaje[i].id_izdelka == atm.id) {
				if (prodaje[i].zeljeni_cas <= cas) {
					if (prodaje[i].kolicina <= atm.zaloga) {
						atm.zaloga -= prodaje[i].kolicina;
						return prodaje[i];
					}
				}
			}
		}
		return null;
	}
	
	public static void sprintajProdajo(Prodaja[] prodaje) {
		System.out.println("---------------------- PRODAJA ----------------------");
		for (int i = 1; i < prodaje.length && prodaje[i] != null; i++) {
			System.out.println(prodaje[i].toString());
		}
		System.out.println("-----------------------------------------------------");
	}
	
	public static void sprintajProizvodnjo(Izdelek[] izdelki, Prodaja[] prodaje, int[][] potekIzdelave, int[][] potekProdaje) {
		System.out.println("------------- PROIZVODNJA ter PRODAJA ---------------");
		for (int i = 1; i < potekIzdelave.length && potekIzdelave[i][0] > 0; i++) {
			Izdelek atm = izdelki[potekIzdelave[i][0]];
			System.out.println("Zacel kuhati " + atm.ime + " ob " + potekIzdelave[i][1] + " |RU: " + atm.cas + " |predviden konec ob " + potekIzdelave[i][2] + " |sarza: " + atm.sarza + " |zaloga: " + atm.zaloga);
			if (potekProdaje[i][0] != 0) {
				System.out.println("PRODANO: " + prodaje[potekProdaje[i][3]] + " kolicina " + potekProdaje[i][2] + " zeljeni cas: " + potekProdaje[i][0] + " dejanski cas: " + potekProdaje[i][1]);
			}
		}
		System.out.println("-----------------------------------------------------");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int dejanskiCas = 0;
		
		Izdelek[] izdelki = new Izdelek[10];
		Prodaja[] prodaje = new Prodaja[10];
		
		int steviloIzdelkov = 3;
		int[][] potekIzdelave = new int[100][3];
		int[][] potekProdaje = new int[100][4];
		int count = 1;
		
		prodaje[1] = new Prodaja("cokolada", 1, 1, 8, 5000);
		prodaje[2] = new Prodaja("marmelada", 2, 2, 5, 10000);
		prodaje[3] = new Prodaja("sok", 3, 3, 3, 25000);
		
		izdelki[1] = new Izdelek("cokolada", 1, 1, 5000);
		izdelki[2] = new Izdelek("marmelada", 2, 2, 2000);
		izdelki[3] = new Izdelek("sok", 3, 3, 10000);
		
		System.out.println("---------------------- UKAZI ------------------------");
		//System.out.println("Dodajanje izdelka: izdelek ime_izdelka id_izdelka cas sarza");
		System.out.println("Kuhanje izdelka: skuhaj id_izdelka");
		System.out.println("Sprintaj proizvodnjo: proizvodnja");
		System.out.println("Sprintaj prodajo: prodaja");
		System.out.println("-----------------------------------------------------");
		
		
		while(true) {
			String[] line = sc.nextLine().split(" ");
			String ukaz = line[0];
			switch(ukaz) {
            case "izdelek":
            	izdelki[steviloIzdelkov] = new Izdelek(line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            	steviloIzdelkov++;
                break;
            case "skuhaj":
            	Prodaja atm;
            	int id = Integer.parseInt(line[1]);
            	potekIzdelave[count][0] = id;
            	potekIzdelave[count][1] = dejanskiCas;
            	dejanskiCas += izdelki[id].skuhaj(dejanskiCas);
            	potekIzdelave[count][2] = dejanskiCas;
            	if ((atm = preveriProdajo(prodaje, izdelki[id], dejanskiCas)) != null) {
            		potekProdaje[count][0] = atm.zeljeni_cas;
            		potekProdaje[count][1] = dejanskiCas;
            		potekProdaje[count][2] = atm.kolicina;
            		potekProdaje[count][3] = atm.id;
            	}
            	
            	count++;
                break;
            case "proizvodnja":
            	sprintajProizvodnjo(izdelki, prodaje, potekIzdelave, potekProdaje);
                break;
            case "prodaja":
            	sprintajProdajo(prodaje);
                break;
            default: System.out.println("neveljaven ukaz");
			}
		}
		
	}

}
