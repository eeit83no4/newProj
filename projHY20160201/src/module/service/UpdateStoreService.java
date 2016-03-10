package module.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._06_Store_ClassDAO;
import module.dao._06_Store_Class_InterfaceDAO;
import module.dao._07_StoreDAO;
import module.dao._07_Store_InterfaceDAO;
import module.dao._21_Store_In_ClassDAO;
import module.dao._21_Store_In_Class_InterfaceDAO;
import module.model._06_Store_ClassVO;
import module.model._07_StoreVO;
import module.model._13_Item_Class_ThirdVO;
import module.model._21_Store_In_ClassVO;
import module.util.HibernateUtil;

public class UpdateStoreService {
	private SessionFactory sessionFactory;
	private _07_Store_InterfaceDAO _07DAO = new _07_StoreDAO();
	private _06_Store_Class_InterfaceDAO _06DAO=new _06_Store_ClassDAO();
	private _21_Store_In_Class_InterfaceDAO _21DAO=new _21_Store_In_ClassDAO();
	public UpdateStoreService(){
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	public void updateStore(_07_StoreVO bean7){		
		deleteStore(bean7);			
	}
	public void deleteStore(_07_StoreVO bean7) {
		//刪除21表 
		List<_21_Store_In_ClassVO> aa = getStoreInId(bean7);
		for(_21_Store_In_ClassVO list:aa){
//			System.out.println(list);
			deleteInClass(list);
		}
	}
	public void deleteItemThird(_13_Item_Class_ThirdVO bean13) {
		getSession().delete(bean13);	
	}
	public List<_21_Store_In_ClassVO> getStoreInId(_07_StoreVO bean7) {
		Query query= getSession().createQuery("from _21_Store_In_ClassVO where storeVO=?");
		query.setParameter(0, bean7);
		return query.list();
	}
	public void deleteInClass(_21_Store_In_ClassVO bean) {
		getSession().delete(bean);		
	}
	public String cuttingStoreString(String class_name){
//	System.out.println(attributes);		
	String bb = class_name.replace(" ", "").replace("\"", "");
//	System.out.println(bb);
	while(true){
		class_name = bb;
		bb = bb.replace(",,", ",");
		if(class_name.equals(bb)){				
//			System.out.println(attributes);
			break;
			}
		}	
		return class_name;
	}
	
	public List<_06_Store_ClassVO> getStoreInClassName(String name) {
		Query query= getSession().createQuery("from _06_Store_ClassVO where class_name=?");
		query.setParameter(0, name);
		return query.list();
	}
	
	
	public void inserStoreClass(_07_StoreVO bean7 , String storeClass){
		Integer pk7 = 0;
		Integer pk6 = 0;	
		//修改店家7表
		//把傳進來的值先存好
		String storeName = bean7.getStore_name();
		String storePhone = bean7.getPhone();
		String storeAddress = bean7.getAddress();
		Date storeFinalUpdate = bean7.getFinal_update();
		//呼叫資料庫的資料
		bean7=_07DAO.findById(bean7.getStore_no());
		//儲存值到VO
		bean7.setStore_name(storeName);
		bean7.setPhone(storePhone);
		bean7.setAddress(storeAddress);
		bean7.setFinal_update(storeFinalUpdate);
		//修改並取得PK
		getSession().update(bean7);
		pk7 = bean7.getStore_no();
		
		//處理6表
		//砍自串
		String[] split = storeClass.split(",");  
		for(String list:split){
			_06_Store_ClassVO bean6=new _06_Store_ClassVO();
			bean6.setClass_name(list.trim());
		//比對資料庫 取得PK
		List<_06_Store_ClassVO> beanAll =_06DAO.getAll();  //all資料庫資料
			for(_06_Store_ClassVO list22:beanAll){			 //比對  超熱,有點溫....
				
			 if(bean6.getClass_name().equals(list22.getClass_name())){
				 pk6 = list22.getClass_no();
				 bean6.setClass_no(pk6);  //如果比對到了  就帶著PK值出來
				 break;
				}	  
			}//for
			
			//沒比對到就幫它新增 取得PK
			if(bean6.getClass_no() == null){
				_06DAO.insert(bean6);							
				pk6 =(int) _06DAO.getSession().getIdentifier(bean6);	
				bean6.setClass_no(pk6);
			}	
			
			_21_Store_In_ClassVO bean21=new _21_Store_In_ClassVO();
			bean21.setStore_classVO(bean6);
			bean21.setStoreVO(bean7);
			_21DAO.insert(bean21);
		}
	}
//	public void updateStore(_07_StoreVO bean7 , String class_name){
//		GetStoreClass getStoreClass=new GetStoreClass();
//		String storeClass = getStoreClass.getStoreString(bean7);
////		System.out.println(bean7.getStore_no());
////		System.out.println(class_name);
//		if(class_name.equals(storeClass)){				
//			_07DAO.update(bean7);			
//		}else{
//			
//			//刪除連線
//			List<_21_Store_In_ClassVO> aa = getStoreClass.getStore_In_ClassNO(bean7);
//			for (_21_Store_In_ClassVO bb:aa){
////				System.out.println(bb.getNo());			
//				_21_Store_In_ClassVO bean21 = _21DAO.findById(bb.getNo());
//				deleteInClass(bean21);	
//			}
//			String storeName = bean7.getStore_name();
//			String address = bean7.getAddress();
//			String phone = bean7.getPhone();
//			
//			bean7=_07DAO.findById(bean7.getStore_no());
//			bean7.setStore_name(storeName);
//			bean7.setAddress(address);
//			bean7.setPhone(phone);
//			
//			_07DAO.update(bean7);	
//			String[] split = cuttingStoreString(class_name);
//			 for(int i=0;i<split.length;i++){
////				 System.out.println(split[i]);
//				 _21_Store_In_ClassVO bean21  = new _21_Store_In_ClassVO();
//				 bean21.setStoreVO(bean7);
//				 List<_06_Store_ClassVO> list = getStoreInClassName(split[i]);
//				 for(_06_Store_ClassVO cc:list){
//					 _06_Store_ClassVO bean6=new _06_Store_ClassVO();
//					 bean6.setClass_no(cc.getClass_no());
////					 System.out.println(bean6.getClass_no());
//					 bean21.setStore_classVO(bean6);
//					 break;
//				 }
//				 _21DAO.insert(bean21);
//			 }
//		}
//	}
	
	public void deleteStoreInClass(_21_Store_In_ClassVO bean) {		
		getSession().delete(bean);
	}
	
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	

		UpdateStoreService updateStoreService = new UpdateStoreService();  
		_07_StoreVO bean = new _07_StoreVO();
		
//		String class_name="豆花類,下午茶,粉圓類";
//		String class_name="下午茶,粉圓類";
		
//		String class_name="點心類,下午茶";
//		bean = _07DAO.findById(14);
//		bean.setStore_no(14);
//		bean.setPublic_state("1");
//		bean.setStore_name("大大雞排店");
//		bean.setPhone("55226688");	
//		bean.setAddress("台北市大安捷運站");
//		bean.setFinal_update(new java.util.Date());	
//		updateStoreService.updateStore(bean, class_name);
		
		_07_Store_InterfaceDAO _07DAO = new _07_StoreDAO();
		bean=_07DAO.findById(11);
		String class_name = "飲料類,甜點類,下午茶";
		updateStoreService.updateStore(bean);
		updateStoreService.inserStoreClass(bean, class_name);
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
