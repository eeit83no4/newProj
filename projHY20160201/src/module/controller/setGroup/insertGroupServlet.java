package module.controller.setGroup;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.dao._04_EmployeeDAO;
import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.model._16_Group_RecordVO;
import module.model._17_Group_UserVO;
import module.service.SetGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;


@WebServlet("/insertGroupServlet.controller")
public class insertGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		_04_EmployeeDAO _04DAO=new _04_EmployeeDAO();
		String jsonString = req.getParameter("jsonString"); 
		SetGroupService setGroupService = new SetGroupService();
		JSONSerializer jSONSerializer=new JSONSerializer();
		Object b=null;
		//將data轉換Object
		try {
			b=jSONSerializer.toJSON(jsonString);
		} catch (Exception e) {			
			System.out.println("jSONSerializer error="+e.toString());
		}
		JSONArray jSONArray=JSONArray.fromObject(b);

		for(int i=0;i<jSONArray.size();i++){
			//將JsonObject轉換成JsonArray格式
			JSONObject gup=(JSONObject)jSONArray.get(i);
			//小幫手
			String adminid =String.valueOf(gup.get("admin_id"));
			String[] adminidArray=adminid.split(",");
			Integer[] adminidIntegerArray=new Integer[adminidArray.length];
			int k=0;
			for(String a:adminidArray){
				adminidIntegerArray[k]=Integer.parseInt(a);
				k++;
			}
			
			//被邀請
			Object user_Ids =gup.get("user_Ids");
			String user_idstring=String.valueOf(user_Ids);
			String[] useridArray=user_idstring.split(",");
			Integer[] useridIntegerArray=new Integer[useridArray.length];
			int j=0;
			for(String a:useridArray){				
				useridIntegerArray[j]=Integer.parseInt(a);
				System.out.println(useridIntegerArray[j]);				
				j++;				
			}	
					
			
//			Integer store_no = Integer.valueOf(req.getParameter("newstoreno")).intValue(); 
//			System.out.println("aaaaaaaaaaaabbbbbb"+store_no);
			//團購名字
			String groupna =String.valueOf(gup.get("groupna"));
			//公告
			String ann =String.valueOf(gup.get("ann"));

			//截止時間
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
			String enddate =String.valueOf(gup.get("enddate"));
			java.util.Date endTime=null;
			try {
				endTime=format.parse(enddate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			//開始新增
			_16_Group_RecordVO bean = new _16_Group_RecordVO();
			
			_04_EmployeeVO _04bb=new _04_EmployeeVO();
			
			_07_StoreVO cc=new _07_StoreVO();
			//for _16_Group_RecordVO
			//建立者
			int creater=1;
			_04bb.setUser_id(creater);
			bean.setEmployeeVO(_04bb);
			//團購名字
			bean.setGroup_name(groupna);
			//參考店家
			cc.setStore_no(1);
			bean.setStoreVO(cc);
			//開始時間
			bean.setStart_date(new java.util.Date());
			//截止時間
			bean.setEnd_date(endTime);
			//公告
			bean.setAnn(ann);
			//剛新增的流水號
			Integer	pp=setGroupService.insertGroup(bean);
			_16_Group_RecordVO newbean = new _16_Group_RecordVO();
			newbean.setGroup_no(pp);
			System.out.println(pp);
			//for 主揪_17_Group_RecordVO
			_17_Group_UserVO _17gg = new _17_Group_UserVO();
			_17gg.setGroup_RecordVO(newbean);
			_17gg.setEmployeeVO(_04bb);
			_17gg.setCo_holder("A");
			_17gg.setGroup_user_name(_04DAO.findById(1).getName());
			setGroupService.insertGroup_user(_17gg);
			//for _17_Group_RecordVO
			for(Integer a:useridIntegerArray){
				int h=0;
				_17_Group_UserVO _17aa = new _17_Group_UserVO();
				//group_no
				_17aa.setGroup_RecordVO(newbean);
				//group_user_id
				_04bb.setUser_id(a);
				_17aa.setEmployeeVO(_04bb);
				//group_user_name
				_04DAO.findById(a).getName();				
				_17aa.setGroup_user_name(_04DAO.findById(a).getName());
				//CO_HOLDER
				for(Integer ad:adminidIntegerArray){
					if(a==ad){
						h=1;
					}				
				 }
				 if(h==1){
					 _17aa.setCo_holder("B");					 
				 }							
				setGroupService.insertGroup_user(_17aa);
			 }
			
			
			
			
			
		}
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(req, resp);
	}




}
