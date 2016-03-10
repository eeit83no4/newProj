package module.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.service.MyGroupService2db;

@WebServlet(
		urlPatterns={"/module.controller.group/MyGroupServlet.deleteOrder"}
)
public class MyGroupServlet_deleteOrder extends HttpServlet {
	private MyGroupService2db mgs2db = new MyGroupService2db();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String temp_detail_no = req.getParameter("detail_no");
		Integer detail_no = Integer.parseInt(temp_detail_no);
		mgs2db.deleteOrder(detail_no);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
