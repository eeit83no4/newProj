package module.service.newStore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._12_ItemDAO;
import module.dao._14_SizeDAO;
import module.dao._15_Item_PriceDAO;
import module.model._07_StoreVO;
import module.model._09_Class_FirstVO;
import module.model._12_ItemVO;
import module.model._14_SizeVO;
import module.model._15_Item_PriceVO;
import module.util.HibernateUtil;

public class newItemsService {
	private SessionFactory sessionFactory;
	public newItemsService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	private _12_ItemDAO _12dao=new _12_ItemDAO();
	private _14_SizeDAO _14dao=new _14_SizeDAO();
	private _15_Item_PriceDAO _15dao=new _15_Item_PriceDAO();
	//------------新增商品(回傳新增商品的流水號)
	public Integer newItems(_12_ItemVO itemVO){
		_12dao.insert(itemVO);
		return (int)getSession().getIdentifier(itemVO);	
	}
	//---------------新增Size,Price	
	public void newSizePrice(Integer itemNo,String newSizeArray){
		//檢查Size名稱是否重複		
		for(String a:newSizeArray.split(",")){
			int start=a.indexOf("(");
			int end=a.indexOf(")");
			String sizeName=a.substring(0, start);//欲新增的Size名稱
			//---------與資料庫裡的Size名稱做比較
			int sizeNo=0;
			for(_14_SizeVO b:_14dao.getAll()){
				if(sizeName.equals(b.getSize_name())){
					sizeNo=b.getSize_no();//如果有重複則取得原有的SizeNo
				}
			}
			if(sizeNo==0){//如果沒有重複就新增SizeVO
				_14_SizeVO new_14VO=new _14_SizeVO();
				new_14VO.setSize_name(sizeName);
				_14dao.insert(new_14VO);
				sizeNo=(int)getSession().getIdentifier(new_14VO);//如果沒有重複則取得新增的SizeNo
			}
			//--------新增ItemPrice
			Double extra=0.0;
			try {
				extra=Double.parseDouble(a.subSequence(start+1, end).toString());
			} catch (NumberFormatException e) {
				System.out.println("加購價字串轉換Double失敗"+e.toString());
			}
			_15_Item_PriceVO _15VO=new _15_Item_PriceVO();
			_14_SizeVO _14VO=new _14_SizeVO();
			_12_ItemVO _12VO=new _12_ItemVO();
			_12VO.setItem_no(itemNo);
			_14VO.setSize_no(sizeNo);
			_15VO.setItemVo(_12VO);
			_15VO.setSizeVO(_14VO);
			_15VO.setIprice(extra);
			_15dao.insert(_15VO);
		}
		
		
	}
	
	
	//------------------測試------------
	public static void main(String args[]){
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			newItemsService newItem=new newItemsService();
			//------
			_12_ItemVO itemVO=new _12_ItemVO();
			_07_StoreVO sotreVO=new _07_StoreVO();
			sotreVO.setStore_no(25);
			_09_Class_FirstVO classFirst=new _09_Class_FirstVO();
			classFirst.setClass1_no(1);
			itemVO.setItem_name("TestItem1");
			itemVO.setStoreVO(sotreVO);
			itemVO.setClass_firstVO(classFirst);
			//剛新增的店家流水號
			Integer newItemNo=newItem.newItems(itemVO);			
			String aa="無敵宇宙大(60),超大(35),大(30),中(25)";			
			newItem.newSizePrice(newItemNo, aa);
			
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}
		
	}
	
	
}
