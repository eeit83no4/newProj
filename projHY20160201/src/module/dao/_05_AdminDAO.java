package module.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.model._04_EmployeeVO;
import module.model._05_AdminVO;
import module.util.HibernateUtil;

public class _05_AdminDAO implements _05_Admin_InterfaceDAO {
	private SessionFactory sessionFactory;
	public _05_AdminDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if(sessionFactory!=null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	@Override
	public _05_AdminVO findById(Integer admin_no) {
		return (_05_AdminVO) getSession().get(_05_AdminVO.class, admin_no);
	}
	@Override
	public List<_05_AdminVO> getAll() {
		return getSession().createQuery("from _05_AdminVO").list();
	}
	@Override
	public void insert(_05_AdminVO adminVO){
		getSession().save(adminVO);	
	}
	@Override
	public void update(_05_AdminVO adminVO){
		getSession().update(adminVO);
	}
	@Override
	public void delete(Integer admin_no){
		_05_AdminVO adminVO = new _05_AdminVO();
		adminVO.setAdmin_no(admin_no);
		getSession().delete(adminVO);
	}
	
	public List<_05_AdminVO> findAuthByUserId(Integer user_id){
		Query query = getSession().createQuery("select auth from _05_AdminVO where user_id=?");
		query.setParameter(0, user_id);
		return query.list();		
	}

	public void updateAuthByUserId(String user_id,String auth){
		Query query = getSession().createQuery("update _05_AdminVO set auth=? where user_id=?");
		query.setParameter(0, auth);
		query.setParameter(1, user_id);
		query.executeUpdate();	
	}
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			_05_AdminDAO dao=new _05_AdminDAO();
			_05_AdminVO bean=new _05_AdminVO();
			
//			dao.updateAuthByUserId("1","A");
			
//			System.out.println(dao.findAuthByUserId(166));
//			System.out.println(dao.findById(1));
//			System.out.println(dao.getAll());
			
			//Insert
//			bean.setAuth("c");
//			_04_EmployeeVO evo=new _04_EmployeeVO();
//			evo.setUser_id(1);
//			bean.setEmployeeVO(evo);
//			dao.insert(bean);
			//Update
//			_04_EmployeeVO evo=new _04_EmployeeVO();
//			evo.setUser_id(1);
//			bean.setEmployeeVO(evo);
//			bean.setAdmin_no(6);
//			bean.setAuth("d");
//			dao.update(bean);
			//Delete
//			dao.delete(1);
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}	
	}

}
