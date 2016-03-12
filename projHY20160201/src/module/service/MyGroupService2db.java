package module.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import module.dao._04_EmployeeDAO;
import module.dao._07_StoreDAO;
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
				_16_Group_RecordDAO grdao_test = new _16_Group_RecordDAO();
//				mg.updateEndTime(1, "2016-03-16T01:00");
//				mg.deleteOrder(9);
//				mg.deleteOrder(9);
				mg.copyGroup(5, 1, "T3", "測試3", "2133-11-11T01:00");
		
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
	
	/*-------------複製團購----------------------------------------------------------------*/
	public void copyGroup(Integer user_id, Integer group_no,String name_new, String ann_new, String enddate2){
		_04_EmployeeDAO emp = new _04_EmployeeDAO();
		_07_StoreDAO storevo = new _07_StoreDAO();
		Set<_17_Group_UserVO> _17VOset=new HashSet<>();
		
		_16_Group_RecordVO vo_old = grdao.findById(group_no);//原團購
		_16_Group_RecordVO vo_new = new _16_Group_RecordVO();//新團購
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		java.util.Date date = null;
		try {
			date = format.parse(enddate2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Set<_04_EmployeeVO> newEmpVO=new HashSet<>();
		for(_17_Group_UserVO a:vo_old.getGroup_Users()){
			_17_Group_UserVO new17VO=new _17_Group_UserVO();
			if(!a.getCo_holder().equals("A")){//本來不是主揪
				if(a.getEmployeeVO()!=vo_old.getEmployeeVO()){//本來不是主揪，現在也不是主揪
					new17VO.setGroup_RecordVO(vo_new);
					new17VO.setEmployeeVO(a.getEmployeeVO());
					new17VO.setGroup_user_name(a.getGroup_user_name());
					new17VO.setCo_holder(a.getCo_holder());	
				}else{//本來不是主揪，現在是主揪
					new17VO.setGroup_RecordVO(vo_new);
					new17VO.setEmployeeVO(a.getEmployeeVO());
					new17VO.setGroup_user_name(a.getGroup_user_name());
					new17VO.setCo_holder("A");
				}							
			}else {//本來是主揪				
				if(a.getEmployeeVO()!=vo_old.getEmployeeVO()){//本來是主糾，現在不是是主揪
					new17VO.setGroup_RecordVO(vo_new);
					new17VO.setEmployeeVO(a.getEmployeeVO());
					new17VO.setGroup_user_name(a.getGroup_user_name());
					new17VO.setCo_holder("B");
				}else{//本來是主糾，現在又是主揪
					new17VO.setGroup_RecordVO(vo_new);
					new17VO.setEmployeeVO(a.getEmployeeVO());
					new17VO.setGroup_user_name(a.getGroup_user_name());
					new17VO.setCo_holder("A");
				}				
			}
			_17VOset.add(new17VO);
		}		
		
		vo_new.setEmployeeVO(emp.findById(user_id));//主糾
		vo_new.setGroup_name(name_new);
		vo_new.setStoreVO(storevo.findById(vo_old.getStoreVO().getStore_no()));
		vo_new.setStart_date(new java.util.Date());
		vo_new.setEnd_date(date);
		vo_new.setAnn(ann_new);
		vo_new.setShipment(vo_old.getShipment());
		vo_new.setGroup_Users(_17VOset);
		grdao.insert(vo_new);
	}
	/*-------------複製團購----------------------------------------------------------------*/
	public void editGroup( Integer group_no,String name_new2, String ann_new2, String ship){
		_16_Group_RecordVO grvo = grdao.findById(group_no);
		if(name_new2!=null){
			grvo.setGroup_name(name_new2);
		}
		if(ann_new2!=null){
			grvo.setAnn(ann_new2);
		}
		if(ship!=null){
			grvo.setShipment(ship);
		}		
		grdao.update(grvo);
	}

}
