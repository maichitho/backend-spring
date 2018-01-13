package com.vietis.carpark.common.util;

/**
 * FilenameUtils
 *
 * @author thomc
 *
 */
public class FilenameUtils extends org.apache.commons.io.FilenameUtils {
	/*-----------------------------------------------
	* Public
	*-----------------------------------------------*/
	/**
	 * Concat File Name<br>
	 *
	 * fileName.ext + conStr = fileName_conStr.ext
	 *
	 * @param fileName fileName
	 * @param conStr connect str
	 * @return conected
	 */
	public static String concatFileName(String fileName, String conStr) {
		String path = FilenameUtils.getFullPath(fileName);
		String baseName = FilenameUtils.getBaseName(fileName);
		String ext = FilenameUtils.getExtension(fileName);

		String con = baseName + "_" + conStr + "." + ext;
		if (!StringUtils.isEmpty(path)) {
			con = path + con;
		}
		return con;
	}
}
