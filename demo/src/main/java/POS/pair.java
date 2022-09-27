package POS;

public class pair {
	private String first;
	private String second;
	public pair(String aFirst,String aSecond)
	{
		first = aFirst;
		second = aSecond;
	}
	public static pair make_pair(String aFirst,String aSecond)
	{
		return new pair(aFirst,aSecond);
	}
	public String getFirst()
	{
		return first;
	}
	public String getSecond()
	{
		return second;
	}
	public void setFirst(String aFirst)
	{
		first = aFirst;
	}
	public void setSecond(String aSecond)
	{
		second=aSecond;
	}
}
