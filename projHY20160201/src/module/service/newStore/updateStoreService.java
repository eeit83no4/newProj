package module.service.newStore;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._07_StoreDAO;
import module.model._07_StoreVO;
import module.model._21_Store_In_ClassVO;
import module.util.HibernateUtil;

public class updateStoreService {
	private SessionFactory sessionFactory;

	public updateStoreService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	private _07_StoreDAO _07dao=new _07_StoreDAO();
	
	//---------------找出店家的類型
	//回傳ex.飲料類,便當類....
	public String findStoreClass(Integer storeNo){
		_07_StoreVO storeVO=_07dao.findById(storeNo);
		String storeClassName=null;
		for(_21_Store_In_ClassVO a:storeVO.getStore_in_classs()){
			String storeClass=a.getStore_classVO().getClass_name();
			if(storeClassName==null){
				storeClassName=storeClass;
			}else{
				storeClassName=storeClassName+","+storeClass;
			}
		}
		if(storeClassName!=null){
			return storeClassName;
		}
		return null;
	}
	//-----------------------
	public static void main(String args[]){
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			updateStoreService updateStoreService=new updateStoreService();
			
			System.out.println(updateStoreService.findStoreClass(14));
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}
	}
}
