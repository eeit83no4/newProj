package module.controller.group;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.model._04_EmployeeVO;
import module.service.MyGroupService;
import module.service.MyGroupService2db;
import module.service.attempGroupService;
import net.sf.json.JSONArray;

@WebServlet(
		urlPatterns={"/module.controller.group/MyGroupServlet_3.controller"})


public class MyGroupServlet_3 extends HttpServlet {
	private MyGroupService2db mg = new MyGroupService2db(); 
	private MyGroupService myGroupService = new MyGroupService();
	private attempGroupService att=new attempGroupService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//接收資料		
		String temp_group_no = req.getParameter("xxx");
		String prodaction = req.getParameter("prodaction");
		String enddate = req.getParameter("enddate");
		_04_EmployeeVO emp = (_04_EmployeeVO)req.getSession().getAttribute("LoginOK");
		String failure = req.getParameter("failure");
		
		//轉換資料
		Integer group_no = Integer.parseInt(temp_group_no);
		//驗證資料
		//呼叫model		
		//根據model執行結果顯示view
		List<String[]> detailDetail = myGroupService.orderDetail_detail(group_no);
		JSONArray jSONObject=JSONArray.fromObject(detailDetail);//轉換json
		List<List<String>> detailByUser = myGroupService.orderDetail_ByUser(group_no);
		JSONArray jSONObject2=JSONArray.fromObject(detailByUser);//轉換json
		
		List<List<String>> detailByItem = myGroupService.orderDetail_ByItem(group_no);
		JSONArray jSONObject3=JSONArray.fromObject(detailByItem);//轉換json
		
		if(prodaction.equals("sucess")){
			System.out.println("HHHHHHHHHHHHHH");
			att.shipmentCount(group_no);
			mg.updateGroupStatus_success(group_no);
			
			resp.sendRedirect("/projHY20160201/xxx.controller?prodaction=已完成的團購");
			
		}else if(prodaction.equals("end")){
			mg.updateGroupEndDate(group_no);
			
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
						
			
		}else if(prodaction.equals("訂購失敗")){
			
			MyGroupService2db mgsService = new MyGroupService2db();
			mgsService.updateForFail(group_no, failure);//存入資料庫
			
			req.setAttribute("failure", failure);//失敗原因傳回
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
		}else if(prodaction.equals("重設時間")){
			System.out.println("group_no = " + group_no);
			System.out.println("enddate" + enddate);
			mg.updateEndTime(group_no, enddate);
			
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

		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	

}
