package com.mulodo.fiveneed.common.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class EmailUtil {
	// Used to generate the HMAC signature. Do not modify.
	public static final String MESSAGE = "SendRawEmail";
	// Version number. Do not modify.
	public static final byte VERSION = 0x02;

	/**
	 * Create SMTP Certification Password
	 *
	 * @param secretAccessKey IAM Secret Access Key
	 * @return SMTP Certification Password
	 */
	public static String makeSMTPPassword(String secretAccessKey) {
		String password = null;
		// Get the AWS secret access key from environment variable
		// AWS_SECRET_ACCESS_KEY.
		String key = secretAccessKey;
		if (key == null) {
			System.out.println(
					"Error: Cannot find environment variable AWS_SECRET_ACCESS_KEY.");
			System.exit(0);
		}

		// Create an HMAC-SHA256 key from the raw bytes of the AWS secret access
		// key.
		SecretKeySpec secretKey =
				new SecretKeySpec(key.getBytes(), "HmacSHA256");

		try {
			// Get an HMAC-SHA256 Mac instance and initialize it with the AWS
			// secret access key.
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(secretKey);

			// Compute the HMAC signature on the input data bytes.
			byte[] rawSignature = mac.doFinal(MESSAGE.getBytes());

			// Prepend the version number to the signature.
			byte[] rawSignatureWithVersion = new byte[rawSignature.length + 1];
			byte[] versionArray = {VERSION};
			System.arraycopy(versionArray, 0, rawSignatureWithVersion, 0, 1);
			System.arraycopy(rawSignature, 0, rawSignatureWithVersion, 1,
					rawSignature.length);

			// To get the final SMTP password, convert the HMAC signature to
			// base 64.
			password = DatatypeConverter
					.printBase64Binary(rawSignatureWithVersion);
		} catch (Exception ex) {
			System.out.println(
					"Error generating SMTP password: " + ex.getMessage());
		}
		return password;
	}

}
