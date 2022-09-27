package POS.Control;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import POS.Model.*;

public class EmployeeControlClass {
	public String employee(AccountInfoClass aAccountInfo,Model model)
	{
		List<String> positions = new ArrayList<String>();
		List<AccountUnitClass> Accounts=aAccountInfo.selectAll();
		int i=0,size=Accounts.size();
		for(i=0;i<size;i++)
		{
			positions.add(Accounts.get(i).getPosition()==1?"직원":"관리자");
		}
		model.addAttribute("Accounts", Accounts);
		model.addAttribute("positions",positions);
		return "employee";
	}
	public String accountupdate(String aValue,String aAccountID,AccountInfoClass aAccountInfo,Model model,HttpSession session)
	{
		
		if(aValue.equals("메인")) return "index";
		AccountUnitClass account= aAccountInfo.getAccount(aAccountID);
		model.addAttribute("account", account);
		return "accountupdate";
	}
	public String processAccountupdate(String aName,String aID,String aPW,String aPosition,String aValue,String aCurID,AccountInfoClass aAccountInfo, Model model,HttpSession session)
	{
		if(aValue.equals("취소")) return employee(aAccountInfo,model);
		if(!aCurID.equals(aID)&&(aAccountInfo.getAccount(aID)!=null))
		{
			System.out.println("");
			model.addAttribute("text","아이디가 중복되었습니다.");
			return "accountupdate";
		}
		aAccountInfo.updateAccount(aAccountInfo.getAccount(aCurID),new AccountUnitClass(aName,aID,aPW,Integer.parseInt(aPosition)));
		
		return employee(aAccountInfo,model);
	}
}
