class Oseba 
{
	String ime;
	
	Oseba oce;
	Oseba brat;
	Oseba sin;
	
	public Oseba(String ime, Oseba oce) 
	{
		this.ime = ime;
		this.oce = oce;
	}
}

public class Rodovnik 
{
	Oseba koren;
	
	public Rodovnik(String ime) 
	{
		koren = new Oseba(ime, null);
	}
	
	//Metoda doda sina podanemu ocetu
	public boolean dodajSina(String oce, String sin) 
	{
		Oseba oci = poisci(oce, koren);
		if (oci != null) {
			Oseba sine = oci.sin;
			if (sine == null) {
				oci.sin = new Oseba(sin, oci);
				return true;
			}
			
			while (sine.brat != null) {
				sine = sine.brat;
			}
			
			sine.brat = new Oseba(sin, oci);
			return true;
		}
	
		return false;
	}
	
	//Metoda za izpis druzinskega drevesa
	private void izpis(int zamik, Oseba o)
	{
		for (int i = 0; i < zamik; i++) {
			System.out.print("_");
		}
		System.out.println(o.ime);
		
		Oseba sin = o.sin;
		
		while (sin != null) {
			izpis(zamik+1, sin);
			sin = sin.brat;
		}
	}
	
	public void izpis()
	{
		// Vstavite svojo kodo
		izpis(0, koren);
	}
	
	
	public Oseba poisci(String ime, Oseba o)
	{
		if (o != null) {
			if (o.ime.equals(ime)) {
				return o;
			}
			
			Oseba v = poisci(ime, o.sin);
			if (v != null) {
				return v;
			}
			
			v = poisci(ime, o.brat);
			if (v != null) {
				return v;
			}
		}
		
		return null;
	}
	
	//Metoda izpise vse sinove oceta, ki ga dolocimo na podlagi podanega imena
	public void izpisiSinove(String ime) 
	{
		// Vstavite svojo kodo
		Oseba oce = poisci(ime, koren);
		
		if (oce != null) {
			if (oce.sin != null) {
				Oseba sin = oce.sin;
				while (sin != null) {
					System.out.print(sin.ime + " ");
					sin = sin.brat;
				}
				System.out.println();
			}
		}
		
		
	}
	
	//Metoda izpise vse vnuke osebe, ki je podana z imenom
	public void izpisiVnuke(String ime) 
	{
		Oseba oce = poisci(ime, koren);
		if (oce != null) {
			if (oce.sin != null) {
				Oseba sin = oce.sin;
				while (sin != null) {
					if (sin.sin != null) {
						Oseba vnuk = sin.sin;
						while (vnuk != null) {
							System.out.print(vnuk.ime + " ");
							vnuk = vnuk.brat;
						}
					}
					sin = sin.brat;
				}
				System.out.println();
			}
		}
	}
	
	//Metoda izpise vse pravnuke osebe, ki je podana z imenom
	public void izpisiPravnuke(String ime) 
	{
		Oseba oce = poisci(ime, koren);
		if (oce != null) {
			if (oce.sin != null) {
				Oseba sin = oce.sin;
				while (sin != null) {
					if (sin.sin != null) {
						Oseba vnuk = sin.sin;
						while (vnuk != null) {
							if (vnuk.sin != null) {
								Oseba pravnuk = vnuk.sin;
								while (pravnuk != null) {
									System.out.print(pravnuk.ime + " ");
									pravnuk = pravnuk.brat;
								}
							}
							vnuk = vnuk.brat;
						}
					}
					sin = sin.brat;
				}
				System.out.println();
			}
		}
	}
	
	//Metoda izpise vse strice osebe, ki je podana z imenom
	public void izpisiStrice(String ime) 
	{
		Oseba o = poisci(ime, koren);
		if (o != null) {
			if (o.oce != null) {
				Oseba oce = o.oce;
				if (oce.oce != null) {
					Oseba dedi = oce.oce;
					Oseba sin = dedi.sin;
					while (sin != null) {
						if (sin != oce) {
							System.out.print(sin.ime + " ");
						}
						sin = sin.brat;
					}
					System.out.println();
				}
			}
		}
	}
	
	//Metoda izpise vse bratrance osebe, ki je podana z imenom
	public void izpisiBratrance(String ime) 
	{
		// Vstavite svojo kodo
		Oseba o = poisci(ime, koren);
		if (o != null) {
			if (o.oce != null) {
				Oseba oce = o.oce;
				if (oce.oce != null) {
					Oseba dedi = oce.oce;
					Oseba sin = dedi.sin;
					while (sin != null) {
						if (sin != oce) {
							izpisiSinove(sin.ime);
							sin = sin.brat;
						}
					}
					//System.out.println();
				}
			}
		}
	}
	
	//Metoda izpise vse potomce osebe, ki je podana z imenom
	public void izpisiVsePotomce(String ime) 
	{
		
	}
		
	//Metoda izpise vse prednike osebe, ki je podana z imenom
	public void izpisiVsePrednike(String ime) 
	{
		Oseba o = poisci(ime, koren);
		if (o != null) {
			if (o.oce != null) {
				Oseba oce = o.oce;
				while (oce != null) {
					System.out.print(oce.ime + " ");
					oce = oce.oce;
				}
				System.out.println();
			}
		}
	}
		
	//Metoda presteje vozlisca v celotnem drevesu
	public int prestejVozlisca(Oseba o) 
	{
		if (o == null) {
			return 0;
		}
		
		Oseba sin = o.sin;
		
		int stevec = 1;
		while (sin != null) {
			stevec += prestejVozlisca(sin);
			sin = sin.brat;
		}
		
		
		return stevec;
	}
}
