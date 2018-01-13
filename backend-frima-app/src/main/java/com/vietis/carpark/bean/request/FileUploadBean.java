package com.vietis.carpark.bean.request;
public class FileUploadBean {
	private String content;
	private String extension;
	private String folder;
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getContent() {
		return content;
	}

	public String getExtension() {
		return extension;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

}
