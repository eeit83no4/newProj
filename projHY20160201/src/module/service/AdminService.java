package module.service;

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
			System.out.println(as.orderMaintain());			
			// ------------------店家維護-----------------------------------
//			System.out.println(as.storeMaintain());			
			// ------------------管理員維護---------------------------------
//			System.out.println(as.adminMaintain());		
		
			
		
		
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			} finally {
				HibernateUtil.closeSessionFactory();
			}

	}
	// ------------------訂單維護-----------------------------------
	public List<Map> orderMaintain(){
		List<Map> finalresult = new ArrayList();
		for(_16_Group_RecordVO grvo : gr.getAll()){
			Map m = new HashMap();
			m.put("訂單編號",grvo.getGroup_no()); //刪除訂單用
			m.put("截止時間",grvo.getEnd_date());
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
			m.put("店家編號", svo.getStore_no());//刪除店家用
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
				m.put("管理員資格", a.findById(evo.getUser_id()).getAuth()); /////////////////////////怪///////////////////////////////
			} catch (NullPointerException e1) {
				e1.printStackTrace();
			}			
			finalresult.add(m);
		}		
		return finalresult;
	}

}
