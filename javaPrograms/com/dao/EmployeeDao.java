 package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.bean.Employee;

public class EmployeeDao {

	@Autowired
	DataSource dataSource;
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public EmployeeDao()
	{
		System.out.println("EmployeeDao Constructor");
	}
	
	public boolean insert(Employee employee)
	{
		boolean flag=true;
		System.out.println("insert");
				
		//String insertQuery = "insert into myuser(first_name,last_name) values('"+employee.getFirstName()+"','"+employee.getLastName()+"')";
				int r1 = jdbcTemplate.update("insert into myuser(firstName,lastName) values (?,?)",employee.getFirstName(),employee.getLastName());
				System.out.println(r1 + " records inserted");
				if(r1==0)
				{
					flag=false;
				}
		return flag;
	}
	
	public List<Employee> listUsers()
	{
			List<Employee> list = jdbcTemplate.query("select * from myuser", new EmployeeMapper());
			return list;
	}
	
	class EmployeeMapper implements RowMapper<Employee>
	{

		public Employee mapRow(ResultSet rs, int count) throws SQLException {
			System.out.println(count);
			Employee employee=new Employee();
			employee.setFirstName(rs.getString("firstName"));
			employee.setLastName(rs.getString("lastName"));
			employee.setId(rs.getInt("id"));
			return employee;
		}
		
	}
	
	public void deleteUser(int id)
	{
		jdbcTemplate.update("delete from myuser where id="+id);
	}
	
	public Employee getUser(int id) {
		return jdbcTemplate.queryForObject("select * from myuser where id = " + id, new EmployeeMapper());
		
//		return jdbcTemplate.queryForObject("select * from myuser where id = " + id, new RowMapper<Employee>() {
//
//			public Employee mapRow(ResultSet rs, int line) throws SQLException {
//				Employee employee = new Employee();
//				employee.setFirstName(rs.getString("firstName"));
//				employee.setLastName(rs.getString("lastName"));
//				employee.setId(rs.getInt("id"));
//				return employee;
//			}
//
//		});
	}
	
	public void updateUser(Employee employee)
	{
		String updateQuery="update myuser set firstName=?,lastName=? where id=?";
		jdbcTemplate.update(updateQuery, employee.getFirstName(),employee.getLastName(),employee.getId());
	}
}
