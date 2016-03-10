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

import module.dao._07_StoreDAO;
import module.dao._12_ItemDAO;
import module.model._07_StoreVO;
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
	private _07_StoreDAO _07dao=new _07_StoreDAO();
	private _12_ItemDAO _12dao=new _12_ItemDAO();
	private final String[] pictureSubs = { "jpeg", "jpg", "gif", "png", "jpe" };
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("insert get values");
		Integer storeNo=Integer.parseInt(request.getParameter("storeNo").trim());//店家編號
		String itemName=request.getParameter("itemName");//商品名字
		String itemClass1st=request.getParameter("itemClass1st");//第一層屬性
		String sizePrice=request.getParameter("SizePrice");//sizeprice
		Part pic=request.getPart("itemPic");//圖片
		String class2class3=request.getParameter("class2class3");//第二第三層屬性
		String extraStuff=request.getParameter("extraStuff");//複選區
		//--------修改商品用------------------------
		String itemno=request.getParameter("itemNo");
		if(itemno!=null&&itemno.trim().length()>0){
			Integer itemNo=Integer.parseInt(itemno.trim());
			request.setAttribute("itemNo", itemNo);
			request.getRequestDispatcher("").forward(request, response);
			return;
		}		
		//----------------------驗證--------------------------
		Map<String,String> errorMsg=new HashMap<>();
		if(itemName==null||itemName.trim().length()==0){
			errorMsg.put("noItemName", "商品名稱不可為空白");
		}
		if(sizePrice==null||sizePrice.trim().length()==0){
			errorMsg.put("noSizePrice", "商品價錢不可為空白");
		}
		boolean issizePriceError=false;
		try {
			String[] aa=sizePrice.split(",");
		} catch (Exception e1) {
			issizePriceError=true;
			System.out.println("createItemServlet sizePrice格式錯誤"+e1.toString());
		}
		if(issizePriceError){
			errorMsg.put("sizePriceError", "價錢輸入格式格式錯誤");
		}
		boolean isclass2class3Error=false;
		try {
			String[] bb=class2class3.split(",");
		} catch (Exception e1) {
			isclass2class3Error=true;
			System.out.println("createItemServlet class2class3格式錯誤"+e1.toString());
		}
		if(isclass2class3Error){
			errorMsg.put("class2class3Error", "單選區輸入格式格式錯誤");
		}
		boolean isextraStuffError=false;
		try {
			String[] cc=extraStuff.split(",");
		} catch (Exception e1) {
			isextraStuffError=true;
			System.out.println("createItemServlet extraStuff格式錯誤"+e1.toString());
		}
		if(isextraStuffError){
			errorMsg.put("extraStuffError", "複選區輸入格式格式錯誤");
		}
		//----------------------驗證結束--------------------------
		//------------------------開始處理----------------
		if(!errorMsg.isEmpty()){//如果有錯誤
			request.setAttribute("errorMsg", errorMsg);
			request.setAttribute("storeNo", storeNo);
			request.getRequestDispatcher("").forward(request, response);
			return;			
		}else{//如果沒錯誤，開始新增
			//---------------------------處理圖片-------------------
			System.out.println("insert handle pic");
			String newPath=null;//儲存圖片的新路徑
			if(pic!=null){//確定有東西
				String pictureName = GlobalService.getFileName(pic);		
				boolean isPicture = false;		
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
			}			
			//---------------------------處理圖片結束-------------------
			//--------------------開始新增-----------------------
			System.out.println("start insert");
			_12_ItemVO _12VO=new _12_ItemVO();
			_07_StoreVO _07VO=_07dao.findById(storeNo);
			_12VO.setStoreVO(_07VO);
			_12VO.setItem_name(itemName);
			if(newPath!=null){
				_12VO.setPic(newPath.getBytes());
			}		
			newItemsService.newItems(_12VO,sizePrice,itemClass1st,class2class3,extraStuff);
			//--------------------新增結束-----------------------
			request.setAttribute("storeNo", storeNo);
			request.getRequestDispatcher("/newStore/showItemServlet.controller").forward(request, response);
			return;
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doGet(request, response);
	}

}
