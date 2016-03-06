package module.controller.newStore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import module.dao._12_ItemDAO;
import module.model._07_StoreVO;
import module.model._09_Class_FirstVO;
import module.model._12_ItemVO;
import module.service.newStore.newItemsClassService;
import module.service.newStore.newItemsService;

/**
 * Servlet implementation class itemServlet
 */
@WebServlet("/newStore/createItemServlet.controller")
@MultipartConfig(
		location="",
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*500,
		maxRequestSize=1024*1024*500*5
		)
public class createItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private newItemsClassService newItemsClassService=new newItemsClassService();
	private newItemsService newItemsService=new newItemsService();
	private _12_ItemDAO _12dao=new _12_ItemDAO();
	private final String[] pictureSubs = { "jpeg", "jpg", "gif", "png", "jpe" };
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer storeNo=Integer.parseInt(request.getParameter("storeNo").trim());//店家編號
		String itemName=request.getParameter("itemName");//商品名字
		String itemClass1st=request.getParameter("itemClass1st");//第一層屬性
		String sizePrice=request.getParameter("SizePrice");//sizeprice
		Part pic=request.getPart("itemPic");//圖片
		String class2class3=request.getParameter("class2class3");//第二第三層屬性
		String extraStuff=request.getParameter("extraStuff");//複選區
		//----------------------驗證--------------------------
		Map<String,String> errorMsg=new HashMap<>();
		if(itemName==null||itemName.trim().length()==0){
			errorMsg.put("noItemName", "商品名稱不可為空白");
		}
		if(sizePrice==null||sizePrice.trim().length()==0){
			errorMsg.put("noSizePrice", "商品價錢不可為空白");
		}		
		//---------------------------處理圖片-------------------
		String pictureName = GlobalService.getFileName(pic);		
		boolean isPicture = false;
		String newPath=null;//儲存的新路徑
		if(pic.getSize()!=0){
			// 判斷圖片格式
			for (int i = 0; i < pictureSubs.length; i++) {
				if (pictureName.split("\\.")[1].toLowerCase().equals(pictureSubs[i])) {					
					isPicture = true;
					break;
				} else if (i == pictureSubs.length - 1) {
					errorMsg.put("mem_pic", "圖片格式錯誤");
				}
			}
			//如果是圖片，開始
			if(isPicture){
				byte[] b = new byte[1024 * 8];//緩衝區大小				
				InputStream picInputStream=pic.getInputStream();
				FileOutputStream fileOutputStream;				
				try {
					newPath="E:/proj/detailPic/"+pictureName;
					fileOutputStream = new FileOutputStream(newPath);
					BufferedInputStream bufferedInputStream=new BufferedInputStream(picInputStream);
					BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);			
					
					while(bufferedInputStream.read(b)!=-1){
						bufferedOutputStream.write(b);
					}
					// 將緩衝區中的資料全部寫出
					bufferedOutputStream.flush();
					// 關閉串流
					bufferedInputStream.close();
					bufferedOutputStream.close();
				} catch (Exception e) {
					System.out.println("存圖片出錯啦"+e.toString());					
				}				
			}			
		}				
		//--------------------開始新增-----------------------
		//1.先新增第一層
		Integer c1NO=newItemsClassService.findClassFirstIdByName(itemClass1st);
		_09_Class_FirstVO _09VO=new _09_Class_FirstVO();
		_09VO.setClass1_no(c1NO);//第一層
		//2.再新增商品
		_12_ItemVO _12VO=new _12_ItemVO();
		_07_StoreVO _07VO=new _07_StoreVO();
		_07VO.setStore_no(storeNo);
		_12VO.setStoreVO(_07VO);
		_12VO.setClass_firstVO(_09VO);
		_12VO.setItem_name(itemName);
		if(newPath!=null){
			_12VO.setPic(newPath.getBytes());
		}		
		Integer itemNo=newItemsService.newItems(_12VO);
		//3.新增sizeprice
		newItemsService.newSizePrice(itemNo, sizePrice);
		//4.-新增第二第三層屬性class2,class3及新增item_class_third
		newItemsService.insertItemClassThird(itemNo, class2class3, extraStuff);
		//---------------開始跳轉----------------
		if(errorMsg!=null||errorMsg.size()>0){
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("/userOrder/createItems/createItems.jsp").forward(request, response);			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
