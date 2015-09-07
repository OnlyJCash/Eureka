/**
 *
 */
package com.eureka.cms.core.cfg.bean.entity;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.eureka.cms.core.cfg.bean.BasicInformation;
import com.eureka.cms.core.common.FieldType;
import com.eureka.cms.core.data.model.Identifier;
import com.google.common.collect.Lists;

/**
 * This bean contains all information about an annotated entity
 *
 * @author mmazzilli
 *
 */
public class EntityDescriptor extends BasicInformation {

	private static final long serialVersionUID = 1617322841280945571L;

	private Class<? extends Identifier> target;

	private boolean localized;
	private boolean hasMetadata;
	private boolean hidden;
	private boolean editInListview;

	private Map<String, FieldDescriptor> fields;

	private Set<String> tags;

	private Set<Class<? extends Identifier>> relatedWith;

	@SuppressWarnings("unchecked")
	public EntityDescriptor(Class<?> target) {
		setTarget((Class<? extends Identifier>) target);
		setFields(new LinkedHashMap<String, FieldDescriptor>());
		relatedWith = new HashSet<Class<? extends Identifier>>();
	}

	public boolean isRelatedWith(Class<?> clazz){
		return relatedWith.contains(clazz);
	}

	public void addField(FieldDescriptor field){
		getFields().put(field.getName(), field);
		if (FieldType.RELATION.equals(field.getFieldType())){
			relatedWith.add(((RelationDescriptor)field).getTarget());
		}
	}

	/**
	 *
	 * @return
	 */
	public List<FieldDescriptor> getVisibleInRelation(){
		List<FieldDescriptor> visibleInRelation = Lists.newArrayList();
		for(FieldDescriptor field : getFields().values()){
			if (BooleanUtils.isTrue(field.isVisibleInRelation())){
				visibleInRelation.add(field);
			}
		}
		return visibleInRelation;
	}



	/**
	 * @return the target
	 */
	public Class<? extends Identifier> getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(Class<? extends Identifier> target) {
		this.target = target;
	}

	/**
	 * @return the localized
	 */
	public boolean isLocalized() {
		return localized;
	}

	/**
	 * @param localized the localized to set
	 */
	public void setLocalized(boolean localized) {
		this.localized = localized;
	}

	/**
	 * @return the hasMetadata
	 */
	public boolean isHasMetadata() {
		return hasMetadata;
	}

	/**
	 * @param hasMetadata the hasMetadata to set
	 */
	public void setHasMetadata(boolean hasMetadata) {
		this.hasMetadata = hasMetadata;
	}

	/**
	 * @return the hidden
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * @return the editInListview
	 */
	public boolean isEditInListview() {
		return editInListview;
	}

	/**
	 * @param editInListview the editInListview to set
	 */
	public void setEditInListview(boolean editInListview) {
		this.editInListview = editInListview;
	}

	/**
	 * @return the fields
	 */
	public Map<String, FieldDescriptor> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(Map<String, FieldDescriptor> fields) {
		this.fields = fields;
	}

	/**
	 * @return the tags
	 */
	public Set<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the relatedWith
	 */
	public Set<Class<? extends Identifier>> getRelatedWith() {
		return relatedWith;
	}

	/**
	 * @param relatedWith the relatedWith to set
	 */
	public void setRelatedWith(Set<Class<? extends Identifier>> relatedWith) {
		this.relatedWith = relatedWith;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
