package co.siten.myvtg.bean;

public class MessageBean {
	private String code;
	private String content;
	private Object data;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MessageBean(String code, String content) {
		super();
		this.code = code;
		this.content = content;
	}
	
	public MessageBean(String code, String content, Object data) {
		super();
		this.code = code;
		this.content = content;
		this.data= data;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
