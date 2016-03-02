package module.controller.group;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.service.AdminService;
import net.sf.json.JSONArray;
@WebServlet(
		urlPatterns={"/admin.controller"}
		)
public class AdminServlet extends HttpServlet {
	AdminService adminservice = new AdminService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodaction = req.getParameter("prodaction");
		
		
		
		if(prodaction.equals("訂單維護")){
			List<Map> all_order = adminservice.orderMaintain();
			JSONArray jSONObject=JSONArray.fromObject(all_order);
//			System.out.println(jSONObject);
			req.setAttribute("all_order", jSONObject);
			req.getRequestDispatcher("/MyGroup/admin.jsp").forward(req, resp);
			
		}else if(prodaction.equals("店家維護")){
			
		}else if(prodaction.equals("管理員維護")){
			
		}
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
