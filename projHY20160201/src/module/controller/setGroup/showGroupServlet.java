package module.controller.setGroup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.dao._04_EmployeeDAO;
import module.dao._17_Group_UserDAO;
import module.dao._17_Group_User_InterfaceDAO;
import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.model._16_Group_RecordVO;
import module.model._17_Group_UserVO;
import module.service.SetGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@WebServlet("/SetGroup/SetGroup.controller")
public class showGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		_04_EmployeeDAO _04DAO=new _04_EmployeeDAO();
		SetGroupService setGroupService = new SetGroupService();
//-----------------------------------------------------------------------------	

		int store_no = 1; // 店家編號

		// ----店家名稱
		String sname = setGroupService.findStoreName(store_no);

		// ------所有員工
		List<String[]> emp = _04DAO.getAllEmp();
	
		// ------所有部門
		List<String[]> dep =_04DAO.getAllDep();
		
		//------依部門分員工


		request.setAttribute("dep", dep);
		request.setAttribute("emdep", emp);
		request.setAttribute("sname", sname);
		System.out.println("xxxx");
		request.getRequestDispatcher("/setGroup/SetGroup3.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
