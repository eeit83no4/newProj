package module.service;

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
import module.model._21_Store_In_ClassVO;
import module.util.HibernateUtil;

public class GetStoreClass {
	private SessionFactory sessionFactory;
	public GetStoreClass() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	public List<_21_Store_In_ClassVO> getStore_In_ClassNO(_07_StoreVO bean) {
		Query query= getSession().createQuery("from _21_Store_In_ClassVO where storeVO=?");
		query.setParameter(0, bean);
		return query.list();
	}
	public List<_06_Store_ClassVO> get_06_Store_ClassNO(int no) {
		Query query= getSession().createQuery("from _06_Store_ClassVO where class_no=?");
		query.setParameter(0, no);
		return query.list();
	}
	public String getStoreString(_07_StoreVO bean){
		String list = null;
		List<_21_Store_In_ClassVO> list22 = getStore_In_ClassNO(bean);	
		for (_21_Store_In_ClassVO aDept : list22) {
			Integer no = aDept.getStore_classVO().getClass_no();			
			List<_06_Store_ClassVO> list33 = get_06_Store_ClassNO(no);
			for (_06_Store_ClassVO bDept : list33) {
				String name = bDept.getClass_name();			
				if(list == null){
					list = name;				
				}else{
					list = list +"," + name;
				}		
			}		
		}		
		return list;		
	}
	
	
	public String getStoreClassInId(_07_StoreVO bean){
		String list = null;
		List<_21_Store_In_ClassVO> list22 = getStore_In_ClassNO(bean);
		for (_21_Store_In_ClassVO aDept : list22){
			Integer no = aDept.getNo();
			if(list == null){
				list = no+"";				
			}else{
				list = list +"," + no;
			}
		}
		return list;
	}
	//進入點
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	

		GetStoreClass getStoreClass = new GetStoreClass();  
		_07_StoreVO bean = new _07_StoreVO();
		

		//輸入店家PK  得到跨兩個table的店家類型
		bean.setStore_no(13);
		String aa = getStoreClass.getStoreString(bean);
		System.out.println(aa);
	
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
