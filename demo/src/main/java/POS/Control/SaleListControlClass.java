package POS.Control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import POS.Model.*;

public class SaleListControlClass {
	public String checksaleInit(SaleInfoClass aSaleInfo,ItemInfoClass aItemInfo,Model model,HttpSession session)
	{
		session.setAttribute("saleID",aSaleInfo.getSaleID());
		return checksale(aSaleInfo,aItemInfo,model,session);
	}
	public String checksale(SaleInfoClass aSaleInfo,ItemInfoClass aItemInfo,Model model, HttpSession session)
	{
		int saleID = 0,i=0,size=0;
		saleID = (int)session.getAttribute("saleID");
		SaleUnitClass sale = aSaleInfo.getSaleInfo(saleID);
		List<SaleItemUnitClass> SaleItemList= aSaleInfo.selectSale(saleID);
		String head="", content="";
		List<String> Texts=new ArrayList<String>();
		ItemUnitClass item=null;
		SaleItemUnitClass saleItem=null;
		size = SaleItemList.size();
		if(saleID != 0 )head = "판매 ID : " + sale.getSaleID() + " 판매 방식 : " + (sale.getSale_method()==1?"카드 거래":"현금거래") +  " 판매 총 액 : " + sale.getTotal_price()+" 원 판매 일자: " + sale.getDate();
		else if(saleID == 0) head = "거래가 없습니다!";
		for(i=0;i<size;i++)
		{
			saleItem = SaleItemList.get(i);
			item = aItemInfo.getItem(saleItem.getItemID());
			content=item.getName()+" : "+saleItem.getCount()+"개 ("+item.getPrice()+"원, 총"+item.getPrice()*saleItem.getCount()+")";
			Texts.add(content);
		}
		model.addAttribute("head", head);
		model.addAttribute("texts", Texts);
		return "checksale";
	}
	public String doChecksale(String aValue,SaleInfoClass aSaleInfo,ItemInfoClass aItemInfo,Model model,HttpSession session)
	{
		int	saleID = (int)session.getAttribute("saleID");
		if(aValue.equals("환불")) {
			int size=0,i=0;
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
			model.addAttribute("items",SaleItemList);
			
			return "refund";
		}
		else if(aValue.equals("메인")) return "index";
		else if(aValue.equals("다음 거래")) 
		{
			saleID = (aSaleInfo.getNextSaleID(saleID)==0)?saleID:aSaleInfo.getNextSaleID(saleID);
		}
		else if(aValue.equals("이전 거래")) 
		{
			saleID = (aSaleInfo.getPrevSaleID(saleID)==0)?saleID:aSaleInfo.getPrevSaleID(saleID);
		}
		session.setAttribute("saleID",saleID);
		return checksale(aSaleInfo, aItemInfo, model, session);
	}
	public String refundProcess(String aType,String[] aCount,SaleInfoClass aSaleInfo,ItemInfoClass aItemInfo,StockInfoClass aStockInfo, Model model,HttpSession session)
	{
		List<SaleItemUnitClass> saleItem = aSaleInfo.selectSale((int)session.getAttribute("saleID"));
		int i=0,size= saleItem.size(),count=0,price=0,saleID = (int)session.getAttribute("saleID");
		SaleItemUnitClass item = null;
		SaleUnitClass sale = aSaleInfo.getSaleInfo(saleID);
		LocalDateTime LDT = LocalDateTime.now();
		if(aType.equals("취소")) return checksale(aSaleInfo, aItemInfo, model, session);
		for(i=0;i<size;i++)
		{
			item = saleItem.get(i);
			count = Integer.parseInt(aCount[i]);
			if(count==0)
			{
				aSaleInfo.deleteSaleItem(item);
			}
			else if(item.getCount()!= count)
			{
				price += aItemInfo.getItem(item.getItemID()).getPrice()*(item.getCount()-count);
				aSaleInfo.updateSaleItem(item, new SaleItemUnitClass(item.getSaleID(),item.getItemID(),count));
				aStockInfo.addStock(new StockUnitClass(item.getItemID(),count,LDT,"환불"));
			}
		}
		if(aSaleInfo.selectSale(saleID).size()==0) 
		{
			aSaleInfo.deleteSale(sale);
			saleID = (aSaleInfo.getNextSaleID(saleID)==0)?aSaleInfo.getPrevSaleID(saleID):aSaleInfo.getNextSaleID(saleID);
			session.setAttribute("saleID",saleID);
			
		}
		else
		{			
			aSaleInfo.updateSale(sale,new SaleUnitClass(sale.getSaleID(),sale.getSale_method(),sale.getDate(),sale.getTotal_price()-price));
		}
		return checksale(aSaleInfo,aItemInfo,model,session);
	}

}
