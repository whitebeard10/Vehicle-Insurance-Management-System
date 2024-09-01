package com.web;

import java.io.IOException;
//import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.InsetsUIResource;

import com.bean.Policy;
//import com.bean.Insurance;
import com.service.InsuranceService;

/**
 * Servlet implementation class InsurenceServlet
 */
@WebServlet("/InsurenceServlet")
public class InsuranceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int uID = -1;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsuranceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

//		InsuranceService services = new InsuranceService();
		InsuranceService underwriterService = new InsuranceService();
//		InsuranceService insuranceService = new InsuranceService();
		// RequestDispatcher rDispatcher=null;
		String actionString = request.getParameter("action");
		if ("Login".equalsIgnoreCase(actionString)) {
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			uID = underwriterService.validateUnderWriterLogin(userName, password);
			if (uID != -1) {
				response.sendRedirect("underwriterLanding.jsp");
				HttpSession session = request.getSession();
				session.setAttribute("uID", uID);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("", null);
				request.setAttribute("error_msg", "Invalid credentials!!");
				response.sendRedirect("underwriterLogin.jsp?error=Invalid Credentials!!");

			}
		}

		// rDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	private InsuranceService updatePolicyService;

    @Override
    public void init() throws ServletException {
    	updatePolicyService = new InsuranceService();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		// RequestDispatcher rDispatcher=null;
		String actionString = request.getParameter("action");
		if ("Create Insurance".equals(actionString)) {
		    InsuranceService createInsuranceService = new InsuranceService();
		    String vehicleNo = request.getParameter("vehicleNo");
		    String vehicleType = request.getParameter("vehicleType");
		    String customerName = request.getParameter("customerName");
		    int engineNo = Integer.parseInt(request.getParameter("engineNo"));
		    int chasisNo = Integer.parseInt(request.getParameter("chasisNo"));
		    String phoneNo = request.getParameter("phoneNo");
		    String insuranceType = request.getParameter("insuranceType");
		    LocalDate fromDate = LocalDate.parse(request.getParameter("fromDate"));
		    int underwriterId = uID;

		    int result = createInsuranceService.createPolicy(vehicleNo, vehicleType, customerName, engineNo, chasisNo,
		            phoneNo, insuranceType, fromDate, underwriterId);
		    
		    // Set appropriate attributes based on result
		    if (result > 0) {
		        request.setAttribute("newID", result);
		        request.setAttribute("message", "Creation Success!!");
		    } else {
		        request.setAttribute("message", "Policy was not created!");
		        request.setAttribute("newID", null);
		    }
		    
		    // Forward to JSP to display results
		    request.getRequestDispatcher("/underwriterLanding.jsp").forward(request, response);
		}
		if ("Renew Policy".equalsIgnoreCase(actionString)) {
			InsuranceService renewInsuranceService = new InsuranceService();
			int policyNo = Integer.parseInt(request.getParameter("policyID"));
			Policy renewedPolicy = renewInsuranceService.renewPolicy(policyNo, uID);
			if (renewedPolicy != null) {
				// Set updated policy details as request attributes
				request.setAttribute("renewPolicyID", renewedPolicy.getPolicyNo());
				request.setAttribute("renewedCustomerName", renewedPolicy.getCustomerName());
				request.setAttribute("renewedUpdatedType", renewedPolicy.getInsuranceType());
				request.setAttribute("renewedExpirationDate", renewedPolicy.getToDate().toString());
				request.setAttribute("renewedPremiumAmount", renewedPolicy.getPremiumAmt());
			} else {
				// If the policy renewal failed, set an error message
				request.setAttribute("RenewerrorMessage", "Policy renewal failed. Please try again.");
			}

			// Forward back to the original JSP page
			request.getRequestDispatcher("/underwriterLanding2.jsp").forward(request, response);

		}
		if ("Update Policy".equalsIgnoreCase(actionString)) {
			int policyId = Integer.parseInt(request.getParameter("uPolicyID"));
	        int underwriterId = uID;

	        try {
	            Policy updatedPolicy = updatePolicyService.updatePolicy(policyId, underwriterId);

	            request.setAttribute("updatedPolicyID", updatedPolicy.getPolicyNo());
	            request.setAttribute("updatedCustomerName", updatedPolicy.getCustomerName());
	            request.setAttribute("updatedInsuranceType", updatedPolicy.getInsuranceType());
	            request.setAttribute("updatedExpirationDate", updatedPolicy.getToDate().toString());
	            request.setAttribute("updatedPremiumAmt", updatedPolicy.getPremiumAmt());

	            RequestDispatcher dispatcher = request.getRequestDispatcher("/underwriterLanding3.jsp");
	            dispatcher.forward(request, response);

	        } catch (RuntimeException e) {
	            request.setAttribute("UpdateErrorMessage", e.getMessage());
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/underwriterLanding3.jsp");
	            dispatcher.forward(request, response);
	        }
	    }
		
		if ("View Policy".equalsIgnoreCase(actionString)) {
		    int policyId = Integer.parseInt(request.getParameter("vPolicyID"));
		    try {
		        InsuranceService viewService = new InsuranceService();
		        Policy viewingPolicy = viewService.viewPolicy(policyId);
		        if (viewingPolicy != null) {
		            request.setAttribute("viewedPolicy", viewingPolicy);
		        } else {
		            request.setAttribute("ViewErrorMessage", "No data found for the given policy ID.");
		        }
		    } catch (Exception e) {
		        request.setAttribute("ViewErrorMessage", "An error occurred while retrieving the policy: " + e.getMessage());
		    }
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/underwriterLanding4.jsp");
		    dispatcher.forward(request, response);
		}

	}

}