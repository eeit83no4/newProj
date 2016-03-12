package module.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.model._01_OrganizationVO;
import module.model._02_Employee_StatusVO;
import module.model._03_JobVO;
import module.model._04_EmployeeVO;
import module.util.HibernateUtil;

public class _04_EmployeeDAO implements _04_Employee_interfaceDAO  {
	private SessionFactory sessionFactory;
	public _04_EmployeeDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	public Session getSession() {
		if(sessionFactory!=null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	
	
	//動態查詢
	@Override
	public _04_EmployeeVO findActive(String key,String value){
        StringBuilder hql=new StringBuilder("from _04_EmployeeVO");
        if(key=="email"){        	
        	hql.append(" where email=?");
        }else if(key=="status"){
        	hql.append(" where status=?");
        }
        _04_EmployeeVO ss = (_04_EmployeeVO)getSession().createQuery(hql.toString()).setParameter(0, value).list().get(0);
        return ss;
    }
	
	
	
	@Override
	public _04_EmployeeVO findById(Integer user_id) {
		return (_04_EmployeeVO) getSession().get(_04_EmployeeVO.class, user_id);
	}
	
	@Override
	public List<_04_EmployeeVO> getAll() {
		return getSession().createQuery("from _04_EmployeeVO").list();
	}
	@Override
	public List<String> getAllEmails() {
		return getSession().createQuery("select email from _04_EmployeeVO").list();
		
	}
	@Override
	public void insert(_04_EmployeeVO employeeVO){
		getSession().save(employeeVO);			
	}
	@Override
	public void update(List<_04_EmployeeVO> finbydep){
		getSession().update(finbydep);
	}
	
	@Override
	public void delete(Integer user_id){
		_04_EmployeeVO employeeVO = new _04_EmployeeVO();
		employeeVO.setUser_id(user_id);
		getSession().delete(employeeVO);
	}

	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			_04_EmployeeDAO dao=new _04_EmployeeDAO();
			_04_EmployeeVO bean=new _04_EmployeeVO();
			System.out.println(dao.getAll());
//			System.out.println(dao.findById(1));
//			System.out.println(dao.findActive("email", "angela.wang@hy-tech.com.tw"));
//			System.out.println(dao.getAllEmails());
			//Insert
//		    bean.setUser_id(998);		    
//		    bean.setC_name("JY");
//		    bean.setName("JIMMY");
//		    bean.setComp_id("CO99999999");
//		    bean.setEmail("xxx@xxx.com");
//		    bean.setTitle("專案經理");
//		    bean.setLevel(2);
//		    _01_OrganizationVO ovo=new _01_OrganizationVO();
//		    ovo.setOrg_id("DEP0001");
//		    bean.setOrganizationVO(ovo);
//		    _02_Employee_StatusVO svo=new _02_Employee_StatusVO();
//		    svo.setStatus_no(1);
//		    bean.setEmployee_StatusVO(svo);
//		    _03_JobVO jvo=new _03_JobVO();
//		    jvo.setJob_no(1);
//		    bean.setJobVO(jvo);   
//		    dao.insert(bean);
			
			//Update
//			bean.setUser_id(998);
//			bean.setName("KITTY");
//			_01_OrganizationVO ovo=new _01_OrganizationVO();
//			ovo.setOrg_id("DEP0002");
//			bean.setOrganizationVO(ovo);
//			bean.setLevel(1);
//			dao.update(bean);
			
			//Delete
//			dao.delete(998);
			//select by email
//			System.out.println(dao.findByEmail("angela.wang@hy-tech.com.tw").get(0).getUser_id());
			
			
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}
	}
public List<String[]> getAllEmp() {
		
		Query query= getSession().createQuery("select user_id,name from _04_EmployeeVO");
		List<Object[]> list =query.list();

		List<String[]> lis=new ArrayList<>() ;
			for(Object[] a:list){
				String[] k=new String[2];
				k[0]=String.valueOf(a[0]);
				k[1]=String.valueOf(a[1]);
				lis.add(k);
			}
			return lis;
	}
	String FIND_BY_DEP="select user_id,name from _04_EmployeeVO where dep_id=?";
	public List<String[]> findByDep(String dep) {
//		return getSession().createQuery("select name from _04_EmployeeVO where dep_id=?" + dep_id).list();
		List<String[]> list= null;
		Query query = getSession().createQuery(FIND_BY_DEP);
		query.setParameter(0, dep);
		list = query.list();
		List<String[]> lis=new ArrayList<>() ;
		for(Object[] a:list){
			String[] k=new String[2];
			k[0]=String.valueOf(a[0]);
			k[1]=String.valueOf(a[1]);
			lis.add(k);
		}
		return lis;
		
		
		
	}
	public List getAllDep() {
		return getSession().createQuery("select org_id from _01_OrganizationVO").list();
		
	}
	@Override
	public void update(_04_EmployeeVO employeeVO) {
		// TODO Auto-generated method stub
		
	}
	
}
