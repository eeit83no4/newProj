package module.service;

import java.util.HashMap;
import java.util.HashSet;
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
import module.model._21_Store_In_ClassVO;
import module.util.HibernateUtil;

public class OpenStoreForGroupService {
		
	
	private static _07_StoreDAO _07itemDAO=new _07_StoreDAO();
	private SessionFactory sessionFactory;

	public OpenStoreForGroupService() {
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
			OpenStoreForGroupService att=new OpenStoreForGroupService();
//			int store_no=1;
//			System.out.println(att.findItemsNoByStore(store_no));
//			System.out.println(att.findItemsByStore(store_no));
//			System.out.println(att.find2nds(store_no));
//			System.out.println(att.find3nds(store_no));
//			System.out.println(att.findSizePricesbyStore(store_no));
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}		
	}
	//----------------找出該商品的照片本機路徑(根據商品編號找出商品照片)------------------------
	public Map<Integer,String> findItemPicByStore(Integer store_no){
		_07_StoreVO store=_07itemDAO.findById(store_no);
		if(store!=null){
			Set<_12_ItemVO> storeitems=store.getItems();
			Map<Integer,String> xx=new HashMap<>();
			for(_12_ItemVO a:storeitems){			
				try {
					String pic=new String(a.getPic());
					xx.put(a.getItem_no(), pic);
				} catch (Exception e) {
					System.out.println("錯誤啦="+e.toString());				
				}
			}
			return xx;
		}else{
			return null;
		}
	}
	//----------------找出該商店的商品(純商品編號)------------------------
	public Set<Integer> findItemsNoByStore(Integer store_no){				
		_07_StoreVO store=_07itemDAO.findById(store_no);
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
	//----------------找出該商店的商品(根據商品編號找出商品名字)------------------------
		public Map<Integer,String> findItemsByStore(Integer store_no){				
			_07_StoreVO store=_07itemDAO.findById(store_no);
			if(store!=null){
				Set<_12_ItemVO> storeitems=store.getItems();
				Map<Integer,String> x=new HashMap<>();
				for(_12_ItemVO a:storeitems){					
					try {
						x.put(a.getItem_no(), a.getItem_name());
					} catch (Exception e) {						
						e.printStackTrace();
					}
				}
				
				return x;
			}else{
				return null;
			}
			
		}

	//---------------找出該商店的所有商品size,price(根據商品編號找出該商品的價錢編號與內容)--------------------
	public Map<Integer,Map<String,String>> findSizePricesbyStore(Integer store_no){				
		_07_StoreVO store=_07itemDAO.findById(store_no);
		if(store!=null){
			Set<_12_ItemVO> items=store.getItems();
			Map<Integer,Map<String,String>> sizeprices=new HashMap<>();			
			for(_12_ItemVO a:items){
				Map<String,String> iprices=new HashMap<>();//用來存放size and price				
				Integer itemno=a.getItem_no();//商品編號
				Set<_15_Item_PriceVO> prices2=a.getItem_prices();
				for(_15_Item_PriceVO c:prices2){					
					try {
						
//						Integer priceno=c.getPrice_no();
						String size=c.getSizeVO().getSize_name();
						Double sizeInprice=c.getIprice();
						iprices.put(size, String.valueOf(sizeInprice).split("\\.")[0]);
						
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
	//------------找出該商品的第二層屬性(根據商品編號尋找)-------------------
	public Map<Integer,Set<String>> find2nds(Integer store_no){
		_07_StoreVO store=_07itemDAO.findById(store_no);
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
	
	
	
	//------------找出該商品品的第三層屬性(搭配find2nds())(根據商品編號尋找再根據第二層名稱尋找第三層)-------------------
	public Map<Integer,Map<String,Set<String>>> find3nds(Integer store_no){
		
		_07_StoreVO store=_07itemDAO.findById(store_no);
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
	
	
	
	
	
}
