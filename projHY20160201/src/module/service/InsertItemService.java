package module.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._09_Class_FirstDAO;
import module.dao._10_Class_SecondDAO;
import module.dao._11_Class_ThirdDAO;
import module.dao._12_ItemDAO;
import module.dao._13_Item_Class_ThirdDAO;
import module.model._07_StoreVO;
import module.model._09_Class_FirstVO;
import module.model._10_Class_SecondVO;
import module.model._11_Class_ThirdVO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
import module.util.HibernateUtil;

public class InsertItemService {
	private SessionFactory sessionFactory;
	_10_Class_SecondDAO _10DAO=new _10_Class_SecondDAO();
	_11_Class_ThirdDAO _11DAO= new _11_Class_ThirdDAO();
	_12_ItemDAO _12DAO=new _12_ItemDAO();
	_13_Item_Class_ThirdDAO _13DAO=new _13_Item_Class_ThirdDAO();
	public InsertItemService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	public int getClassFirstId(_09_Class_FirstVO bean) {
		Query query= getSession().createQuery("from _09_Class_FirstVO where class1_name=?");
		query.setParameter(0, bean.getClass1_name());
		List<_09_Class_FirstVO> list22 = query.list();
		_09_Class_FirstVO bean9 = list22.get(0);
		Integer no = bean9.getClass1_no();
		return no;
	}
	
	
	public Integer insertSecond(_09_Class_FirstVO bean9, _12_ItemVO bean12,_10_Class_SecondVO bean10 ,String thirdName  ){
		int pk10 = 0;
		int pk11 = 0;
		Integer pk12 = 0;		
		
//第一層屬性
		int pk9 = getClassFirstId(bean9);  //用"便當" 取得PK2
		bean9.setClass1_no(pk9);
//新增商品Item
		if(bean12.getItem_no() ==null){
			bean12.setClass_firstVO(bean9);
			_12DAO.insert(bean12);
			pk12 =(int) _12DAO.getSession().getIdentifier(bean12);	
		}else{
			pk12 = bean12.getItem_no();
		}
//第二層屬性
		List<_10_Class_SecondVO> beans = _10DAO.getAll();		
		for(_10_Class_SecondVO list:beans){			 //比對"冷熱調整"
		 if(bean10.getClass2_name().equals(list.getClass2_name())){
			 pk10 = list.getClass2_no();
			 bean10.setClass2_no(pk10);  //如果比對到了  就帶著PK值出來
			 break;
			}			  
		}	
//取得"冷熱調整" 的PK		
		if(bean10.getClass2_no() != null){
			bean10.setClass_FirstVO(bean9);  //存入參考
			bean10.setClass2_no(pk10);	   
		}else{
			bean10.setClass_FirstVO(bean9);  //存入參考
			_10DAO.insert(bean10);							
			pk10 =(int) _10DAO.getSession().getIdentifier(bean10);	
			bean10.setClass2_no(pk10);
		}
//字串處理  得到bean11.getClass3_name   dExtra
			String[] split = thirdName.split(",");  //砍自串   超熱(0),有點溫(0),普通熱(0),冰冰涼涼的(5),去冰的(10)
			for (String list:split){
				_11_Class_ThirdVO bean11 = new _11_Class_ThirdVO();  //new一個袋子		
//				bean11.setClass_SecondVO(bean10);		 //放參考值 10的PK				
				int no = list.indexOf("(");
				int no2 = list.indexOf(")");
				bean11.setClass3_name(list.substring(0 , no));   //砍字串   超熱,有點溫,普通熱,冰冰涼涼的,去冰的
				String extra = list.substring(no+1 , no2);   //砍字串   0.0 ,0.0 ,0.0 ,5.0 ,10.0
				double dExtra = Double.parseDouble(extra);
			
			
//第三層屬性			
			List<_11_Class_ThirdVO> beanss =_11DAO.getAll();  //all資料庫資料
			for(_11_Class_ThirdVO list22:beanss){			 //比對  超熱,有點溫....
			 if(bean11.getClass3_name().equals(list22.getClass3_name())){
				 pk11 = list22.getClass3_no();
				 bean11.setClass3_no(pk11);  //如果比對到了  就帶著PK值出來
				 break;
				}			  
			}
//取得   超熱,有點溫...   的PK			
			if(bean11.getClass3_no() != null){
				bean11.setClass_SecondVO(bean10);		 //放參考值 10的PK	
				bean11.setClass3_no(pk11);	   
			}else{
				bean11.setClass_SecondVO(bean10);		 //放參考值 10的PK
				_11DAO.insert(bean11);							
				pk11 =(int) _11DAO.getSession().getIdentifier(bean11);	
				bean11.setClass3_no(pk11);
			}
			_13_Item_Class_ThirdVO bean13=new _13_Item_Class_ThirdVO();
			bean13.setItemVO(bean12);           //複合PK
			bean13.setClass_ThirdVO(bean11);	//複合PK
			bean13.setClass3_name(bean11.getClass3_name());  //名子抄一遍  超熱,有點溫...
			bean13.setExtra(dExtra);		//價格 0.0 ,0.0 ,0.0 ,5.0 ,10.0
			_13DAO.insert(bean13);		
		}//for end	
			return pk12;
	}
	
