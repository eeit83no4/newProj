package module.service.newStore;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._06_Store_ClassDAO;
import module.dao._07_StoreDAO;
import module.dao._21_Store_In_ClassDAO;
import module.model._06_Store_ClassVO;
import module.model._07_StoreVO;
import module.model._21_Store_In_ClassVO;
import module.util.HibernateUtil;

public class newStoreService {
	private SessionFactory sessionFactory;

	public newStoreService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	private _07_StoreDAO _07dao=new _07_StoreDAO();
	private _21_Store_In_ClassDAO _21dao=new _21_Store_In_ClassDAO();
	private _06_Store_ClassDAO _06da0=new _06_Store_ClassDAO();
	//-------------------------新增店家(回傳剛新增的店家流水號)-----------------------
	public Integer newStore(_07_StoreVO storeVO,String storeClass){		
		//新增店家
		_07dao.insert(storeVO);
		int newStoreNo=(int)getSession().getIdentifier(storeVO);//剛剛新增的店家流水號
		String[] storeClassArray=storeClass.split(",");//店家類型的字串陣列
		//新增店家有的類型
		for(String a:storeClassArray){
			_21_Store_In_ClassVO _21Vo=new _21_Store_In_ClassVO();
			_07_StoreVO _07storeVO=new _07_StoreVO();
			_06_Store_ClassVO _06store_classVO=new _06_Store_ClassVO();
			//取得店家類型編號
			Integer storeClassNo=0;
			for(_06_Store_ClassVO b:_06da0.getAll()){
				if(a.equals(b.getClass_name())){
					storeClassNo=b.getClass_no();
				}				
			}
			//開始新增_21_Store_In_Class			
			_07storeVO.setStore_no(newStoreNo);
			_06store_classVO.setClass_no(storeClassNo);
			_21Vo.setStore_classVO(_06store_classVO);
			_21Vo.setStoreVO(storeVO);
			_21dao.insert(_21Vo);			
		}
		return newStoreNo;
	}
	//---------------------測試------------------------------
	public static void main(String args[]){
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			newStoreService newStore=new newStoreService();
			_07_StoreVO _07storeVO=new _07_StoreVO();
			_07storeVO.setStore_name("Test1");
			_07storeVO.setPhone("000000000");
			String storeClass="便當類,飲料類";
			System.out.println(newStore.newStore(_07storeVO, storeClass));
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}
		
		
		
	}
	

}
