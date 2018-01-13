package com.mulodo.fiveneed.constant;

/**
 * Environment Key
 *
 * @author thomc
 *
 */
public enum EnvironmentKey {
	/**
	 * Aws S3 Bucket Prefix
	 */
	AWS_S3_BUCKET_PREFIX("aws.s3.bucket-prefix"),
	/**
	 * Aws S3 Bucket Suffix: transaction
	 */
	AWS_S3_BUCKET_SUFFIX_TRANSACTION("aws.s3.bucket-suffix-transaction"),
	/**
	 * Aws S3 Bucket Suffix: resource
	 */
	AWS_S3_BUCKET_SUFFIX_RESOURCE("aws.s3.bucket-suffix-resource"),
	/**
	 * Aws S3 Path: transaction
	 */
	AWS_S3_BUCKET_PATH_TRANSACTION("aws.s3.bucket-path-transaction"),
	/**
	 * Aws S3 Path: bgimage
	 */
	AWS_S3_BUCKET_PATH_BGIMAGE("aws.s3.bucket-path-bgimage"),
	/**
	 * Aws S3 Path: -bgimage-basefile
	 */
	AWS_S3_BUCKET_PATH_BGIMAGE_BASEFILE("aws.s3.bucket-path-bgimage-basefile"),
	/**
	 * Aws S3 Path: -bgimage-dwg
	 */
	AWS_S3_BUCKET_PATH_BGIMAGE_DWG("aws.s3.bucket-path-bgimage-dwg"),

	/**
	 * Loop number key in application.properties
	 */
	SHA256_LOOPNUMBER_KEY("sha256.loop_number"),
	/**
	 * Aws S3 Path: profile
	 */
	AWS_S3_BUCKET_PATH_PROFILE("aws.s3.bucket-path-profile"),
  

	;

	/*-----------------------------------------------
	* Property
	*-----------------------------------------------*/
	/**
	 * Environment Key name
	 */
	private String key;

	/*-----------------------------------------------
	* Constructor
	*-----------------------------------------------*/
	EnvironmentKey(String key) {
		this.key = key;
	}

	/*-----------------------------------------------
	* Getter
	*-----------------------------------------------*/
	public String getKey() {
		return key;
	}
}
