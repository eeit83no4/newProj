package module.controller.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.service.MyGroupService2db;

@WebServlet(
		urlPatterns={"/module.controller.group/MyGroupServlet.payStatus"}
)
public class MyGroupServlet_paystatus extends HttpServlet {
	private MyGroupService2db mgService2db = new MyGroupService2db(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String prodaction = req.getParameter("prodaction");
		String temp_detail_no = req.getParameter("detail_no");
		String pay_status = req.getParameter("pay_status");
		String temp_group_no = req.getParameter("group_no");
		String temp_user_id = req.getParameter("user_id");
		
		if(prodaction.equals("表3付款狀態修改")){		
			Integer detail_no = Integer.parseInt(temp_detail_no);			
			mgService2db.updatePayStatus(detail_no,pay_status);
		}else if(prodaction.equals("表2付款狀態修改")){			
			Integer group_no = Integer.parseInt(temp_group_no);
			Integer user_id = Integer.parseInt(temp_user_id);
			mgService2db.updatePayStatusByUser(group_no,user_id,pay_status);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
