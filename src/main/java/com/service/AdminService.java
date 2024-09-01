package com.service;

import java.time.LocalDate;
import java.util.List;

import com.bean.Policy;
import com.bean.Underwriter;
import com.dao.AdminDao;

public class AdminService {
	
	//to get total vehicles in the records
	public int totalVehicles() {
		AdminDao totalVehicleDao = new AdminDao();
		return totalVehicleDao.getTotalVehicles();
	}
	
	//to get total underwriters in the records
	public int totalUnderwriters() {
		AdminDao totalUnderwritersDao = new AdminDao();
		return totalUnderwritersDao.getTotalUnderwriters();
	}
	
	//register new underwriter
	public int registerUnderwriter(String name, LocalDate dob, LocalDate joiningDate, String password) {
		Underwriter createUnderwriter = new Underwriter(name, dob, joiningDate, password);
		AdminDao createUnderwriterDao = new AdminDao();
		return createUnderwriterDao.registerUnderwriter(createUnderwriter);
		
	}
	
	//search underwriter by ID
	public Underwriter searchUnderwriter(int underwriterId) {
		AdminDao searchDao = new AdminDao();
		return searchDao.getUnderwriterById(underwriterId);
		
	}
	
	//delete underwriter by ID
	public boolean deleteUnderwriter(int underwriterId) {
		AdminDao delteteDao = new AdminDao();
		return delteteDao.deleteUnderwriter(underwriterId);
	}
	
	//update underwriter password
	public int updateUnderwriterPassword(int underwriterID, String newPassword) {
		AdminDao updatePasswordDao = new AdminDao();
		return updatePasswordDao.updateUnderwriterPassword(underwriterID, newPassword);
	}
	
	//view all underwriters with their details
	public List<Underwriter> viewAllUnderwriters(){
		AdminDao viewAllUnderwriters = new AdminDao();
		return viewAllUnderwriters.getAllUnderwriters();
	}
	
	//view all vehicles registered by underwriter ID
	public List<Policy> viewVehicledByUnderwriterId(int underwriterID){
		AdminDao viewVehiclesDao = new AdminDao();
		return viewVehiclesDao.getVehiclesByUnderwriterId(underwriterID);
	}
	
	
	
	

}