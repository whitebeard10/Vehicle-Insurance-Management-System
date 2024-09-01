package com.service;

import java.time.LocalDate;

import com.bean.Policy;
//import com.bean.Insurance;
//import com.bean.Underwriter;
//import com.dao.UnderwriterDao;
import com.dao.PolicyDao;
import com.dao.UnderwriterLoginDao;
//import com.dao.UnderwriterLoginDao;
//import com.util.DBUtil;
//
//import java.util.List;
//import java.util.Date;

public class InsuranceService {
	
	//validate login
	public int validateUnderWriterLogin(String userName, String password) {
		UnderwriterLoginDao newLoginDao = new UnderwriterLoginDao();
		return newLoginDao.validateLogin(userName, password);
	}

	//creating a new policy
	public int createPolicy(String vehicleNo, String vehicleType, String customerName, int engineNo, int chasisNo,
			String phoneNo, String insuranceType, LocalDate fromDate, int underwriterId) {
		Policy policy = new Policy(vehicleNo, vehicleType, customerName, engineNo, chasisNo, phoneNo, insuranceType, fromDate, underwriterId);
		PolicyDao policyDao = new PolicyDao();
		return policyDao.createPolicy(policy, underwriterId);
		
	}
	
	//renewing an existing/expired policy
	public Policy renewPolicy(int policyNo, int underwriterId) {
		PolicyDao renewPolicyDao = new PolicyDao();
		return renewPolicyDao.renewPolicy(policyNo, underwriterId);
	}
	
	
	//updating an exiting policy from third party to full insurance
	public Policy updatePolicy(int policyNo, int underwriterId) {
		PolicyDao updatePolicyDao = new PolicyDao();
		return updatePolicyDao.updatePolicyType(policyNo, underwriterId);
	}
	
	//view existing policy by policy number
	 public Policy viewPolicy(int policyNo) {
		 PolicyDao viewPolicyDao = new PolicyDao();
		 return viewPolicyDao.viewPolicy(policyNo);
	 }
	
	

}