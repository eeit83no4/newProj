package module.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.model._17_Group_UserVO;
import module.model._18_Order_DetailVO;
import module.util.HibernateUtil;
import net.sf.json.JSONObject;

public class _18_Order_DetailDAO implements _18_Order_Detail_InterfaceDAO {

	private SessionFactory sessionFactory;

	public _18_Order_DetailDAO() {
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

			_18_Order_Detail_InterfaceDAO dao = new _18_Order_DetailDAO();
			_18_Order_DetailVO bean = new _18_Order_DetailVO();
//			System.out.println(dao.getOdNo(1));
			// ---------------------
			// System.out.println(dao.getAll());
			// ----------------------------
			// System.out.println(dao.findById(2));
			// ---------------------
			// dao.delete(2);

			// -------------------------
			// bean.setDetail_no(3);
			// _17_Group_UserVO first = new _17_Group_UserVO();
			// first.setGroup_user_no(1);
			// bean.setGroup_userVO(first);
			// bean.setOstore_name("茶店");
			// bean.setOprice_no(999);
			// dao.update(bean);

			// ------------------------

			// _17_Group_UserVO first = new _17_Group_UserVO();
			// first.setGroup_user_no(1);
			// bean.setGroup_userVO(first);
			// bean.setOstore_name("綠茶店");
			// dao.insert(bean);
//			System.out.println(dao.getDetailNo(2));

			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}

	@Override
	public void insert(_18_Order_DetailVO order_DetailVO) {
		this.getSession().save(order_DetailVO);
	}

	@Override
	public void update(_18_Order_DetailVO order_DetailVO) {
		getSession().update(order_DetailVO);
	}

	@Override
	public void delete(Integer detail_no) {
		_18_Order_DetailVO order_DetailVO = new _18_Order_DetailVO();
		order_DetailVO.setDetail_no(detail_no);
		getSession().delete(order_DetailVO);
	}

	@Override
	public _18_Order_DetailVO findById(Integer detail_no) {
		return (_18_Order_DetailVO) getSession().get(_18_Order_DetailVO.class, detail_no);
	}

	@Override
	public List<_18_Order_DetailVO> getAll() {
		return getSession().createQuery("from _18_Order_DetailVO").list();
	}
	
	@Override
	public List<_18_Order_DetailVO> getDetailNo(Integer group_user_no) {//用group_user_no抓出detail_no //按人統計付款狀態用
		return getSession().createQuery("from _18_Order_DetailVO where group_user_no="+group_user_no).list();
	}
	

	@Override
	public JSONObject getOdNo(int group_user_no) {//create by Gimmy Kitty
		Query query = getSession()
				.createQuery("select detail_no,oitem_name,quantity,oprice,oprice_after,oclass,pay_status from _18_Order_DetailVO where group_user_no=" + group_user_no);
		List<Object[]> list =query.list();
		JSONObject GU= new JSONObject();
		List<String> ls ;
			for(Object[] a:list){
				ls =new ArrayList<String>();
				ls.add(a[1].toString());
				ls.add(a[2].toString());
				ls.add(a[3].toString());
				ls.add(a[4].toString());
				ls.add(a[5].toString());
				ls.add(a[6].toString());
				GU.put(a[0].toString(), ls.toString());
			}
//		for (int i=0;i<list.size();i++){
//			GU.put(1, list.toArray());	
//		}
		return GU;
	}
}
