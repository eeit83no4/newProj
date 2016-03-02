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
	@Override
	public void validate() {
//		System.out.println("aaa");
//		System.out.println(sub);
		
	}
	
	private GetStoreDataService getStoreDataService=new GetStoreDataService();
	@Override
	public String execute() throws Exception {
		
		if(!sub.equals("新增")){
			data = getStoreDataService.getStoreData(Integer.parseInt(sub));
		}else{
			data = getStoreDataService.getStoreClassAll();
		}		
		
		System.out.println(data);
		
		String storeClassAll = getStoreDataService.storeClassAll();
		System.out.println(storeClassAll);
//		return INPUT;
		return SUCCESS;
	}
}
