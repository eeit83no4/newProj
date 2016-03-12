package module.controller.userOrder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import module.dao._12_ItemDAO;

public class showPicAction extends ActionSupport implements ServletResponseAware{

	private static final long serialVersionUID = 1L;
	private Integer itemno;
	
	public Integer getItemno() {
		return itemno;
	}
	public void setItemno(Integer itemno) {
		this.itemno = itemno;
	}

	private HttpServletResponse response;
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;		
	}
	
	
	@Override
	public String execute() throws Exception {		
		
		
		_12_ItemDAO _12dao=new _12_ItemDAO();
		//取得圖片來源，從資料庫
		byte[] imgByte=_12dao.findById(itemno).getPic();
		if(imgByte==null){//如果圖片不存在
						
			//預設圖片			
			String anonymous=ServletActionContext.getServletContext().getRealPath("/images/anonymous.png");
			File file=new File(anonymous);			
			byte[] photo=new byte[(int)file.length()];	
			
			try {
				FileInputStream fileInputStream=new FileInputStream(file);
				OutputStream os =response.getOutputStream();
				//使用緩衝區
				BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
				BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(os);
				while(bufferedInputStream.read(photo)!=-1){
					bufferedOutputStream.write(photo);
				}
				// 將緩衝區中的資料全部寫出
				bufferedOutputStream.flush();
				// 關閉串流
				bufferedInputStream.close();
				bufferedOutputStream.close();
			} catch (Exception e) {
				System.out.println("圖片輸出失敗："+e.toString());				
			}			
			
		}else{//如果圖片存在
			String imgString=new String(imgByte);
			File file=new File(imgString);
			
			byte[] photo=new byte[(int)file.length()];	
			
			try {
				FileInputStream fileInputStream=new FileInputStream(file);
				OutputStream os =response.getOutputStream();
				//使用緩衝區
				BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
				BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(os);
				while(bufferedInputStream.read(photo)!=-1){
					bufferedOutputStream.write(photo);
				}
				// 將緩衝區中的資料全部寫出
				bufferedOutputStream.flush();
				// 關閉串流
				bufferedInputStream.close();
				bufferedOutputStream.close();
			} catch (Exception e) {
				System.out.println("圖片輸出失敗："+e.toString());				
			}			
		}
		
		

		
		
		return Action.NONE;
	}
	
	
	
	

}
