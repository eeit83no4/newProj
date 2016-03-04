package module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import module.dao._06_Store_ClassDAO;
import module.dao._07_StoreDAO;
import module.model._06_Store_ClassVO;
import module.model._07_StoreVO;
import module.util.HibernateUtil;

public class GetStoreDataService {
	private static _07_StoreDAO _07DAO= new _07_StoreDAO();
	
	public Map<String,String>  getStoreData(int storeId){  //傳入店家PK
		
		Map<String,String> data=new HashMap<>(); 
		_07_StoreVO bean = _07DAO.findById(storeId);	
		String storeName = bean.getStore_name();   //取得店家名稱  storeName=雞排店
		data.put("storeName",storeName);   
		
		GetStoreClass getClass=new GetStoreClass();
		String storeClass = getClass.getStoreString(bean);  //取得店家類型  storeClass=豆花類,下午茶,粉圓類
		data.put("storeClass",storeClass);
		
		
		String storeClassAll = storeClassAll();
		data.put("storeClassAll",storeClassAll);
		return data;
	}
	
	public Map<String,String> getStoreClassAll(){
		Map<String,String> data=new HashMap<>(); 
		String storeClassAll = storeClassAll();
		data.put("storeClassAll",storeClassAll);
		return data;
	}
	
	public String storeClassAll(){
		String storeClassAll = null;
		 _06_Store_ClassDAO bean6=new _06_Store_ClassDAO();
			List<_06_Store_ClassVO> dataDB = bean6.getAll();
			for(_06_Store_ClassVO list:dataDB){
				String name = list.getClass_name();
				if(storeClassAll == null){
					storeClassAll = name;							
				}else{
					storeClassAll = storeClassAll +"," + name;
				}
			}
			return storeClassAll;
	}
	
	
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	
			 GetStoreDataService getStoreDataService=new GetStoreDataService();
			 
			 
			 int storeId = 13 ;
			 Map<String, String> data = getStoreDataService.getStoreData(storeId);
			System.out.println(data);
			 
//			 Map<String, String> data = getStoreDataService.getStoreClassAll();
//			 System.out.println(data);
			 
//			 String aa = getStoreDataService.storeClassAll();
//				System.out.println(aa);
				
			 
				
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
