package exce;

public class ElectronicDevicesNotFoundException extends Exception {
	
	private int id;
	
	public ElectronicDevicesNotFoundException(int id)
	{
		this.id = id;
	}
	
	public String toString()
	{
		return "Invalid Employee ID "+id;
	}

}

