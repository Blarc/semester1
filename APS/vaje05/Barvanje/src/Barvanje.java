
//
// Razred resuje problem barvanja grafa, ki je podan v matricni obliki.
// Ob instanciranju razreda konstruktor prejme nxn matriko binarnih
// vrednosti, kjer je n stevilo vozlisc. Ce sta vozlisci i in j povezani,
// sta v matriki na mestih (i,j) ter (j,i) enici.
//

import java.util.*;

public class Barvanje 
{	
	// Hranili bomo graf in barve vozlisc.
	int[][] graf;
	int[] vozlisca;
	
	Barvanje(int[][] g)
	{
		graf = g;
		vozlisca = new int[graf.length];
	}
	
	public void izpisi_barve()
	{
		for (int i = 0; i < vozlisca.length; i++)
			System.out.print(vozlisca[i] + ", ");

		System.out.println();
	}
	
	public static int[][] generiraj_graf(int n, double p, Random rand)
	{
		int[][] g = new int[n][n];
		
		for (int i = 1; i < n; i++)
			for (int j = i + 1; j < n; j++)
			{
				if (rand.nextDouble() < p)
				{
					g[i][j] = 1;
					g[j][i] = 1;
				}
			}
		
		return g;
	}
	
	public static void izpisi_graf(int[][] graf)
	{
		for (int i = 0; i < graf.length; i++)
		{
			for (int j = 0; j < graf[i].length; j++)
				System.out.print(graf[i][j] + " ");
			
			System.out.println();
		}
	}
	
	public boolean dovoljeno(int vozlisce, int barva) {
		for (int i = 0; i < graf[vozlisce].length; i++) {
			if (graf[vozlisce][i] > 0 && vozlisca[i] == barva) {
				return false;
			}
		}
		return true;
	}
	
	public int povezave(int vozlisce) {
		int c = 0;
		for (int i = 0; i < graf[vozlisce].length; i++) {
			if (graf[vozlisce][i] > 0) {
				c++;
			}
		}
		return c;
	}
	
	//1. pobarvaš vse kar se da
	//2. največ povezav
	//3. izčrpno iskanje
	
	/*public void pobarvaj()
	{
		for (int i = 0; i < graf.length; i++) {
			for (int barva = 1; barva < graf.length; barva++) {
				if(dovoljeno(vozlisca[i], barva)) {
					vozlisca[i] = barva;
					break;
				}
			}
		}
	}*/
	
	public void pobarvaj()
	{
		for (int i = 0; i < )
	}
	
	
	public static void main(String[] args) 
	{
		Random rand = new Random();
		
		/*
		int[][] graf = {
				{0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
				{0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0},
				{0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
				{1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
				{0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
				{1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0},
				{0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
				{1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0},
				{0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
				{0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0},
				{0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1},
				{1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
				{0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
				{1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0},
				{0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
				{1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0}};
		*/
		
		int[][] graf = generiraj_graf(5, 0.4, rand);
		izpisi_graf(graf);
		
		System.out.println();
		
		Barvanje bojan = new Barvanje(graf);
		int stPonovitev = 1000;
		
		System.out.println("Barvanje vozlisc:");
		long zacetniCas = System.nanoTime();

		for (int i = 0; i < stPonovitev; i++)
			bojan.pobarvaj();
		
		double povCasIzvajanja = (double)(System.nanoTime() - zacetniCas) / stPonovitev;
		
		bojan.izpisi_barve();
		System.out.println("Povprecen cas izvajanja: " + povCasIzvajanja + "\n");
	}

}
