package module.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.taglibs.standard.lang.jstl.test.Bean2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._07_StoreDAO;
import module.model._07_StoreVO;
import module.model._12_ItemVO;
import module.util.HibernateUtil;



public class CreateGroupService {
	public static _07_StoreDAO Dao07 =new _07_StoreDAO();
	
	private SessionFactory sessionFactory;
	public CreateGroupService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	public static void main(String args[]){		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			CreateGroupService CGS=new CreateGroupService();
			CGS.findStoreAndInsert(1);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}		
	}
	
	
	
	
	public void findStoreAndInsert(Integer store_no){				
		_07_StoreVO store=Dao07.findById(store_no);
		if(store!=null){
			_07_StoreVO bean=new _07_StoreVO();
			bean.setStore_name(store.getStore_name());
			bean.setPhone(store.getPhone());
			bean.setAddress(store.getAddress());
			bean.setFinal_update(new java.util.Date());
			bean.setEmployeeVO(store.getEmployeeVO());
			bean.setPublic_state("9");
			_07_StoreVO storeVO=new _07_StoreVO();
			storeVO.setStore_no(store_no);
			bean.setUse_by_group(storeVO);
//			System.out.println(store);
//			System.out.println(bean);
			Dao07.insert(bean);
			
			
			
			
//		System.out.println(store);	
		}else{
		}
		
	}
	
	
}
