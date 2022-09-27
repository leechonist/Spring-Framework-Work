package POS.Model;


public class SaleItemUnitClass {
	private int mSaleID;
	private int mItemID;
	private int mCount;
	public SaleItemUnitClass(int aSaleId,int aItemId,int aCount)
	{
		this.mSaleID = aSaleId;
		this.mItemID = aItemId;
		this.mCount =aCount;
	}
	
	//setter
	public void setSaleID(int aSaleID)
	{
		this.mSaleID = aSaleID;
	}
	public void setItemID(int aItemID)
	{
		this.mItemID = aItemID;
	}
	public void setCount(int aCount)
	{
		this.mCount = aCount;
	}
	
	//getter 
	public int getSaleID()
	{
		return this.mSaleID;
	}
	public int getItemID()
	{
		return this.mItemID;
	}
	public int getCount()
	{
		return this.mCount;
	}

	
}

