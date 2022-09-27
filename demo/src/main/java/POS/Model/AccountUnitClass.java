package POS.Model;

public class AccountUnitClass {
	private String mName;
	private String mId;
	private String mPassword;
	private int mPosition;
	public AccountUnitClass(String aName, String aId,String aPassword,int aPosition)
	{
		this.mName = aName;
		this.mId=aId;
		this.mPassword = aPassword;
		this.mPosition = aPosition;
	}
	//setter
	public void setName(String aName)
	{
		this.mName = aName;
	}
	public void setId(String aId)
	{
		this.mId = aId;
	}
	public void setPassword(String aPassword)
	{
		this.mPassword = aPassword;
	}
	public void setPosition(int aPosition)
	{
		this.mPosition = aPosition;
	}
	
	//getter
	public String getName()
	{
		return this.mName;
	}
	public String getId()
	{
		return this.mId;
	}
	public String getPassword()
	{
		return this.mPassword;
	}
	public int getPosition()
	{
		return this.mPosition;
	}
	
}
