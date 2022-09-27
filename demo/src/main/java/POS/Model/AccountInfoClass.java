package POS.Model;

import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class AccountInfoClass {
	private JdbcTemplate mJdbcTemplate;
	public AccountInfoClass(DataSource dataSource)
	{
		this.mJdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<AccountUnitClass> selectAll()
	{
		List<AccountUnitClass> results = mJdbcTemplate.query("select * from LOGIN_DATA",
				(ResultSet rs,int rowNum)->{
					AccountUnitClass account = new AccountUnitClass(rs.getString("name"),rs.getString("id"),rs.getString("password"),rs.getInt("position"));
					return account;
				});
		return results;
	}
	public boolean addAccount(AccountUnitClass aAccount)
	{
		String sql = "insert into login_data(name,id,password,position) value ('";
		sql += aAccount.getName()+"', '"+aAccount.getId()+"', '"+aAccount.getPassword()+"', "+aAccount.getPosition()+")";
		mJdbcTemplate.execute(sql);
		
		return true;
	}
	public boolean updateAccount(AccountUnitClass aTarget,AccountUnitClass aContent)
	{
		String sql = "update login_data set ";
		sql += "name = '"+aContent.getName();
		sql += "', id = '"+aContent.getId();
		sql += "', password = '"+aContent.getPassword();
		sql += "', position = "+aContent.getPosition();
		sql += " where id = '"+aTarget.getId()+"'";
		mJdbcTemplate.execute(sql);
		return true;
	}
	public AccountUnitClass getAccount(String aId)
	{
		String sql = "select * from login_data where id = '"+aId+"'";
		List<AccountUnitClass> accountList = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					return new AccountUnitClass(rs.getString("name"),rs.getString("id"),rs.getString("password"),rs.getInt("position"));
	
				});
		if(accountList.size()==0) return null;
		else return accountList.get(0);
	}
	public boolean deleteAccount(AccountUnitClass aTarget)
	{
		String sql = "delete from login_data where id = '"+aTarget.getId()+"'";
		mJdbcTemplate.execute(sql);
		return true;
	}
}
