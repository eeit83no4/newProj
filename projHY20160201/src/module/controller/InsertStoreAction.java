package module.controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import module.model._04_EmployeeVO;
import module.model._07_StoreVO;
import module.model._12_ItemVO;
import module.service.GetStoreClass;
import module.service.GetStoreDataService;
import module.service._07_StoreService;

public class InsertStoreAction extends ActionSupport implements RequestAware,SessionAware {
	
	private String store;
	private String storeClass;
	private String phone;
	private String address;	
	private String sub;			
	private String attributes;	
	private String item;
	private String submit;	
	private Map<String, String> data;	
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getStoreClass() {
		return storeClass;
	}
	public void setStoreClass(String storeClass) {
		this.storeClass = storeClass;
	}	
	public String getClass1() {
		return phone;
	}
	public void setClass1(String class1) {
		this.phone = class1;
	}
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;		
	}
	private Map<String, Object> session;
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
		
	}
	
	public void validate() {
//		System.out.println(store);
//		System.out.println(phone);
//		System.out.println(address);
//		if(store==null || store.length()==0){
//			String aa = this.getText("nullname");
//			this.addFieldError("store", "必填");
//		}
//		if(phone==null || phone.trim().length()==0) {
//			this.addFieldError("phone", this.getText("phoneError"));
//		}
//		if(address==null || address.trim().length()==0) {
//			this.addFieldError("address", this.getText("addressError"));
//		}	
	}
	
	private GetStoreDataService getStoreDataService=new GetStoreDataService();
	@Override
	public String execute() throws Exception {
		
		_07_StoreVO bean=new _07_StoreVO();
		bean.setStore_name(store);
		bean.setPhone(phone);
		bean.setAddress(address);
		bean.setFinal_update(new java.util.Date());	
		//參考員工
		_04_EmployeeVO bean4=(_04_EmployeeVO)session.get("LoginOK");
//		_04_EmployeeVO bean4=new _04_EmployeeVO();
//		bean4.setUser_id(166);
		bean.setEmployeeVO(bean4);
		System.out.println(sub);
		_07_StoreVO bean7 = new _07_StoreVO();
		boolean flag = true;
		System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		if(request.get("StoreNo")!=null){
			sub = request.get("StoreNo") + "";
		}
//		System.out.println(request.get("StoreNo"));
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		if(sub==null){
			flag = false;
		_07_StoreService storeService=new _07_StoreService();
		Integer StoreNo = storeService.insertStore(bean, storeClass.replace(" ", ""));
//		System.out.println(StoreNo);
		request.put("StoreNo", StoreNo);		
		bean7.setStore_no(StoreNo);
				data = getStoreDataService.getStoreData(StoreNo);
				System.out.println(request.get("StoreNo"));
				System.out.println(request.get("StoreNo"));
				System.out.println(request.get("StoreNo"));
			}else{
			request.put("StoreNo", sub);			
			bean7.setStore_no(Integer.parseInt(sub.trim()));
				data = getStoreDataService.getStoreData(Integer.parseInt(sub.trim()));
			}	
		System.out.println(request.get("StoreNo"));
		GetStoreClass getStoreClass = new GetStoreClass();  
//		List<_12_ItemVO> getItemAll = getStoreClass.get07IsItemAll(bean7);
//		System.out.println(getItemAll);		
		 Object getItemAll = getStoreClass.get07IsItemAllJson(bean7);
//		System.out.println(getItemAll);		
		request.put("getItemAll", getItemAll);
		
		 String storeClassAll = getStoreDataService.storeClassAll();
//		 System.out.println(submit);
//		 System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
		 if(flag){
			 return "success";			 
		 }else{
			 System.out.println("有重載");
			 System.out.println("有重載");
			 System.out.println("有重載");
			 System.out.println("有重載");
			 System.out.println("有重載");
			 return "replace";	
		 }
	}
	
	
}
