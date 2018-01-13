package com.vietis.carpark.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.vietis.carpark.common.util.CommonUtil;

@Service("FTPService")
@PropertySource(value = { "classpath:application.properties" })
public class FTPServiceImpl implements FTPService {
	private static final Logger logger = Logger.getLogger(FTPServiceImpl.class);
	@Autowired
	private Environment environment;

	/**
	 * @return login FTP server
	 * @throws IOException
	 */
	@Override
	public FTPClient loginFTP() throws IOException {
		FTPClient ftp = new FTPClient();
		String host = environment.getProperty("ftp.host");
		String userName = environment.getProperty("ftp.username");
		String pass = environment.getProperty("ftp.password");
		// try to connect
		ftp.connect(host, Integer.parseInt(environment.getProperty("ftp.port")));
		// login to server
		if (!ftp.login(userName, pass)) {
			ftp.logout();
			return null;
		}
		int reply = ftp.getReplyCode();
		// FTPReply stores a set of constants for FTP reply codes.
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return null;
		}
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		return ftp;
	}

	/**
	 * @param copiedId
	 * @param destId
	 * @throws Exception
	 * @return download and upload file to FTP
	 */
	public void downLoadAndUploadFileFtp(Integer copiedId, Integer destId) throws Exception {
		FTPClient ftp = null;
		try {
			// login FTP
			ftp = loginFTP();
			if (ftp == null) {
				return;
			}
			String ftpPath = environment.getProperty("ftp.path");
			ftp.changeWorkingDirectory(ftpPath);
			String imageId = fileExistsInFTP(copiedId, ftp);
			if (CommonUtil.isEmpty(imageId)) {
				return;
			}
			File file = new File("image." + imageId.split("\\.")[1]);
			OutputStream outputStream = new FileOutputStream(file);
			ftp.retrieveFile(imageId, outputStream);
			InputStream inputStream = new FileInputStream(file);
			ftp.storeFile(ftpPath + "/" + destId + "." + imageId.split("\\.")[1], inputStream);
			outputStream.close();
			inputStream.close();
			logout(ftp);
		} catch (Exception ex) {
			logger.error("", ex);
			throw new Exception(ex);
		} finally {
			if (ftp != null) {
				try {
					logout(ftp);
				} catch (IOException e) {
					logger.error("", e);
					throw new Exception(e);
				}
			}

		}
	}

	/**
	 * @param fileName
	 * @param ftpClient
	 * @return check file exists in FTP
	 */
	private String fileExistsInFTP(Integer fileName, FTPClient ftpClient) throws IOException {
		String[] files = ftpClient.listNames();
		for (String file : files) {
			String[] value = file.split("\\.");
			if (fileName.toString().equals(value[0])) {
				return file;
			}
		}
		return null;
	}

	/**
	 * @param file
	 * @param fileName
	 * @param folderPath
	 * @throws Exception
	 * @return upload file to FTP
	 */
	@Override
	public String uploadFileToFtp(File file, String fileName, String folderPath) throws Exception {
		FTPClient ftp = null;
		try {
			ftp = loginFTP();
			if (ftp != null) {
				String ftpPath = environment.getProperty("ftp.path") + "/" + folderPath;
				boolean isExist = ftp.changeWorkingDirectory(ftpPath);
				if (!isExist) {
					// create directory at here
					ftp.makeDirectory(ftpPath);
					boolean maked = ftp.changeWorkingDirectory(ftpPath);
				}
				InputStream inputStream = new FileInputStream(file);
				boolean storeFile = ftp.storeFile(fileName, inputStream);
				inputStream.close();
				String url = environment.getProperty("ftp.http") + folderPath + "/" + fileName;
				if (storeFile)
					return url;
				else
					return "";
			}
		} catch (Exception e) {
			logger.error("", e);
			throw new Exception(e);
		} finally {
			if (ftp != null) {
				try {
					logout(ftp);
				} catch (IOException e) {
					logger.error("", e);
					throw new Exception(e);
				}
			}
		}

		return null;

	}

	/**
	 * Logs out and disconnects from the server
	 */
	private void logout(FTPClient ftpClient) throws IOException {
		if (ftpClient != null && ftpClient.isConnected()) {
			ftpClient.logout();
			ftpClient.disconnect();
		}
	}

	/**
	 * create folder in ftp server
	 * 
	 * @throws IOException
	 */
	@Override
	public void createFolder(String folderName) throws IOException {
		FTPClient ftp = null;
		try {
			ftp = loginFTP();
			String ftpPath = "";
			if (ftp != null) {
				String dirToCreate = ftpPath + "/" + folderName;
				ftp.makeDirectory(dirToCreate);
				logout(ftp);
			}
			return;
		} catch (IOException e) {
			logger.error("", e);
			throw new IOException(e);
		}

	}
}
