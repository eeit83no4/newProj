package module.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._07_StoreDAO;
import module.dao._07_Store_InterfaceDAO;
import module.model._07_StoreVO;
import module.util.HibernateUtil;

public class StoreService {
	private _07_StoreDAO _07_StoreDao=new _07_StoreDAO();
	//select insert update delete 目前僅有查詢
	//模糊查詢店家名稱 bean值=null就查詢全部店家名稱
	public List<_07_StoreVO> getStoreName(String bean) {
		List<_07_StoreVO> result = null;
		if(bean.trim().length()!=0) {			
			result = _07_StoreDao.getStoreName(bean); 
		} 
		else {
			result = _07_StoreDao.getAllStoreName();
		}
		return result;
	}
	//查詢我新增的所有店家
	public List<_07_StoreVO> getAllMystoreName(String user_id) {
		List<_07_StoreVO> result = null;
		result = _07_StoreDao.getAllMyStoreName(user_id);
		return result;
	}
	//模糊查詢我新增的店家名稱
	public List<_07_StoreVO> getMystoreName(String user_id,String bean2){
		List<_07_StoreVO> result = null;
		if(bean2.trim().length()!=0) {			
			result = _07_StoreDao.getMyStoreName(user_id, bean2); 
		}
		return result;
	}
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			_07_StoreDAO dao=new _07_StoreDAO();
//			String user_id ="1";
//			dao.getAllMyStoreName(user_id);
//			System.out.println(dao.getAllMyStoreName(user_id));

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}	
	}
	
}
