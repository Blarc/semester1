
public class Student extends ListElement implements Comparable
{
	private String name;
	private LinkedList marks;
	
	public Student(String name)
	{
		this.name = name;
		marks = new LinkedList();
	}
	
	public boolean addMark(Mark newMark)
	{
		//
		// NALOGA
		//
		// Funkcija preveri, ali je student ze opravil izpit "newMark.course"
		//
		// Ce ni, doda zapis o opravljenem izpitu v seznam "marks" in vrne true
		// sicer vrne false
		//
		
		return false;
	}
	
	public double getAverageMark()
	{
		//
		// NALOGA
		//
		// Funkcija izracuna in vrne povprecno oceno opravljenih izpitov
		//
		//
		
		return 0;
	}
	
	public void print()
	{
		System.out.println("Ime studenta: " + name);
		System.out.println("Opravljeni predmeti:");
		marks.print();
		System.out.println("Povprecna ocena: " + getAverageMark());
		System.out.println();
	}
	
	public int compareTo(Object other)
	{
		Student otherStudent = (Student)other;
		
		double myAvg = getAverageMark();
		double otherAvg = otherStudent.getAverageMark();
		if (myAvg > otherAvg)
			return 1;
		else if (myAvg < otherAvg)
			return -1;
		else
			return 0;
	}
}
