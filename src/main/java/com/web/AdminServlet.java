package com.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.bean.Policy;
import com.bean.Underwriter;
import com.dao.AdminDao;
import com.service.AdminService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String actionString = request.getParameter("action");
		if(actionString.equalsIgnoreCase("Search Underwriter")) {
			int underwriterId = Integer.parseInt(request.getParameter("underwriterId"));
			try {
				AdminService searchService = new AdminService();
				Underwriter searchedUnderwriter = searchService.searchUnderwriter(underwriterId);
				
				if(searchedUnderwriter != null) {
					request.setAttribute("underwriter", searchedUnderwriter);
				}else {
					request.setAttribute("createError", "No underwriter found with the provided ID.");
				}
			} catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("createError", e.getMessage());
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/searchUnderwriter.jsp");
			dispatcher.forward(request, response);
			
		}
		if (actionString.equalsIgnoreCase("Delete Underwriter")) {
			AdminService deleteService = new AdminService();
			int underwriterId = Integer.parseInt(request.getParameter("underwriterId"));
			try {
				boolean isDeleted = deleteService.deleteUnderwriter(underwriterId);
				if(isDeleted) {
					request.setAttribute("deleted", true);
					request.setAttribute("isDeletedMessage", "Sucessfully Deleted details for underwriter id: "+underwriterId);
				}else {
					request.setAttribute("isDeletedMessage", "No underwriter found with the provided ID.");
				}
			} catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("isDeletedMessage", e.getMessage());
			}
			request.getRequestDispatcher("/deleteUnderwriter.jsp").forward(request, response);
		
			
		}
		if(actionString.equalsIgnoreCase("View All Underwriters")) {
			AdminService viewUnderwriterService = new AdminService();
			try {
				List<Underwriter> underwriters = viewUnderwriterService.viewAllUnderwriters();
				int totalUnderwriters = viewUnderwriterService.totalUnderwriters();
				
				request.setAttribute("underwriters", underwriters);
				request.setAttribute("totalUnderwriters", totalUnderwriters);
				
				request.getRequestDispatcher("/viewAllUnderwriters.jsp").forward(request, response);
				
			}catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("errMessage", "Error: "+e.getMessage());
				request.getRequestDispatcher("/viewAllUnderwriters.jsp").forward(request, response);
			}
		}
		if(actionString.equalsIgnoreCase("View Vehicles")) {
			AdminService viewVehiclesByIDService = new AdminService();
			int underwriterId = Integer.parseInt(request.getParameter("underwriterId"));
			try {
				List<Policy> vehiclesList = viewVehiclesByIDService.viewVehicledByUnderwriterId(underwriterId);
				request.setAttribute("vehicles", vehiclesList);
				request.getRequestDispatcher("/viewAllVehicles.jsp").forward(request, response);
			}catch (Exception e) {
				// TODO: handle exception
				request.setAttribute("vehicleErrMessage", e.getMessage());
				request.getRequestDispatcher("/viewAllVehicles.jsp").forward(request, response);
				 
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String actionString = request.getParameter("action");
		if(actionString.equalsIgnoreCase("Login")) {
			if(request.getParameter("username").equals("admin")&&request.getParameter("password").equals("admin123")) {
				try {
					AdminService stats = new AdminService();
					int totalVehivcles = stats.totalVehicles();
					int totalUnderwriters = stats.totalUnderwriters();
					request.setAttribute("totalVehicles", totalVehivcles);
					request.setAttribute("totalUnderwriters", totalUnderwriters);
					request.getRequestDispatcher("/adminLanding.jsp").forward(request, response);
				} catch (Exception e) {
					// TODO: handle exception
					request.setAttribute("loginErrorMsg", "This error was observed: "+e.getMessage());
					request.getRequestDispatcher("/adminLanding.jsp").forward(request, response);
					
					
				}
			}else {
				request.setAttribute("loginErrorMsg", "Invalid Credentials!!");
				request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
			}
		}
		if (actionString.equalsIgnoreCase("Register Underwriter")) {
		    AdminService createUnderwriterAdminService = new AdminService();
		    String name = request.getParameter("underwriterName");
		    LocalDate dob = LocalDate.parse(request.getParameter("dob"));
		    LocalDate joiningDate = LocalDate.parse(request.getParameter("joiningDate"));
		    String password = request.getParameter("password");

		    int newUnderwriterId = createUnderwriterAdminService.registerUnderwriter(name, dob, joiningDate, password);

		    if (newUnderwriterId > 0) {
		        request.setAttribute("newID", newUnderwriterId);
		        request.setAttribute("message", "Creation Success!!");
		        request.getRequestDispatcher("/UnderwriterRegistration.jsp").forward(request, response);
		    } else {
		        request.setAttribute("message", "Try again!!!!");
		        request.setAttribute("newID", null);
		        request.getRequestDispatcher("/UnderwriterRegistration.jsp").forward(request, response);
		    }
		}

		if(actionString.equalsIgnoreCase("doUpdate")) {
			doUpdate(request,response);
		}
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    int underwriterId = Integer.parseInt(request.getParameter("underwriterId"));
	    String newPassword = request.getParameter("newPassword");
	    AdminService updatePasswordService = new AdminService();

	    // Call your DAO method to update the password and get the result code
	    //int result = underwriterDAO.updateUnderwriterPassword(underwriterId, newPassword);
	    int result = updatePasswordService.updateUnderwriterPassword(underwriterId, newPassword);

	    // Handle the result with a switch case
	    switch (result) {
	        case 1:
	            request.setAttribute("isUpdatedMessage", "The new password is the same as the old password.");
	            break;
	        case 2:
	            request.setAttribute("isUpdatedMessage", "Underwriter ID not found.");
	            break;
	        case 3:
	            request.setAttribute("isUpdatedMessage", "Password updated successfully.");
	            break;
	        default:
	            request.setAttribute("isUpdatedMessage", "An unexpected error occurred.");
	            break;
	    }

	    // Forward to the JSP page to display the message
	    request.getRequestDispatcher("/updateUnderwriterPassword.jsp").forward(request, response);
	}

}