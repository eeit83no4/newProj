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
	public _09_Class_FirstVO findClassFirstIdByName(String class1stName){
		_09_Class_FirstVO _09VO=null;
		//----檢查名稱是否重複
		for(_09_Class_FirstVO a:_09dao.getAll()){
			if(class1stName.equals(a.getClass1_name())){
				_09VO=a;
			}
		}
		//----沒有重複就新增
		if(_09VO==null){
			_09_Class_FirstVO new_09VO=new _09_Class_FirstVO();
			new_09VO.setClass1_name(class1stName);
			_09dao.insert(new_09VO);
			_09VO=_09dao.findById((int)getSession().getIdentifier(new_09VO));
		}		
		return _09VO;
	}
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓第二層屬性↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓	
	public _10_Class_SecondVO findClass2ByName(String c2Name,_09_Class_FirstVO _09VO){
		_10_Class_SecondVO _10VO=null;
		for(_10_Class_SecondVO b:_10dao.getAll()){
			if(c2Name.equals(b.getClass2_name())){
				_10VO=b;//如果重複，則從DB取得第二層屬性的PK
			}
		}
		if(_10VO==null){
			_10_Class_SecondVO new_10VO=new _10_Class_SecondVO();			
			new_10VO.setClass2_name(c2Name);
			new_10VO.setClass_FirstVO(_09VO);
			_10dao.insert(new_10VO);
			_10VO=_10dao.findById((int)getSession().getIdentifier(new_10VO));//如果沒有重複，則取得新增後的第二層屬性的PK
		}
		return _10VO;
	}
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓第三層屬性↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	public _11_Class_ThirdVO findClass3ByName(String c3Name,_10_Class_SecondVO _10VO){
		_11_Class_ThirdVO _11VO=null;
		for(_11_Class_ThirdVO a:_11dao.getAll()){
			if(c3Name.equals(a.getClass3_name())){
				_11VO=a;
			}
		}
		if(_11VO==null){
			_11_Class_ThirdVO new_11VO=new _11_Class_ThirdVO();			
			new_11VO.setClass_SecondVO(_10VO);
			new_11VO.setClass3_name(c3Name);
			_11dao.insert(new_11VO);
			_11VO=_11dao.findById((int)getSession().getIdentifier(new_11VO));
		}
		return _11VO;
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
