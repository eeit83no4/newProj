package module.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._12_ItemDAO;
import module.dao._13_Item_Class_ThirdDAO;
import module.dao._16_Group_RecordDAO;
import module.model._07_StoreVO;
import module.model._12_ItemVO;
import module.model._13_Item_Class_ThirdVO;
import module.model._15_Item_PriceVO;
import module.util.HibernateUtil;
import net.sf.json.JSONObject;

public class GetAttributes {
	private static _16_Group_RecordDAO _16grDAO=new _16_Group_RecordDAO();
	private SessionFactory sessionFactory;
	public GetAttributes() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	private static _12_ItemDAO _12DAO=new _12_ItemDAO();
	private static _13_Item_Class_ThirdDAO _13DAO=new _13_Item_Class_ThirdDAO();
	
	public List<_13_Item_Class_ThirdVO> get_13_Store_ClassNO(_12_ItemVO bean12) {
		Query query= getSession().createQuery("from _13_Item_Class_ThirdVO where itemVO=?");
		query.setParameter(0, bean12);
		return query.list();
	}
	public String get_09_ClassNO(Integer no) {		
		_12_ItemVO bean12=_12DAO.findById(no);
		String firstName = null;
		 List<_13_Item_Class_ThirdVO> aa = get_13_Store_ClassNO(bean12);
		 for(_13_Item_Class_ThirdVO list:aa){	
//			 System.out.print(list.getClass_ThirdVO().getClass_SecondVO().getClass_FirstVO().getClass1_name());
			 firstName = list.getClass_ThirdVO().getClass_SecondVO().getClass_FirstVO().getClass1_name();
			 break;
			}
		 return firstName;
	}
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			 
			 GetAttributes att=new GetAttributes();
			 
			 Integer no = 20;
			 String aa = att.get_09_ClassNO(no);
				System.out.println(aa);
			 
			 
			 
			 
			 
//			 //----最後再包json就可以了
			 JSONObject json=JSONObject.fromObject(att.find3nds(1));
			 System.out.println(json);
			
			 
			 
				
			 

		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
	
	
	
	//------------冠斌要的-------------------
		public Map<String,Map[]> find3nds(Integer item_no){			
			
			_12_ItemVO  item=_12DAO.findById(item_no);
			Map<String,Map[]> object=new HashMap<>();//存放所有
			
//			String itemno=item.getItem_name();
			Set<_13_Item_Class_ThirdVO> icts=item.getItem_class_thirds();
			
			
			Map[] aaa=new TreeMap[1];
			Map<String,Set<String>> c2c3=new HashMap<String,Set<String>>();//用來存放個別商品的第二層第三層
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
							String noextra=c3name+"(0)";
							c3.add(noextra);
						}
					}						
				}
				c2c3.put(c2name, c3);
			}
			Set<String> size=new HashSet<String>();//用來存放該商品的第三層屬性s
			for(_15_Item_PriceVO a:item.getItem_prices()){
				String sizename=a.getSizeVO().getSize_name();
				String sizeprice=String.valueOf(a.getIprice()).split("\\.")[0];				
				size.add(sizename+"("+sizeprice+")");
			}
			c2c3.put("Size", size);
			
			
			Map<String,Set<String>> resultMap = sortMapByKey(c2c3);    //按Key进行排序  
			  
	        for (Map.Entry<String, Set<String>> entry : resultMap.entrySet()) {  
//	            System.out.println(entry.getKey() + " " + entry.getValue());  
	        }

//	        aaa[0]=c2c3;  //舊的
	        aaa[0]=resultMap;
			object.put("defaultClass", aaa);
			return object;	
			
//			if(store!=null){
//				Set<_12_ItemVO> items=store.getItems();//找到該商店內的商品s
//				Map<String,Map[]> array=new HashMap<>();//用來存放所有商品資訊		
//				for(_12_ItemVO a:items){//解析個別商品
//					Map<String,Set<String>> c2c3=new HashMap<String,Set<String>>();//用來存放個別商品的第二層第三層
//					String itemno=a.getItem_name();
//					Set<_13_Item_Class_ThirdVO> icts=a.getItem_class_thirds();
//					Map[] aaa=new HashMap[1];
//					for(_13_Item_Class_ThirdVO b:icts){
//						String c2name=b.getClass_ThirdVO().getClass_SecondVO().getClass2_name();//該商品的第二層屬性名稱					
//						Set<String> c3=new HashSet<String>();//用來存放該商品的第三層屬性s
//						
//						for(_13_Item_Class_ThirdVO c:icts){
//							String c3name=c.getClass3_name();//該商品的第三層屬性名稱
//							Double extra=c.getExtra();//該商品的第三層屬性的加購價						
//							if(c2name==c.getClass_ThirdVO().getClass_SecondVO().getClass2_name()){
//								if(extra>0){//如果有加購價
//									String withextra=c3name+"("+extra+")";
//									c3.add(withextra);
//								}else{//如果沒有加購價
//									String noextra=c3name;
//									c3.add(noextra);
//								}
//							}						
//						}
//						c2c3.put(c2name, c3);
//					}
//					aaa[0]=c2c3;
//					if(c2c3.size()>0&&c2c3!=null){//檢查是否有第二第三層屬性
//						array.put(itemno,aaa);
//					}			
//				}
//				return array;
//			}else{
//				return null;
//			}
			
		}



/** 
 * 使用 Map按key进行排序 
 * @param map 
 * @return 
 */  
public static Map<String,Set<String>> sortMapByKey(Map<String,Set<String>> map) {
    if (map == null || map.isEmpty()) {  
        return null;  
    } 

    Map<String,Set<String>> sortMap = new TreeMap<String, Set<String>>(new MapKeyComparator());  

    sortMap.putAll(map);  

    return sortMap;  
	}


//比较器类  
public static class MapKeyComparator implements Comparator<String>{  
  public int compare(String str1, String str2) {  
      return str1.compareTo(str2);  
 		}
	}



}