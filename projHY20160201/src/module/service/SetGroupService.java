package module.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import module.dao._04_EmployeeDAO;
import module.dao._07_StoreDAO;
import module.dao._16_Group_RecordDAO;
import module.dao._17_Group_UserDAO;
import module.model._01_OrganizationVO;
import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.model._16_Group_RecordVO;
import module.model._17_Group_UserVO;
import module.util.HibernateUtil;
import net.sf.json.JSONObject;

public class SetGroupService {

	private static _07_StoreDAO _07stDAO = new _07_StoreDAO();
	private static _16_Group_RecordDAO _16grDAO = new _16_Group_RecordDAO();
	private static _04_EmployeeDAO _04emDAO = new _04_EmployeeDAO();
	private static _17_Group_UserDAO _17guserDAO = new _17_Group_UserDAO();

	// 找出店家名
	public String findStoreName(Integer store_no) {
		String store = _07stDAO.findById(store_no).getStore_name();
		return store;
	}

	// 找員工名 部門
	public String findEmpDep(Integer user_id) {

		String emp = _04emDAO.findById(user_id).getName();
		_01_OrganizationVO dep = _04emDAO.findById(user_id).getOrganizationVO();
		String depd = dep.getOrg_id();
		String ed = emp + "       " + depd;
		return ed;
	}


	
	
	// 依部門尋找員工
//	public List findEmp(String org_id) {
//		List depp = _04emDAO.findBydep(org_id);
//		return depp;
//	}

	

//	public String getAllEmpDep() {
//
//		List emp = _04emDAO.getAllEmp();
//		List dep = _04emDAO.getAllDep();
//		String all = emp + "       " + dep;
//		return all;
//	}
	// public List getAllDep(){
	// List dep=_04emDAO.getAllDep();
	// return dep;
	// }
	
	
	public void insertGroup_user(_17_Group_UserVO bean1) {
		_17guserDAO.insert(bean1);
		}
	
	public Integer insertGroup(_16_Group_RecordVO bean) {
		_16grDAO.insert(bean);
		
		Integer	pk =(int) _16grDAO.getSession().getIdentifier(bean);	 //取得剛剛新增的PK值
	  return pk;
	}
	// 新增團購人員
	// public void inserGroupUser(Integer group_no,_17_Group_UserVO bean17){
	// _17_Group_UserDAO
	//
	// }

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			SetGroupService dao = new SetGroupService();
			
			
			
		
			// 店家名
			// System.out.println(dao.findStoreName(1));

			// 員工 部門
			// System.out.println(dao.findEmpDep(2));

//			 System.out.println(dao.findEmp("DEP0006"));
//			for(Object[] a:dao.getAllEmp()){
//				System.out.println(a[0]+"--"+a[1]);
//			}
		
//			 System.out.println(dao.getAllEmpDep());
			// _16_Group_RecordVO bean= new _16_Group_RecordVO();
			// bean.setAnn("adsdasdasd");
			// bean.setGroup_name("God");
			// bean.setStart_date(java.sql.Date.valueOf("2016-02-20"));
			// dao.inserGroup(bean);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
