package module.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.util.HibernateUtil;

public class _07_StoreDAO implements _07_Store_InterfaceDAO {
	private SessionFactory sessionFactory;

	public _07_StoreDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}

	@Override
	public void insert(_07_StoreVO storeVO) {
		getSession().save(storeVO);

	}

	@Override
	public void update(_07_StoreVO storeVO) {
		getSession().update(storeVO);
	}

	@Override
	public void delete(Integer store_no) {
		_07_StoreVO bean = new _07_StoreVO();
		bean.setStore_no(store_no);
		getSession().delete(bean);
	}

	@Override
	public _07_StoreVO findById(Integer store_no) {
		return (_07_StoreVO) getSession().get(_07_StoreVO.class, store_no);
	}

	@Override
	public List<_07_StoreVO> getAll() {
		return getSession().createQuery("from _07_StoreVO").list();
	}
	@Override
	public List<_07_StoreVO> getStoreName(String bean){	
		Query query=getSession().createQuery("select store_no,store_name  from _07_StoreVO where public_state=1 and store_name like '%"+bean+"%'");
		return query.list();
	}
	@Override
	public List<_07_StoreVO> getMyStoreName(String user_id,String bean){	
		Query query=getSession().createQuery("select store_no,store_name  from _07_StoreVO where employeeVO ="+user_id+"and public_state !=3 and public_state !=9 and store_name like '%"+bean+"%'");
		return query.list();
	}
	@Override
	public List<_07_StoreVO> getAllStoreName() {
		Query query=getSession().createQuery("select store_no,store_name from _07_StoreVO where public_state =1");
		return query.list();
	}
	@Override
	public List<_07_StoreVO> getAllMyStoreName(String user_id){	
		Query query=getSession().createQuery("select store_no,store_name from _07_StoreVO where employeeVO ="+user_id+"and public_state =1 or public_state =2");
//		List<_07_StoreVO[]> list =query.list();
//		for (_07_StoreVO[] a:list){
//			for(_07_StoreVO b:a){
//			}
//		}
		return query.list();
	}
	@Override
	public List<_07_StoreVO> getStoreAll(String store_no) {
		Query query=getSession().createQuery("select store_no,store_name,phone,address from _07_StoreVO where store_no="+store_no);
		return query.list();
	}
	
//	public  List<String> xxx(Integer user_id){
//		_04_EmployeeDAO _04empDAO = new _04_EmployeeDAO();
//		List<String> ooo = new ArrayList(); 
//		Set<_07_StoreVO> store = _04empDAO.findById(user_id).getStores();
//		for(_07_StoreVO a : store){
//			ooo.add(a.getStore_name());
//		}
//		return ooo;
//	}
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

			_07_StoreDAO dao = new _07_StoreDAO();
			_07_StoreVO bean = new _07_StoreVO();
			
//			System.out.println(dao.xxx(2));
//			System.out.println(dao.getMyStoreName("1","0"));
//			System.out.println(dao.getAllMyStoreName("1"));

//            getAll
//			System.out.println(dao.getAll());
//
//			// findById
//			System.out.println(dao.findById(1));
//			System.out.println(dao.getStoreAll("1"));

			//delete
//          dao.delete(2);
			
          //update閬釣���-------------------------------------

//
//     		 bean.setStore_name("aaa");
//     		 bean.setStore_no(3);
//     		 dao.update(bean);

			
//			 insert閬釣���---------------	
//			 _07_StoreVO emp=new _07_StoreVO();
//     		emp.setStore_no(1); 		
////			bean.setStoreVO(storeVO);
//	   		bean.setStore_name("asfasf");
//	   		dao.insert(bean);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
