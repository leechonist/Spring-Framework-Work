package POS.Control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import POS.Model.*;

public class SaleControlClass {

	public String sale(ItemInfoClass aItemInfo,Model model,HttpSession session)
	{
		List<ItemUnitClass> itemList = aItemInfo.selectAll();
		model.addAttribute("Items",itemList);
		return "sale";		
	}
	public String saleresult(String[] aArray,String aMethod,SaleInfoClass aSaleInfo,StockInfoClass aStockInfo,ItemInfoClass aItemInfo,Model model, HttpSession session)
	{
		int method=0,i=0,size = aArray.length,saleID = aSaleInfo.getSaleID()+1,total=0,value=0;
		List<SaleItemUnitClass> saleItemList = new ArrayList<SaleItemUnitClass>();
		LocalDateTime LDT=LocalDateTime.now();
		System.out.println(size);
		for(i=0;i<size;i++)
		{
			value = Integer.parseInt(aArray[i]);
			if(value > aItemInfo.getItem(i+1).getCount()) return "salefail";
			else if(value==0) continue;
			else {
				saleItemList.add(new SaleItemUnitClass(saleID,i+1,value));
				total+=value*aItemInfo.getItem(i+1).getPrice();
			}
		}
		if(saleItemList.size()==0) return "salefail";
		method = aMethod.equals("카드 결제")?1:2;
		aSaleInfo.beginSale(new SaleUnitClass(saleID,method,LDT,total));
		size = saleItemList.size();
		for(i=0;i<size;i++)
		{
			ItemUnitClass item = aItemInfo.getItem(saleItemList.get(i).getItemID());
			aSaleInfo.addSaleItem(saleItemList.get(i));
			aItemInfo.updateItem(item,new ItemUnitClass(item.getName(),item.getId(),item.getPrice(),item.getCount()-saleItemList.get(i).getCount()));
			aStockInfo.addStock(new StockUnitClass(item.getId(),-saleItemList.get(i).getCount(),LDT,"판매"));
		}
		saleID = aSaleInfo.getSaleID();i=0;size=0;
		SaleUnitClass sale = aSaleInfo.getSaleInfo(saleID);
		List<SaleItemUnitClass> SaleItemList= aSaleInfo.selectSale(saleID);
		String head="", content="";
		List<String> Texts=new ArrayList<String>();
		ItemUnitClass item=null;
		SaleItemUnitClass saleItem=null;
		size = SaleItemList.size();
		head = "판매 ID : " + sale.getSaleID() + " 판매 방식 : " + (sale.getSale_method()==1?"카드 거래":"현금거래") +  " 판매 총 액 : " + sale.getTotal_price()+" 원 판매 일자: " + sale.getDate();
		for(i=0;i<size;i++)
		{
	
			saleItem = SaleItemList.get(i);
			item = aItemInfo.getItem(saleItem.getItemID());
			content=item.getName()+" : "+saleItem.getCount()+"개 ("+item.getPrice()+"원, 총"+item.getPrice()*saleItem.getCount()+")";
			Texts.add(content);
		}
		model.addAttribute("head", head);
		model.addAttribute("texts", Texts);
		return "saleresult";
	}
	public String salefail(Model model)
	{
		return "salefail";
	}
}
