package module.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._07_StoreDAO;
import module.dao._12_ItemDAO;
import module.dao._16_Group_RecordDAO;
import module.dao._17_Group_UserDAO;
import module.dao._18_Order_DetailDAO;
import module.model._07_StoreVO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
import module.model._15_Item_PriceVO;
import module.model._16_Group_RecordVO;
import module.model._17_Group_UserVO;
import module.model._18_Order_DetailVO;
import module.model._21_Store_In_ClassVO;
import module.util.HibernateUtil;

public class attempGroupService {
	
	
	private static _16_Group_RecordDAO _16grDAO=new _16_Group_RecordDAO();
	private static _17_Group_UserDAO _17guDAO=new _17_Group_UserDAO();
	private static _18_Order_DetailDAO _18detailDAO=new _18_Order_DetailDAO();
	private static _12_ItemDAO _12itemDAO=new _12_ItemDAO();
	private static _07_StoreDAO _07storeDAO=new _07_StoreDAO();
	private SessionFactory sessionFactory;

	public attempGroupService() {
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
			attempGroupService att=new attempGroupService();
			//找出該團購的商品
//			System.out.println(att.findItemsByGroup(1));
//			System.out.println(att.findItemsNoByGroup(1));
			//找出該團購的所有商品size,price----------------------------------
//			System.out.println(att.findSizePricesbyGroup(1).get(16));
			
			//找出該團購目前的訂購人數-------------------------------------		
//			System.out.println(att.findUserByGroup(1));
			//找出該團購目前的累積金額---------------------------------------
//			System.out.println(att.findAmountByGroup(1));
			//找出該物品的第二第三層屬性
//			System.out.println(att.find3nds(1));
//			使用者下訂
			List<_18_Order_DetailVO> listDetail=new ArrayList<_18_Order_DetailVO>();
			_18_Order_DetailVO d1=new _18_Order_DetailVO();
			_18_Order_DetailVO d2=new _18_Order_DetailVO();
			_18_Order_DetailVO d3=new _18_Order_DetailVO();
			_18_Order_DetailVO d4=new _18_Order_DetailVO();
			
//			_17_Group_UserVO user=new _17_Group_UserVO();
//			user.setGroup_user_no(1);
			_17_Group_UserVO user=_17guDAO.findById(1);
//			System.out.println("user="+user);
//			d1.setGroup_userVO(user);
//			d1.setOstore_name("紅茶店");
//			d1.setOprice_no(528);
//			d1.setOitem_name("紅茶");
//			d1.setOriginal_oprice(25.0);
//			d1.setOprice(30.0);
//			d1.setOprice_after(30.0);
//			d1.setOclass("中,正常(全糖),少冰,加珍珠(5)");
//			d1.setQuantity(2);
//			
//			d2.setGroup_userVO(user);
//			d2.setOstore_name("紅茶店");
//			d2.setOprice_no(527);
//			d2.setOitem_name("紅茶");
//			d2.setOriginal_oprice(20.0);
//			d2.setOprice(25.0);
//			d2.setOprice_after(25.0);
//			d2.setOclass("小,正常(全糖),少冰,加珍珠(5)");
//			d2.setQuantity(2);
//			
//			d3.setGroup_userVO(user);
//			d3.setOstore_name("紅茶店");
//			d3.setOprice_no(534);
//			d3.setOitem_name("綠茶");
//			d3.setOriginal_oprice(25.0);
//			d3.setOprice(30.0);
//			d3.setOprice_after(30.0);
//			d3.setOclass("中,正常(全糖),少冰,加珍珠(5)");
//			d3.setQuantity(2);
//			
//			d4.setGroup_userVO(user);
//			d4.setOstore_name("紅茶店");
//			d4.setOprice_no(531);
//			d4.setOitem_name("奶茶");
//			d4.setOriginal_oprice(25.0);
//			d4.setOprice(25.0);
//			d4.setOprice_after(25.0);
//			d4.setOclass("中,正常(全糖),少冰");
//			d4.setQuantity(2);
//			
//			listDetail.add(d1);
//			listDetail.add(d2);
//			listDetail.add(d3);
//			listDetail.add(d4);
//			att.ordering(listDetail);
			//------------------找出該團購的商品------------------
//			System.out.println(att.findGroupItem(1));
//			int groupno=3;
//			for(_12_ItemVO b:att.findGroupItem(groupno)){
//				String itemname=b.getItem_name();
//				Map<String, Set<String>> sizeprice=att.findSizePricesbyGroup(groupno);
//				for(String a:sizeprice.get(itemname)){
//					System.out.println(itemname);					
//					System.out.println(a);
//				}
//				
//				for(_13_Item_Class_ThirdVO t:b.getItem_class_thirds()){
//					Map<String, Map<String, Set<String>>> class2=att.find3nds(groupno);
//					Map<String, Set<String>> k=class2.get(itemname);
//					String class2name=t.getClass_ThirdVO().getClass_SecondVO().getClass2_name();
//					System.out.println("<"+class2name+">");
//					Set<String> class3=k.get(class2name);
//					for(String r:class3){
//						System.out.println(r);
//					}
//				}
//				System.out.println("------------------");
//			}
//			System.out.println(att.find2nds(3));
			
			
//			System.out.println(att.findAnnByGroup(1));
//			System.out.println(att.findItemPicByGroup(1));
//			System.out.println(att.findPricenoByGroup(1).get("16"));
//			Map<Integer,String[]> nn=new HashMap<>();
//			String[] aa=new String[2];
//			aa[0]="紅茶";
//			aa[1]="大:30,中:25,小:20";
//			nn.put(1, aa);
//			String[] bb=new String[2];
//			bb[0]="綠茶";
//			bb[1]="大:30,中:25,小:20";
//			nn.put(2, bb);
//			String[] cc=new String[2];
//			cc[0]="奶茶";
//			cc[1]="大:30,中:25,小:20";
//			nn.put(3, cc);
			//---------------------------------------------------------------
			
//			System.out.println(att.find3nds(1));			
			//--------------------------運費計算
			System.out.println(att.findShipmentByGroup(2));
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}		
	}
	
	
	/*使用者確定下訂之後會呼叫_18_Order_DetailDAO的insert方法新增多筆資料，
	 * 接著要做的是更新_17_Group_UserVO的金錢跟時間欄位
	 * 接著要做的是更新_16_Group_RecordVO的金錢欄位
	 */
	public void ordering(List<_18_Order_DetailVO> userorder){
		int groupUserNo=0;
		double originAmout=0.0;
		double amount=0;
		double amountAfter=0;
		//--------------新增order_tail----------------
		for(_18_Order_DetailVO a:userorder){
			_18detailDAO.insert(a);
			groupUserNo=a.getGroup_userVO().getGroup_user_no();
			originAmout=originAmout+(a.getQuantity()*a.getOriginal_oprice());
			amount=amount+(a.getQuantity()*a.getOprice());
			amountAfter=amountAfter+(a.getQuantity()*a.getOprice_after());
		}
		System.out.println("groupUserNo="+groupUserNo);
		//--------------更新GroupUser金額-----------------		
		_17_Group_UserVO user=_17guDAO.findById(groupUserNo);
		System.out.println("user="+user);
		Double alOriginal_user_amount=user.getOriginal_user_amount();
		Double alUser_amount=user.getUser_amount();
		Double alUser_amount_after=user.getUser_amount_after();
		user.setOriginal_user_amount(originAmout+alOriginal_user_amount);
		user.setUser_amount(amount+alUser_amount);
		user.setUser_amount_after(amountAfter+alUser_amount_after);
		user.setOrder_time(new java.util.Date());
		_17guDAO.update(user);
		//----------------更新GroupRecord金額-------------
		_16_Group_RecordDAO groupDao=new _16_Group_RecordDAO();
		
		Integer groupno=user.getGroup_RecordVO().getGroup_no();//團購編號
		
		_16_Group_RecordVO groupVO=groupDao.findById(groupno);	
		Double user_amount=0.0;
		Double user_amount_after=0.0;
		for(_17_Group_UserVO a:groupVO.getGroup_Users()){
			user_amount=user_amount+a.getUser_amount();
			user_amount_after=user_amount_after+a.getUser_amount_after();
		}	
		groupVO.setGroup_no(groupno);
		groupVO.setGroup_amount(user_amount);
		groupVO.setGroup_amount_after(user_amount_after);		
		groupDao.update(groupVO);
		
	}
	//----------------找出該商品的照片本機路徑------------------------
