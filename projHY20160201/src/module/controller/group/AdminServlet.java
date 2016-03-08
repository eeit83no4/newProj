package module.controller.group;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.model._04_EmployeeVO;
import module.service.AdminService;
import net.sf.json.JSONArray;
@WebServlet(
		urlPatterns={"/admin.controller"}
		)
public class AdminServlet extends HttpServlet {
	AdminService adminservice = new AdminService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		_04_EmployeeVO employeeVO = (_04_EmployeeVO)req.getSession().getAttribute("LoginOK");
//		System.out.println(employeeVO.getUser_id()); //登入者的ID
		//接收資料
		String prodaction = req.getParameter("prodaction");
		String auth = req.getParameter("auth");
		String user_id = req.getParameter("user_id");
		String group_noString = req.getParameter("groupno");
		
	
			if(prodaction.equals("主畫面")){
				List<Map> all_order = adminservice.orderMaintain();
				JSONArray jSONObject1=JSONArray.fromObject(all_order);
				List<Map> all_store = adminservice.storeMaintain();
				JSONArray jSONObject2=JSONArray.fromObject(all_store);
				List<Map> all_admin = adminservice.adminMaintain();
				JSONArray jSONObject3=JSONArray.fromObject(all_admin);				
				req.setAttribute("all_order", jSONObject1);
				req.setAttribute("all_store", jSONObject2);
				req.setAttribute("all_admin", jSONObject3);
				req.getRequestDispatcher("/MyGroup/admin.jsp").forward(req, resp);	
			}else if(prodaction.equals("刪除團購")){
				Integer group_no=Integer.valueOf(group_noString).intValue();
				adminservice.delete(group_no);
			}else if(prodaction.equals("管理員維護")){
				adminservice.updateAuthByUserId(user_id, auth);
			}
			
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
