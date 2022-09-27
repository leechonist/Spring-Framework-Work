package POS.Model;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;
import POS.*;
import org.springframework.jdbc.core.JdbcTemplate;

public class AnalyzeInfoClass {
	private JdbcTemplate mJdbcTemplate;
	public AnalyzeInfoClass(DataSource dataSource)
	{
		this.mJdbcTemplate = new JdbcTemplate(dataSource);
	}
	public List<pair> getTotalPrice(String aValue)
	{
		String sql = "";
		if(aValue.equals("1")) sql="SELECT DATE_FORMAT(sale_date,'%Y년%m월%d일') as date, sum(total_price) as total FROM sale_info_data GROUP BY date order by total DESC Limit 10";
		else if(aValue.equals("2")) sql="SELECT DATE_FORMAT(sale_date,'%Y년%U주차') as date, sum(total_price) as total FROM sale_info_data GROUP BY date order by total DESC Limit 10";
		else if(aValue.equals("3")) sql="SELECT DATE_FORMAT(sale_date,'%Y년%m월') as date, sum(total_price) as total FROM sale_info_data GROUP BY date order by total DESC Limit 10";
		
		List<pair> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					return new pair(rs.getString("date"),rs.getString("total")+"원");
				});
		return results;
	}
	public List<pair> getTotalSale(String aValue)
	{
		String sql = "";
		if(aValue.equals("1")) sql="select DATE_FORMAT(sale_info_data.sale_date,'%Y년%m월%d일') as date,sum(count) as total from sale_info_data join sale_data on sale_info_data.sale_id = sale_data.sale_id group by date order by total DESC Limit 10";
		else if(aValue.equals("2")) sql="select DATE_FORMAT(sale_info_data.sale_date,'%Y년%U주차') as date,sum(count) as total from sale_info_data join sale_data on sale_info_data.sale_id = sale_data.sale_id group by date order by total DESC Limit 10";
		else if(aValue.equals("3")) sql="select DATE_FORMAT(sale_info_data.sale_date,'%Y년%m월') as date,sum(count) as total from sale_info_data join sale_data on sale_info_data.sale_id = sale_data.sale_id group by date order by total DESC Limit 10";

		List<pair> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					return new pair(rs.getString("date"),rs.getString("total")+"개");
				});
		return results;
	}
	public List<pair> getMostSaleItem()
	{
		String sql="select i.name as name, i.id,sum(s.count) as totalcount from  item_data i inner join sale_data s on i.id=s.item_id group by i.id order by totalcount DESC Limit 10";

		List<pair> results = mJdbcTemplate.query(sql,
				(ResultSet rs,int rowNum)->{
					return new pair(rs.getString("name"),rs.getString("totalcount")+"개");
				});
		return results;
		
	}
}
