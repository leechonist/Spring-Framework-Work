package POS.Model;

import java.sql.ResultSet;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class SaleInfoClass {

	private JdbcTemplate mJdbcTemplate;
	public SaleInfoClass(DataSource dataSource)
	{
		this.mJdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<SaleItemUnitClass> selectAll()
	{
		List<SaleItemUnitClass> results = mJdbcTemplate.query("select * from SALE_DATA",
				(ResultSet rs,int rowNum)->{
					SaleItemUnitClass Sale = new SaleItemUnitClass(rs.getInt("sale_id"),rs.getInt("item_id"),rs.getInt("count"));
					return Sale;
				});
		return results;
	}
	public List<SaleItemUnitClass> selectSale(int aIndex)
	{
		List<SaleItemUnitClass> results = mJdbcTemplate.query("select * from SALE_DATA where sale_id = "+aIndex,
				(ResultSet rs,int rowNum)->{
					SaleItemUnitClass Sale = new SaleItemUnitClass(rs.getInt("sale_id"),rs.getInt("item_id"),rs.getInt("count"));
					return Sale;
				});
		return results;
	}
	public boolean addSaleItem(SaleItemUnitClass aItem)
	{
		String sql = "insert into sale_data(sale_id,item_id,count) value ('";
		sql += aItem.getSaleID()+"', '"+aItem.getItemID()+"', "+aItem.getCount()+")";
		mJdbcTemplate.execute(sql);
		
		return true;
	}
	public boolean beginSale(SaleUnitClass aSale)
	{
		String sql = "insert into sale_info_data(sale_id,sale_method,sale_date,total_price) value(";
		sql += aSale.getSaleID()+","+aSale.getSale_method()+",'"+aSale.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"',"+aSale.getTotal_price()+")";
		mJdbcTemplate.execute(sql);
		
		return true;
	}
	public boolean updateSaleItem(SaleItemUnitClass aTarget,SaleItemUnitClass aContent)
	{
		String sql = "update sale_data set ";
		sql += "sale_id = "+aContent.getSaleID();
		sql += ", item_id = "+aContent.getItemID();
		sql += ", count = "+aContent.getCount();
		sql += " where sale_id = "+aTarget.getSaleID()+" AND item_id = "+aTarget.getItemID();
		mJdbcTemplate.execute(sql);
		return true;
	}
	public boolean updateSale(SaleUnitClass aTarget,SaleUnitClass aContent)
	{
		String sql = "update sale_info_data set ";
		sql += "sale_id = "+aContent.getSaleID();
		sql += ", sale_method = "+aContent.getSale_method();
		sql += ", sale_date = '"+aContent.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		sql += "', total_price = "+aContent.getTotal_price();
		sql += " where sale_id = "+aTarget.getSaleID();
		mJdbcTemplate.execute(sql);
		return true;
	}
	public SaleItemUnitClass getSaleItem(int aSaleID, int aItemID)
	{
		String sql = "select * from sale_data where sale_id = "+aSaleID+" AND item_id = "+aItemID;
		List<SaleItemUnitClass> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					SaleItemUnitClass Sale = new SaleItemUnitClass(rs.getInt("sale_id"),rs.getInt("item_id"),rs.getInt("count"));
					return Sale;
				});
		if(results.size()==0) return null;
		else return results.get(0);
	}
	public boolean deleteSaleItem(SaleItemUnitClass aTarget)
	{
		String sql = "delete from sale_data where sale_id = "+aTarget.getSaleID()+" AND item_id = "+aTarget.getItemID();
		mJdbcTemplate.execute(sql);
		return true;
	}
	public boolean deleteSale(SaleUnitClass aTarget)
	{
		String sql = "delete from sale_Info_data where sale_id = "+aTarget.getSaleID();
		mJdbcTemplate.execute(sql);
		return true;
	}
	public int getSaleID()
	{
		String sql = "select max(sale_id) from sale_info_data";
		List<Integer> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					return rs.getInt("max(sale_id)");
				});
		if(results.size()==0) return 0;
		else return results.get(0);
	}
	public SaleUnitClass getSaleInfo(int aIndex)
	{
		String sql = "select * from sale_info_data where sale_id = "+aIndex;
		List<SaleUnitClass> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					SaleUnitClass Sale = new SaleUnitClass(rs.getInt("sale_id"),rs.getInt("sale_method"),rs.getTimestamp("sale_date").toLocalDateTime(),rs.getInt("total_price"));
					return Sale;
				});
		if(results.size()==0) return null;
		else return results.get(0);
		
	}
	public int getNextSaleID(int aIndex)
	{
		String sql = "select * from sale_info_data where sale_id > "+aIndex + " order by sale_id ASC";
		List<Integer> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					return rs.getInt("sale_id");
				});
		if(results.size()==0) return 0;
		else return results.get(0);		
	}
	public int getPrevSaleID(int aIndex)
	{
		String sql = "select * from sale_info_data where sale_id < "+aIndex + " order by sale_id DESC";
		List<Integer> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					return rs.getInt("sale_id");
				});
		if(results.size()==0) return 0;
		else return results.get(0);				
	}
}
