package module.service.newStore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._09_Class_FirstDAO;
import module.dao._10_Class_SecondDAO;
import module.dao._11_Class_ThirdDAO;
import module.dao._13_Item_Class_ThirdDAO;
import module.model._09_Class_FirstVO;
import module.util.HibernateUtil;

public class newItemsClassService {
	private SessionFactory sessionFactory;
	public newItemsClassService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	private _09_Class_FirstDAO _09dao=new _09_Class_FirstDAO();
	private _10_Class_SecondDAO _10dao=new _10_Class_SecondDAO();
	private _11_Class_ThirdDAO _11dao=new _11_Class_ThirdDAO();
	private _13_Item_Class_ThirdDAO _13dao=new _13_Item_Class_ThirdDAO();
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓第一層屬性↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	public Integer findClassFirstIdByName(String class1stName){
		Integer class1stNo=0;
		//----檢查名稱是否重複
		for(_09_Class_FirstVO a:_09dao.getAll()){
			if(class1stName.equals(a.getClass1_name())){
				class1stNo=a.getClass1_no();
			}
		}
		//----沒有重複就新增
		if(class1stNo==0){
			_09_Class_FirstVO _09VO=new _09_Class_FirstVO();
			_09VO.setClass1_name(class1stName);
			_09dao.insert(_09VO);
			class1stNo=(int)getSession().getIdentifier(_09VO);
		}		
		return class1stNo;
	}
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	//[冷熱,正常全冰,少冰,去冰][]
	
	
	
	
	
	
	//-----------測試
	public static void main(String args[]){
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			newItemsClassService newItemsClassService=new newItemsClassService();
			//-------------findClassFirstIdByName
			System.out.println(newItemsClassService.findClassFirstIdByName("糕點TEST"));
			//----------
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
		
		
		
	}
	

}
