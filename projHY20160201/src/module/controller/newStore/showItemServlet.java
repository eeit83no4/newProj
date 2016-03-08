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
@WebServlet("/newStore/showItemServlet.controller")
public class showItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private _07_StoreDAO _07dao=new _07_StoreDAO();
    private SelectItemsService selectItemsService=new SelectItemsService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String storeno=request.getParameter("storeNo");
		Integer storeNo=0;
		if(storeno!=null&&storeno.trim().length()>0){
			System.out.println("舊的");
			storeNo=Integer.parseInt(storeno);//店家編號
		}else{
			System.out.println("新增");
			storeNo=(int)request.getAttribute("storeNo");
		}
		
		String itemno=request.getParameter("itemNO");
		if(itemno!=null&&itemno.trim().length()>0){
			Integer itemNO=Integer.parseInt(itemno);
			request.setAttribute("itemNO", itemNO);
		}
				
		//---------------------
		Set<_12_ItemVO> itemVOs=_07dao.findById(storeNo).getItems();
		//----------------		
		List<Integer> itemNos=new ArrayList<>();		
		if(itemVOs!=null&&itemVOs.size()>0){
			for(_12_ItemVO a:_07dao.findById(storeNo).getItems()){
				itemNos.add(a.getItem_no());
			}
		}
		//-----------------------
		Map<Integer,String> itemName=new HashMap<>();
		if(itemVOs!=null&&itemVOs.size()>0){
			for(_12_ItemVO a:itemVOs){			
				itemName.put(a.getItem_no(),a.getItem_name());
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
		request.setAttribute("storeNo", storeNo);
		request.setAttribute("itemNos", itemNos);
		request.setAttribute("itemVOs", itemVOs);
		request.setAttribute("itemsSize", itemsSize);
		request.setAttribute("itemsc2c3", itemsc2c3);
		request.setAttribute("itemsextra", itemsextra);
		request.setAttribute("itemName", itemName);
		System.out.println("go to");
		request.getRequestDispatcher("/userOrder/createItems/createItems.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