	public ArrayList  cuttingHtmlString(String attributes){
//		System.out.println(attributes);		
		String bb = attributes.replace(" ", "");
		while(true){
			attributes = bb;
			bb = bb.replace(",,", ",");
			if(attributes.equals(bb)){				
//				System.out.println(attributes);
				break;
			}
		}	
		ArrayList aList=new ArrayList();
		String list2=null;
		String[] split = attributes.split(",");
		for(int i =0 ;i< split.length ;i++ ){
			int no = split[i].indexOf(")");
			if(no==-1){
				if(list2 != null){
//					System.out.println(list2);	
					aList.add(list2);
				}
//				System.out.println(split[i]);
				aList.add(split[i]);
				list2=null;
			}		
			else{
				if(list2 == null){
					list2 = split[i];				
				}else{
					list2 = list2 +"," + split[i];
				}
			}

			if(i == split.length-1){
//				System.out.println(list2);
				aList.add(list2);
			}
		}
//		System.out.println(aList.get(1));
		return aList;
	}
	
	
	
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	

		 InsertItemService insertSecondService = new InsertItemService(); 	 
		//09第一層屬性
		_09_Class_FirstVO bean9=new _09_Class_FirstVO();
		bean9.setClass1_name("飲料"); 
		//12物品
		_07_StoreVO storeVO=new _07_StoreVO();
		storeVO.setStore_no(13);	//參考店家
		_12_ItemVO bean12=new _12_ItemVO();
		bean12.setStoreVO(storeVO);  
		bean12.setItem_name("鐵觀音");
//		bean12.setPic(null); //照片
		//10第二層屬性
		_10_Class_SecondVO bean10 = new _10_Class_SecondVO();
		bean10.setClass2_name("冷熱調整");		
		//bean13 第三層屬性
		String thirdName="超熱(0),有點溫(0),普通熱(0),冰冰涼涼的(5),去冰的(10)";	
	
		insertSecondService.insertSecond( bean9,bean12,bean10,thirdName);  //傳入四個參數
		
		
		
//		String attributes="SIZE, 特大(30), 大(25), 中(20), 小(15), 冷熱, 正常冰(0), 少冰(0), 去冰(0), 溫(0), 甜度, 正常(0), 半糖(0), 少糖(0), 無糖(0)";

		String attributes="加料,加雞塊(10.0),加飯飯(5.0),Size,不分大小(55)";
		
		ArrayList bb = insertSecondService.cuttingHtmlString(attributes);
		System.out.println(bb.get(1));
		
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
