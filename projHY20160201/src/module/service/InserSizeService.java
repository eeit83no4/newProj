package module.service;

import java.util.List;

import module.dao._14_SizeDAO;
import module.dao._15_Item_PriceDAO;
import module.model._12_ItemVO;
import module.model._14_SizeVO;
import module.model._15_Item_PriceVO;
import module.util.HibernateUtil;

public class InserSizeService {
	private _15_Item_PriceDAO _15DAO=new _15_Item_PriceDAO();
	private _14_SizeDAO _14DAO=new _14_SizeDAO();
	
	public void inserSize(String sizeName ,int itemId){		
		Integer pk = 0 ;	
		String[] split = sizeName.split(",");  //砍自串   小(15),中(20),大(25)
		for (String list:split){	
			int no = list.indexOf("(");
//			bean11.setClass3_name(list.substring(0 , no));   //砍字串    小,中,大
			_14_SizeVO bean14=new _14_SizeVO();
			bean14.setSize_name(list.substring(0 , no));
		
			List<_14_SizeVO> VO = _14DAO.getAll();
			for(_14_SizeVO listDB:VO){
				if(list.substring(0 , no).equals(listDB.getSize_name())){
					pk = listDB.getSize_no();
					bean14.setSize_no(pk);					
					break;
				}
			}	
			
			String extra = list.substring(no+1 , list.length()-1);   //砍字串  15,20,25
			Double dExtra = Double.parseDouble(extra);			
			
//			  System.out.println(bean15.getItemVo().getItem_no());
//			  System.out.println(bean15.getIprice());			 
//			  System.out.println(bean14.getSize_no());
			if(bean14.getSize_no() == null){	 
				_14DAO.insert(bean14);
				pk = (int) _14DAO.getSession().getIdentifier(bean14);
				bean14.setSize_no(pk);
			}
			
			_15_Item_PriceVO bean15 = new _15_Item_PriceVO();
			bean15.setIprice(dExtra);	
			_12_ItemVO bean12 = new _12_ItemVO();		
			bean12.setItem_no(itemId);
			bean15.setItemVo(bean12);
			bean15.setSizeVO(bean14);				
			_15DAO.insert(bean15);
		}
	}
	public static void main(String[] args) {
		try {
			 HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();	
		InserSizeService inserSizeService=new InserSizeService();
		
		
		String sizeName="小(15.0),中(20.0),大(25.0),特大(110.0)";
		 int itemId = 27;
		 
		inserSizeService.inserSize(sizeName , itemId);
		
		
		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
}
