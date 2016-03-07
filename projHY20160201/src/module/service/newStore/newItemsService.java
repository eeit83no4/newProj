package module.service.newStore;

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
	private _13_Item_Class_ThirdDAO _13dao=new _13_Item_Class_ThirdDAO();
	private _15_Item_PriceDAO _15dao=new _15_Item_PriceDAO();
	private newItemsClassService newItemsClassService=new newItemsClassService();
	//------------新增商品(回傳新增商品的流水號)(先findClassFirstIdByName)
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
	//--------------------新增時沒有加料------------------------
	public void insertItemwithouExtra(Integer itemNo,String c2c3){
		String c3NoString=null;
		for(String a:c2c3.split(";")){
			String[] c2c3First=a.split(",");
			String c2Name=c2c3First[0];//取得第二層屬性
			//判斷第二層屬性是否重複
			Integer c2No=newItemsClassService.findClass2ByName(c2Name,itemNo);
			//判斷第三層屬性			
			for(int i=1;i<c2c3First.length;i++){
				String c3Name=c2c3First[i];
				if(c3NoString==null){
					c3NoString=String.valueOf(newItemsClassService.findClass3ByName(c3Name, c2No));
				}else{
					c3NoString=c3NoString+","+newItemsClassService.findClass3ByName(c3Name, c2No);
				}				
			}			
		}
		for(String a:c3NoString.split(",")){
			_13_Item_Class_ThirdVO _13VO=new _13_Item_Class_ThirdVO();
			_12_ItemVO _12VO=new _12_ItemVO();
			_11_Class_ThirdVO _11VO=_11dao.findById(Integer.parseInt(a));
			_12VO.setItem_no(itemNo);
			_13VO.setItemVO(_12VO);
			_13VO.setClass_ThirdVO(_11VO);
			_13VO.setClass3_name(_11VO.getClass3_name());
			_13dao.insert(_13VO);			
		}		
	}
	//-----------------------------新增時沒有第二第三層屬性------------------------
	public void insertItemwithoutc2c3(Integer itemNo,String extraStuff){
		String c3NoString=null;
		for(String a:extraStuff.split(",")){
			int start=a.indexOf("(");			
			String c3extraName=a.substring(0, start);
			System.out.println("create service c3extraName="+c3extraName);
			//找出第二層加料的編號
			int c2ExtraNo=0;
			for(_10_Class_SecondVO b:_10dao.getAll()){
				if(b.getClass2_name().equals("加料")){
					c2ExtraNo=b.getClass2_no();
				}
			}
			//判斷第三層是否重複
			int c3ExtraNo=0;
			for(_11_Class_ThirdVO c:_11dao.getAll()){
				if(c3extraName.equals(c.getClass3_name())){
					c3ExtraNo=c.getClass3_no();
					if(c3NoString==null){
						c3NoString=String.valueOf(c3ExtraNo);
					}else{
						c3NoString=c3NoString+","+c3ExtraNo;
					}					
				}
			}
			if(c3ExtraNo==0){
				_11_Class_ThirdVO _11VO=new _11_Class_ThirdVO();
				_10_Class_SecondVO _10VO=new _10_Class_SecondVO();
				_10VO.setClass2_no(c2ExtraNo);
				_11VO.setClass_SecondVO(_10VO);
				_11VO.setClass3_name(c3extraName);
				_11dao.insert(_11VO);
				c3NoString=c3NoString+","+(int)getSession().getIdentifier(_11VO);
			}			
		}
		
		System.out.println(c3NoString);
		for(String a:c3NoString.split(",")){
			_13_Item_Class_ThirdVO _13VO=new _13_Item_Class_ThirdVO();
			_12_ItemVO _12VO=new _12_ItemVO();
			_11_Class_ThirdVO _11VO=_11dao.findById(Integer.parseInt(a));
			_12VO.setItem_no(itemNo);
			_13VO.setItemVO(_12VO);
			_13VO.setClass_ThirdVO(_11VO);
			_13VO.setClass3_name(_11VO.getClass3_name());			
			for(String b:extraStuff.split(",")){
				int start=b.indexOf("(");
				int end=b.indexOf(")");				
				if(_11VO.getClass3_name().equals(b.substring(0,start))){
					_13VO.setExtra(Double.parseDouble(b.substring(start+1, end)));
				}			
			}			
			_13dao.insert(_13VO);			
		}
	}
	
	
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓一組↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	//-------------------新增第二第三層屬性class2,class3(先新增商品)
	//冷熱,正常全冰,少冰,去冰;
	//甜度,正常全糖,少糖,半糖,無糖;
	//extraStuff=加珍珠(5),加椰果(10),加芋頭(20)
	public String findC2C3ByName(Integer itemNo,String c2c3,String extraStuff){
		String c3NoString=null;
		for(String a:c2c3.split(";")){
			String[] c2c3First=a.split(",");
			String c2Name=c2c3First[0];//取得第二層屬性
			//判斷第二層屬性是否重複
			Integer c2No=newItemsClassService.findClass2ByName(c2Name,itemNo);
			//判斷第三層屬性			
			for(int i=1;i<c2c3First.length;i++){
				String c3Name=c2c3First[i];
				if(c3NoString==null){
					c3NoString=String.valueOf(newItemsClassService.findClass3ByName(c3Name, c2No));
				}else{
					c3NoString=c3NoString+","+newItemsClassService.findClass3ByName(c3Name, c2No);
				}				
			}			
		}
		System.out.println("create service extraStuff="+extraStuff);
		for(String a:extraStuff.split(",")){
			int start=a.indexOf("(");			
			String c3extraName=a.substring(0, start);
			System.out.println("create service c3extraName="+c3extraName);
			//找出第二層加料的編號
			int c2ExtraNo=0;
			for(_10_Class_SecondVO b:_10dao.getAll()){
				if(b.getClass2_name().equals("加料")){
					c2ExtraNo=b.getClass2_no();
				}
			}
			//判斷第三層是否重複
			int c3ExtraNo=0;
			for(_11_Class_ThirdVO c:_11dao.getAll()){
				if(c3extraName.equals(c.getClass3_name())){
					c3ExtraNo=c.getClass3_no();
					c3NoString=c3NoString+","+c3ExtraNo;
				}
			}
			if(c3ExtraNo==0){
				_11_Class_ThirdVO _11VO=new _11_Class_ThirdVO();
				_10_Class_SecondVO _10VO=new _10_Class_SecondVO();
				_10VO.setClass2_no(c2ExtraNo);
				_11VO.setClass_SecondVO(_10VO);
				_11VO.setClass3_name(c3extraName);
				_11dao.insert(_11VO);
				c3NoString=c3NoString+","+(int)getSession().getIdentifier(_11VO);
			}			
		}		
		return c3NoString;
	}	
	//-------------新增item_class_third(裡面會先呼叫第二第三層屬性class2,class3)
	//冷熱,正常全冰,少冰,去冰;
	//甜度,正常全糖,少糖,半糖,無糖;
	//extraStuff=加珍珠(5),加椰果(10),加芋頭(20)
	public void insertItemClassThird(Integer itemNo,String c2c3,String extraStuff){
		String c3Nos=findC2C3ByName(itemNo,c2c3,extraStuff);
		for(String a:c3Nos.split(",")){
			_13_Item_Class_ThirdVO _13VO=new _13_Item_Class_ThirdVO();
			_12_ItemVO _12VO=new _12_ItemVO();
			_11_Class_ThirdVO _11VO=_11dao.findById(Integer.parseInt(a));
			_12VO.setItem_no(itemNo);
			_13VO.setItemVO(_12VO);
			_13VO.setClass_ThirdVO(_11VO);
			_13VO.setClass3_name(_11VO.getClass3_name());			
			for(String b:extraStuff.split(",")){
				int start=b.indexOf("(");
				int end=b.indexOf(")");				
				if(_11VO.getClass3_name().equals(b.substring(0,start))){
					_13VO.setExtra(Double.parseDouble(b.substring(start+1, end)));
				}			
			}			
			_13dao.insert(_13VO);			
		}		
	}
	//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑一組↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑	
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
			Integer itemNO=newItem.newItems(itemVO);
			
			String c2c3="冷熱,正常全冰,少冰,去冰;甜度,正常全糖,少糖,半糖,無糖";
			String extraStuff="加珍珠(5),加椰果(10),加芋頭(20)";
			newItem.insertItemwithoutc2c3(itemNO,extraStuff);
			
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}
		
	}
	
	
}
