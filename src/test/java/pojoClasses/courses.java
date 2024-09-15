package pojoClasses;

import java.util.List;

public class courses {
	private List<webautomation> webAutomation;
	private List<Api> api;
	private List<mobile> mobile;
	
	public List<webautomation> getWebAutomation() {
		return webAutomation;
	}
	public void setWebAutomation(List<webautomation> webAutomation) {
		this.webAutomation = webAutomation;
	}
	public List<Api> getApi() {
		return api;
	}
	public void setApi(List<Api> api) {
		this.api = api;
	}
	public List<mobile> getMobile() {
		return mobile;
	}
	public void setMobile(List<mobile> mobile) {
		this.mobile = mobile;
	}
	
	
}
