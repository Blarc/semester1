
public class Sudoku {

	// Rekurzivna funkcija, ki poisce resitev uganke na naslednji nacin:
	// - ce smo prisli do konca tabele, vrni pozitiven rezultat
	// - ce je vsebina celice polje[i][j] ze podana, 
	//      rekurzivno poisci resitev za naslednjo celico 
	// - sicer
	//      za vse mozne vrednosti celice polje[i][j] preveri, ali je situacija dovoljena
	//         ce je, rekurzivno poisci resitev za naslednjo celico
	//
	//      ce nobena vrednost ni dovoljena, razveljavi vsebino celice polje[i][j] in vrni negativen rezultat
	//
	
    static boolean resi(int i, int j, int[][] polje) 
    {	
    	// napisi rekurzivno funkcijo
    	if (j == 9 && i == 8) {
    		return true;
    	}
    	
    	if (j < 9) {
    		if (polje[i][j] > 0) {
    			return resi(i, j+1, polje);
    		}
    		for (int val = 1; val < 10; val++) {
    			//System.out.print(val + " ");
    			if (veljavno(i, j, val, polje)) {
    				polje[i][j] = val;
    				if (resi(i, j+1, polje) ) {
    					return true;
    				}
    			}
    		}
    		polje[i][j] = 0;
    		return false;
    	}
    	
    	return resi(i+1, 0, polje);
    }

    // Funkcija preveri, ali je dovoljeno postaviti vrednost "val" v polje[i][j]
    static boolean veljavno(int i, int j, int val, int[][] polje) 
    {
    	// preveri j-ti stolpec
        //   ce se vrednost "val" ze nahaja v j-tem stolpcu, potem je situacija neveljavna
    	for (int k = 0; k < 9; k++) {
    		if (polje[k][j] == val) {
    			return false;
    		}
    	}
       
    	// preveri i-to vrstico
        //   ce se vrednost "val" ze nahaja v i-ti vrstici, potem je situacija neveljavna
        
    	for (int k = 0; k < 9; k++) {
    		if (polje[i][k] == val) {
    			return false;
    		}
    	}
    	// preveri ustrezni kvadrat
        //   ce se vrednost "val" ze nahaja v kvadratu, potem je situacija neveljavna
        
    	int y = i - i%3;
    	int x = j - j%3;
    	for (int k = 0; k < 3; k++) {
    		for (int l = 0; l < 3; l++) {
    			if (polje[y+k][x+l] == val) {
    				return false;
    			}
    		}
    	}
    	
        // vse pogoje smo preverili, situacija je veljavna*/
        return true;
    }

    static void izpisiPolje(int[][] polje) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0)
                System.out.println(" -----------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                if (polje[i][j] == 0)
                	System.out.print(" ");
                else
                    System.out.print(polje[i][j]);

                System.out.print(' ');
            }
            System.out.println("|");
        }
        System.out.println(" -----------------------");
    }

    public static void main(String[] args) {
        int[][] polje = {
        		{0,8,0,4,0,2,0,6,0},
        		{0,3,4,0,0,0,9,1,0},
        		{9,6,0,0,0,0,0,8,4},
        		{0,0,0,2,1,6,0,0,0},
        		{0,0,0,0,0,0,0,0,0},
        		{0,0,0,3,5,7,0,0,0},
        		{8,4,0,0,0,0,0,7,5},
        		{0,2,6,0,0,0,1,3,0},
        		{0,9,0,7,0,1,0,4,0}
        };
        
        izpisiPolje(polje);
        if (resi(0,0,polje))
            izpisiPolje(polje);
        else
            System.out.println("Ni resitve");
        
    }
}
