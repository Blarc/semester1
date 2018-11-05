public class Mark extends ListElement implements Comparable
{
	private String course;
	private int mark;
	
	public Mark(String course, int mark)
	{
		this.course = course;
		this.mark = mark;
	}
	
	public String getCourse()
	{
		return course;
	}
	
	public int getMark()
	{
		return mark;
	}
	
	public void print()
	{
		System.out.println("Ime predmeta: " + course + ", ocena predmeta: " + mark);
	}
	
	public boolean equals(Mark other)
	{
		if (course.equals(other.course))
			return true;
		else
			return false;
	}
	
	public int compareTo(Object other)
	{
		Mark otherMark = (Mark)other;
		return mark - otherMark.mark;
	}
}
