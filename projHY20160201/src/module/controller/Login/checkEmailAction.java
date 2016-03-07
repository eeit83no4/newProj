package module.controller.Login;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import module.dao._04_EmployeeDAO;
import net.sf.json.JSONArray;

public class checkEmailAction extends ActionSupport implements ServletResponseAware{
	
	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;		
	}
		
	
	@Override
	public String execute() throws Exception {		
		
		System.out.println("checkEmailAction");
		_04_EmployeeDAO _04empDAO=new _04_EmployeeDAO();
		List<String> allEmails=_04empDAO.getAllEmails();//將所有員工的email取出
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		JSONArray jSONObject=JSONArray.fromObject(allEmails);//轉換json
		response.getWriter().print(jSONObject);
		
		return Action.NONE;
	}

	
	
	
	
	

}
