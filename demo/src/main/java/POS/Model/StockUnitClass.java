package POS.Model;

import java.time.LocalDateTime;

public class StockUnitClass {
	private int mItemID;
	private int mCount;
	private LocalDateTime mDate;
	private String mDesc;
	
	public StockUnitClass(int aItemID,int aCount,LocalDateTime aDate,String aDesc)
	{
		this.mItemID = aItemID;
		this.mCount = aCount;
		this.mDate = aDate;
		this.mDesc = aDesc;
	}
	
	//setter
	public void setItemid(int aItemID)
	{
		this.mItemID = aItemID;
	}
	public void setCount(int aCount)
	{
		this.mCount = aCount;
	}
	public void setDate(LocalDateTime aDate)
	{
		this.mDate = aDate;
	}
	public void setDesc(String aDesc)
	{
		this.mDesc = aDesc;
	}
	//getter
	public int getItemid()
	{
		return this.mItemID;
	}
	public int getCount()
	{
		return this.mCount;
	}
	public LocalDateTime getDate()
	{
		return this.mDate;
	}
	public String getDesc()
	{
		return this.mDesc;
	}

}
