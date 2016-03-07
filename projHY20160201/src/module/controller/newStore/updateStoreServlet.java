package module.controller.newStore;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.model._07_StoreVO;
import module.service.newStore.newStoreService;

/**
 * Servlet implementation class updateStoreServlet
 */
@WebServlet("/newStore/updateStoreServlet")
public class updateStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private newStoreService newStoreService=new newStoreService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		_07_StoreVO storeVO=new _07_StoreVO();
		String storeNoForUpdate=request.getParameter("storeNo");		
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
		//處理店家類型
		String storeClassString=null;
		for(String a:storeClass){
			if(storeClassString==null){
				storeClassString=a;
			}else{
				storeClassString=storeClassString+","+a;
			}
		}
		//----------
		storeVO.setStore_no(Integer.parseInt(storeNoForUpdate.trim()));
		storeVO.setStore_name(storeName);
		storeVO.setPhone(storePhone);
		storeVO.setAddress(storeAdd);
		//------------
		newStoreService.updateStore(storeVO, storeClassString);
		
		//店家更新成功
		request.setAttribute("storeNo", Integer.parseInt(storeNoForUpdate.trim()));
		request.getRequestDispatcher("/newStore/showItemServlet").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
