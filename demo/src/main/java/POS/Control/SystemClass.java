package POS.Control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import POS.Model.*;
@Controller
public class SystemClass {
	@Autowired
	private AccountInfoClass accountInfo;
	@Autowired
	private ItemInfoClass itemInfo;
	@Autowired
	private SaleInfoClass saleInfo;
	@Autowired
	private StockInfoClass stockInfo;
	@Autowired
	private TestControlClass mTestControl;
	@Autowired
	private AccountControlClass mAccountControl;
	@Autowired
	private SaleControlClass mSaleControl;
	@Autowired
	private StockControlClass mStockControl;
	@Autowired
	private SaleListControlClass mSaleHistoryControl;
	@Autowired
	private AnalyzeControlClass mAnalyzeControl;
	@Autowired
	private EmployeeControlClass mEmployeeControl;
	
	private AccountUnitClass mAccount;
	
	@GetMapping(value="/test")
	public String test(Model model)
	{
		return mTestControl.test(model, accountInfo);
	}
	@GetMapping(value="/item_test")
	public String item_test(Model model)
	{
		return mTestControl.item_test(model, itemInfo);
	}
	@GetMapping(value="/sale_test")
	public String sale_test(Model model)
	{
		return mTestControl.sale_test(model, saleInfo);
	}
	@GetMapping(value="/stock_test")
	public String stock_test(Model model)
	{
		return mTestControl.stock_test(model, stockInfo);
	}

	@GetMapping(value="/main")
	public String main(Model model)
	{
		
		return "main";
	}
	

	//
	// 계정
	//
	
	@GetMapping(value="/login")
	public String login(Model model)
	{
		return mAccountControl.login(model);
	}
	@PostMapping(value="logincheck.do")
	public String loginCheck(
			@RequestParam(value="strID",defaultValue="")String aID,
			@RequestParam(value="strPW",defaultValue="")String aPW,	
			@RequestParam(value="value",defaultValue="")String aValue
			,Model model,HttpSession session)
	{
		return mAccountControl.loginCheck(aID, aPW, aValue, mAccount, accountInfo, model, session);
	}
	@GetMapping(value="/signin")
	public String signin(Model model)
	{
		return mAccountControl.signin(model);
	}
	@PostMapping(value="signincheck.do")
	public String signinCheck(
			@RequestParam(value="strName",defaultValue="")String aName,
			@RequestParam(value="strID",defaultValue="")String aID,
			@RequestParam(value="strPW",defaultValue="")String aPW,
			@RequestParam(value="strCheck",defaultValue="")String aCheck,
			@RequestParam(value="value",defaultValue="")String aValue
			,Model model,HttpSession session)
	{
		return mAccountControl.signinCheck(aName, aID, aPW, aCheck, aValue, mAccount, accountInfo, model, session);
	}

	@GetMapping(value="/logout")
	public String logout(Model model,HttpSession session)
	{
		return mAccountControl.logout(model,session);
	}
	@GetMapping(value="/editaccount")
	public String editAccount(Model model,HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mAccountControl.editAccount(model, session);
	}
	@PostMapping(value="editaccount.do")
	public String processEditAccount(
			@RequestParam(value="strName",defaultValue="")String aName,
			@RequestParam(value="strID",defaultValue="")String aID,
			@RequestParam(value="strPW",defaultValue="")String aPW,
			@RequestParam(value="value",defaultValue="")String aValue
			,Model model, HttpSession session)
	{
		return mAccountControl.processEditAccount(aName, aID, aPW, aValue, accountInfo, model, session);
	}

	//
	// 거래
	//
	
	@GetMapping(value="/sale")
	public String sale(Model model,HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mSaleControl.sale(itemInfo, model, session);		
	}
	@PostMapping(value="/saleresult")
	public String saleresult(
			@RequestParam(value="Count",defaultValue="")String[] aArray,			
			@RequestParam(value="method",defaultValue="")String aMethod,
			Model model, HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mSaleControl.saleresult(aArray, aMethod, saleInfo, stockInfo, itemInfo, model, session);
	}
	@GetMapping(value="/salefail")
	public String salefail(Model model)
	{
		return mSaleControl.salefail(model);
	}
	

	//
	// 거래 내역
	//
	

