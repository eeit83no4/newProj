package module.controller.group;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.dao._16_Group_RecordDAO;
import module.model._04_EmployeeVO;
import module.service.MyGroupService;
import module.util.HibernateUtil;
import net.sf.json.JSONArray;


@WebServlet(
		urlPatterns={"/MyGroup/group_detail.controller"})
public class MyGroupServlet_2 extends HttpServlet {
	private MyGroupService myGroupService = new MyGroupService();
	_16_Group_RecordDAO gr = new _16_Group_RecordDAO();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {				
		
		//接收資料		
		String temp_group_no = req.getParameter("xxx");
		String prodaction = req.getParameter("prodaction");
		_04_EmployeeVO emp = (_04_EmployeeVO)req.getSession().getAttribute("LoginOK");
		//轉換資料
		Integer group_no = Integer.parseInt(temp_group_no); 
		//驗證資料
		//呼叫model		
		//根據model執行結果顯示view
		List<String[]> detailDetail = myGroupService.orderDetail_detail_new(group_no);
		JSONArray jSONObject=JSONArray.fromObject(detailDetail);//轉換json
		List<List<String>> detailByUser = myGroupService.orderDetail_ByUser(group_no);
		JSONArray jSONObject2=JSONArray.fromObject(detailByUser);//轉換json		
		List<List<String>> detailByItem = myGroupService.orderDetail_ByItem(group_no);
		JSONArray jSONObject3=JSONArray.fromObject(detailByItem);//轉換json
		
		List<String[]> detailUpper= myGroupService.orderDetail_byGroup_upper(group_no);
		long longSec =  Long.parseLong(detailUpper.get(0)[7],10);
		String longDay =  myGroupService.getTimeDay(longSec);
		req.setAttribute("status", detailUpper.get(0)[8]);
		req.setAttribute("EndSec", longSec);
		req.setAttribute("EndDay", longDay);
		req.setAttribute("group_no", myGroupService.orderDetail_byGroup_upper(group_no).get(0)[9]);
		req.setAttribute("group_status", myGroupService.findCo_holder(emp.getUser_id(), group_no));		

		req.setAttribute("detail_Detail", jSONObject);
		req.setAttribute("detail_ByUser", jSONObject2);
		req.setAttribute("detail_ByItem", jSONObject3);
		req.setAttribute("detailUpper", detailUpper);
		req.getRequestDispatcher("/MyGroup/group_detail.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	
	
}
