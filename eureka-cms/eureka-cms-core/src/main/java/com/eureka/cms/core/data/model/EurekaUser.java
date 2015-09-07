/**
 *
 */
package com.eureka.cms.core.data.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.eureka.cms.core.common.Constants;
import com.eureka.cms.core.common.PropertyType;
import com.eureka.cms.core.config.annotation.EurekaEntity;
import com.eureka.cms.core.config.annotation.Property;
import com.eureka.cms.core.config.annotation.Relation;

/**
 * @author User
 *
 */
@Entity
@Table(name="eureka_user")
@EurekaEntity(label = "User", plural = "Users", tags = Constants.TAG_EUREKA_ENTITIES_MANAGEMENT_NAME)
public class EurekaUser extends Identifier {

	private static final long serialVersionUID = 7696961876514897626L;

	@Column(unique=true)
	@Property(sortIndex = 0, type = PropertyType.TEXT, searchable=true, visibleInSearchResult=true)
	private String username;
	@Column
	@Property(sortIndex = 1, type = PropertyType.EMAIL, searchable=true, visibleInSearchResult=true)
	private String email;
	@Column
	private String password;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@Property(sortIndex = 2, type = PropertyType.TIMESTAMP,searchable=true, visibleInSearchResult=true)
	private Date activationDate;

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="eureka_user_group", joinColumns = @JoinColumn( name="user_id"), inverseJoinColumns = @JoinColumn( name="group_id"))
	@Relation(sortIndex = 3, target = Group.class, type = PropertyType.MANY_TO_MANY)
	private Set<Group> groups;

	@Override
	@SequenceGenerator(name = "SEQ_EUREKA_USER_ID", sequenceName = "SEQ_EUREKA_USER_ID")
	public Long getId() {
		return super.getId();
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the activationDate
	 */
	public Date getActivationDate() {
		return activationDate;
	}

	/**
	 * @param activationDate the activationDate to set
	 */
	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	/**
	 * @return the groups
	 */
	public Set<Group> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

}
