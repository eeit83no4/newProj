package module.service.newStore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._07_StoreDAO;
import module.dao._12_ItemDAO;
import module.model._10_Class_SecondVO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
import module.model._15_Item_PriceVO;
import module.util.HibernateUtil;

public class SelectItemsService {
	private SessionFactory sessionFactory;
	public SelectItemsService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	private _07_StoreDAO _07dao=new _07_StoreDAO();
	private _12_ItemDAO _12dao=new _12_ItemDAO();
	//秀出商品價錢
	public String showSize(Integer itemNo){
		_12_ItemVO itemVO=_12dao.findById(itemNo);
		String sizePrice=null;
		//大(5),中(10),小(20)
		for(_15_Item_PriceVO a:itemVO.getItem_prices()){
			String sizeName=a.getSizeVO().getSize_name();
			String price=String.valueOf(a.getIprice()).split("\\.")[0];
			if(sizePrice==null){
				sizePrice=sizeName+"("+price+")";
			}else{
				sizePrice=sizePrice+","+sizeName+"("+price+")";
			}			
		}
		return sizePrice;		
	}
	//--------秀出商品細項
	public String showc2c3(Integer itemNo){
		//冷熱,正常全冰,少冰,去冰;甜度,正常全糖,少糖,半糖,無糖
		_12_ItemVO itemVO=_12dao.findById(itemNo);
		String c2c3=null;
		for(_10_Class_SecondVO a:itemVO.getClass_firstVO().getClass_Seconds()){
			if(!a.getClass2_name().equals("加料")){
				String c2name=a.getClass2_name();
				if(c2c3==null){
					c2c3=c2name;
				}else{
					c2c3=c2c3+";"+c2name;
				}				
				for(_13_Item_Class_ThirdVO b:itemVO.getItem_class_thirds()){
					if(c2name==b.getClass_ThirdVO().getClass_SecondVO().getClass2_name()){
						String c3name=b.getClass3_name();
						c2c3=c2c3+","+c3name;
					}
				}				
			}			
		}
		return c2c3;
	}
	//--------秀出加料區
		public String showExtra(Integer itemNo){
			//冷熱,正常全冰,少冰,去冰;甜度,正常全糖,少糖,半糖,無糖
			_12_ItemVO itemVO=_12dao.findById(itemNo);
			String c2c3=null;
			for(_10_Class_SecondVO a:itemVO.getClass_firstVO().getClass_Seconds()){
				if(a.getClass2_name().equals("加料")){
					String c2name=a.getClass2_name();			
					for(_13_Item_Class_ThirdVO b:itemVO.getItem_class_thirds()){
						if(c2name==b.getClass_ThirdVO().getClass_SecondVO().getClass2_name()){
							String c3name=b.getClass3_name();
							String price=String.valueOf(b.getExtra()).split("\\.")[0];
							if(c2c3==null){
								c2c3=c3name+"("+price+")";
							}else{
								c2c3=c2c3+","+c3name+"("+price+")";
							}							
						}
					}				
				}			
			}
			return c2c3;
		}
	
	//------------------測試------------
	public static void main(String args[]){
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			SelectItemsService selectItemsService=new SelectItemsService();
			System.out.println(selectItemsService.showc2c3(34));
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}
		
	}	

}
