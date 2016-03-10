package module.controller.userOrder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.service.attempGroupService;
import module.service.indexService;
@WebServlet("/index/indexServlet.controller")
public class indexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final indexService indexservice=new indexService();
	private static final attempGroupService att=new attempGroupService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		_04_EmployeeVO user=(_04_EmployeeVO)request.getSession().getAttribute("LoginOK");
		Integer userId=user.getUser_id();
		//進行中的團購(排除自己)
		List<Map<String, String>> ingGroups=indexservice.sendIngGroup(userId);
		//我發起的團購(只有自己)
		List<Map<String, String>> myGroups=indexservice.sendMeingGroup(userId);
		//最新店家
		List<_07_StoreVO> latestStores=att.getAllStoresTiemSorted();
		
		if(ingGroups.size()>0&&ingGroups!=null){
			request.setAttribute("ingGroups", ingGroups);
		}
		if(myGroups.size()>0&&myGroups!=null){
			request.setAttribute("myGroups", myGroups);
		}		
		request.setAttribute("latestStores", latestStores);		
		
		request.getRequestDispatcher("/realindex2.jsp").forward(request, response);
	}

	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
	
	
	
	

}
