package POS.Control;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import POS.Model.*;

public class StockControlClass {
	public String stock(ItemInfoClass aItemInfo,Model model,HttpSession session)
	{
		List<ItemUnitClass> itemList = aItemInfo.selectAll();
		model.addAttribute("Items",itemList);
		return "stock";
	}
	public String stockchange(String aValue,String aItemID,ItemInfoClass aItemInfo,StockInfoClass aStockInfo,Model model,HttpSession session)
	{
		if(aValue.equals("메인")) return "index";
		else if(aValue.equals("상품 추가")) return "itemadd";		
		if(aItemID.equals("")) return stock(aItemInfo,model,session);
		ItemUnitClass item = aItemInfo.getItem(Integer.parseInt(aItemID));
		if(aValue.equals("정보 수정")) {
			model.addAttribute("itemID", item.getId());			
			model.addAttribute("itemname", item.getName());
			model.addAttribute("itemprice", item.getPrice());
			model.addAttribute("itemcount", item.getCount());
			return "itemupdate";	
		}
		else if(aValue.equals("재고 내역")) {
			List<StockUnitClass> array = aStockInfo.selectID(Integer.parseInt(aItemID));
			List<String> Texts = new ArrayList<String>();
			String Head=item.getName() +" 의 재고 내역";
			int i=0,size=array.size();
			for(i=0;i<size;i++)
			{
				Texts.add("입고 수 : " + array.get(i).getCount()+" , 날짜 : "+array.get(i).getDate()+", 설명 : "+array.get(i).getDesc());
			}
			model.addAttribute("texts",Texts);
			model.addAttribute("head",Head);
			return "stockhistory";	
		}
		else {
			model.addAttribute("itemID", item.getId());			
			model.addAttribute("itemname", item.getName());
			model.addAttribute("itemprice", item.getPrice());
			model.addAttribute("itemcount", item.getCount());
			return "stockchange";
		}
	}
	public String processStockchange(String aCount,String aDesc,String aItemID,String aValue,ItemInfoClass aItemInfo,StockInfoClass aStockInfo,Model model,HttpSession session)
	{
		int count = Integer.parseInt(aCount);
		LocalDateTime LDT = LocalDateTime.now();
		ItemUnitClass item = aItemInfo.getItem(Integer.parseInt(aItemID));
		
		if(aValue.equals("취소") || item==null) return stock(aItemInfo,model,session);
		if(item.getCount()+count<0) count=-item.getCount();
		aStockInfo.addStock(new StockUnitClass(item.getId(),count,LDT,aDesc));
		aItemInfo.updateItem(item,new ItemUnitClass(item.getName(),item.getId(),item.getPrice(),count+item.getCount()));
		return stock(aItemInfo,model,session);
	}
	public String processItemupdate(String aItemID,String aName,String aPrice,String aCount,String aValue,ItemInfoClass aItemInfo,StockInfoClass aStockInfo,Model model,HttpSession session)
	{
		int count = Integer.parseInt(aCount);
		LocalDateTime LDT = LocalDateTime.now();
		ItemUnitClass item = aItemInfo.getItem(Integer.parseInt(aItemID));
		String Desc = "관리자 수정";
		if(aValue.equals("취소") || item==null) return stock(aItemInfo,model,session); //item == null 이거 필요 한가?
		if(item.getCount()+count<0) count=-item.getCount();
		aStockInfo.addStock(new StockUnitClass(item.getId(),count-item.getCount(),LDT,Desc));
		aItemInfo.updateItem(item,new ItemUnitClass(aName,Integer.parseInt(aItemID),Integer.parseInt(aPrice),count));
		return stock(aItemInfo,model,session);
	}
	public String processItemadd(String aName,String aPrice,String aCount,String aValue,ItemInfoClass aItemInfo,StockInfoClass aStockInfo,Model model,HttpSession session)
	{
		int itemID = aItemInfo.getItemID()+1;
		if(aValue.equals("취소")) return stock(aItemInfo,model,session);
		aItemInfo.addItem(new ItemUnitClass(aName,itemID,Integer.parseInt(aPrice),Integer.parseInt(aCount)));
		if(!aCount.equals("0"))
		{
			LocalDateTime LDT = LocalDateTime.now();
			aStockInfo.addStock(new StockUnitClass(itemID,Integer.parseInt(aCount),LDT,"관리자 수정"));
		}
		
		return stock(aItemInfo,model,session);
	}
}
