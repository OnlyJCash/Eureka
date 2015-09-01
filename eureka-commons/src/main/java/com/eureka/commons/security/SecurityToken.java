/**
 *
 */
package com.eureka.commons.security;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;


/**
 * @author User
 *
 */
public abstract class SecurityToken {

	private static final String SECURITY_SALT = SecurityToken.class.getCanonicalName();

	/**
	 * @param args
	 *
	 * @return
	 */
	public static String unique(Object... args){
		if (null == args || args.length <= 0){
			throw new IllegalArgumentException("Security Token: args must have values!");
		}
		return DigestUtils.sha256Hex(UUID.randomUUID()+"-"+StringUtils.join(args, "") + DigestUtils.sha1(SECURITY_SALT));
	}

	/**
	 *
	 * @param args
	 *
	 * @return
	 */
	public static String get(Object...args){
		if (null == args || args.length <= 0){
			throw new IllegalArgumentException("Security Token: args must have values!");
		}
		return DigestUtils.md5Hex(StringUtils.join(args, "") + DigestUtils.md5Hex(SECURITY_SALT));
	}

	public static void main(String[] args) {
		System.out.println(SecurityToken.get("eureka"));
	}

}
