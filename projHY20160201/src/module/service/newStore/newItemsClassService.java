package module.service.newStore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._09_Class_FirstDAO;
import module.dao._10_Class_SecondDAO;
import module.dao._11_Class_ThirdDAO;
import module.dao._13_Item_Class_ThirdDAO;
import module.model._09_Class_FirstVO;
import module.model._10_Class_SecondVO;
import module.model._11_Class_ThirdVO;
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
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓第二層屬性↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓	
	public Integer findClass2ByName(String c2Name,Integer c1No){
		Integer c2NO=0;
		for(_10_Class_SecondVO b:_10dao.getAll()){
			if(c2Name.equals(b.getClass2_name())){
				c2NO=b.getClass2_no();//如果重複，則從DB取得第二層屬性的PK
			}
		}
		if(c2NO==0){
			_10_Class_SecondVO _10VO=new _10_Class_SecondVO();
			_09_Class_FirstVO _09VO=new _09_Class_FirstVO();
			_09VO.setClass1_no(c1No);
			_10VO.setClass2_name(c2Name);
			_10VO.setClass_FirstVO(_09VO);
			_10dao.insert(_10VO);
			c2NO=(int)getSession().getIdentifier(_10VO);//如果沒有重複，則取得新增後的第二層屬性的PK
		}
		return c2NO;
	}
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓第三層屬性↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	public Integer findClass3ByName(String c3Name,Integer c2No){
		Integer c3NO=0;
		for(_11_Class_ThirdVO a:_11dao.getAll()){
			if(c3Name.equals(a.getClass3_name())){
				c3NO=a.getClass3_no();
			}
		}
		if(c3NO==0){
			_11_Class_ThirdVO _11VO=new _11_Class_ThirdVO();
			_10_Class_SecondVO _10VO=new _10_Class_SecondVO();
			_10VO.setClass2_no(c2No);
			_11VO.setClass_SecondVO(_10VO);
			_11VO.setClass3_name(c3Name);
			_11dao.insert(_11VO);
			c3NO=(int)getSession().getIdentifier(_11VO);
		}
		return c3NO;
	}
	
	
	
	//-----------測試
	public static void main(String args[]){
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			newItemsClassService newItemsClassService=new newItemsClassService();
			//-------------findClassFirstIdByName
			
			//----------
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
		
		
		
	}
	

}
