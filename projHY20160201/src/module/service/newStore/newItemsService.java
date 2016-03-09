package module.service.newStore;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._10_Class_SecondDAO;
import module.dao._11_Class_ThirdDAO;
import module.dao._12_ItemDAO;
import module.dao._13_Item_Class_ThirdDAO;
import module.dao._14_SizeDAO;
import module.dao._15_Item_PriceDAO;
import module.model._07_StoreVO;
import module.model._09_Class_FirstVO;
import module.model._10_Class_SecondVO;
import module.model._11_Class_ThirdVO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
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
	private _10_Class_SecondDAO _10dao=new _10_Class_SecondDAO();
	private _11_Class_ThirdDAO _11dao=new _11_Class_ThirdDAO();
	private _12_ItemDAO _12dao=new _12_ItemDAO();
	private _14_SizeDAO _14dao=new _14_SizeDAO();
//	private _13_Item_Class_ThirdDAO _13dao=new _13_Item_Class_ThirdDAO();
//	private _15_Item_PriceDAO _15dao=new _15_Item_PriceDAO();
	private newItemsClassService newItemsClassService=new newItemsClassService();

	//----------------------新增商品
	public void newItems(_12_ItemVO itemVO,String newSizeArray,String class1stName,String c2c3,String extraStuff){
		//第一層屬性
		_09_Class_FirstVO _09VO=newItemsClassService.findClassFirstIdByName(class1stName);
		//-----------------------檢查Size名稱是否重複
		Set<_15_Item_PriceVO> _15VOSet=new HashSet<>();
		for(String a:newSizeArray.split(",")){
			_15_Item_PriceVO _15VO=new _15_Item_PriceVO();
			int start=a.indexOf("(");
			int end=a.indexOf(")");
			String sizeName=a.substring(0, start);//欲新增的Size名稱
			//--------ItemPrice
			Double extra=0.0;
			try {
				extra=Double.parseDouble(a.subSequence(start+1, end).toString());
			} catch (NumberFormatException e) {
				System.out.println("加購價字串轉換Double失敗"+e.toString());
			}
			//---------與資料庫裡的Size名稱做比較
			_14_SizeVO _14VO=null;			
			for(_14_SizeVO b:_14dao.getAll()){
				if(sizeName.equals(b.getSize_name())){
					//如果有重複則取得原有的SizeNo
					_14VO=b;
				}
			}
			if(_14VO==null){//如果沒有重複就新增SizeVO
				_14_SizeVO new_14VO=new _14_SizeVO();
				new_14VO.setSize_name(sizeName);
				_14dao.insert(new_14VO);
				int sizeNo=(int)getSession().getIdentifier(new_14VO);				
				_15VO.setItemVo(itemVO);
				_15VO.setSizeVO(_14dao.findByPK(sizeNo));
				_15VO.setIprice(extra);
				_15VOSet.add(_15VO);				
				//如果沒有重複則取得新增的SizeNo				
			}else{
				_15VO.setItemVo(itemVO);
				_15VO.setSizeVO(_14VO);
				_15VO.setIprice(extra);
				_15VOSet.add(_15VO);
			}
		}
		itemVO.setClass_firstVO(_09VO);
		itemVO.setItem_prices(_15VOSet);
		_12dao.insert(itemVO);
		_12_ItemVO new12ItemVo=_12dao.findById((int)getSession().getIdentifier(itemVO));
		//----------------------判斷屬性----------------------------
		if(c2c3!=null&&c2c3.trim().length()>0){
			Set<_13_Item_Class_ThirdVO> new_13VOSet=new HashSet<>();
			for(String a:c2c3.split(";")){			
				String[] c2c3First=a.split(",");
				String c2Name=c2c3First[0];//取得第二層屬性
				//判斷第二層屬性是否重複
				_10_Class_SecondVO _10VO=newItemsClassService.findClass2ByName(c2Name, _09VO);
				//判斷第三層屬性是否重複
				for(int i=1;i<c2c3First.length;i++){
					_13_Item_Class_ThirdVO _13VO=new _13_Item_Class_ThirdVO();
					String c3Name=c2c3First[i];//第三層屬性名稱
					_11_Class_ThirdVO _11VO=newItemsClassService.findClass3ByName(c3Name, _10VO);
					_13VO.setClass_ThirdVO(_11VO);
					_13VO.setClass3_name(_11VO.getClass3_name());
					_13VO.setItemVO(new12ItemVo);
					new_13VOSet.add(_13VO);
				}
			}
			new12ItemVo.setItem_class_thirds(new_13VOSet);
			_12dao.update(new12ItemVo);			
		}
		
		//----------------------判斷加料-----------------------------
		if(extraStuff!=null&&extraStuff.trim().length()>0){
			Set<_13_Item_Class_ThirdVO> _13VOSet=new HashSet<>();
			for(String a:extraStuff.split(",")){
				_13_Item_Class_ThirdVO new_13VO=new _13_Item_Class_ThirdVO();
				int start=a.indexOf("(");			
				String c3extraName=a.substring(0, start);
				//找出第二層的加料
				_10_Class_SecondVO new10VO=null;
				for(_10_Class_SecondVO b:_10dao.getAll()){
					if(b.getClass2_name().equals("加料")){
						new10VO=b;
					}
				}
				//判斷第三層是否重複
				_11_Class_ThirdVO new11VO=null;
				for(_11_Class_ThirdVO c:_11dao.getAll()){
					if(c3extraName.equals(c.getClass3_name())){
						new11VO=c;
					}
				}
				if(new11VO==null){
					_11_Class_ThirdVO _new11VO=new _11_Class_ThirdVO();
					_new11VO.setClass_SecondVO(new10VO);
					_new11VO.setClass3_name(c3extraName);
					_11dao.insert(_new11VO);
					int c3no=(int)getSession().getIdentifier(_new11VO);
					new_13VO.setClass_ThirdVO(_11dao.findById(c3no));
					new_13VO.setClass3_name(_11dao.findById(c3no).getClass3_name());
					new_13VO.setItemVO(new12ItemVo);
					for(String b:extraStuff.split(",")){
						int start1=b.indexOf("(");
						int end=b.indexOf(")");				
						if(_11dao.findById(c3no).getClass3_name().equals(b.substring(0,start1))){
							new_13VO.setExtra(Double.parseDouble(b.substring(start1+1, end)));
						}			
					}				
					_13VOSet.add(new_13VO);
				}else{
					new_13VO.setClass_ThirdVO(new11VO);
					new_13VO.setClass3_name(new11VO.getClass3_name());
					new_13VO.setItemVO(new12ItemVo);
					for(String b:extraStuff.split(",")){
						int start1=b.indexOf("(");
						int end=b.indexOf(")");				
						if(new11VO.getClass3_name().equals(b.substring(0,start1))){
							new_13VO.setExtra(Double.parseDouble(b.substring(start1+1, end)));
						}			
					}				
					_13VOSet.add(new_13VO);
				}				
			}
			new12ItemVo.setItem_class_thirds(_13VOSet);			
			_12dao.update(new12ItemVo);			
		}		
			
	}	
	
	
	
	//------------------測試------------
	public static void main(String args[]){
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			newItemsService newItem=new newItemsService();
			//------
			_12_ItemVO itemVO=new _12_ItemVO();
			_09_Class_FirstVO _09VO=new _09_Class_FirstVO();
			_07_StoreVO _07VO=new _07_StoreVO();
			_07VO.setStore_no(1);
			_09VO.setClass1_no(1);
			itemVO.setItem_name("啦啦啦快點測試啦");
			itemVO.setClass_firstVO(_09VO);
			itemVO.setStoreVO(_07VO);
//			Integer itemNO=newItem.newItems(itemVO);
			
			String c2c3="冷熱,正常全冰,少冰,去冰;甜度,正常全糖,少糖,半糖,無糖";
			String extraStuff="加珍珠(5),加椰果(10),加芋頭(20)";
//			newItem.insertItemwithoutc2c3(itemNO,extraStuff);
			
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}
		
	}
	
	
}
