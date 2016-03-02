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
import module.service.MyGroupService;
import net.sf.json.JSONArray;

@WebServlet(
		urlPatterns={"/xxx.controller"}
)
public class MyGroupServlet_1 extends HttpServlet {
	MyGroupService myGroupService = new MyGroupService();

	@Override
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		//接收資料		
				String prodaction = request.getParameter("prodaction");
				_04_EmployeeVO emp = (_04_EmployeeVO)request.getSession().getAttribute("LoginOK");
				
			

		//根據model執行結果顯示view
				if(prodaction.equals("進行中的團購")){
					List<String[]> group_ing = myGroupService.searchMyAllGroup_ing(emp.getUser_id());

					JSONArray jSONObject=JSONArray.fromObject(group_ing);//轉換json
//					System.out.println(jSONObject);
					request.setAttribute("select", jSONObject);
					request.getRequestDispatcher(
							"/MyGroup/group_details_first.jsp").forward(request, response);
					
				}else if(prodaction.equals("已完成的團購")){
					List<String[]> group_ed = myGroupService.searchMyAllGroup_ed(emp.getUser_id());

					JSONArray jSONObject=JSONArray.fromObject(group_ed);//轉換json
//					System.out.println(jSONObject);
					request.setAttribute("select", jSONObject);
					request.getRequestDispatcher(
							"/MyGroup/group_details_second.jsp").forward(request, response);
					
				}else if(prodaction.equals("個人歷史訂購紀錄")){
					List<Map> personalHR= myGroupService.personalHistoryRecord(emp.getUser_id());

					JSONArray jSONObject=JSONArray.fromObject(personalHR);//轉換json
//					System.out.println(jSONObject);
					request.setAttribute("select", jSONObject);
					request.getRequestDispatcher(
							"/MyGroup/group_details_third.jsp").forward(request, response);

				}	
	}
	
	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
