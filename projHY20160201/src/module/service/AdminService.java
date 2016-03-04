package module.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import module.dao._04_EmployeeDAO;
import module.dao._05_AdminDAO;
import module.dao._07_StoreDAO;
import module.dao._16_Group_RecordDAO;
import module.model._04_EmployeeVO;
import module.model._05_AdminVO;
import module.model._07_StoreVO;
import module.model._16_Group_RecordVO;
import module.util.HibernateUtil;

public class AdminService {

	private _16_Group_RecordDAO gr = new _16_Group_RecordDAO();
	private _07_StoreDAO s = new _07_StoreDAO();
	private _04_EmployeeDAO e = new _04_EmployeeDAO();
	private _05_AdminDAO a = new _05_AdminDAO();
	

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			AdminService as = new AdminService();
			// ------------------訂單維護-----------------------------------
//			System.out.println(as.orderMaintain());			
			// ------------------店家維護-----------------------------------
//			System.out.println(as.storeMaintain());			
			// ------------------管理員維護---------------------------------
//			System.out.println(as.adminMaintain());		
			// ------------------修改auth---------------------------------
//			as.updateAuthByUserId("3", "A");
		
			
		
		
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			} finally {
				HibernateUtil.closeSessionFactory();
			}

	}
	// ------------------訂單維護-----------------------------------
	public List<Map> orderMaintain(){
		SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		List<Map> finalresult = new ArrayList();
		for(_16_Group_RecordVO grvo : gr.getAll()){
			Map m = new HashMap();
			m.put("團購編號",grvo.getGroup_no());
			m.put("截止時間",format.format(grvo.getEnd_date()).toString());
			m.put("團購名稱",grvo.getGroup_name());
			m.put("店家",grvo.getStoreVO().getStore_name());
			m.put("發起人",grvo.getEmployeeVO().getName());
			m.put("訂單狀態",grvo.getStatus());
			finalresult.add(m);
		}
		return finalresult;
	}
	
	// ------------------店家維護-----------------------------------
	public List<Map> storeMaintain(){
		List<Map> finalresult = new ArrayList();
		for(_07_StoreVO svo : s.getAll()){
			Map m = new HashMap();
			m.put("店家編號", svo.getStore_no());
			m.put("建立者", svo.getEmployeeVO().getName());
			m.put("店家名稱", svo.getStore_name());
			finalresult.add(m);
		}		
		return finalresult;
	}
			
	// ------------------管理員維護---------------------------------
	public List<Map> adminMaintain(){
		List<Map> finalresult = new ArrayList();
		for(_04_EmployeeVO evo : e.getAll()){
			Map m = new HashMap();			
			try {
				m.put("員工編號", evo.getUser_id());
				m.put("員工部門", evo.getOrganizationVO().getOrg_name());
				m.put("員工姓名", evo.getName());
				if(a.findAuthByUserId(evo.getUser_id()).size()!=0){
					m.put("管理員資格", a.findAuthByUserId(evo.getUser_id()).get(0));
				} 
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}			
			finalresult.add(m);
		}		
		return finalresult;
	}
	
	// ------------------修改auth---------------------------------
	public void updateAuthByUserId(String user_id,String auth){
			a.updateAuthByUserId(user_id, auth);
	}

}
