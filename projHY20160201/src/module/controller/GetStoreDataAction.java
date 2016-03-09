package module.controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import module.service.GetStoreDataService;

public class GetStoreDataAction extends ActionSupport {	
	private String sub;	
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
	//接收資料
	private String phone;
	private String address;
	private String store;
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
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	
	
	//資料驗證
	@Override
	public void validate() {
		System.out.println(store);
		System.out.println(phone);
		System.out.println(address);
//		if(store==null || store.length()==0){
//		//String aa = this.getText("nullname");
//		this.addFieldError("store", "必填");
//	}
//	if(phone==null || phone.trim().length()==0) {
//		this.addFieldError("phone", this.getText("phoneError"));
//	}
//	if(address==null || address.trim().length()==0) {
//		this.addFieldError("address", this.getText("addressError"));
//	}	
	}
	
	private GetStoreDataService getStoreDataService=new GetStoreDataService();
	@Override
	public String execute() throws Exception {
		
//		if(!sub.equals("新增")){
//			data = getStoreDataService.getStoreData(Integer.parseInt(sub));
//		}else{
			data = getStoreDataService.getStoreClassAll();
//		}		
		
//		System.out.println(data);
		
		String storeClassAll = getStoreDataService.storeClassAll();
		System.out.println(storeClassAll);
//		return INPUT;
		return SUCCESS;
	}
}
