package module.service.newStore;

import java.util.List;

import org.hibernate.Query;
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
	private updateStoreService updateStoreService=new updateStoreService();
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
			_21Vo.setStoreVO(_07storeVO);
			_21dao.insert(_21Vo);			
		}
		return newStoreNo;
	}
	//-------------------------修改店家(回傳剛新增的店家流水號)-----------------------
	public void updateStore(_07_StoreVO storeVO,String storeClass){
		_07_StoreVO storeFromDB=_07dao.findById(storeVO.getStore_no());//資料庫店家資料
		storeFromDB.setStore_name(storeVO.getStore_name());
		storeFromDB.setAddress(storeVO.getAddress());
		storeFromDB.setPhone(storeVO.getPhone());
		
		_07dao.update(storeFromDB);
		
		//比對店家類型
		String[] newstoreClassArray=storeClass.split(",");//想更新的店家類型的字串陣列
		String fromDB=updateStoreService.findStoreClass(storeVO.getStore_no());//資料庫店家類型
		boolean isClassDifferent=false;	
		
		if(!fromDB.equals(storeClass)){
			isClassDifferent=true;//店家類型不同			
		}
		
		
		if(isClassDifferent){//類型不一樣，先刪除舊的，再輸入新的
			System.out.println("true");
			
			//刪除有問題
//			for(_21_Store_In_ClassVO a:storeVO.getStore_in_classs()){				
//				_21dao.delete(a.getNo());
//				System.out.println("delete");
//			}
			
			for(String b:newstoreClassArray){
				_21_Store_In_ClassVO _21VO=new _21_Store_In_ClassVO();
				_06_Store_ClassVO _06VO=null;
				for(_06_Store_ClassVO c:_06da0.getAll()){
					if(b.equals(c.getClass_name())){
						_06VO=c;
					}
				}
				_21VO.setStoreVO(storeVO);
				_21VO.setStore_classVO(_06VO);
				_21dao.insert(_21VO);				
			}
		}else{
			System.out.println("false");
		}
	
		
		
	}
	
	//------------------取得店家類型
	public List<_21_Store_In_ClassVO> getStoreClassForUpdate(_07_StoreVO storeVO){
		Query query=getSession().createQuery("from _21_Store_In_ClassVO where storeVO=?");
		query.setParameter(0, storeVO);
		return query.list();
	}
	
	//---------------------測試------------------------------
	public static void main(String args[]){
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			newStoreService newStore=new newStoreService();
			_07_StoreVO _07storeVO=new _07_StoreVO();
			_07storeVO.setStore_no(34);
			_07storeVO.setStore_name("reatka");
			_07storeVO.setPhone("000210011111");
			String storeClass="便當類,飲料類,下午茶";
							  //便當類
			newStore.updateStore(_07storeVO, storeClass);
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}
		
		
		
	}
	

}
