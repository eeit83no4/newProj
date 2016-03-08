package module.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._12_ItemDAO;
import module.dao._13_Item_Class_ThirdDAO;
import module.dao._15_Item_PriceDAO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
import module.model._15_Item_PriceVO;
import module.util.HibernateUtil;

public class UpdateItemService {
	private SessionFactory sessionFactory;
	public UpdateItemService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	private  _12_ItemDAO _12DAO=new _12_ItemDAO();

	public void UpdateItem(_12_ItemVO bean12){
			deleteItem(bean12);			
	}
	public List<_15_Item_PriceVO> getItemPriceId(_12_ItemVO bean12) {
		Query query= getSession().createQuery("from _15_Item_PriceVO where itemVo=?");
		query.setParameter(0, bean12);
		return query.list();
	}
	public void deleteItemPrice(_15_Item_PriceVO bean15) {
		getSession().delete(bean15);	
	}
	
	public List<_13_Item_Class_ThirdVO> getThirdId(_12_ItemVO bean12) {
		Query query= getSession().createQuery("from _13_Item_Class_ThirdVO where itemVO=?");
		query.setParameter(0, bean12);
		return query.list();
	}
	public void deleteItemThird(_13_Item_Class_ThirdVO bean13) {
		getSession().delete(bean13);	
	}
	
	public void deleteItem(_12_ItemVO bean12) {	
		//刪除15表 13表
		List<_15_Item_PriceVO> aa = getItemPriceId(bean12);
		for(_15_Item_PriceVO list:aa){
//			System.out.println(list);
			deleteItemPrice(list);
		}
		List<_13_Item_Class_ThirdVO> bb = getThirdId(bean12);
		for(_13_Item_Class_ThirdVO list:bb){
//			System.out.println(list);
			deleteItemThird(list);
		}
	}
//	public List<_13_Item_Class_ThirdVO> getItemClassThirdId(int no){
//		Query query= getSession().createQuery("from _13_Item_Class_ThirdVO where itemVO=?");
//		_12_ItemVO bean=new _12_ItemVO();
//		bean.setItem_no(no);
//		query.setParameter(0, bean);
//		return query.list();
//	}	
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	

		UpdateItemService updateStoreService = new UpdateItemService();  
		
	
//		_12_ItemVO bean12 = new _12_ItemVO();
//		bean12.setItem_no(19);
//		updateStoreService.UpdateItem(bean12);
		
		
		
//		_07_StoreVO bean7 = new _07_StoreVO();		
//		bean7.setStore_no(8);
//		_12_ItemVO bean12 = new _12_ItemVO();	
//		bean12.setStoreVO(bean7);
//		bean12.setItem_no(7);
//		updateStoreService.UpdateItem(bean12);
		

		
		
//		//09第一層屬性
//		_09_Class_FirstVO bean9=new _09_Class_FirstVO();
//		bean9.setClass1_name("飲料"); 
//		//12物品
//		bean7.setStore_no(3);	//參考店家
//		bean12.setStoreVO(bean7);  
//		bean12.setItem_name("鐵觀音");
////		bean12.setPic(null); //照片
//		//10第二層屬性
//		_10_Class_SecondVO bean10 = new _10_Class_SecondVO();
//		bean10.setClass2_name("冷熱調整");		
//		//bean13 第三層屬性
//		String thirdName="超熱(0),有點溫(0),普通熱(0),冰冰涼涼的(5),去冰的(10)";	
//			
//		InsertItemService insertItemService=new InsertItemService();
//		insertItemService.insertSecond(bean9, bean12, bean10, thirdName);
		
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
