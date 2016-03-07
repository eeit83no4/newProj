package module.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._07_StoreDAO;
import module.dao._11_Class_ThirdDAO;
import module.dao._12_ItemDAO;
import module.dao._13_Item_Class_ThirdDAO;
import module.dao._15_Item_PriceDAO;
import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
import module.model._15_Item_PriceVO;
import module.util.HibernateUtil;



public class CreateGroupService {
	public static _07_StoreDAO Dao07 =new _07_StoreDAO();
	public static _11_Class_ThirdDAO Dao11=new _11_Class_ThirdDAO();
	public static _12_ItemDAO Dao12 =new _12_ItemDAO();
	public static _13_Item_Class_ThirdDAO Dao13=new _13_Item_Class_ThirdDAO();
	public static _15_Item_PriceDAO Dao15 =new _15_Item_PriceDAO();
	
	private SessionFactory sessionFactory;
	public CreateGroupService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	public static void main(String args[]){		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			CreateGroupService CGS=new CreateGroupService();
//			CGS.findStoreAndInsert(1,1);
//			CGS.findItemAndInsert(1);
//			CGS.findClass3AndInsert(1);
			CGS.findItemPriceAndInsert(1);
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}		
	}
	
/*-------------------------------------------------------------------------------------------------------------------------------*/	
	//發起團購新增店家
	int newstoreno = 0 ;
	public void findStoreAndInsert(Integer store_no,Integer user_id){				
		_07_StoreVO store=Dao07.findById(store_no);
			_07_StoreVO bean07=new _07_StoreVO();
			bean07.setStore_name(store.getStore_name());
			bean07.setPhone(store.getPhone());
			bean07.setAddress(store.getAddress());
			bean07.setFinal_update(new java.util.Date());
			_04_EmployeeVO employeeVO =new _04_EmployeeVO();
			employeeVO.setUser_id(user_id);
			bean07.setEmployeeVO(employeeVO);
			
			//團購店家預設9
			bean07.setPublic_state("9");
			//儲存store_no
			_07_StoreVO storeVO=new _07_StoreVO();
			storeVO.setStore_no(store_no);
			bean07.setUse_by_group(storeVO);
//			System.out.println(store);
//			System.out.println(bean);
			Dao07.insert(bean07);
			//取得新的storeno
			newstoreno =(int) Dao07.getSession().getIdentifier(bean07);
//			System.out.println(newstoreno);	
//			System.out.println(itemno);	
	}
/*-------------------------------------------------------------------------------------------------------------------------------*/

	public void dlData(Integer dlstore_no){
		_07_StoreVO store=Dao07.findById(dlstore_no);
		store.setPublic_state("9");
		Dao07.update(store);
	}
	
/*-------------------------------------------------------------------------------------------------------------------------------*/
	
	//發起團購新增商品
	int newitemno = 0;
	public void findItemAndInsert(Integer itemNo){
		_12_ItemVO Item=Dao12.findById(itemNo); //取得店家的item資料
			_12_ItemVO bean12 =new _12_ItemVO();
			bean12.setStoreVO(Dao07.findById(newstoreno));
			bean12.setClass_firstVO(Item.getClass_firstVO());
			bean12.setItem_name(Item.getItem_name());
		Dao12.insert(bean12);
		//儲存新的商品no
		newitemno =(int) Dao12.getSession().getIdentifier(bean12);
	}
	
/*-------------------------------------------------------------------------------------------------------------------------------*/

	//發起團購新增第三層屬性
	public void findClass3AndInsert(Integer itemNo){
		//取得新的商品no
		_12_ItemVO itemVO=new _12_ItemVO();
		itemVO.setItem_no(newitemno);
		//取得店家的第三層屬性
		List<_13_Item_Class_ThirdVO> class_Third =Dao13.getStoreAllItemClass(itemNo);
//上下兩種皆可
//			for(_13_Item_Class_ThirdVO list:class_Third){
//				_13_Item_Class_ThirdVO bean13=new _13_Item_Class_ThirdVO();
//				System.out.println(list.getClass_ThirdVO().getClass3_no());
//				_13_Item_Class_ThirdVO class_ThirdVO=new _13_Item_Class_ThirdVO();
//				class_ThirdVO.setClass_ThirdVO(list.getClass_ThirdVO());
//				
//					bean13.setItemVO(itemVO);
//					bean13.setClass_ThirdVO(list.getClass_ThirdVO());
//					bean13.setClass3_name(list.getClass3_name());
//					bean13.setExtra(list.getExtra());
//					Dao13.insert(bean13);
//			}
		
			for(int i=0;i<class_Third.size();i++){
				_13_Item_Class_ThirdVO bean13=new _13_Item_Class_ThirdVO();
				bean13.setItemVO(itemVO);
//					System.out.println("itemno=="+itemVO.getItem_no());
				bean13.setClass_ThirdVO(class_Third.get(i).getClass_ThirdVO());
//					System.out.println("c3="+class_Third.get(i).getClass_ThirdVO().getClass3_no());
				bean13.setClass3_name(class_Third.get(i).getClass3_name());
//					System.out.println(class_Third.get(i).getClass3_name());
				bean13.setExtra(class_Third.get(i).getExtra());
//					System.out.println(class_Third.get(i).getExtra());
				Dao13.insert(bean13);
			}

	}
	
/*-------------------------------------------------------------------------------------------------------------------------------*/
	
	public void findItemPriceAndInsert(Integer itemNo){
		_12_ItemVO itemVO=new _12_ItemVO();
		itemVO.setItem_no(newitemno);
		
		List<_15_Item_PriceVO> item_Price = Dao15.getItemAll(itemNo);
		
		for(int i=0;i<item_Price.size();i++){
//			System.out.println(item_Price.get(i).getSizeVO());
			_15_Item_PriceVO bean15=new _15_Item_PriceVO();
			bean15.setItemVo(itemVO);
			bean15.setSizeVO(item_Price.get(i).getSizeVO());
			bean15.setIprice(item_Price.get(i).getIprice());
			Dao15.insert(bean15);
		}
	}
}
