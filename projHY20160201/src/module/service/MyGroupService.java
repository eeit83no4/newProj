package module.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.mysql.jdbc.StandardSocketFactory;
import module.dao._04_EmployeeDAO;
import module.dao._16_Group_RecordDAO;

import module.model._16_Group_RecordVO;

import module.dao._17_Group_UserDAO;
import module.dao._18_Order_DetailDAO;

import module.model._17_Group_UserVO;
import module.model._18_Order_DetailVO;
import module.util.HibernateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyGroupService {

	private _04_EmployeeDAO e = new _04_EmployeeDAO();
	private _16_Group_RecordDAO gr = new _16_Group_RecordDAO();
	private _17_Group_UserDAO gu = new _17_Group_UserDAO();
	private _18_Order_DetailDAO od = new _18_Order_DetailDAO();

	public static void main(String[] args) {
		 _16_Group_RecordDAO gr1 = new _16_Group_RecordDAO();
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			MyGroupService mgs = new MyGroupService();
			// ------------------抓出此團購剩餘的時間------------------
//			 System.out.println(mgs.getTimeSec(gr1.findById(1).getEnd_date()));
			// ------------------找出此團購的商品數量------------------
			// System.out.println(mgs.countAmountOfProduct(1));
			// ------------------查詢該團購參與人數------------------
			// System.out.println(mgs.findUserByGroup(1));
			// ------------------查詢該團購細項------------------
			// System.out.println(mgs.orderDetail(1));
			// ------------------抓出"按件統計"所需資料------------------
//			System.out.println(mgs.orderDetail_ByItem(2));

			// ------------------抓出"按人統計"所需資料------------------
//			JSONArray jSONObject2=JSONArray.fromObject(mgs.orderDetail_ByUser(1));//轉換json
//			System.out.println(jSONObject2);
//			for (List<String> sb : mgs.orderDetail_ByUser(1)) {
//				for(String s:sb){
//				 for (int i = 0; i < s.length(); i++) {
//				 System.out.print(s+" ,");
//				 }
//				}
//				 System.out.println("");
//				 }
			// ------------------抓出"明細列表"所需資料------------------
			 for (String[] sb : mgs.orderDetail_detail_new(1)) {
			 for (int i = 0; i < sb.length; i++) {
			 System.out.print(sb[i]+" ,");
			 }
			 System.out.println("");
			 }
			// ------------------查詢此人參加的所有進行中團購項目------------------
//			 for (String[] sb : mgs.searchMyAllGroup_ing(1)) {
//			 for (int i = 0; i < sb.length; i++) {
//			 System.out.print(sb[i]+" ,");
//			 }
//			 System.out.println("");
//			 }
			// ------------------查詢此人參加的所有已完成團購項目------------------
//			 for (String[] sb : mgs.searchMyAllGroup_ed(1)) {
//			 for (int i = 0; i < sb.length; i++) {
//			 System.out.print(sb[i]+" ,");
//			 }
//			 System.out.println("");
//			 }
			// ------------------查詢該團購訂單名細(上)------------------
//			for(String[] a:mgs.orderDetail_byGroup_upper(1)){
//				for (int i = 0; i < a.length; i++) {
//					 System.out.println(a[i]+" ,");
//					 }
//			}
//			JSONArray jSONObject=JSONArray.fromObject(mgs.searchMyAllGroup_ed(1));//轉換json
//			System.out.println(jSONObject);
			
			;
//			System.out.println(mgs.orderDetail_byGroup_upper(1));			 
			// ------------------查詢個人歷史訂購紀錄------------------
//			mgs.personalHistoryRecord(1);			
			// ------------------查詢該團購訂單名細(上)------------------
//			for(String[] a:mgs.orderDetail_byGroup_upper(2)){
//				for(String b:a)
//				System.out.println(b);
//			};
			// ------------------判斷此人是否有權限對此團購進行動作------------------			
//			System.out.println(mgs.findCo_holder(166, 2));

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}

	// ------------------抓出此團購剩餘的秒數------------------
	public String getTimeSec(java.util.Date times) {

//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z YYYY");
//		SimpleDateFormat format = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);

		java.util.Date d1 = new java.util.Date();
		java.util.Date d2 = times;
		
		String now_Date = format.format(d1);
		String end_Date = format.format(d2);
		
//		System.out.println("------------------------------"+end_Date);
//		System.out.println("++++++++++++"+now_Date);
		java.util.Date dd1 = null;
		java.util.Date dd2 = null;

		try {
			dd1 = format.parse(now_Date);
			dd2 = format.parse(end_Date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 毫秒ms
		long diff = d2.getTime() - d1.getTime();
		String strdiff =  String.valueOf(diff);

		return strdiff;
	}
	//------------------秒數轉天數------------------
	public String getTimeDay(long diff) {
		String diffTime = null;
		try {
			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			String StringdiffDays = String.valueOf(diffDays);
			String StringdiffHours = String.valueOf(diffHours);
			String StringdiffMinutes = String.valueOf(diffMinutes);
			String StringdiffSeconds = String.valueOf(diffSeconds);
			if (diffSeconds <= 0) {
				diffTime = "已截止";
			} else {
				diffTime = StringdiffDays + "天" + StringdiffHours + "時" + StringdiffMinutes + "分" + StringdiffSeconds
						+ "秒";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diffTime;
	}

	// ------------------找出此團購的商品數量------------------
	public Integer countAmountOfProduct(Integer group_no) {
		Set<_17_Group_UserVO> users = gr.findById(group_no).getGroup_Users();
		Integer total = 0;
		for (_17_Group_UserVO a : users) {
			Set<_18_Order_DetailVO> details = a.getOrder_Details();
			for (_18_Order_DetailVO b : details) {
				Integer quantity = b.getQuantity();
				total = total + quantity;
			}
		}
		return total;
	}

	// ------------------查詢該團購參與人數------------------
	public String findUserByGroup(Integer group_no) {
		Set<_17_Group_UserVO> users = gr.findById(group_no).getGroup_Users();
		Set<Integer> counts = new HashSet<Integer>();
		for (_17_Group_UserVO a : users) {
			if (a.getUser_amount() != null) {// 排除被邀請但未參加的人
				Set<_18_Order_DetailVO> details = a.getOrder_Details();
				for (_18_Order_DetailVO b : details) {
					int user = b.getGroup_userVO().getGroup_user_no();
					counts.add(user);
				}
			}
		}
		String Stringcounts = String.valueOf(counts.size());
		return Stringcounts;
	}

	// ------------------抓出"按件統計"所需資料------------------
	public List<List<String>> orderDetail_ByItem(Integer group_no) {
		Set<String> oitemNos = new HashSet(); // 先抓出飲料
		Set<_17_Group_UserVO> users = gr.findById(group_no).getGroup_Users();
		for (_17_Group_UserVO a : users) {
			for(_18_Order_DetailVO b:a.getOrder_Details()){
				oitemNos.add(b.getOitem_name());
			}
		}
		
		Iterator it = oitemNos.iterator();  
		int j=-1;
		List<List<String>> finalResult = new ArrayList<>();
		while (it.hasNext()) {
            String st = (String) it.next();
            MyGroupService mgs = new MyGroupService();
            int k =0;
    		j=j+1;
    			Boolean notExsit = true;//判斷Result中是否已經有此商品，沒有則新增
    			for (String[] sb : mgs.orderDetail_detail(group_no)) { //和orderDetail_detail()抓到的資料比對
    				List<String> result = new ArrayList<>();
    				if (st.equals(sb[2])) {
    					if (notExsit) {
    						result.add(sb[2]);
    						result.add(sb[3]);
    						result.add(sb[4]);
    						result.add(sb[1]);
    						result.add(sb[3]);
    						result.add(sb[7]);
    						finalResult.add(result);
    						notExsit = false;
    						
    					} else { //finalResult裡面已經有此團員ID則抓出資料修改
    						Integer w = Integer.parseInt(sb[3]);
    						Integer e = Integer.parseInt(finalResult.get(j).get(1));
//    						result.get(j)[1] = String.valueOf(w+e);
    						finalResult.get(j).set(1, String.valueOf(w+e));
    						finalResult.get(j).add(sb[1]);
    						finalResult.get(j).add(sb[3]);
    						finalResult.get(j).add(sb[7]);
    					}
    				}
    		}            
    }
		return finalResult;			
	}
	
	// ------------------抓出"按人統計"所需資料------------------
	public List<List<String>> orderDetail_ByUser(Integer group_no) {
		List<String> empNos = new ArrayList<>(); // 先抓出參加團購成員的id
		Set<_17_Group_UserVO> users = gr.findById(group_no).getGroup_Users();
		for (_17_Group_UserVO a : users) {
			empNos.add(a.getEmployeeVO().getUser_id().toString());
		}
		MyGroupService mgs = new MyGroupService();
		List<List<String>> finalResult = new ArrayList<>();
		for (int i = 0; i < empNos.size(); i++) {
			Boolean notExsit = true;//判斷finalResult中是否已經有此團員，沒有則新增
			for (String[] sb : mgs.orderDetail_detail(group_no)) { //和orderDetail_detail()抓到的資料比對
				List<String> result = new ArrayList<>();
				
				if (empNos.get(i).equals(sb[0])) {
					if (notExsit) {
						result.add(sb[0]);//團員ID
						result.add(sb[1]);//姓名
						result.add(sb[3]);//數量
						Double price = Double.parseDouble(sb[4])*Integer.parseInt(sb[3]);
						result.add(price.toString());//原總價
						Double price_after = Double.parseDouble(sb[5])*Integer.parseInt(sb[3]);
						result.add(price_after.toString());//計算後原總價
//						result.add(sb[6]);//付款狀態
//						result.add(sb[8]);//拿取狀態
						result.add(sb[2]);//商品名稱
						result.add(sb[3]);//數量
						result.add(sb[7]);//商品備註
						
						finalResult.add(result);
						notExsit = false;
						
					} else { //finalResult裡面已經有此團員ID則抓出資料修改
						Integer quantity=Integer.parseInt(finalResult.get(i).get(2))+Integer.parseInt(sb[3]);
						finalResult.get(i).set(2, quantity.toString());//數量
						Double oprice = Double.parseDouble(finalResult.get(i).get(3))+Double.parseDouble(sb[4])*Integer.parseInt(sb[3]);
						finalResult.get(i).set(3, oprice.toString());//原總價
						Double oprice_after = Double.parseDouble(finalResult.get(i).get(4))+Double.parseDouble(sb[5])*Integer.parseInt(sb[3]);
						finalResult.get(i).set(4, oprice_after.toString());//計算後原總價
						finalResult.get(i).add(sb[2]);//商品名稱
						finalResult.get(i).add(sb[3]);//數量
						finalResult.get(i).add(sb[7]);//商品備註
						
					}
				}				
			}
		}
//		System.out.println(finalResult);
		return finalResult;
	}
	
	// ----回傳: (員工ID、員工姓名)+(商品名稱、數量、原價、折價後價錢、付款狀態、商品屬性)+(訂購時間)------原始版本
	public List<String[]> orderDetail_detail(Integer group_no) {
		List<String[]> sbs = new ArrayList<String[]>(); // 大袋子
		Set<_17_Group_UserVO> users = gr.findById(group_no).getGroup_Users();
		for (_17_Group_UserVO a : users) {
			Set<_18_Order_DetailVO> details = a.getOrder_Details();
			for (_18_Order_DetailVO b : details) {
				String[] starray = new String[9]; // 小袋子
					starray[0] = a.getEmployeeVO().getUser_id().toString(); // 員工ID
					starray[1] = a.getEmployeeVO().getName(); // 員工姓名
					starray[8] = a.getOrder_time().toString(); // 訂購時間
					
					starray[2] = b.getOitem_name();
					starray[3] = b.getQuantity().toString();
					starray[4] = b.getOprice().toString();
					starray[5] = b.getOprice_after().toString();
					starray[6] = b.getPay_status();
					starray[7] = b.getOclass();
					starray[8] = b.getTake_status();
				sbs.add(starray);				
			}
		}
		return sbs;
	}
	
	// ------------------抓出"明細列表"所需資料------------------
	// ----回傳: (員工ID、員工姓名)+(商品名稱、數量、原價、折價後價錢、付款狀態、商品屬性)+(訂購時間)
		public List<String[]> orderDetail_detail_new(Integer group_no) {
			List<String[]> sbs = new ArrayList<String[]>(); // 大袋子
			Set<_17_Group_UserVO> users = gr.findById(group_no).getGroup_Users();
			for (_17_Group_UserVO a : users) {
				Set<_18_Order_DetailVO> details = a.getOrder_Details();
				for (_18_Order_DetailVO b : details) {
					String[] starray = new String[8]; // 小袋子
						starray[0] = a.getEmployeeVO().getUser_id().toString(); // 員工ID
						starray[1] = a.getEmployeeVO().getName(); // 員工姓名
						starray[2] = b.getOitem_name();
						starray[3] = b.getQuantity().toString();
						starray[4] = b.getOprice().toString();
						starray[5] = b.getOprice_after().toString();
						starray[6] = b.getOclass();
						starray[7] = a.getOrder_time().toString(); // 訂購時間
					sbs.add(starray);				
				}
			}
			return sbs;
		}

	// ------------------查詢此人參加的所有進行中團購項目------------------
	public List<String[]> searchMyAllGroup_ing(Integer user_id) {
		List<String[]> sbs = new ArrayList<String[]>(); // 大袋子
		MyGroupService mgs = new MyGroupService();
		MyGroupService myGroupService = new MyGroupService();
		for (_17_Group_UserVO a : gu.findByGroupUserId(user_id)) {
			String[] starray = new String[7]; // 小袋子
//			System.out.println(a.getGroup_RecordVO().getGroup_no()); // 此人參與的團購的編號
			_16_Group_RecordVO b = gr.findById(a.getGroup_RecordVO().getGroup_no());
			if(a.getGroup_RecordVO().getStatus().equals("進行中")){
			try {
				starray[0] = b.getEnd_date().toString();
				starray[1] = b.getGroup_name();
				starray[2] = b.getStoreVO().getStore_name();
				starray[3] = b.getEmployeeVO().getName();
				starray[4] = myGroupService.findUserByGroup(b.getGroup_no());
				starray[5] = b.getGroup_amount_after().toString();
				starray[6] = String.valueOf(a.getGroup_RecordVO().getGroup_no());
			} catch (NullPointerException e) {
				System.out.println("錯誤啦="+e.toString());
			}			
				sbs.add(starray);
			}			
//			System.out.println("sbs="+sbs);
		 }		
		return sbs;		
	}
	
	// ------------------查詢此人參加的所有已完成團購項目------------------
		public List<String[]> searchMyAllGroup_ed(Integer user_id) {
			List<String[]> sbs = new ArrayList<String[]>(); // 大袋子
			MyGroupService myGroupService = new MyGroupService();
			for (_17_Group_UserVO a : gu.findByGroupUserId(user_id)) {
				String[] starray = new String[7]; // 小袋子
//				System.out.println(a.getGroup_RecordVO().getGroup_no()); // 此人參與的團購的編號
				_16_Group_RecordVO b = gr.findById(a.getGroup_RecordVO().getGroup_no());
				if(!a.getGroup_RecordVO().getStatus().equals("進行中")){
				try {
					starray[0] = b.getEnd_date().toString();
					starray[1] = b.getGroup_name();
					starray[2] = b.getStoreVO().getStore_name();
					starray[3] = b.getEmployeeVO().getName();
					starray[4] = myGroupService.findUserByGroup(b.getGroup_no());
					starray[5] = b.getGroup_amount_after().toString();
					starray[6] = String.valueOf(a.getGroup_RecordVO().getGroup_no());
				} catch (NullPointerException e) {
					System.out.println("錯誤啦="+e.toString());
				}
					sbs.add(starray);
				}
//				System.out.println("sbs="+sbs);
			 }		
			return sbs;		
		}
		
	
	// ------------------查詢個人歷史訂購紀錄------------------
		public List<Map> personalHistoryRecord(Integer user_id){
			List<Map> finalResult= new ArrayList(); // 大袋子
			for (_17_Group_UserVO a : gu.findByGroupUserId(user_id)) {//抓出此人有參加哪些團				

				for(_18_Order_DetailVO b : a.getOrder_Details()){ //
					Map m = new HashMap(); //小袋子
					m.put("截止時間", a.getGroup_RecordVO().getEnd_date().toString());
					m.put("團購名稱", a.getGroup_RecordVO().getGroup_name());
					m.put("店家", b.getOstore_name());
					m.put("商品名稱", b.getOitem_name());
					m.put("備註", b.getOclass());	
					m.put("原價", b.getOriginal_oprice());
					m.put("數量", b.getQuantity());
					m.put("實付金額", b.getOprice_after());
					m.put("實付小計", b.getQuantity()*b.getOprice_after());	
					finalResult.add(m);
				}			
			}
			for(Map a:finalResult){
			System.out.println(a);
			}
			return finalResult;
		}

	
	// ------------------查詢該團購訂單名細(上)------------------
	public List<String[]> orderDetail_byGroup_upper(Integer group_no){
		List<String[]> sbs = new ArrayList<String[]>(); // 大袋子
		String[] starray = new String[10];
		_16_Group_RecordVO grvo = gr.findById(group_no);
		starray[0] = grvo.getGroup_name();
		starray[1] = grvo.getStoreVO().getStore_name();
		starray[2] = grvo.getAnn();
		starray[3] = grvo.getGroup_amount_after().toString();
		starray[4] = this.countAmountOfProduct(group_no).toString();
		starray[5] = grvo.getStoreVO().getPhone().toString();
		starray[6] = grvo.getEmployeeVO().getName();
		starray[7] = this.getTimeSec(gr.findById(group_no).getEnd_date()).toString();
		starray[8] = grvo.getStatus();
		starray[9] = grvo.getGroup_no().toString();
		sbs.add(starray);
		return sbs;
	}
	
	// ------------------判斷此人是否有權限對此團購進行動作------------------
	public int findCo_holder(Integer group_user_id, Integer group_no){
		int result = 0;
		for(_17_Group_UserVO a:gu.findByGroupUserId2(group_user_id, group_no)){
			if(!a.getCo_holder().equals("C")){
				result = 1;			
			}			
		}	
		return result;		
	}
	
	
	
	
	
	
}
