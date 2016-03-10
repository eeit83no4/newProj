package module.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.fabric.Response;

import module.model._04_EmployeeVO;
import module.model._17_Group_UserVO;
import module.service.MyGroupService2db;

@WebServlet(
		urlPatterns={"/module.controller.group/MyGroupServlet.deleteOrder"}
)
public class MyGroupServlet_deleteOrder extends HttpServlet {
	private MyGroupService2db mgs2db = new MyGroupService2db();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
//		_04_EmployeeVO mb=(_04_EmployeeVO)session.getAttribute("LoginOK");
		String temp_detail_no = req.getParameter("detail_no");
//		Integer groupNo=Integer.parseInt(req.getParameter("groupno").trim());
		Integer detail_no = Integer.parseInt(temp_detail_no);
		mgs2db.deleteOrder(detail_no);
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
