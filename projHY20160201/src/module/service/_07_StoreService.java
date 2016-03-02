package module.service;

import java.util.List;

import module.dao._06_Store_ClassDAO;
import module.dao._06_Store_Class_InterfaceDAO;
import module.dao._07_StoreDAO;
import module.dao._07_Store_InterfaceDAO;
import module.dao._21_Store_In_ClassDAO;
import module.dao._21_Store_In_Class_InterfaceDAO;
import module.model._06_Store_ClassVO;
import module.model._07_StoreVO;
import module.model._21_Store_In_ClassVO;
import module.util.HibernateUtil;

public class _07_StoreService {
	private  _07_Store_InterfaceDAO _07DAO = new _07_StoreDAO();
	private _06_Store_Class_InterfaceDAO _06DAO = new _06_Store_ClassDAO();
	private _21_Store_In_Class_InterfaceDAO _21DAO=new _21_Store_In_ClassDAO();
	
	public Integer insertStore(_07_StoreVO bean  ,String class_name) {
		int pk = 0 ;
		int pk2 = 0;
		if(bean!=null) {			
			
			if(bean.getStore_no() == null){
				if(bean.getPublic_state() != "9" || bean.getPublic_state() != "2"){					
					bean.setPublic_state("1");  //預設值為2(非公開店家)  這邊改成1
				}
			_07DAO.insert(bean);		//將傳入的bean直接新增			
			pk =(int) _07DAO.getSession().getIdentifier(bean);	 //取得剛剛新增的PK值
			bean.setStore_no(pk);  //將PK值存進bean裡面	
			}
			
			String[] split = class_name.split(",");			 //砍字串    豆花類,下午茶,粉圓類
			for (String list:split){
				_06_Store_ClassVO bean6 = new _06_Store_ClassVO();  //new一個袋子
				bean6.setClass_name(list);				 //裝字串  ---> 豆花類,下午茶,粉圓類    其中一個

			  
			List<_06_Store_ClassVO> beans =_06DAO.getAll();  //all資料庫資料
			for(_06_Store_ClassVO list22:beans){			 //跑回圈  與使用者資料做比對
			 if(bean6.getClass_name().equals(list22.getClass_name())){
				 pk2 = list22.getClass_no();
				 bean6.setClass_no(pk2);  //如果比對到了  就帶著PK值出來
				 break;
				}			  
			}
			
			//前面有比對到   就會有PK值               如果沒有比對到  就insert一個		
			if(bean6.getClass_no() != null){
				bean6.setClass_no(pk2);	   
			}else{
				_06DAO.insert(bean6);							
				pk2 =(int) _06DAO.getSession().getIdentifier(bean6);	
				bean6.setClass_no(pk2);
			}
			
			//將21資料表號新增 (迴圈  可能多筆)
			_21_Store_In_ClassVO bean21 = new _21_Store_In_ClassVO();
			bean21.setStoreVO(bean);	
			bean21.setStore_classVO(bean6);
			_21DAO.insert(bean21);
			}//end for		
		}
		return pk;
	}

	//進入點
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	

		_07_StoreService storeService = new _07_StoreService();  
		_07_StoreVO bean = new _07_StoreVO();
		
		
		
//		String class_name="粉圓類,下午茶";
		String class_name="豆花類,下午茶,粉圓類";
//		String class_name="冰品類";
		
		bean.setStore_name("雞排店");
		bean.setPhone("55226688");	
		bean.setAddress("台北市大安捷運站");
//		bean.setFinal_update(java.sql.Date.valueOf("2016-01-22"));
		bean.setFinal_update(new java.util.Date());	
		
		storeService.insertStore(bean, class_name);  //傳入兩個參數
		
		
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	
}
