package module.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.service.CreateGroupService;


@WebServlet("/UpstoreDeletestore.controller")
public class UpstoreDeletestore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String dlstore_no =request.getParameter("dlstore_no");
//		System.out.println(dlstore_no);
		
		Integer dlstore_no =Integer.parseInt(request.getParameter("dlstore_no"));
		
		CreateGroupService CGS =new CreateGroupService();
		CGS.dlData(dlstore_no);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
