/**
 *
 */
package com.eureka.cms.core.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 *
 * @author michele.mazzilli
 *
 */
@MappedSuperclass
public abstract class Identifier implements Serializable {

	private static final long serialVersionUID = -838906799382844021L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", insertable=true, updatable=false, nullable=false)
	private Long id;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Identifier [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}



}
