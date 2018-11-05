import java.io.*;

public class ZnakovniTokovi {

	public static Student readStudent (BufferedReader reader) throws Exception
	{
		String line;
		Student student = null;
		
		// - preberemo vrstico z imenom studenta (uporabimo metodo readLine() vhodnega toka )
		//
		// - ustvarimo nov predmet razreda Student
		//
		// - preberemo vrstico s stevilom opravljenih izpitov
		//		- za pretvorbo iz znakovnega zaporedja v celostevilcno vrednost lahko uporabimo metodo parseInt(String s) razreda Integer.
		//		  na primer:
		//        				int numOfEntries = Integer.parseInt(line);
		//
		// - v zanki beremo vrstice o opravljenih izpitih
		//		- prebrano vrstico razdelimo z uporabo locila ",".
		//		  Lahko uporabimo metodo split v razredu String, za razdelitev vrstice glede na locilo ","
		//		  na primer:
		//						String[] tokens = line.split(",");
		//
		//		- ustvarimo nov predmet razreda Mark in ga dodamo v seznam opravljenih izpitov ("marks" v razredu Student)
		
		
		// vrnemo referenco na ustvarjen predmet razreda Student
		return student;
	}
	
	public static void readFile(String fileName, LinkedList list)
	{
		BufferedReader reader = null;
		
		try
		{
			String line;
			
			// - Datoteko odpremo tako, da ustvarimo predmet reader
			//
			// - Preberemo vrstico s stevilom zapisov
			//		- uporabimo metodo readLine() vhodnega toka 
			//  	- za pretvorbo iz znakovnega zaporedja v celostevilcno vrednost lahko uporabimo metodo parseInt(String s) razreda Integer.
			//
			// - V zanki beremo zapise o studentih (uporabimo metodo "readStudent") in jih dodajamo v seznam "list" 
			
        }
		catch (FileNotFoundException ex)
		{
			System.err.println("Ne najdem zahtevane datoteke!");
		}
		catch (IOException ex) 
		{
			System.err.println("Napaka pri branju datoteke");
        }
		catch (NumberFormatException ex)
		{
			System.err.println("Napaka pri zapisu stevila");
		}
		catch (Exception ex)
		{
			System.err.println("Napacni podatki");
		}
		finally 
		{
			try 
			{ 
				//
				// Zapremo vhodni tok (metoda close())
				//
			}
			catch (IOException ex) { System.err.println("Napaka pri zapiranju datoteke");}
		}
		
	}
	
	public static void main(String[] args) 
	{
		//
		// V tekstovni datoteki "ocene.txt" se nahajajo zapisi o studentih in njihovih
		// opravljenih izpitih.
		//
		// V prvi vrstici datoteke se nahaja vrednost, ki doloca stevilo zapisov (studentov).
		// V naslednjih vrsticah se nahajajo zapisi o studentih.
		//
		// Zapis o studentu vsebuje vrstico z imenom studenta. V naslednji vrstici se nahaja vrednost,
		// ki doloca stevilo zapisov o opravljenih izpitih. Nato sledijo zapisi o opravljenih izpitih.
		//
		// Zapis o opravljenem izpitu je oblike "X,Y", 
		// pri cemer je X ime predmeta (String) ter Y dobljena ocena (int)
		//
		//
		//
		// Implementirajte naslednje funkcije:
		//
		// "readFile" in "readStudent" v razredu ZnakovniTokovi
		// "sort" v razredu LinkedList
		// "addMark" in "getAverageMark" v razredu Student
		//
		
		LinkedList listOfStudents = new LinkedList();
		readFile("ocene.txt", listOfStudents);
		listOfStudents.print();
		
		System.out.println("\n\nSortiran seznam studentov (glede na povprecno oceno):\n");
		listOfStudents.sort();
		listOfStudents.print();
	}

}
