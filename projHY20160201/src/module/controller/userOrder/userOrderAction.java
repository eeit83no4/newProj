package module.controller.userOrder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import module.dao._12_ItemDAO;
import module.dao._17_Group_UserDAO;
import module.model._17_Group_UserVO;
import module.model._18_Order_DetailVO;
import module.service.attempGroupService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

public class userOrderAction extends ActionSupport{
	private static _17_Group_UserDAO _17guDAO=new _17_Group_UserDAO();
	private static final long serialVersionUID = 1L;
	private String jsonString;	
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	@Override
	public String execute() throws Exception {
		attempGroupService att=new attempGroupService();
		List<_18_Order_DetailVO> beans=new ArrayList<>();
		JSONSerializer jSONSerializer=new JSONSerializer();
		Object b=null;
		//將data轉換Object
		try {
			b=jSONSerializer.toJSON(jsonString);
		} catch (Exception e) {			
			System.out.println("jSONSerializer error="+e.toString());
		}
		//將Object轉換成JsonArray格式
		JSONArray jSONArray=JSONArray.fromObject(b);

		for(int i=0;i<jSONArray.size();i++){
			//將JsonObject轉換成JsonArray格式
			JSONObject net=(JSONObject)jSONArray.get(i);
			//group_user_no
			Object group_user_noobject=net.get("group_user_no");
			String group_user_nostring=String.valueOf(group_user_noobject);
			Integer group_user_noInt=Integer.parseInt(group_user_nostring);
			_17_Group_UserVO uservo=_17guDAO.findById(group_user_noInt);
			//ostore_name
			String ostore_name=String.valueOf(net.get("ostore_name"));
			//oprice_no
			int oprice_no=0;
			try {
				oprice_no=((Number)net.get("oprice_no")).intValue();
			} catch (Exception e2) {
				System.out.println("userOrderAction-oprice_no轉型失敗="+e2.toString());				
			}
			//item_name			
			String oitem_namestring=String.valueOf(net.get("oitem_name"));
			//originitemprice			
			double originitempricedouble=0.0;
			try {
				originitempricedouble=((Number)net.get("originitemprice")).doubleValue();
			} catch (Exception e1) {
				System.out.println("userOrderAction-originitempricedouble轉型失敗="+e1.toString());				
			}
			//解析oprice					
			double opricedouble=0.0;
			try {
				opricedouble = ((Number)net.get("oprice")).doubleValue();
			} catch (Exception e) {	
				System.out.println("userOrderAction-opricedouble轉型失敗="+e.toString());				
			}
			//解析oprice_after						
			double oprice_afterdouble=0.0;
			try {
				oprice_afterdouble = ((Number)net.get("oprice_after")).doubleValue();
			} catch (Exception e) {
				System.out.println("userOrderAction-oprice_afterdouble轉型失敗="+e.toString());				
			}
			//oclass			
			String oclassstring=String.valueOf(net.get("oclass"));
			//ps
			String psstring=null;
			try {
				psstring=String.valueOf(net.get("ps"));
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			//解析quantity					
			Integer quantityinteger=0;			
			try {
				quantityinteger = ((Number)net.get("quantity")).intValue();
			} catch (Exception e) {
				System.out.println("userOrderAction-quantityinteger轉型失敗="+e.toString());				
			}
			//↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓解析pic↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
			Object pic=net.get("pic");
			byte[] itempic=null;
			int picno=0;//圖片編號
			try {
				picno=((Number)pic).intValue();
			} catch (Exception e) {
				System.out.println("userOrderAction-picno轉型失敗="+e.toString());				
			}
			if(picno>0){
				_12_ItemDAO itemDao=new _12_ItemDAO();				
				itempic=itemDao.findById(picno).getPic();								
			}				
			//↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑圖片處理完畢↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
			
			_18_Order_DetailVO beanvo=new _18_Order_DetailVO();
			beanvo.setGroup_userVO(uservo);
			beanvo.setOstore_name(ostore_name);
			beanvo.setOprice_no(oprice_no);
			beanvo.setOitem_name(oitem_namestring);
			if(itempic!=null){
				beanvo.setOimage(itempic);
			}			
			beanvo.setOriginal_oprice(originitempricedouble);
			beanvo.setOprice(opricedouble);
			beanvo.setOprice_after(oprice_afterdouble);
			beanvo.setOclass(oclassstring);
			beanvo.setPs(psstring);
			beanvo.setQuantity(quantityinteger);
			beans.add(beanvo);
		}
		att.ordering(beans);
		
		
		return Action.NONE;
	}

	
	
	
	
}
