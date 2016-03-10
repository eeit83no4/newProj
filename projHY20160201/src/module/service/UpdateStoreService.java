package module.service;

import java.util.List;

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
import module.model._21_Store_In_ClassVO;
import module.util.HibernateUtil;
import sun.security.pkcs.ParsingException;

public class UpdateStoreService {
	private SessionFactory sessionFactory;
	private static  _07_Store_InterfaceDAO _07DAO = new _07_StoreDAO();
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
		
	public void updateStore(_07_StoreVO bean , String class_name){
		GetStoreClass getStoreClass=new GetStoreClass();
		String storeClass = getStoreClass.getStoreString(bean);
		if(class_name.equals(storeClass)){		
			_07DAO.update(bean);			
		}else{
			String classNo = getStoreClass.getStoreClassInId(bean);
			String[] split = classNo.split(",");
			for (String list:split){
				int no = Integer.parseInt(list);	
				_21_Store_In_ClassVO VO = _21DAO.findById(no);
				VO.setNo(no);
				deleteStoreInClass(VO);				
			}
			_07_StoreService storeService = new _07_StoreService();
			storeService.insertStore(bean, class_name);
			_07DAO.update(bean);
		}
	}
	
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
		
		String class_name="點心類,下午茶";
		bean = _07DAO.findById(14);
		bean.setStore_no(14);
		bean.setPublic_state("1");
		bean.setStore_name("大大雞排店");
		bean.setPhone("55226688");	
		bean.setAddress("台北市大安捷運站");
		bean.setFinal_update(new java.util.Date());	
		updateStoreService.updateStore(bean, class_name);

		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
