package module.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import module.model._04_EmployeeVO;
import module.service.CreateGroupService;

@WebServlet("/CreateGroupservlet.controller")
public class CreateGroupservlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession servletSession=request.getSession();
/*----------------------------------------------------------------------------------------------------------*/
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxasasasasasasasas");
		_04_EmployeeVO xx = (_04_EmployeeVO)servletSession.getAttribute("LoginOK");
		//須抓取使用者登入的id 僅test用
		Integer user_id=null;
		try {
			user_id = xx.getUser_id();
//			user_name = String.valueOf(xx.getC_name());
		} catch (Exception e) {				
			System.out.println("StoreServlet轉型錯誤:"+e.toString());
		}
		
/*----------------------------------------------------------------------------------------------------------*/		

		
		
		Integer store_no = Integer.valueOf(request.getParameter("store_no")).intValue(); 
//		System.out.println(store_no);
		
		String itemno=request.getParameter("itemno");
//		System.out.println(itemno);
		
		String[] item_no = itemno.split(",");
		Integer[] itemNo=new Integer[item_no.length];
		
//		for(String a:item_no){
//			itemNo[i]=Integer.parseInt(a.trim());
//			System.out.println("++++++++++++++++"+itemNo[i]);
//			i++;
//			
//		}
//		for(Integer b:itemNo){
//			System.out.println("int****"+b);
//		}
/*----------------------------------------------------------------------------------------------------------*/
		
		int i=0;
		CreateGroupService dao=new CreateGroupService();
		Integer x = dao.findStoreAndInsert(store_no, user_id);
		
		for(String a:item_no){
			itemNo[i]=Integer.parseInt(a.trim());
			dao.findItemAndInsert(itemNo[i]);
			dao.findClass3AndInsert(itemNo[i]);
			dao.findItemPriceAndInsert(itemNo[i]);
			i++;
		}
		System.out.println("bbbbbb"+x);
		request.setAttribute("newstoreno", x);
		
//		request.setAttribute("newstoreno", newstoreno);
		request.getRequestDispatcher("/StorePage/OpenStoreForGroup.jsp").forward(request, response);
		
		
/*----------------------------------------------------------------------------------------------------------*/
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
