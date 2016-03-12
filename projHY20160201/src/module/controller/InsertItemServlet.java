package module.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.model._07_StoreVO;
import module.model._09_Class_FirstVO;
import module.model._10_Class_SecondVO;
import module.model._12_ItemVO;
import module.service.InserSizeService;
import module.service.InsertItemService;
import module.service.UpdateItemService;
import module.service.insertPicByBase64;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@WebServlet("/SelectItemServlet.insert")
public class InsertItemServlet extends HttpServlet {
	private insertPicByBase64 insertPic=new insertPicByBase64();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json; charset=utf-8");
		//從頁面取得使用者所點選的店家no 並轉成Integer
		String itemName = null;
		System.out.println(req.getParameter("jsonString").trim().replace("\"", ""));
		System.out.println(req.getParameter("itemId").trim());
		if(req.getParameter("itemName")!=null&&req.getParameter("itemName").trim().length()>0){
			itemName = req.getParameter("itemName").trim();
			System.out.println(itemName);
		}else{
			itemName = req.getParameter("itemName22").trim();
			System.out.println(itemName);
		}		
		System.out.println(req.getParameter("storeNo").trim());
		System.out.println(req.getParameter("first55").trim());
//		System.out.println(req.getParameter("jsonImg").trim().length());
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		InsertItemService insertItemService=new InsertItemService();
		//取得資料
		Integer itemId =null;
		String jsonString=req.getParameter("jsonString").trim();
		Integer storeNo =Integer.parseInt(req.getParameter("storeNo").trim());
		String first =req.getParameter("first55").trim();
		//----------------------處理圖片------------------
		String jsonImg =req.getParameter("jsonImg");
		String picPath=null;
		if(jsonImg!=null){
			JSONSerializer jSONSerializer=new JSONSerializer();
			Object b=null;
			//將imgsString轉換Object
			try {
				b=jSONSerializer.toJSON(jsonImg);
			} catch (Exception e) {			
				System.out.println("jSONSerializer error="+e.toString());
			}				
//			將Object轉換成JsonArray格式			
			JSONArray jSONArray=JSONArray.fromObject(b);
			for(int i=0;i<jSONArray.size();i++){
				String picbase64=(String)jSONArray.get(i);
				picPath=insertPic.picInsert(picbase64);				
			}						
		}
		//----------圖片處理完畢
		if(req.getParameter("itemId")!=null&&req.getParameter("itemId").trim().length()>0){
			itemId = Integer.parseInt(req.getParameter("itemId").trim());
		}else{
			itemId = insertItemService.inserOneItem(itemName , first , storeNo);
			System.out.println(itemId);
		}	
		
		UpdateItemService updateStoreService = new UpdateItemService();  
			
		
		
		//刪除關聯
		_12_ItemVO bean12 = new _12_ItemVO();			
		bean12.setItem_no(itemId);
		updateStoreService.UpdateItem(bean12);
		//砍字串		
		ArrayList getAtr = insertItemService.cuttingHtmlString(jsonString);
//		Integer itemNo = null;
		String size  = null;
		for(int i=0;i<getAtr.size();i=i+2){		
//			System.out.println(getAtr.get(i));
			if(getAtr.get(i).equals("Size")){
				size =(String) getAtr.get(i+1);
//				i=i+2;				
			}	
		if(size != null){
			InserSizeService inserSizeService=new InserSizeService();
			inserSizeService.inserSize(size , itemId);			//新增Size  需要物品的PK
			size  = null;
			}else{
				_09_Class_FirstVO bean9=new _09_Class_FirstVO();
				bean9.setClass1_name(first); 
				//12物品
				_07_StoreVO storeVO=new _07_StoreVO();
				storeVO.setStore_no(storeNo);	//參考店家
				bean12.setStoreVO(storeVO);
				bean12.setItem_name(itemName);
				if(picPath!=null&&picPath.trim().length()>0){
					bean12.setPic(picPath.getBytes()); //照片---------------------------------------------------
				}
				//10第二層屬性
				_10_Class_SecondVO bean10 = new _10_Class_SecondVO();
				bean10.setClass2_name((String)getAtr.get(i));		
				//bean13 第三層屬性
				String thirdName=(String) getAtr.get(i+1);			
				itemId = insertItemService.insertSecond( bean9,bean12,bean10,thirdName);  //傳回新增物品的PK
				System.out.println(itemId);
			}	
		}//for end
		
		//傳ID
//		String itemNoS = Integer.toString(itemId);
//		System.out.println(itemNoS);
//		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//		Map<String,String> data=new HashMap<>(); 
//		data.put("itemNoS", itemNoS);
//		JSONObject json=JSONObject.fromObject(data);
//		 resp.getWriter().print(json);
		//傳ID
				
		
		Map<String,String> data=new HashMap<>(); 
		data.put("itemName", itemName);
		JSONObject json=JSONObject.fromObject(data);
		 resp.getWriter().print(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}