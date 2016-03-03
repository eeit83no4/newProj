package module.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.model._07_StoreVO;
import module.model._09_Class_FirstVO;
import module.model._10_Class_SecondVO;
import module.model._12_ItemVO;
import module.service.GetAttributes;
import module.service.InserSizeService;
import module.service.InsertItemService;
import module.service.UpdateItemService;
import net.sf.json.JSONObject;

@WebServlet("/SelectItemServlet.insert")
public class InsertItemServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		//從頁面取得使用者所點選的店家no 並轉成Integer
		
		System.out.println(req.getParameter("jsonString").trim().replace("\"", ""));
		System.out.println(req.getParameter("itemId").trim());
		System.out.println(req.getParameter("itemName").trim());
		System.out.println(req.getParameter("storeNo").trim());
		System.out.println(req.getParameter("first55").trim());
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		String jsonString=req.getParameter("jsonString").trim();
		Integer itemId = Integer.parseInt(req.getParameter("itemId").trim());
		String itemName = req.getParameter("itemName").trim();
		Integer storeNo =Integer.parseInt(req.getParameter("storeNo").trim());
		String first =req.getParameter("first55").trim();
		UpdateItemService updateStoreService = new UpdateItemService();  
		
		_07_StoreVO bean7 = new _07_StoreVO();		
		bean7.setStore_no(storeNo);
		_12_ItemVO bean12 = new _12_ItemVO();	
		bean12.setStoreVO(bean7);
		bean12.setItem_no(itemId);
		updateStoreService.UpdateItem(bean12);
		
	
		InsertItemService insertItemService=new InsertItemService();
		ArrayList getAtr = insertItemService.cuttingHtmlString(jsonString);
		
//		for(Object list:getAtr){
//			System.out.println(list);
//		}
		
		Integer pk12= null;
		String size  = null;
		for(int i=0;i<getAtr.size();i=i+2){			
			if(getAtr.get(i).equals("Size")){
				size =(String) getAtr.get(i+1);
				i=i+2;				
			}
		System.out.println(getAtr.get(i));
		System.out.println(getAtr.get(i+1));
		
		_09_Class_FirstVO bean9=new _09_Class_FirstVO();
		bean9.setClass1_name(first); 
		//12物品
		_07_StoreVO storeVO=new _07_StoreVO();
//		storeVO.setStore_no(pk7);	//參考店家
		_12_ItemVO bean112=new _12_ItemVO();
		bean112.setStoreVO(storeVO);
		if(pk12 == null){
		}else{
			bean112.setItem_no(pk12);			
		}
		bean112.setItem_name(itemName);
//		bean12.setPic(null); //照片		
		
		//10第二層屬性
		_10_Class_SecondVO bean10 = new _10_Class_SecondVO();
		bean10.setClass2_name((String)getAtr.get(i));		
		//bean13 第三層屬性
		String thirdName=(String) getAtr.get(i+1);	
		
		pk12=insertItemService.insertSecond( bean9,bean12,bean10,thirdName);  //傳回新增物品的PK
		if(size != null){
			InserSizeService inserSizeService=new InserSizeService();
			inserSizeService.inserSize(size , pk12);			//新增Size  需要物品的PK
			size  = null;
			}
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}