	@GetMapping(value="/checksale")
	public String checksaleInit(Model model,HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mSaleHistoryControl.checksaleInit(saleInfo, itemInfo, model, session);
	}
	@PostMapping(value="checksale.do")
	public String doChecksale(
			@RequestParam(value="value",defaultValue="")String aValue,
			Model model,HttpSession session)
	{
		return mSaleHistoryControl.doChecksale(aValue, saleInfo, itemInfo, model, session);
	}
	
	@PostMapping(value="refund.do")
	public String refundProcess(
			@RequestParam(value="type",defaultValue="")String aType,
			@RequestParam(value="Count",defaultValue="")String[] aCount,
			Model model,HttpSession session)
	{
		return mSaleHistoryControl.refundProcess(aType, aCount, saleInfo, itemInfo, stockInfo, model, session);
	}
	
	
	//
	// 재고
	//

	@GetMapping(value="/stock")
	public String stock(Model model,HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mStockControl.stock(itemInfo, model, session);
	}
	@PostMapping(value="/stockchange")
	public String stockchange(			
			@RequestParam(value="itemid",defaultValue="")String aItemID,
			@RequestParam(value="value",defaultValue="")String aValue,
			Model model,HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mStockControl.stockchange(aValue,aItemID, itemInfo,stockInfo, model, session);
	}
	@PostMapping(value="stockchange.do")
	public String processStockchange(
			@RequestParam(value="Count",defaultValue="")String aCount,
			@RequestParam(value="Description",defaultValue="")String aDesc,
			@RequestParam(value="itemid",defaultValue="")String aItemID,
			@RequestParam(value="value",defaultValue="")String aValue,
			Model model,HttpSession session)
	{
		return mStockControl.processStockchange(aCount, aDesc, aItemID, aValue, itemInfo, stockInfo, model, session);
	}
	@PostMapping(value="itemupdate.do")
	public String processItemupdate(@RequestParam(value="itemid",defaultValue="")String aID,
			@RequestParam(value="name",defaultValue="")String aName,
			@RequestParam(value="price",defaultValue="")String aPrice,
			@RequestParam(value="count",defaultValue="")String aCount,
			@RequestParam(value="value",defaultValue="")String aValue,
			Model model,HttpSession session)
	{
		return mStockControl.processItemupdate(aID, aName, aPrice, aCount, aValue, itemInfo, stockInfo, model, session);
	}
	@PostMapping(value="itemadd.do")
	public String processItemadd(
			@RequestParam(value="name",defaultValue="")String aName,
			@RequestParam(value="price",defaultValue="")String aPrice,
			@RequestParam(value="count",defaultValue="")String aCount,
			@RequestParam(value="value",defaultValue="")String aValue,
			Model model,HttpSession session)
	{
		return mStockControl.processItemadd(aName, aPrice, aCount, aValue, itemInfo, stockInfo, model, session);
	}
	
	//
	// 직원 관리
	//
	@GetMapping(value="/employee")
	public String employee(Model model,HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mEmployeeControl.employee(accountInfo, model);
	}
	@PostMapping(value="/accountupdate")
	public String accountupdate(
			@RequestParam(value="value",defaultValue="")String aValue,
			@RequestParam(value="accountid",defaultValue="")String aAccountID,
			Model model,HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mEmployeeControl.accountupdate(aValue, aAccountID, accountInfo, model, session);
	}
	@PostMapping(value="accountupdate.do")
	public String processAccountupdate(
			@RequestParam(value="strName",defaultValue="")String aName,
			@RequestParam(value="strID",defaultValue="")String aID,
			@RequestParam(value="strPW",defaultValue="")String aPW,
			@RequestParam(value="accountposition",defaultValue="")String aPosition,
			@RequestParam(value="value",defaultValue="")String aValue,
			@RequestParam(value="curID",defaultValue="")String aCurID,
			Model model,HttpSession session)
	{
		return mEmployeeControl.processAccountupdate(aName, aID, aPW, aPosition, aValue, aCurID, accountInfo, model, session);
	}
	
	//
	// 분석
	//
	@GetMapping(value="/analyze")
	public String analyze(Model model,HttpSession session)
	{
		if((String)session.getAttribute("id")==null) return mAccountControl.login(model);
		return mAnalyzeControl.analyze();
	}
	@PostMapping(value="analyze.do")
	public String analyzing(@RequestParam(value="value",defaultValue="")String aValue,
			@RequestParam(value="type",defaultValue="")String aType,
			Model model)
	{
		return mAnalyzeControl.analyzing(aValue, aType, itemInfo, model);
	}
}