//	public Map<Integer,String> findItemPicByGroup(Integer group_no){
//		_07_StoreVO store=_16grDAO.findById(group_no).getStoreVO();
//		if(store!=null){
//			Set<_12_ItemVO> storeitems=store.getItems();
//			Map<Integer,String> xx=new HashMap<>();
//			for(_12_ItemVO a:storeitems){			
//				try {
//					String pic=new String(a.getPic());
//					xx.put(a.getItem_no(), pic);
//				} catch (Exception e) {
//					System.out.println("錯誤啦="+e.toString());				
//				}
//			}
//			return xx;
//		}else{
//			return null;
//		}
//	}
	//----------------找出該團購的商品(純編號)------------------------
	public Set<Integer> findItemsNoByGroup(Integer group_no){				
		_07_StoreVO store=_16grDAO.findById(group_no).getStoreVO();
		if(store!=null){
			Set<_12_ItemVO> storeitems=store.getItems();
			Set<Integer> items=new HashSet<>();		
			for(_12_ItemVO a:storeitems){
				items.add(a.getItem_no());
			}
			return items;
		}else{
			return null;
		}
		
	}	
	//----------------找出該團購的商品------------------------
	public List<Map<Integer,String>> findItemsByGroup(Integer group_no){				
		_07_StoreVO store=_16grDAO.findById(group_no).getStoreVO();
		if(store!=null){
			Set<_12_ItemVO> storeitems=store.getItems();
			List<Map<Integer,String>> items=new ArrayList<Map<Integer,String>>();
			Map<Integer,String> x=new HashMap<>();
			for(_12_ItemVO a:storeitems){
				
				try {
					x.put(a.getItem_no(), a.getItem_name());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			items.add(x);
			return items;
		}else{
			return null;
		}
		
	}
	//---------------找出該團購的所有商品的priceno--------------------
//	public Map<String,Set<Integer>> findPricenoByGroup(Integer group_no){
//		_07_StoreVO store=_16grDAO.findById(group_no).getStoreVO();
//		if(store!=null){
//			Map<String,Set<Integer>> findPricenoByGroup=new HashMap<>();
//			for(_12_ItemVO a:store.getItems()){
//				Set<Integer> pricenoSet=new HashSet<>();
//				for(_15_Item_PriceVO b:a.getItem_prices()){					
//					int priceno=b.getPrice_no();
//					pricenoSet.add(priceno);					
//				}
//				findPricenoByGroup.put(String.valueOf(a.getItem_no()), pricenoSet);
//			}
//			return findPricenoByGroup;
//		}
//		return null;
//	}
	//---------------找出該團購的所有商品size,price--------------------
	public Map<Integer,Set<String>> findSizePricesbyGroup(Integer group_no){				
		_07_StoreVO store=_16grDAO.findById(group_no).getStoreVO();
		if(store!=null){
			Set<_12_ItemVO> items=store.getItems();
			Map<Integer,Set<String>> sizeprices=new HashMap<Integer,Set<String>>();			
			for(_12_ItemVO a:items){
				Set<String> iprices=new HashSet<String>();//用來存放size and price				
				Integer itemno=a.getItem_no();//商品編號
				Set<_15_Item_PriceVO> prices2=a.getItem_prices();
				for(_15_Item_PriceVO c:prices2){					
					try {
						
						Integer priceno=c.getPrice_no();
						String size=c.getSizeVO().getSize_name();
						Double sizeInprice=c.getIprice();
						String ss=size+"("+sizeInprice+")";
						iprices.add(String.valueOf(priceno)+"="+ss);//size and price
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				sizeprices.put(itemno, iprices);			
			}		
			return sizeprices;		
		}else{
			return null;
		}
		
	}
	//------------找出該物品的第二層屬性-------------------
	public Map<Integer,Set<String>> find2nds(Integer group_no){
		_07_StoreVO store=_16grDAO.findById(group_no).getStoreVO();//找到該團購的商店
		if(store!=null){
			Set<_12_ItemVO> items=store.getItems();//找到該商店內的商品s
			Map<Integer,Set<String>> _2nds=new HashMap<>();
			for(_12_ItemVO a:items){
				Set<String> xx=new HashSet<>();
				Integer itemno=a.getItem_no();
				for(_13_Item_Class_ThirdVO b:a.getItem_class_thirds()){
					String class2name=b.getClass_ThirdVO().getClass_SecondVO().getClass2_name();
					xx.add(class2name);
				}
				_2nds.put(itemno, xx);
			}
			return _2nds;
		}else{
			return null;
		}		
	}	
	//------------找出該物品的第三層屬性-------------------
	public Map<Integer,Map<String,Set<String>>> find3nds(Integer group_no){
		
		_07_StoreVO store=_16grDAO.findById(group_no).getStoreVO();//找到該團購的商店
		if(store!=null){
			Set<_12_ItemVO> items=store.getItems();//找到該商店內的商品s
			Map<Integer,Map<String,Set<String>>> array=new HashMap<>();//用來存放所有商品資訊		
			for(_12_ItemVO a:items){//解析個別商品
				Map<String,Set<String>> c2c3=new HashMap<String,Set<String>>();//用來存放個別商品的第二層第三層
				Integer itemno=a.getItem_no();
				Set<_13_Item_Class_ThirdVO> icts=a.getItem_class_thirds();
				for(_13_Item_Class_ThirdVO b:icts){
					String c2name=b.getClass_ThirdVO().getClass_SecondVO().getClass2_name();//該商品的第二層屬性名稱					
					Set<String> c3=new HashSet<String>();//用來存放該商品的第三層屬性s
					for(_13_Item_Class_ThirdVO c:icts){
						String c3name=c.getClass3_name();//該商品的第三層屬性名稱
						Double extra=c.getExtra();//該商品的第三層屬性的加購價						
						if(c2name==c.getClass_ThirdVO().getClass_SecondVO().getClass2_name()){
							if(extra>0){//如果有加購價
								String withextra=c3name+"("+extra+")";
								c3.add(withextra);
							}else{//如果沒有加購價
								String noextra=c3name;
								c3.add(noextra);
							}
						}						
					}
					c2c3.put(c2name, c3);
				}
				if(c2c3.size()>0&&c2c3!=null){//檢查是否有第二第三層屬性
					array.put(itemno,c2c3);
				}			
			}
			return array;
		}else{
			return null;
		}
		
	}
	//------------------找出該團購的公告事項------------------
	public String findAnnByGroup(Integer group_no){
		String ann=_16grDAO.findById(group_no).getAnn();		
		if(ann!=null&&ann.trim().length()>0){
			return ann;
		}else{
			return null;
		}
	}
	//-----------------找出該團購目前的訂購人數------------------
	public int findUserByGroup(Integer group_no){		
		Set<_17_Group_UserVO> users=_16grDAO.findById(group_no).getGroup_Users();
		if(users!=null&&users.size()>0){
			Set<Integer> counts=new HashSet<Integer>();
			for(_17_Group_UserVO a:users){
				Set<_18_Order_DetailVO> details=a.getOrder_Details();				
				for(_18_Order_DetailVO b:details){					
					int user=b.getGroup_userVO().getGroup_user_no();
					counts.add(user);
				}				
			}
			return counts.size();
		}else{
			return 0;
		}		
	}	
	//------------------找出該團購目前的累積金額------------------
	public double findAmountByGroup(Integer group_no){		
		Set<_17_Group_UserVO> users=_16grDAO.findById(group_no).getGroup_Users();
		Double total=0.0;
		if(users!=null&&users.size()>0){
			for(_17_Group_UserVO a:users){
				Set<_18_Order_DetailVO> details=a.getOrder_Details();
				for(_18_Order_DetailVO b:details){
					Double oprice=b.getOprice_after();
					Integer quantity=b.getQuantity();
					total=total+(oprice*quantity);
				}
			}
			return total;
		}else{
			return total;
		}		
	}
	//--------------------------運費計算----------------------
	public String findShipmentByGroup(Integer group_no){
		String shipfromDB=_16grDAO.findById(group_no).getShipment();
		if(shipfromDB!=null&&shipfromDB.trim().length()>0){
			int start=shipfromDB.indexOf("(");
			int end=shipfromDB.indexOf(")");
			
			String shiprule=shipfromDB.substring(0, start);//運費計算規則
			String shipfee=shipfromDB.substring(start+1, end);//運費
			
			String ship=shiprule+","+shipfee;
			return ship;
		}		
		return null;		
	}	
	//-----------拿到group_user_no(透過團購編號與使用者編號)
	public Integer getGroupUserNo(Integer group_no,Integer group_user_id){
		List<_17_Group_UserVO> groupUserList=_17guDAO.findByGroupUserId(group_user_id);
		Integer groupUserNo=0;
		for(_17_Group_UserVO a:groupUserList){
			Integer dbGrNo=a.getGroup_RecordVO().getGroup_no();//資料庫抓出的團購編號
			if(group_no==dbGrNo){
				groupUserNo=a.getGroup_user_no();
				break;
			}
		}
		return groupUserNo;
	}
	
	
	//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓發起團購用↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	//-------------找出所有商店(排除不可見狀態)
	public List<_07_StoreVO> getAllStores(){
		List<_07_StoreVO> allStores=_07storeDAO.getAll();
		List<_07_StoreVO> newAllSotres=new ArrayList<_07_StoreVO>();
		for(_07_StoreVO a:allStores){
			if(a.getPublic_state().equals("1")){
				newAllSotres.add(a);
			}
		}
		return newAllSotres;
	}
	//-------------找出所有商店的商品與價格(排除不可見狀態)
	public Map<Integer,Set<String[]>> getItemByAllStore(){
		List<_07_StoreVO>  storeVOList=_07storeDAO.getAll();
		Map<Integer,Set<String[]>> nn=new HashMap<>();
		for(_07_StoreVO a:storeVOList){
			if(a.getPublic_state().equals("1")){
				Integer store_no=a.getStore_no();//店家編號
				Set<String[]> t=new HashSet<>();
				for(_12_ItemVO b:a.getItems()){				
					String[] aa=new String[2];
					String item_name=b.getItem_name();//商品名字
					String xx=null;
					for(_15_Item_PriceVO c:b.getItem_prices()){
						String sizename=c.getSizeVO().getSize_name();//size name
						Double price=c.getIprice();//price
						if(xx==null){
							xx=sizename+":"+price;
						}else{
							xx=xx+","+sizename+":"+price;
						}					
					}
					aa[0]=item_name;
					aa[1]=xx;
					t.add(aa);				
				}
				nn.put(store_no, t);
			}
		}
		return nn;
	}
	//--------找出所有店家的類型(排除不可見狀態)
	public Map<Integer,String> findStoreClass(){
		List<_07_StoreVO>  storeVOList=_07storeDAO.getAll();
		Map<Integer,String> v=new HashMap<>();
		for(_07_StoreVO a:storeVOList){
			if(a.getPublic_state().equals("1")){
				Integer store_no=a.getStore_no();//商店編號
				String classes=null;
				for(_21_Store_In_ClassVO b:a.getStore_in_classs()){
					String classname=b.getStore_classVO().getClass_name();//類型名稱
					if(classes==null){
						classes=classname;
					}else{
						classes=classes+","+classname;
					}
				}						
				v.put(store_no, classes);
			}				
		}		
		return v;
	}
	//---------------找出所有商店(排除不可見+時間排序倒序)
	public List<_07_StoreVO> getAllStoresTiemSorted(){		
		List<_07_StoreVO> store=getAllStores();
		//------------排序後
		System.out.println("排序前");
		for(_07_StoreVO a:store){
			System.out.println(a.getFinal_update());
		}
		//---------開始排序
		Collections.sort(store, new Comparator<_07_StoreVO>() {
			@Override
			public int compare(_07_StoreVO o1, _07_StoreVO o2) {
				return o2.getFinal_update().compareTo(o1.getFinal_update());				
			}			
		});
		//--------排序後
		System.out.println("排序後");
		for(_07_StoreVO a:store){
			System.out.println(a.getFinal_update());
		}
		return store;
	}
	
	
}
