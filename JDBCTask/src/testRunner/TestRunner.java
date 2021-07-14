package testRunner;

import java.util.ArrayList;
import java.util.List;

import model.ElectronicDevices;

import com.fis.app.dao.ElectronicDeviceDAOImplementation;
import com.fis.app.dao.IElectronicDeviceDAO;

import model.ElectronicDevices;

public class TestRunner {

	public static void main(String[] args) {
		
		try {
			ElectronicDevices ed1 = new ElectronicDevices("laptop", 1, "dell", 1000, 100, 5, "black");
			ElectronicDevices ed2 = new ElectronicDevices("moblie", 2, "lg", 100, 50, 4, "white");
			ElectronicDevices ed3 = new ElectronicDevices("computer", 3, "sony", 2000, 200, 5, "yellow");
			ElectronicDevices ed4 = new ElectronicDevices("computer", 4, "dell", 3000, 100, 5, "brown");
			List<ElectronicDevices> elist = new ArrayList<>();
			elist.add(ed1);
			elist.add(ed2);
			elist.add(ed3);
			elist.add(ed4);
			//System.out.println(elist);
			
			IElectronicDeviceDAO dao = new ElectronicDeviceDAOImplementation();
			
			//boolean a = dao.addDevice(ed1);
			/*boolean b = dao.addDevice(ed2);
			boolean c = dao.addDevice(ed3);*/
			//boolean d = dao.deleteDevice(1);
			//System.out.println("deleted "+d);
			/*boolean e = dao.addDevice(ed4);
			System.out.println("added "+e);*/
			/*boolean f = dao.changeDeviceCost(1, 1500);
			System.out.println("cahnged "+f);*/
			/*boolean g = dao.changeDeviceRating(1, 4);
			System.out.println("chaged "+g);*/
			/*int count = dao.countDeviceType("computer");
			System.out.println("Count "+count);*/
			/*int sum = dao.getSumofPriceBasedOnType("computer");
			System.out.println("sum "+sum);*/
			List<ElectronicDevices> list = dao.getDeviceBasedOnPriceRangeAndType(1000, 3000, "computer", elist);
			System.out.println(list);
			
			
			/*List<ElectronicDevices> list = dao.getDevicesBasedOnBrandNameAndType("dell", "Laptop");
			
			list.stream().forEach((emp)->System.out.println(emp));*/	
		} catch (Exception e) {
			System.out.println(" Problem "+e);
		}
	}
}