package module.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._12_ItemDAO;
import module.dao._12_Item_InterfaceDAO;
import module.model._12_ItemVO;
import module.util.HibernateUtil;
import net.sf.json.JSONObject;

public class DeleteItemService {
	private static _12_Item_InterfaceDAO _12DAO=new _12_ItemDAO();
	private SessionFactory sessionFactory;
	public DeleteItemService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	public void deleteItem(_12_ItemVO bean12){
		getSession().delete(bean12);
	}
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	

		 
		DeleteItemService deleteItemService = new DeleteItemService();  
		_12_ItemVO bean12 = _12DAO.findById(37);
		deleteItemService.deleteItem(bean12);
		
		GetAttributes att=new GetAttributes();

		 
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
