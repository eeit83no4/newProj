package module.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.model._16_Group_RecordVO;
import module.model._17_Group_UserVO;
import module.util.HibernateUtil;
import net.sf.json.JSONObject;

public class _17_Group_UserDAO implements _17_Group_User_InterfaceDAO {
	private SessionFactory sessionFactory;

	public _17_Group_UserDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public Session getSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			_17_Group_User_InterfaceDAO dao = new _17_Group_UserDAO();
//			List<_17_Group_UserVO> beans = dao.getUserIdNo(1);
//			System.out.println(dao.getUserIdNo(1));
//			for(_17_Group_UserVO bean :beans){
//				System.out.println();
//			}
			// _17_Group_UserVO bean = new _17_Group_UserVO();

//			 System.out.println(dao.findByGroupUserId(1));
			 for(_17_Group_UserVO a:dao.findByGroupUserId2(1,1)){
				 System.out.println(a.getCo_holder());				 
			 }
			// System.out.println(dao.countamount(1)[0]);

			// System.out.println(dao.countAmountOfProduct(1));

			// -------------------
			// System.out.println(dao.getAll());
			// -----------------------------
			// System.out.println(dao.findById(1));
			// ----------------------------
			// _18_Order_DetailDAO detail=new _18_Order_DetailDAO();
			// detail.delete(25);
			// dao.delete(19);
			// ------------------------------------------

			// _16_Group_RecordVO first = new _16_Group_RecordVO();
			// first.setGroup_no(1);
			// bean.setGroup_RecordVO(first);
			//
			// _04_EmployeeVO first2 = new _04_EmployeeVO();
			// first2.setUser_id(1);
			// bean.setEmployeeVO(first2);
			//
			// bean.setGroup_user_name("dog");
			// bean.setGroup_user_no(1);
			// dao.update(bean);

			// --------------------------
			// _16_Group_RecordVO first = new _16_Group_RecordVO();
			// first.setGroup_no(1);
			// bean.setGroup_RecordVO(first);
			//
			// _04_EmployeeVO first2 = new _04_EmployeeVO();
			// first2.setUser_id(1);
			// bean.setEmployeeVO(first2);
			//
			// bean.setGroup_user_name("dogggggggg");
			//
			// dao.insert(bean);

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	@Override
	public void insert(_17_Group_UserVO group_UserVO) {
		this.getSession().save(group_UserVO);
	}

	@Override
	public void update(_17_Group_UserVO group_UserVO) {
		getSession().update(group_UserVO);
	}

	@Override
	public void delete(Integer group_user_no) {
		_17_Group_UserVO group_UserVO = new _17_Group_UserVO();
		group_UserVO.setGroup_user_no(group_user_no);
		getSession().delete(group_UserVO);
	}

	@Override
	public _17_Group_UserVO findById(Integer group_user_no) {
		return (_17_Group_UserVO) getSession().get(_17_Group_UserVO.class, group_user_no);
	}

	@Override
	public List<_17_Group_UserVO> getAll() {
		return getSession().createQuery("from _17_Group_UserVO").list();
	}

	// ........查詢此人參加哪些團購...............................................................................//

	public List<_17_Group_UserVO> findByGroupUserId(Integer group_user_id) {
		Query query = getSession().createQuery("from _17_Group_UserVO where group_user_id=?");
		query.setParameter(0, group_user_id);
		return query.list();
	}
	
	// ........查詢此人參加的某次團購的項目...............................................................................//
		public List<_17_Group_UserVO> findByGroupUserId2(Integer group_user_id, Integer group_no) {
			Query query = getSession().createQuery("from _17_Group_UserVO where group_user_id=? and group_no=?");
			query.setParameter(0, group_user_id);
			query.setParameter(1, group_no);		
			return query.list();
		}

	@Override
	public JSONObject getUserIdNo(int group_no) {
		Query query = getSession()
				.createQuery("select group_user_no, group_user_name from _17_Group_UserVO where group_no=" + group_no);
		List<Object[]> list =query.list();
		Map<Integer, String> GUmap =new HashMap<Integer, String>();
		JSONObject GU= new JSONObject();
		for (int i=0;i<list.size();i++){
			Object[] os=list.get(i);
//			System.out.println(os[0]+","+os[1]);
			GUmap.put((Integer)os[0],(String)os[1]);
		}
		GU.putAll(GUmap);
		return GU;
	}

	// ........查詢某團購參與人數..........................................................................................//

	public Long[] countamount(Integer group_user_id) {
		Integer[] group_no = new Integer[10];
		// Integer group_no=0;
		Long[] result = new Long[10];
		Query query = getSession().createQuery("select group_RecordVO from _17_Group_UserVO where group_user_id=?");
		query.setParameter(0, group_user_id);
		List<_16_Group_RecordVO> list = query.list();
		int i = 0;
		for (_16_Group_RecordVO Group_Record : list) {
			group_no[i] = Group_Record.getGroup_no();
			i++;
		}
		System.out.println(group_no[0]);
		for (int j = 0; j <= 2; j++) {
			Query query2 = getSession()
					.createQuery("select count(*) from _17_Group_UserVO where user_amount IS NOT NULL and group_no=?");
			query2.setParameter(0, group_no[j]);
			Long group_user_count = (Long) query2.list().get(0);
			result[j] = group_user_count;
		}
		System.out.println(result[0]);
		return result;
	}

	// ...................................................................................................//

	// public Long[] countAmountOfProduct(Integer group_user_id){
	// Integer[] group_no = new Integer[10];
	// Integer[] group__user_no = new Integer[10];
	// Long[] result = new Long[10];
	// List list2 = null;
	// Query query = getSession().createQuery("select group_RecordVO from
	// _17_Group_UserVO where group_user_id=?");
	// query.setParameter(0, group_user_id);
	// List<_16_Group_RecordVO> list = query.list();
	// int i =0;
	// for (_16_Group_RecordVO Group_Record : list) {
	// group_no[i] = Group_Record.getGroup_no();
	// i++;
	// }
	//
	// for(int j=0; j<=2; j++){
	// System.out.println("group_no" + group_no[j]);
	// Query query2 = getSession().createQuery("select group_user_no from
	// _17_Group_UserVO where user_amount IS NOT NULL and group_no=?");
	// query2.setParameter(0, group_no[j]);
	// list2 = query2.list();
	// System.out.println(list2);
	// }
	// System.out.println(list2);
	// for(int k=0; k<=0; k++){
	// Query query3 = getSession().createQuery("select group_user_no from
	// _17_Group_UserVO where user_amount IS NOT NULL and group_no=?");
	// query3.setParameter(0, list2[k]);
	// List list3 = query3.list();
	// System.out.println(list3);
	// }

	// System.out.println(result[0]);
	// return result;
	//
	// }

	// ...................................................................................................//

	// public Long[] countAmountOfProduct(Integer group_no){
	// Long[] result = new Long[10];
	// Query query = getSession().createQuery("select group_user_no from
	// _17_Group_UserVO where user_amount IS NOT NULL and group_no=?");
	// query.setParameter(0, group_no);
	// List<Object[]> list = query.list();
	//// System.out.println(list.get(1));
	// for(int i=0; i<=3; i++){
	// Query query2 = getSession().createQuery("select sum(quantity) from
	// _18_Order_DetailVO where group_userVO=?");
	// query2.setParameter(0, list.get(i));
	// List<Object[]> list2 = query2.list();
	// System.out.println(list2);
	// }
	//
	// return result;
	// }

}
