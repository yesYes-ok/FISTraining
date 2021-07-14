package com.fis.app.dao;

import java.util.List;

import exce.ElectronicDevicesNotFoundException;
import model.ElectronicDevices;

public interface IElectronicDeviceDAO {

	
	public boolean addDevice(ElectronicDevices device);
	public List<ElectronicDevices> getAllDevices();
	
	public boolean changeDeviceCost(int deviceId,int newCost)throws ElectronicDevicesNotFoundException;
	public boolean changeDeviceRating(int deviceId,int newRating)throws ElectronicDevicesNotFoundException;
	
	public boolean deleteDevice(int deviceId)throws ElectronicDevicesNotFoundException;
	
	public List<ElectronicDevices> getDevicesBasedOnBrandNameAndType(String brandName,String type);
	public int countDeviceType(String type);
	public int getSumofPriceBasedOnType(String type);
	public List<ElectronicDevices> getDeviceBasedOnPriceRangeAndType(int range1, int range2, String type,List<ElectronicDevices> list);
	
	
	
}