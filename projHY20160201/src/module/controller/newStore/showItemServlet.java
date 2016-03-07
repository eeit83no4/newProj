package module.controller.newStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.dao._07_StoreDAO;
import module.model._12_ItemVO;
import module.service.newStore.SelectItemsService;

/**
 * Servlet implementation class showItemServlet
 */
@WebServlet("/newStore/showItemServlet")
public class showItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private _07_StoreDAO _07dao=new _07_StoreDAO();
    private SelectItemsService selectItemsService=new SelectItemsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer storeNo=Integer.parseInt(request.getParameter("storeNo"));//店家編號
		//---------------------
		Set<_12_ItemVO> itemVOs=_07dao.findById(storeNo).getItems();
		//----------------		
		List<Integer> itemNos=new ArrayList<>();		
		if(itemVOs!=null&&itemVOs.size()>0){
			for(_12_ItemVO a:_07dao.findById(storeNo).getItems()){
				itemNos.add(a.getItem_no());
			}
		}		
		//-------------
		Map<Integer,String> itemsSize=new HashMap();
		if(itemVOs!=null&&itemVOs.size()>0){
			for(Integer a:itemNos){
				itemsSize.put(a, selectItemsService.showSize(a));
			}
		}		
		//-----------------------
		Map<Integer,String> itemsc2c3=new HashMap();
		if(itemVOs!=null&&itemVOs.size()>0){
			for(Integer a:itemNos){
				itemsc2c3.put(a, selectItemsService.showc2c3(a));
			}
		}		
		//-----------------------------
		Map<Integer,String> itemsextra=new HashMap();
		if(itemVOs!=null&&itemVOs.size()>0){
			for(Integer a:itemNos){
				itemsextra.put(a, selectItemsService.showExtra(a));
			}
		}		
		//-------------------
		request.setAttribute("itemNos", itemNos);
		request.setAttribute("itemVOs", itemVOs);
		request.setAttribute("itemsSize", itemsSize);
		request.setAttribute("itemsc2c3", itemsc2c3);
		request.setAttribute("itemsextra", itemsextra);
		request.getRequestDispatcher("/userOrder/createItems/createItems.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
