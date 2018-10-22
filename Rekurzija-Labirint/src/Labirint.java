
public class Labirint {

	//
	// Oznake:
	//
	// '#' zid
	// ' ' hodnik
	// 'C' cilj
	// '.' oznaka, da smo trenutno lokacijo vkljucili v pot
	//
	
	// Rekurzivna funkcija, ki poišèe pot skozi labirint
	//
	// - labirint je podan z dvodimenzionalnim poljem "labirint"
	// - "x" in "y" sta trenutni koordinati potnika, ki se premika proti cilju
	//

	public static boolean najdiPot(char[][] labirint, int x, int y)
	{
		// preveri ali je y-koordinata veljavna
		if (y >= labirint[0].length || y < 0) {
			return false;
		}
		
		// preveri ali je x-koordinata veljavna
		if (x >= labirint.length || x < 0) {
			return false;
		}
				
		// preveri ali smo prispeli do cilja?
		// - èe smo na cilju, zakljuèi in vrni rezultat "true"
		if (labirint[y][x] == 'C') {
			return true;
		}
				
		// ali je na trenutni lokaciji zid?
		// - èe je, zakljuèi in vrni rezultat "false"
		if (labirint[y][x] == '#') {
			return false;
		}
		
		// ali smo v tej tocki že bili?
		// - èe smo, zakljuèi in vrni rezultat "false"
		if (labirint[y][x] == '.') {
			return false;
		}
		
		// èe smo prispeli do sem, pomeni, da smo izvedli veljavni premik
		// - oznaci, da je trenutni polozaj na poti, ki jo gradimo
		labirint[y][x] = '.';
		
		// rekurzivni klic - ali pridemo do cilja, èe se premaknemo proti vzhodu
		// - èe je odgovor potrdilen, zakljuèi in vrsni "true"
		if (najdiPot(labirint, x+1, y)) {
			return true;
		}
		// rekurzivni klic - ali pridemo do cilja, èe se premaknemo proti severu
		// - èe je odgovor potrdilen, zakljuèi in vrsni "true"
		if (najdiPot(labirint, x, y-1)) {
			return true;
		}	
		// rekurzivni klic - ali pridemo do cilja, èe se premaknemo proti zahodu
		// - èe je odgovor potrdilen, zakljuèi in vrsni "true"
		if (najdiPot(labirint, x-1, y)) {
			return true;
		}		
		// rekurzivni klic - ali pridemo do cilja, èe se premaknemo proti jugu
		// - èe je odgovor potrdilen, zakljuèi in vrsni "true"
		if (najdiPot(labirint, x, y+1)) {
			return true;
		}		
		// èe smo prišli do sem, pomeni, da ta položaj ni na poti do cilja
		// - odznacimo ga
		labirint[y][x] = ' ';
		return false;
	}
	
	public static void izpis(char[][] labirint)
	{
		for (int i = 0; i < labirint.length; i++)
		{
			for (int j = 0;  j < labirint[i].length; j++)
				System.out.print(labirint[i][j]);
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		char[][] labirint = {
				{'#','#','#','#','#','#','#','#','#'},
				{'#',' ',' ',' ',' ',' ','#',' ','#'},
				{'#',' ','#','#','#',' ','#',' ','#'},
				{'#',' ','#','#','#',' ','#',' ','#'},
				{'#',' ',' ',' ','#','#','#',' ','#'},
				{'#',' ','#',' ','#',' ',' ',' ','#'},
				{'#',' ','#',' ',' ',' ','#',' ','#'},
				{'#',' ','#','#','#','#','#',' ','#'},
				{'#',' ',' ',' ','#',' ',' ','C','#'},
				{'#','#','#','#','#','#','#','#','#'}};

		System.out.println("Izgled labirinta:");
		izpis(labirint);

		System.out.println("\nNajdena pot skozi labirint:");
		// poišèimo izhod iz labirinta - izhodišèni položaj je na koordinati (x=5,y=3)
		if (najdiPot(labirint, 5, 3))
			izpis(labirint);
		else
			System.out.println("Ne najdem poti skozi labirint!");
	}
}
