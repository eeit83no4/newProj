package module.controller.setGroup;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.dao._04_EmployeeDAO;
import net.sf.json.JSONArray;
@WebServlet("/SetGroup/showDepEmp.controller")
public class showDepEmpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		
		
		String dep2 = req.getParameter("dep_id");
		System.out.println(dep2);
		_04_EmployeeDAO _04DAO=new _04_EmployeeDAO();
		
		
		List<String[]> finbydep =_04DAO.findByDep(dep2);
        JSONArray jSONObject=JSONArray.fromObject(finbydep);
		System.out.println("------"+finbydep);
		
		resp.getWriter().print(jSONObject);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}

}
