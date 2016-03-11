package module.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._07_StoreDAO;
import module.dao._12_ItemDAO;
import module.dao._16_Group_RecordDAO;
import module.dao._17_Group_UserDAO;
import module.dao._18_Order_DetailDAO;
import module.model._16_Group_RecordVO;
import module.model._17_Group_UserVO;
import module.util.HibernateUtil;

public class indexService {
	private static _16_Group_RecordDAO _16grDAO=new _16_Group_RecordDAO();
	private static _17_Group_UserDAO _17guDAO=new _17_Group_UserDAO();
	private static _18_Order_DetailDAO _18detailDAO=new _18_Order_DetailDAO();
	private static _12_ItemDAO _12itemDAO=new _12_ItemDAO();
	private static _07_StoreDAO _07storeDAO=new _07_StoreDAO();
	private static attempGroupService att=new attempGroupService();
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SessionFactory sessionFactory;

	public indexService() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	public static void main(String[] arg){
		indexService indService=new indexService();
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			System.out.println(indService.sendIngGroup(6));
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally{
			HibernateUtil.closeSessionFactory();
		}	
		
	}
	
	//---------找出進行中的團購(排除掉自己發起的,排除掉時間過期的)
	public List<_16_Group_RecordVO> ingGroup(Integer userId){
		List<_16_Group_RecordVO> grBefore=_16grDAO.getAll();
		List<_16_Group_RecordVO> grAfter=new ArrayList<>();
		for(_16_Group_RecordVO a:grBefore){
			int groupHolder=a.getEmployeeVO().getUser_id();//團購的建立者
			if(userId!=groupHolder){//判斷自己不是主揪
				//判斷剩餘時間
				String endDate=a.getEnd_date().toString();//團購設定的截止時間
				java.util.Date d = new java.util.Date();
				String nowDate=format.format(d);//目前時間
				
				java.util.Date d1 = null;
				java.util.Date d2 = null;
				
				try {
					d1=format.parse(endDate);
					d2=format.parse(nowDate);
					//比對之後正在進行的團購(排除掉過期的)
					if((d1.getTime()-d2.getTime())>0){
						//接下來找到自己被邀請的
						for(_17_Group_UserVO b:a.getGroup_Users()){							
							if(b.getEmployeeVO().getUser_id()==userId){
								grAfter.add(a);
							}														
						}						
					}										
				} catch (ParseException e) {					
					e.printStackTrace();
				}				
			}			
		}
		return grAfter;
	}
	public List<Map<String,String>> sendIngGroup(Integer userId){
		List<Map<String,String>> k=new ArrayList<>();
		for(_16_Group_RecordVO a:ingGroup(userId)){
			Map<String,String> b=new HashMap<>();
			Integer groupNo=a.getGroup_no();//團購編號
			String usersNumber=String.valueOf(att.findUserByGroup(groupNo));//目前訂購人數
			String amount=String.valueOf(att.findAmountByGroup(groupNo)).split("\\.")[0];//目前累積金額
			String holderName=a.getEmployeeVO().getName();//發起人的名字
			String groupName=a.getGroup_name();//團購名字
			String frontGroupNo=String.valueOf(groupNo);//送去前端的團購編號
			b.put("groupNo", frontGroupNo);
			b.put("usersNumber", usersNumber);
			b.put("amount", amount);
			b.put("holderName", holderName);
			b.put("groupName", groupName);
			k.add(b);			
		}
		return k;
	}
		
	//---------找出我發起的團購(只有自己發起的)
	public List<_16_Group_RecordVO> meingGroup(Integer userId){
		List<_16_Group_RecordVO> grBefore=_16grDAO.getAll();
		List<_16_Group_RecordVO> grAfter=new ArrayList<>();
		for(_16_Group_RecordVO a:grBefore){
			int groupHolder=a.getEmployeeVO().getUser_id();//團購的建立者
			if(userId==groupHolder){
				grAfter.add(a);
			}			
		}			
		return grAfter;
	}
	public List<Map<String,String>> sendMeingGroup(Integer userId){
		List<Map<String,String>> k=new ArrayList<>();
		for(_16_Group_RecordVO a:meingGroup(userId)){
			Map<String,String> b=new HashMap<>();
			Integer groupNo=a.getGroup_no();//團購編號
			String usersNumber=String.valueOf(att.findUserByGroup(groupNo));//目前訂購人數
			String amount=String.valueOf(att.findAmountByGroup(groupNo)).split("\\.")[0];//目前累積金額
			String holderName=a.getEmployeeVO().getName();//發起人的名字
			String groupName=a.getGroup_name();//團購名字
			String frontGroupNo=String.valueOf(groupNo);//送去前端的團購編號
			b.put("groupNo", frontGroupNo);
			b.put("usersNumber", usersNumber);
			b.put("amount", amount);
			b.put("holderName", holderName);
			b.put("groupName", groupName);
			k.add(b);			
		}
		return k;
	}

}
