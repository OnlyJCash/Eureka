/**
 *
 */
package com.eureka.cms.core.data.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;

/**
 * @author User
 *
 */
@Entity
@Table(name="EUREKA_ROWMETADATA")
public class Audit extends Identifier {

	private static final long serialVersionUID = 6049893433169753077L;

	public enum WorkflowStatus {
		DRAFT, PUBLISHED
	}

	@CreationTimestamp
	@Column(name="creation_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationTime;

	@Column(name="last_modification_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModificationTime;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="created_by", insertable=true, updatable=false)
	private EurekaUser createdBy;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="modified_by")
	private EurekaUser modifiedBy;
	@Column(name="class_name")
	private String className;
	@Column(name="deleted", columnDefinition="boolean default false")
	private Boolean delete;

	@Column
	@Enumerated(EnumType.STRING)
	private WorkflowStatus status;

	@Column(name="published_from")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedFrom;

	@Column(name="published_to")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedTo;


	@Override
	@SequenceGenerator(name = "SEQ_METADATA_ID", sequenceName = "SEQ_METADATA_ID")
	public Long getId() {
		return super.getId();
	}

	public Audit() {
		setStatus(WorkflowStatus.DRAFT);
	}

	/**
	 * @return the creationTime
	 */
	public Date getCreationTime() {
		return creationTime;
	}

	/**
	 * @param creationTime the creationTime to set
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	/**
	 * @return the lastModificationTime
	 */
	public Date getLastModificationTime() {
		return lastModificationTime;
	}

	/**
	 * @param lastModificationTime the lastModificationTime to set
	 */
	public void setLastModificationTime(Date lastModificationTime) {
		this.lastModificationTime = lastModificationTime;
	}

	/**
	 * @return the createdBy
	 */
	public EurekaUser getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(EurekaUser createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public EurekaUser getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(EurekaUser modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the delete
	 */
	public Boolean getDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return the status
	 */
	public WorkflowStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(WorkflowStatus status) {
		this.status = status;
	}

	/**
	 * @return the publishedFrom
	 */
	public Date getPublishedFrom() {
		return publishedFrom;
	}

	/**
	 * @param publishedFrom the publishedFrom to set
	 */
	public void setPublishedFrom(Date publishedFrom) {
		this.publishedFrom = publishedFrom;
	}

	/**
	 * @return the publishedTo
	 */
	public Date getPublishedTo() {
		return publishedTo;
	}

	/**
	 * @param publishedTo the publishedTo to set
	 */
	public void setPublishedTo(Date publishedTo) {
		this.publishedTo = publishedTo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
