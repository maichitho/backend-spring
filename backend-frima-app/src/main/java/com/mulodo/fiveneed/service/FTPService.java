package com.mulodo.fiveneed.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public interface  FTPService {
	
	
	/**
	 * @return login FTP server
 	 * @throws IOException
	 */
	public FTPClient loginFTP() throws IOException;
	
	/**
	 * @param copiedId
	 * @param destId
	 * @throws Exception
	 * @return download and upload file to FTP
	 */
	public void downLoadAndUploadFileFtp(Integer copiedId, Integer destId) throws Exception;
	
	/**
	 * @param file
	 * @param fileName
	 * @param folderPath
	 * @throws Exception
	 * @return upload file to FTP
	 */
	public String uploadFileToFtp(File file , String fileName, String folderPath) throws Exception;
	
	/**
	 * @param folderName
	 * @throws IOException
	 * @return create folder
	 */
	public void createFolder(String folderName) throws IOException;
}
