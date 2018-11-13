
// Podana je n x n matrika. Vrstice ustrezajo zaposlencem, stolpci strojem.
// Vrednost na mestu (i, j) doloca ucinkovitost i-tega delavca na
// j-tem stroju. Cilj je kar najbolj ucinkovito razporediti zaposlence
// po strojih, tako da bo koncni izkoristek cim vecji.

public class Razporejanje 
{
	// Hranimo matriko z ucinkovitnostmi delavcev na strojih,
	// trenutno razporeditev zaposlencev, ter izkupicek trenutne
	// razporeditve
	int[][] ucinkovitost;
	int[] razporeditev;
	int izkupicek;
	
	public Razporejanje(int[][] p) 
	{
		ucinkovitost = p;
		razporeditev = new int[p.length];
		reset();
	}
	
	// Iznici razporeditve - -1 zaznamuje, da delavec ni
	// dodeljen nobenemu stroju.
	public void reset() 
	{
		for(int i = 0; i < razporeditev.length; i++) 
		{
			razporeditev[i] = -1;
		}
		
		// skupna vrednost razporeditve delavcev
		izkupicek = 0;
	}
	
	public void izpisi_resitev() 
	{
		System.out.print("Skupna vrednost: " + izkupicek + ", razporeditev: ");
		
		for(int i = 0; i < razporeditev.length; i++) 
			System.out.print(razporeditev[i] + " ");
		System.out.println();
	}
	
	
	public void razporedi() 
	{
		
	}
	
	public static void main(String args[]) 
	{
		// Kvadratna matrika ucinkovitosti
		// Dimenzija 1 - delavec, dimenzija 2 - stroj
		int[][] ucinkovitost = {
				{70,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
				{ 5, 76, 51, 50, 50, 43, 32, 24, 29, 24,  4},
				{ 0, 60, 92, 70, 50, 50, 49, 26, 34, 37,  2},
				{ 2, 95, 63, 50, 30, 60, 43, 27, 32, 20,  8},
				{ 0, 45, 54, 57, 43, 38, 55, 40, 35, 34,  9},
				{ 0, 60, 97, 72, 13, 86, 54, 62, 63, 30, 29},
				{ 8, 53, 42, 40, 31, 83, 68, 54, 58, 56, 40},
				{ 0, 47, 56, 39, 13, 79, 57, 73, 62, 53, 52},
				{ 4,  9, 12, 11, 10, 78, 78, 52, 90, 93, 68},
				{ 0, 13,  7,  8, 12, 67, 56, 54, 78, 89, 73},
				{10,  4, 11, 12,  1, 59, 34, 58, 80, 80, 80}};
		
		Razporejanje k = new Razporejanje(ucinkovitost);
		
		
		int stPonovitev = 1000;
		long zacetniCas = System.nanoTime();

		for (int i = 0; i < stPonovitev; i++)
			k.razporedi();
		
		double povCasIzvajanja = (double)(System.nanoTime() - zacetniCas) / stPonovitev;
		k.izpisi_resitev();
		System.out.println("Povprecen cas izvajanja: " + povCasIzvajanja + "\n");
	}
}
