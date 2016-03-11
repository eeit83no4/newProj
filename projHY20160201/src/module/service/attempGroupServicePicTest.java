package module.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._12_ItemDAO;
import module.model._12_ItemVO;
import module.util.HibernateUtil;

public class attempGroupServicePicTest {
	
	private SessionFactory sessionFactory;
	public attempGroupServicePicTest(){
		sessionFactory=HibernateUtil.getSessionFactory();
	}
	public Session getSession(){
		if(sessionFactory!=null){
			Session session= sessionFactory.getCurrentSession();
			return session;
		}
		return null;
	}
	
	
	
	public static void main(String arg[]){
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			attempGroupServicePicTest attP=new attempGroupServicePicTest();			
			_12_ItemDAO _12dao=new _12_ItemDAO();
			
//			"C:/temp/blacktea.jpg"
			
			
			String picpath=attP.xxxBuffer();//圖片路徑
			_12_ItemVO itemvo=_12dao.findById(37);
			if(picpath!=null&&picpath.length()>0){
				itemvo.setPic(picpath.getBytes());
				_12dao.update(itemvo);
				System.out.println("success");
			}else{
				System.out.println("failed");
			}
			
//			String xxx=new String(_12dao.findById(16).getPic());
//			System.out.println(xxx);
			System.out.println(picpath);
			
			
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();			
		}
		
		
		
		
		
	}
	
	//未使用緩衝區
//	public String xxx(){
//		
//		File file=new File("C:/temp/blacktea.jpg");//來源，使用者瀏覽器
//		byte[] bFile=new byte[(int)file.length()];		
//		
//		try {
//			FileInputStream fileInputStream=new FileInputStream(file);
//			fileInputStream.read(bFile);
//			fileInputStream.close();
//		} catch (FileNotFoundException e) {				
//			e.printStackTrace();
//		} catch (IOException e) {				
//			e.printStackTrace();
//		}		
//		
//		String picPath=null;//目的地，本機儲存路徑		
//		try {
//			int picname=(int)(Math.random()*10000000)+1;
//			picPath="e:/projPic/proj"+picname+".jpg";
//			FileOutputStream fileOutputStream=new FileOutputStream(picPath);
//			fileOutputStream.write(bFile);
//			fileOutputStream.close();
//		} catch (FileNotFoundException e) {			
//			e.printStackTrace();
//		} catch (IOException e) {			
//			e.printStackTrace();
//		}
//		return picPath;
//		
//	}
	
	//使用緩衝區
	public String xxxBuffer(){		
		File file=new File("C:/temp/匿名.png");//來源，使用者瀏覽器
		byte[] bFile=new byte[(int)file.length()];		
		String picPath=null;//目的地，本機儲存路徑		
		FileOutputStream fileOutputStream;
		try {
			int picname=(int)(Math.random()*10000000)+1;//存入的圖片名稱
			picPath="C:/Images/proj"+picname+".png";//設定目的地
			FileInputStream fileInputStream=new FileInputStream(file);
			fileOutputStream = new FileOutputStream(picPath);
			//使用緩衝區
			BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
			BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);			
			
			while(bufferedInputStream.read(bFile)!=-1){
				bufferedOutputStream.write(bFile);
			}
			// 將緩衝區中的資料全部寫出
			bufferedOutputStream.flush();
			// 關閉串流
			bufferedInputStream.close();
			bufferedOutputStream.close();
		} catch (FileNotFoundException e1) {			
			e1.printStackTrace();
		} catch (IOException e1) {			
			e1.printStackTrace();
		}
					
				
		
		return picPath;
		
	}

}
