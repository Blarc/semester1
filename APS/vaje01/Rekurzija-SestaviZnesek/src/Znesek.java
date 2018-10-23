public class Znesek {

	// funkcija preveri, ce obstaja podmnozica elementov v tabeli 'vrednosti',
	// ki se sesteje v 'znesek'.
	
	public static boolean sestavi(int[] vrednosti, int index, int znesek)
	{
		// robni pogoj: 
		// - znesek je sestavljen
		// - porabili smo vse elemente, zneska nismo sestavili
		
		if (znesek == 0) {
			return true;
		}
		
		if (index >= vrednosti.length) {
			return false;
		}
		
		if (vrednosti[index] <= znesek) {
			System.out.print(vrednosti[index]);
			return sestavi(vrednosti, index+1, znesek - vrednosti[index]);
		} else {
			return sestavi(vrednosti, index+1, znesek);
		}
		// problem poskusimo resiti tako, da uporabimo trenutni element 
		// (mozno le, ce zahtevani znesek ni manjsi od vrednosti trenutnega elementa)
		// 
		// ce nam ne uspe, preskocimo trenutni element in nadaljujemo iskanje
				
		
	}
	
	public static void main(String[] args) {
		int[] vrednosti = {7,8,5,1,3,9,2,5,2,3,5};
		int znesek = 10;
		
		System.out.print("Znesek " + znesek + " dobimo tako, da sestejemo elemente: ");
		
		if (!sestavi(vrednosti, 0, znesek))
			System.out.println("Zneska ni mogoce sestaviti s podanimi elementi");
	}

}