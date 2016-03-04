package module.service;

import module.dao._07_StoreDAO;
import module.dao._07_Store_InterfaceDAO;
import module.model._07_StoreVO;
import module.util.HibernateUtil;

public class DeleteStoreService {
	private static  _07_Store_InterfaceDAO _07DAO = new _07_StoreDAO();
	
	public void deleteStore(_07_StoreVO bean){		
		_07DAO.update(bean);
	}
	
	
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	

		 DeleteStoreService deleteStoreService = new DeleteStoreService();  
		_07_StoreVO bean = new _07_StoreVO();
		
		
		
		
		bean = new _07_StoreVO();
		bean = _07DAO.findById(13);
		bean.setPublic_state("3");
		bean.setFinal_update(new java.util.Date());			
		deleteStoreService.deleteStore(bean);
		
		
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
