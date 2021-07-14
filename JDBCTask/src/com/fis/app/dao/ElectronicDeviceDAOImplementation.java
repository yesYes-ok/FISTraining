package com.fis.app.dao;

import model.ElectronicDevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exce.ElectronicDevicesNotFoundException;
import model.ElectronicDevices;

public class ElectronicDeviceDAOImplementation implements IElectronicDeviceDAO  {

	Connection con = null;
	
	String insertElecDevQuery = "insert into fisapp.electronicdevice values(?,?,?,?,?,?,?)";
	String selectAllElecDev = "select * from fisapp.electronicdevice";
	String changeDeviceCost = "update fisapp.electronicdevice set cost = ? where idelectronicdevice = ?";
	String changeDeviceRating = "update fisapp.electronicdevice set stratRating = ? where idelectronicdevice = ?";
	String deleteDevice= "delete from fisapp.electronicdevice where idelectronicdevice = ?";
	String getDeviceByBrandNameAndType = "select * from fisapp.electronicdevice where brandName = ? and deviceType = ?";
	String countType = "select count(deviceType) from fisapp.electronicdevice where deviceType = ?";
	String sumOfPrice = "select sum(cost) from fisapp.electronicdevice where deviceType = ?";
	
	
	
	public boolean addDevice(ElectronicDevices device){
		con = DatabaseUtil.getConnection();
		boolean isInserted = false;
		try{
		if (con != null) {

			// Step1 : extract employee details from e object
			int id = device.getDeviceId();
			String name = device.getBrandName();
			String deviceType = device.getDeviceType();
			int cost = device.getCost();
			int power = device.getPower();
			int starRating = device.getStarRatings();
			String color = device.getColor();

			// Step 2 :- write the code to create complete insert Query with data
			PreparedStatement ps = con.prepareStatement(insertElecDevQuery);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, deviceType);
			ps.setInt(4, cost);
			ps.setInt(5, power);
			ps.setInt(6, starRating);
			ps.setString(7, color);

			// Step 3 : execute Query
			int i = ps.executeUpdate(); // only works with insert , update & delete

			if (i > 0)
				isInserted = true;

		}
		}
		catch (Exception e){
			System.out.println(e);
		}

		return isInserted;
	}
	
	public List<ElectronicDevices> getAllDevices(){
		con = DatabaseUtil.getConnection();
		List<ElectronicDevices> edList = new ArrayList<>();
		try{
		if (con != null) {

			PreparedStatement ps = con.prepareStatement(selectAllElecDev);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ElectronicDevices temp = new ElectronicDevices();
				temp.setBrandName(rs.getString("brandName"));
				temp.setDeviceType(rs.getString("deviceType"));
				temp.setColor(rs.getString("color"));
				temp.setCost(rs.getInt("cost"));
				temp.setDeviceId(rs.getInt("idelectronicdevice"));
				temp.setStarRatings(rs.getInt("startRating"));
				edList.add(temp);
			}

		}
		}
		catch (Exception e){
			System.out.println(e);
		}
		return edList;
	}

	
	
	
	public boolean deleteDevice(int deviceId){
		
		con = DatabaseUtil.getConnection();
		boolean isDeleted = false;
		try{
		if (con != null){
			PreparedStatement ps = con.prepareStatement(deleteDevice);
			 //ResultSet rs = ps.executeQuery();
			 ps.setInt(1, deviceId);
			 int i = ps.executeUpdate(); // only works with insert , update & delete

				if (i > 0)
					isDeleted = true;
		}		
		}
		catch (Exception e){
			System.out.println(e);
		}
		return isDeleted;
	}

	
	public List<ElectronicDevices> getDevicesBasedOnBrandNameAndType(String brandName,String type){
		con = DatabaseUtil.getConnection();
		List<ElectronicDevices> edList = new ArrayList<>();
		try{
		if (con != null) {

			PreparedStatement ps = con.prepareStatement(getDeviceByBrandNameAndType);
			ps.setString(1, brandName);
			ps.setString(2, type);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ElectronicDevices temp = new ElectronicDevices();
				temp.setBrandName(rs.getString("brandName"));
				temp.setDeviceType(rs.getString("deviceType"));
				temp.setColor(rs.getString("color"));
				temp.setCost(rs.getInt("cost"));
				temp.setDeviceId(rs.getInt("idelectronicdevice"));
				temp.setStarRatings(rs.getInt("stratRating"));
				edList.add(temp);
			}

		}
		}
		catch (Exception e){
			System.out.println(e);
		}
		return edList;
		
	}
	
	
	public boolean changeDeviceCost(int deviceId,int newCost){
		con = DatabaseUtil.getConnection();
		boolean changed = false;
		try{
		if (con != null){
			
			PreparedStatement ps = con.prepareStatement(changeDeviceCost);
			//ResultSet rs = ps.executeQuery();
			ps.setInt(1, newCost);
			ps.setInt(2, deviceId);
			int i = ps.executeUpdate(); // only works with insert , update & delete

			if (i > 0)
				changed = true;
	
		}
		}
		catch (Exception e){
			System.out.println(e);
		}
		return changed;
	}

	

	@Override
	public boolean changeDeviceRating(int deviceId, int newRating)
			throws ElectronicDevicesNotFoundException {
		con = DatabaseUtil.getConnection();
		boolean changed = false;
		try{
		if (con != null){
			
			PreparedStatement ps = con.prepareStatement(changeDeviceRating);
			//ResultSet rs = ps.executeQuery();
			ps.setInt(1, newRating);
			ps.setInt(2, deviceId);
			int i = ps.executeUpdate(); // only works with insert , update & delete

			if (i > 0)
				changed = true;
	
		}
		}
		catch (Exception e){
			System.out.println(e);
		}
		return changed;
		
	}

	@Override
	public int countDeviceType(String type) {
		// TODO Auto-generated method stub
		int c=0;
		con = DatabaseUtil.getConnection();
		try{
			if (con != null){
				PreparedStatement ps = con.prepareStatement(countType);
				ps.setString(1, type);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					 c = rs.getInt(1);
				}
			}
			}
			catch (Exception e){
				System.out.println(e);
			}		
		return c;
	}

	@Override
	public int getSumofPriceBasedOnType(String type) {
		int sum=0;
		con = DatabaseUtil.getConnection();
		try{
			if (con != null){
				PreparedStatement ps = con.prepareStatement(sumOfPrice);
				ps.setString(1, type);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()){
					sum = rs.getInt(1);
				}
			}
			}
			catch (Exception e){
				System.out.println(e);
			}		
		return sum;
	}

	@Override
	public List<ElectronicDevices> getDeviceBasedOnPriceRangeAndType(
			int range1, int range2, String type,List<ElectronicDevices> list) {
		List<ElectronicDevices> el = new ArrayList<>();
		el = list;
		el = el.stream().filter(device ->{
			return (device.getCost()>range1 && device.getCost()<=range2) && (device.getDeviceType().equals(type));
		}).collect(Collectors.toList());
		
		return el;
	}

	
	
	
	
	
	
}
	
	

