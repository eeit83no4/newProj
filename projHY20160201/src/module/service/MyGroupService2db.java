package module.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._04_EmployeeDAO;
import module.dao._16_Group_RecordDAO;
import module.dao._17_Group_UserDAO;
import module.dao._18_Order_DetailDAO;
import module.model._04_EmployeeVO;
import module.model._16_Group_RecordVO;
import module.model._17_Group_UserVO;
import module.model._18_Order_DetailVO;
import module.util.HibernateUtil;

public class MyGroupService2db {
	private static _16_Group_RecordDAO _16grDAO=new _16_Group_RecordDAO();
	private static _17_Group_UserDAO _17guDAO=new _17_Group_UserDAO();
	private static _18_Order_DetailDAO _18detailDAO=new _18_Order_DetailDAO();
	
//	private SessionFactory sessionFactory;
//	public MyGroupService2db() {
//		sessionFactory = HibernateUtil.getSessionFactory();
//	}
//	public Session getSession() {
//		if (sessionFactory != null) {
//			return sessionFactory.getCurrentSession();
//		}
//		return null;
//	}
	
	public static void main(String[] args) {
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
				MyGroupService2db mg = new MyGroupService2db();
//				mg.updateEndTime(1, "2016-03-16T01:00");
//				mg.deleteOrder(9);
//				mg.deleteOrder(9);
				mg.copyGroup(1, "AAAAAAAAA", "拜偷偷偷", "2222-05-16T01:00");

		
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
		} finally {
			HibernateUtil.closeSessionFactory();
		}

	}
	
	_16_Group_RecordDAO grdao = new _16_Group_RecordDAO();
//	_16_Group_RecordVO grvo = new _16_Group_RecordVO();
	_18_Order_DetailDAO oddao = new _18_Order_DetailDAO();
	
	public void updateGroupStatus_success(Integer group_no){
		_16_Group_RecordVO a = grdao.findById(group_no);
		a.setStatus("成功");
		
		grdao.update(a);
		
	}
	
	public void updateGroupStatus_failed(Integer group_no){
		_16_Group_RecordVO a = grdao.findById(group_no);
		a.setStatus("失敗");
		
		grdao.update(a);
		
	}
	
	public void updateGroupEndDate(Integer group_no){
		_16_Group_RecordVO a = grdao.findById(group_no);
		a.setEnd_date(new java.util.Date());
		
		grdao.update(a);
		
	}
	
	/*-------------修改團購狀態、失敗原因-----------------------------------------------------------------*/
	public void updateForFail(Integer group_no,String failure){
		_16_Group_RecordDAO grdao = new _16_Group_RecordDAO();
		_16_Group_RecordVO vo = grdao.findById(group_no);
		vo.setStatus("失敗");
		vo.setFailure(failure);
		grdao.update(vo);
	}
	
	/*-------------修改付款狀態(明細列表)-----------------------------------------------------------------*/
	public void updatePayStatus(Integer detail_no,String pay_status){
		_18_Order_DetailDAO oddao = new _18_Order_DetailDAO();
		_18_Order_DetailVO odvo = oddao.findById(detail_no);
		odvo.setPay_status(pay_status);
		oddao.update(odvo);		
	}
	
	/*-------------修改付款狀態(按人統計用)-----------------------------------------------------------------*/
	public void updatePayStatusByUser(Integer group_no,Integer user_id,String pay_status){		
		_17_Group_UserDAO gudao = new _17_Group_UserDAO();
		_18_Order_DetailDAO oddao = new _18_Order_DetailDAO();
		//先從group_user表抓出group_user_no
		Integer group_user_no = gudao.getGroupUserNo(group_no, user_id).get(0).getGroup_user_no(); 																		
		//再從order_detail表抓出detail_no	
		List<_18_Order_DetailVO> vos= oddao.getDetailNo(group_user_no);		
		for(_18_Order_DetailVO vo:vos){
			Integer detail_no = vo.getDetail_no();
			_18_Order_DetailVO odvo = oddao.findById(detail_no);
			odvo.setPay_status(pay_status);
			oddao.update(odvo);	
		}		
	}
	

	/*-------------重設時間-----------------------------------------------------------------*/
	public void updateEndTime(Integer group_no,String enddate){
		_16_Group_RecordDAO grdao = new _16_Group_RecordDAO();
		_16_Group_RecordVO vo = grdao.findById(group_no);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		java.util.Date date = null;
		try {
			date = format.parse(enddate);
			System.out.println("成功");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(date);
		vo.setEnd_date(date);
		grdao.update(vo);
	}

	/*-------------刪除訂購(明細列表)-----------------------------------------------------------------*/
	public void deleteOrder(Integer detail_no){	
		
		oddao.delete(detail_no);

	}
	
	/*-------------重設時間-----------------------------------------------------------------*/
	public void copyGroup(Integer group_no,String name_new, String ann_new, String enddate2){
		_16_Group_RecordDAO grdao = new _16_Group_RecordDAO();
		_16_Group_RecordVO vo = grdao.findById(group_no);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		java.util.Date date = null;
		try {
			date = format.parse(enddate2);
			System.out.println("成功");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int newstoreno =(int) grdao.getSession().getIdentifier(vo);
		
//		vo.setGroup_no(newstoreno);
		vo.setGroup_name(name_new);
		vo.setAnn(ann_new);
		vo.setEnd_date(date);		
		grdao.insert(vo);
	}

}
