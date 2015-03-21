package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DBEvent implements IEventsList{

	private static DBEvent instance = null;
	
	public static DBEvent getInstance()
	{
		if(instance==null)
			instance = new DBEvent();
		return instance;
	}
	
	private Connection connection = null;
	
	private DBEvent()
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
	public ArrayList<Event> getAllEvents() {
		ArrayList<Event> events = new ArrayList<Event>();
		if(connection!=null)
		{
			try
			{
				String sql = "SELECT * FROM Event ORDER BY date";
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql);
				while(result.next())
				{
					int id = result.getInt("id");
					String name = result.getString("name");
					int price = result.getInt("price");
					String description = result.getString("description");
					int minAge = result.getInt("minAge");
					Date date = result.getDate("date");
					int seats = result.getInt("seats");
					
					ArrayList<Comedian> comedians = new ArrayList<Comedian>();
					
					sql = "SELECT comedianId FROM ComedianBooking WHERE eventId ="+id;
					ResultSet comedianBookingResult = statement.executeQuery(sql);
					while(comedianBookingResult.next())
					{
						//Get Comedian ID from ComedianBookings
						int comedianId=comedianBookingResult.getInt("comedianId");
						//Use comedian ID to get Comedian from Comedian						
						sql = "SELECT * FROM Comedian WHERE id ="+comedianId;
						ResultSet comedianResult = statement.executeQuery(sql);
						
						while(comedianResult.next())
						{
							//Get Comedian Data
							String comedianName = comedianResult.getString("name");
							String comedianDesc = comedianResult.getString("description");
							
							comedians.add(new Comedian(comedianId, comedianName, comedianDesc));

						}
						
					}
					
					Event event = new Event(id, name, price, description, minAge, date,  seats, comedians);
					events.add(event);
					
				}
				
				statement.close();
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return events;
	}

	@Override
	public void addEvent(Event e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEvent(Event e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEvent(Event e) {
		// TODO Auto-generated method stub
		
	}

}
