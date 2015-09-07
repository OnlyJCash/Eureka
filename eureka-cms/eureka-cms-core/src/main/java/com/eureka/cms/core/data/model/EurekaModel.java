/**
 *
 */
package com.eureka.cms.core.data.model;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * @author User
 *
 */
@MappedSuperclass
public abstract class EurekaModel extends Identifier {

	private static final long serialVersionUID = 7144385623199207051L;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="audit_id")
	private Audit audit;

	/**
	 * @return the audit
	 */
	public Audit getAudit() {
		return audit;
	}

	/**
	 * @param audit the rowMetadata to set
	 */
	public void setAudit(Audit audit) {
		this.audit = audit;
	}

}
