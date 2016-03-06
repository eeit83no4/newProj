package module.controller.newStore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.model._07_StoreVO;
import module.service.newStore.newStoreService;

/**
 * Servlet implementation class createOrupdateStoreServlet
 */
@WebServlet("/newStore/createStoreServlet.controller")
public class createStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private newStoreService newStoreService=new newStoreService();
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		_07_StoreVO storeVO=new _07_StoreVO();
		
		String storeNoForUpdate=request.getParameter("storeNo");
		
		
		if(storeNoForUpdate!=null&&storeNoForUpdate.trim().length()>0){
			
		}else{
			
		}
		
		String storeName=request.getParameter("storeName");
		String storePhone=request.getParameter("storePhone");
		String storeAdd=request.getParameter("storeAdd");
		String[] storeClass=request.getParameterValues("storeClass");
		//--------------驗證是否有店家名稱
		String noStoreName=null;//錯誤訊息
		if(storeName==null||storeName.trim().length()==0){
			noStoreName="請輸入店家名稱";
		}
		if(noStoreName!=null&&noStoreName.trim().length()>0){
			request.setAttribute("noStoreName", noStoreName);
			request.getRequestDispatcher("/userOrder/createItems/createStore.jsp").forward(request, response);
			return;
		}
		//---------------
		storeVO.setStore_name(storeName);
		storeVO.setPhone(storePhone);
		storeVO.setAddress(storeAdd);
		//處理店家類型
		String storeClassString=null;
		for(String a:storeClass){
			if(storeClassString==null){
				storeClassString=a;
			}else{
				storeClassString=storeClassString+","+a;
			}
		}
		//------------
		Integer storeNo=newStoreService.newStore(storeVO, storeClassString);
		if(storeNo>0){
			//店家成功新增
			request.setAttribute("storeNo", storeNo);
			request.getRequestDispatcher("/userOrder/createItems/createItems.jsp").forward(request, response);
			return;
		}else{
			//店家新增失敗
			request.setAttribute("createError", "新增店家時發生錯誤啦!");
			request.getRequestDispatcher("").forward(request, response);
			return;
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
