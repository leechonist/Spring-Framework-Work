package POS.Model;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class StockInfoClass {
	private JdbcTemplate mJdbcTemplate;
	public StockInfoClass(DataSource dataSource)
	{
		this.mJdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<StockUnitClass> selectAll()
	{
		List<StockUnitClass> results = mJdbcTemplate.query("select * from STOCK_DATA",
				(ResultSet rs,int rowNum)->{
					StockUnitClass Stock = new StockUnitClass(rs.getInt("item_id"),rs.getInt("count"),rs.getTimestamp("stock_date").toLocalDateTime(),rs.getString("DSC"));
					return Stock;
				});
		return results;
	}
	public List<StockUnitClass> selectID(int aIndex)
	{
		List<StockUnitClass> results = mJdbcTemplate.query("select * from STOCK_DATA where item_id = "+aIndex,
				(ResultSet rs,int rowNum)->{
					StockUnitClass Stock = new StockUnitClass(rs.getInt("item_id"),rs.getInt("count"),rs.getTimestamp("stock_date").toLocalDateTime(),rs.getString("DSC"));
					return Stock;
				});
		return results;		
	}
	public boolean addStock(StockUnitClass aStock)
	{
		String sql = "insert into stock_data(item_id,count,stock_date,dsc) value ('";
		sql += aStock.getItemid()+"', '"+aStock.getCount()+"', '"+aStock.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"','"+aStock.getDesc()+"')";
		mJdbcTemplate.execute(sql);
		
		return true;
	}
	public boolean updateStock(StockUnitClass aTarget,StockUnitClass aContent)
	{
		String sql = "update stock_data set ";
		sql += "item_id = '"+aContent.getItemid();
		sql += "', count = '"+aContent.getCount();
		sql += "', stock_date = '"+aContent.getDate();
		sql += "where Item_id = '"+aTarget.getItemid()+"' AND date = '"+aTarget.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"'";
		mJdbcTemplate.execute(sql);
		return true;	
		
	}
	public StockUnitClass getStockItem(int aItemID, LocalDateTime aDate)
	{
		String sql = "select * from stock_data where item_id = "+aItemID+" AND stock_date = '"+aDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"'";
		List<StockUnitClass> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					StockUnitClass Sale = new StockUnitClass(rs.getInt("item_id"),rs.getInt("count"),rs.getTimestamp("sale_date").toLocalDateTime(),rs.getString("DSC"));
					return Sale;
				});
		if(results.size()==0) return null;
		else return results.get(0);
	}
	public boolean deleteStock(StockUnitClass aTarget)
	{
		String sql = "delete from stock_data where item_id = "+aTarget.getItemid()+" AND stock_date = '"+aTarget.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"'";
		mJdbcTemplate.execute(sql);
		return true;
	}
}