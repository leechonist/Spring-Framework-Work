package POS.Model;

import java.time.LocalDateTime;

public class SaleUnitClass {
	private int mSaleID;
	private int mSale_method;
	private LocalDateTime mDate;
	private int mTotal_price;
	
	public SaleUnitClass(int aSaleId,int aSale_method,LocalDateTime aDate,int aTotal_price)
	{
		this.mSaleID = aSaleId;
		this.mSale_method = aSale_method;
		this.mDate = aDate;
		this.mTotal_price = aTotal_price;
	}
	
	//setter
	public void setSaleID(int aSaleID)
	{
		this.mSaleID = aSaleID;
	}
	public void setSale_method(int aSale_method)
	{
		this.mSale_method = aSale_method;
	}
	public void setDate(LocalDateTime aDate)
	{
		this.mDate = aDate;
	}
	public void setTotal_price(int aTotal_price)
	{
		this.mTotal_price = aTotal_price;
	}
	
	//getter 
	public int getSaleID()
	{
		return this.mSaleID;
	}
	public int getSale_method()
	{
		return this.mSale_method;
	}
	public LocalDateTime getDate()
	{
		return this.mDate;
	}
	public int getTotal_price()
	{
		return this.mTotal_price;
	}
	


}
