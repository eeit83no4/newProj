package module.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.model._07_StoreVO;
import module.model._10_Class_SecondVO;
import module.model._11_Class_ThirdVO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
import module.service.OpenStoreForGroupService;
import module.service.OpenStoreForGroupServiceTest;

@WebServlet("/OpenStoreForGroupServlet.select")
public class OpenStoreForGroupServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		//從頁面取得使用者所點選的店家no 並轉成Integer
		Integer store_no =Integer.parseInt(req.getParameter("store_no"));
//		System.out.println(store_no);
		
/*------------------------------------------------------------------------------------------------------------------------------*/		

		OpenStoreForGroupServiceTest OSFGST = new OpenStoreForGroupServiceTest();
		OpenStoreForGroupService OSFGS = new OpenStoreForGroupService();
		
/*------------------------------------------------------------------------------------------------------------------------------*/
		
		//店家資訊
		_07_StoreVO bean07 =OSFGST.getStoreAll(store_no);
		
/*------------------------------------------------------------------------------------------------------------------------------*/

		//店家的商品資訊
		List<_12_ItemVO> bean12 =OSFGST.getStoreItemList(store_no);

/*------------------------------------------------------------------------------------------------------------------------------*/

		//商品總數
		int c1size = bean12.size();
//		System.out.println(c1size);
		
/*------------------------------------------------------------------------------------------------------------------------------*/		

		//商品第三層屬性
		int item_no = 0;
		List<List<_13_Item_Class_ThirdVO>> list13 = new ArrayList();
		List<_13_Item_Class_ThirdVO> itemBean13 = null;
		List<_11_Class_ThirdVO> c3Bean11 = null;
		List<_10_Class_SecondVO> c2Bean10 = null;
		for(int i=0;i<c1size;i++){
			item_no =bean12.get(i).getItem_no();
//			System.out.println(bean12.get(i).getItem_no());
			itemBean13 =OSFGST.getStoreAllItemClass(item_no);
//			System.out.println(itemBean13);
			list13.add(itemBean13);
		}
		//拆解list       
		for(List<_13_Item_Class_ThirdVO> b:list13){
			for(_13_Item_Class_ThirdVO a:b){
//				System.out.println(a.getClass_ThirdVO().getClass3_no());
			}
		}
//		System.out.println(bean12.get(0).getItem_no());
//		System.out.println(list13);
		
/*------------------------------------------------------------------------------------------------------------------------------*/

		//商品名稱
		Map<Integer, String> itemnames=OSFGS.findItemsByStore(store_no);
		
		
		//商品編號
		Set<Integer> itemnos=OSFGS.findItemsNoByStore(store_no);
		
		//商品size{1=[501=中(25.0), 502=大(30.0), 500=小(20.0)],.....}
		Map<Integer,Map<String,String>> sizeprice = OSFGS.findSizePricesbyStore(store_no);
//		System.out.println(sizeprice);
		
		//商品的第二層屬性{1=[冷熱, 加料, 甜度], 2=[冷熱, 加料, 甜度], 3=[冷熱, 加料, 甜度]}
		List<Map<Integer, Set<String>>> class2List=new ArrayList<>();
		Map<Integer, Set<String>> class2=OSFGS.find2nds(store_no);
		class2List.add(class2);
//		System.out.println(find2nds);
		
		//商品的第三層屬性[{1={冷熱=[正常全冰, 少冰, 去冰], 加料=[加珍珠(5.0), 加布丁(10.0)], 甜度=[半糖(5分), 少糖(7分), 正常(全糖)]},...}]
		Map<Integer, Map<String, Set<String>>> class3=OSFGS.find3nds(store_no);		
		List<Map<Integer, Map<String, Set<String>>>> class3List=new ArrayList<>();		
		class3List.add(class3);
//		System.out.println(class3List);
		
/*------------------------------------------------------------------------------------------------------------------------------*/		
		
		req.setAttribute("store", bean07);//店家資訊
		req.setAttribute("item", bean12);//商品資訊
		req.setAttribute("c1size", c1size);
		
		
		req.setAttribute("itemnos", itemnos);
		req.setAttribute("itemnames", itemnames);
		req.setAttribute("sizeprices", sizeprice);
		req.setAttribute("class2Lists", class2List);
		req.setAttribute("class3Lists", class3List);
		req.setAttribute("store_no", store_no);
		
//		req.setAttribute("c2", c2);
		req.getRequestDispatcher("/StorePage/OpenStoreForGroup.jsp").forward(req, resp);
		
		
//		System.out.println(bean12);
//		System.out.println(bean12.size());

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}