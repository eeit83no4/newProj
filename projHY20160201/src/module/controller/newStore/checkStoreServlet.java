package module.controller.newStore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.dao._07_StoreDAO;
import module.model._07_StoreVO;
import module.service.newStore.updateStoreService;

/**
 * Servlet implementation class checkStoreServlet
 */
@WebServlet("/newStore/checkStoreServlet.controller")
public class checkStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
    private _07_StoreDAO _07dao=new _07_StoreDAO();
	private updateStoreService updateStoreService=new updateStoreService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String storeNo=request.getParameter("storeNo");
		if(storeNo!=null&&storeNo.trim().length()>0){
			//---------------修改店家
			System.out.println("xxxxxxxxxxxxxx");
			int store_no=Integer.parseInt(storeNo);
			_07_StoreVO storeVO=_07dao.findById(store_no);
			String storeClasses=updateStoreService.findStoreClass(store_no);
			request.setAttribute("storeVO", storeVO);
			request.setAttribute("storeClasses", storeClasses);
			request.getRequestDispatcher("/userOrder/createItems/createStore.jsp").forward(request, response);
			return;
		}else{
			//----------新增店家
			response.sendRedirect("/projHY20160201/userOrder/createItems/createStore.jsp");
		}
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
