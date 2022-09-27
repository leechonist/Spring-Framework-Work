package POS.Control;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import POS.Model.*;

public class AccountControlClass {

	public String login(Model model)
	{
		String str = "";
		model.addAttribute("Text",str);
		return "login";
	}
	public String loginCheck(String aID,String aPW,	String aValue,AccountUnitClass aAccount,AccountInfoClass aAccountInfo,Model model,HttpSession session)
	{
		String str="";
		if(aValue.equals("취소")) return "index";
		aAccount = aAccountInfo.getAccount(aID);
		if (aAccount==null)str = "아이디를 다시 확인해주세요.";
		
		else if(aID.equals(aAccount.getId()))
		{
			if(aPW.equals(aAccount.getPassword()))
			{
				
				session.setAttribute("name", aAccount.getName());
				session.setAttribute("id", aAccount.getId());
				session.setAttribute("pw", aAccount.getPassword());
				session.setAttribute("position", aAccount.getPosition()==1?"직원":"관리자");
				return "index";
			}
			str="비밀번호를 다시 확인해주세요.";
		}
		model.addAttribute("Text",str);
		return "login";
	}
	public String signin(Model model)
	{
		String str = "";
		model.addAttribute("Text",str);
		return "signin";		
	}
	public String signinCheck(String aName,String aID,String aPW,String aCheck,String aValue,AccountUnitClass aAccount,AccountInfoClass aAccountInfo,Model model,HttpSession session)
	{
		String str="";
		if(aValue.equals("취소")) return "index";
		aAccount = aAccountInfo.getAccount(aID);
		if(aAccount!=null)
		{
			str="아이디가 중복되었습니다.";
		}
		else if(!aPW.equals(aCheck))
		{
			str="비밀번호를 다시 확인해주세요.";
		}
		else
		{
			aAccountInfo.addAccount(new AccountUnitClass(aName,aID,aPW,1));
			return "index";
		}
		model.addAttribute("Text",str);
		
		return "signin";
	}
	public String logout(Model model,HttpSession session)
	{
		session.invalidate();		
		return "index";
	}
	public String editAccount(Model model,HttpSession session)
	{
		return "editaccount";
	}
	public String processEditAccount(String aName,String aID,String aPW,String aValue,AccountInfoClass aAccountInfo,Model model, HttpSession session)
	{
		int pos = ((String)session.getAttribute("position")).equals("직원")?1:2;
		AccountUnitClass account = aAccountInfo.getAccount(aID),target = new AccountUnitClass((String)session.getAttribute("name"),(String)session.getAttribute("id"),
				(String)session.getAttribute("pw"),pos);
		String str="";
		if(aValue.equals("메인")) return "index";
		if(!(target.getId().equals(aID)) && (account!=null))
		{
			str="아이디가 중복되었습니다.";
		}
		else
		{			
			aAccountInfo.updateAccount(aAccountInfo.getAccount((String)session.getAttribute("id")),new AccountUnitClass(aName,aID,aPW,((String)session.getAttribute("position")).equals("직원")?1:2));
			session.setAttribute("name", aName);
			session.setAttribute("id", aID);
			session.setAttribute("pw", aPW);
			session.setAttribute("position", (String)session.getAttribute("position"));
			str="변경이 완료되었습니다.";
		}
		model.addAttribute("Text",str);
		
		return "editaccount";
	}
}
