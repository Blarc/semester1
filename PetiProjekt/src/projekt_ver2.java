import java.util.Arrays;
import java.util.Scanner;

public class projekt_ver2 {
	
	
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
		
		public int skuhaj(int zac_cas, int[][]potekIzdelave, int count) {
			System.out.println("Zacel kuhati " + ime + " ob " + zac_cas + " |RU: " + cas + " |predviden konec ob " + (zac_cas + cas) + " |sarza: " + sarza);
			potekIzdelave[count][0] = zac_cas + cas;
			potekIzdelave[count][1] = id;
			return cas;
		}
		
		public String toString() {
			return ime + "("+ id + ") trenutna zaloga: " + zaloga;
		}
	}
	
	public static void povecajZalogo(Izdelek[] seznam, int[][] potekIzdelave, int cas) {
		for (int i = 0; i < potekIzdelave.length; i++) {
			if (potekIzdelave[i][0] <= cas && potekIzdelave[i][1] > 0) {
				Izdelek atm = seznam[potekIzdelave[i][1]];
				atm.zaloga += atm.sarza;
				potekIzdelave[i][0] = 0;
				potekIzdelave[i][1] = 0;
			}
			else {
				break;
			}
		}
	}
	
	public static void sprintajZalogo(Izdelek[] seznam) {
		System.out.println("--------- TRENUTNA ZALOGA ----------");
		for (int i = 0; i < seznam.length; i++) {
			if (seznam[i] != null) {
				System.out.println(seznam[i].toString());
			}
			else {
				System.out.println("------------------------------------");
				return;
			}
		}
		System.out.println("------------------------------");
	}
	
	/*public static void sprintajProizvodnjo(Izdelek[] seznam, int[][] potekIzdelave, int zac_cas) {
		System.out.println("--------------------- PROIZVODNJA -------------------");
		for (int i = 0; i < potekIzdelave.length; i++) {
			seznam[potekIzdelave[i]].skuhaj(zac_cas);
		}
	}*/

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int dejanskiCas = 0;
		int casKuhanja = 0;
		int count = 0;
		Izdelek[] izdelki = new Izdelek[10];
		int steviloIzdelkov = 3;
		int[][] potekIzdelave = new int[10][2];
		
		izdelki[0] = new Izdelek("cokolada", 0, 5, 5000);
		izdelki[1] = new Izdelek("marmelada", 1, 7, 2000);
		izdelki[2] = new Izdelek("sok", 2, 2, 10000);
		
		System.out.println("------------ UKAZI ----------------");
		//System.out.println("Dodajanje izdelka: izdelek ime_izdelka id_izdelka cas sarza");
		System.out.println("Kuhanje izdelka: skuhaj id_izdelka");
		System.out.println("Povecanje casa: cas kolicina");
		System.out.println("Sprintaj zalogo: zaloga");
		System.out.println("-----------------------------------");
		
		
		while(true) {
			String[] line = sc.nextLine().split(" ");
			String ukaz = line[0];
			switch(ukaz) {
            case "izdelek":
            	izdelki[steviloIzdelkov] = new Izdelek(line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), Integer.parseInt(line[4]));
            	steviloIzdelkov++;
                break;
            case "skuhaj":
            	casKuhanja += izdelki[Integer.parseInt(line[1])].skuhaj(casKuhanja, potekIzdelave, count);
            	count++;
                break;
            case "cas":
            	dejanskiCas += Integer.parseInt(line[1]);
            	povecajZalogo(izdelki, potekIzdelave, dejanskiCas);
            	System.out.println("Trenutni cas: " + dejanskiCas);
                break;
            case "zaloga":
            	sprintajZalogo(izdelki);
                break;
            default: System.out.println("neveljaven ukaz");
			}
		}
		
	}

}
