package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBCustomer implements ICustomer{

	private static DBCustomer instance = null;
	
	public static DBCustomer getInstance()
	{
		if(instance==null)
			instance = new DBCustomer();
		return instance;
	}
	
	private Connection connection = null;
	
	private DBCustomer()
	{
		try
		{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connection = DriverManager.getConnection("jdbc:derby:ComedyVenueDB");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Customer> getAllCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		if(connection!=null)
		{
			try
			{
				String sql = "SELECT * FROM Customer ORDER BY id";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while(result.next())
				{
					int id = result.getInt("id");
					String name = result.getString("name");
					String email = result.getString("email");
					String phoneNr = result.getString("phoneNr");
					
					Customer customer = new Customer(id, name, email, phoneNr);
					customers.add(customer);
					
				}
				
				statement.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return customers;
	}

	@Override
	public void addCustomer(Customer c) {
		
		if(connection!=null)
		{
			try
			{
				String sql = "INSERT INTO Customer(name, email, phoneNr) VALUES ('" + c.getName()+"', '" + c.getEmail()+"', '"+c.getPhoneNr()+"')";
				Statement statement = connection.createStatement();
				statement.executeQuery(sql);
				statement.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void removeCustomer(Customer c) {
		
		if(connection!=null)
		{
			try
			{
				String sql = "DELETE FROM Customer " +
							"WHERE id = "+ c.getId();
				Statement statement = connection.createStatement();
				statement.executeQuery(sql);
				statement.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
