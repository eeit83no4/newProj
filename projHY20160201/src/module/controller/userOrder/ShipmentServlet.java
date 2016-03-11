package module.controller.userOrder;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShipmentServlet
 */
@WebServlet("/userOrder/ShipmentServlet.controller")
public class ShipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String unCheckShip=request.getParameter("ajaxShipt");
		System.out.println("unCheckShip="+unCheckShip);
//		response.getWriter().write("this is a test ajax ="+unCheckShip);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
