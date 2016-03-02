package module.controller.Login;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import module.dao._04_EmployeeDAO;
import module.model._04_EmployeeVO;

public class loginAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}

	@Override
	public String execute() throws Exception {
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		_04_EmployeeVO mb;
		_04_EmployeeDAO dao=new _04_EmployeeDAO();	
		//透過使用者的email取的使用者在資料庫的資料
		mb = dao.findActive("email",email);
		System.out.println("mb="+mb);
		//將使用者的資料存入session，識別字串為LoginOK
		session.put("LoginOK", mb);
		return Action.NONE;
	}
	
	
	
	

}
