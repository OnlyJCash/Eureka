/**
 *
 */
package com.eureka.cms.core.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author User
 *
 */
@Entity
@Table(name="eureka_auth_token")
@NamedQueries({
	@NamedQuery(name=SessionToken.NQ_UPDATE_VALIDITY_TOKEN,
			query="Update AuthenticationToken Set expiredAt = :newExpiredAt where token = :authToken and expiredAt > current_timestamp"),
			@NamedQuery(name=SessionToken.NQ_DELETE_BY_TOKEN, query="Delete AuthenticationToken where token = :authToken"),
			@NamedQuery(name=SessionToken.NQ_FIND_BY_TOKEN_AND_USER, query="Select a FROM AuthenticationToken a where a.token = :authToken and a.user.id = :userId")
})
public class SessionToken extends Identifier {

	private static final long serialVersionUID = 6502063846824031502L;

	public static final String NQ_UPDATE_VALIDITY_TOKEN = "AuthenticationToken.updateValidityToken";
	public static final String NQ_DELETE_BY_TOKEN = "AuthenticationToken.deleteByToken";
	public static final String NQ_FIND_BY_TOKEN_AND_USER = "AuthenticationToken.findByTokenAndUser";

	@Column
	private String token;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiredAt;

	@Column
	private String platform;
	@Column
	private String platformId;

	@ManyToOne
	@JoinColumn(name="user_id")
	private EurekaUser user;

	@Column
	private String optional1;
	@Column
	private String optional2;
	@Column
	private String optional3;
	@Column
	private String optional4;
	@Column
	private String optional5;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the expiredAt
	 */
	public Date getExpiredAt() {
		return expiredAt;
	}
	/**
	 * @param expiredAt the expiredAt to set
	 */
	public void setExpiredAt(Date expiredAt) {
		this.expiredAt = expiredAt;
	}
	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}
	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	/**
	 * @return the platformId
	 */
	public String getPlatformId() {
		return platformId;
	}
	/**
	 * @param platformId the platformId to set
	 */
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	/**
	 * @return the user
	 */
	public EurekaUser getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(EurekaUser user) {
		this.user = user;
	}
	/**
	 * @return the optional1
	 */
	public String getOptional1() {
		return optional1;
	}
	/**
	 * @param optional1 the optional1 to set
	 */
	public void setOptional1(String optional1) {
		this.optional1 = optional1;
	}
	/**
	 * @return the optional2
	 */
	public String getOptional2() {
		return optional2;
	}
	/**
	 * @param optional2 the optional2 to set
	 */
	public void setOptional2(String optional2) {
		this.optional2 = optional2;
	}
	/**
	 * @return the optional3
	 */
	public String getOptional3() {
		return optional3;
	}
	/**
	 * @param optional3 the optional3 to set
	 */
	public void setOptional3(String optional3) {
		this.optional3 = optional3;
	}
	/**
	 * @return the optional4
	 */
	public String getOptional4() {
		return optional4;
	}
	/**
	 * @param optional4 the optional4 to set
	 */
	public void setOptional4(String optional4) {
		this.optional4 = optional4;
	}
	/**
	 * @return the optional5
	 */
	public String getOptional5() {
		return optional5;
	}
	/**
	 * @param optional5 the optional5 to set
	 */
	public void setOptional5(String optional5) {
		this.optional5 = optional5;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
