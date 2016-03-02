package module.service;

import java.util.List;

import module.dao._07_StoreDAO;
import module.dao._11_Class_ThirdDAO;
import module.dao._12_ItemDAO;
import module.dao._13_Item_Class_ThirdDAO;
import module.model._07_StoreVO;
import module.model._11_Class_ThirdVO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
import module.util.HibernateUtil;

public class OpenStoreForGroupServiceTest {
	public _07_StoreDAO dao07 =new _07_StoreDAO();
	public _12_ItemDAO dao12 =new _12_ItemDAO();
	public _11_Class_ThirdDAO dao11=new _11_Class_ThirdDAO();
	public _13_Item_Class_ThirdDAO dao13 =new _13_Item_Class_ThirdDAO();
	public _07_StoreVO getStoreAll(Integer store_no){
		_07_StoreVO result07 = null;
		result07 =dao07.findById(store_no);
		return result07;
	}
	public _12_ItemVO getStoreItem(Integer store_no){
		_12_ItemVO result12 = null;
		result12 = dao12.findById(store_no);
		return result12;
	}
	public List<_12_ItemVO> getStoreItemList(Integer store_no){
		List<_12_ItemVO> result12 = null;
		result12 = dao12.getStoreItem(store_no);
		return result12;
	}
	public List<_13_Item_Class_ThirdVO> getStoreAllItemClass(Integer item_no){
		List<_13_Item_Class_ThirdVO> result13 = null;
		result13 = dao13.getStoreAllItemClass(item_no);
		return result13;
	}
	public List<_11_Class_ThirdVO> getStoreAllCT(Integer class3_no){
		List<_11_Class_ThirdVO> result11=null;
		result11 = dao11.getStoreAllCT(class3_no);
		return result11 ;
	}
	
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			_11_Class_ThirdDAO dao11=new _11_Class_ThirdDAO();
//			System.out.println(dao.getStoreItem(1));
//			String user_id ="1";
//			dao.getAllMyStoreName(user_id);
//			System.out.println(dao.getAllMyStoreName(user_id));
			System.out.println(dao11.getStoreAllCT(1));

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}	
	}
}
