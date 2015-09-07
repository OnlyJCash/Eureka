/**
 *
 */
package com.eureka.cms.core.config.loader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eureka.cms.core.common.Constants;
import com.eureka.cms.core.config.annotation.EurekaEntity;
import com.eureka.cms.core.config.annotation.Property;
import com.eureka.cms.core.config.annotation.Relation;
import com.eureka.cms.core.config.bean.entity.EntityDescriptor;
import com.eureka.cms.core.config.bean.entity.FieldDescriptor;
import com.eureka.cms.core.config.bean.entity.PropertyDescriptor;
import com.eureka.cms.core.config.bean.entity.RelationDescriptor;
import com.eureka.cms.core.support.EurekaSupport;
import com.eureka.commons.aop.logging.Loggable;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * @author mmazzilli
 *
 */
public class EurekaEntityLoaderImpl implements EurekaEntityLoader {

	private static final Logger logger = LoggerFactory.getLogger(EurekaEntityLoaderImpl.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.eureka.cms.cfg.loader.EurekaEntityLoader#load(java.lang. Class)
	 */
	@Override
	@Loggable
	@SuppressWarnings("unchecked")
	public EntityDescriptor load(Class<?> target) {
		EntityDescriptor entity = new EntityDescriptor(target);

		EurekaEntity eurekaEntity = target.getAnnotation(EurekaEntity.class);
		entity.setLabel(eurekaEntity.label());
		entity.setDescription(eurekaEntity.description());
		entity.setName(EurekaSupport.getEurekaEntityName(target));
		entity.setTags(EurekaSupport.asSet(eurekaEntity.tags()));
		entity.setLocalized(eurekaEntity.localized());
		entity.setEditInListview(eurekaEntity.editInListview());

		entity.setHasMetadata(!ReflectionUtils.getAllFields(target, ReflectionUtils.<Field>withName(Constants.EUREKA_MODEL_AUDIT_FIELD_NAME)).isEmpty());

		addFields(entity, target);
		logger.trace("Entity {} loaded {}", target.getClass().getSimpleName(), entity);
		return entity;
	}

	/**
	 *
	 * @param entity
	 * @param target
	 */
	@SuppressWarnings("unchecked")
	private void addFields(EntityDescriptor entity, Class<?> target) {
		logger.trace("START - {} .addFields", this.getClass().getSimpleName());
		Predicate<Field> filter = Predicates.or(ReflectionUtils.<Field>withAnnotation(Property.class), ReflectionUtils.<Field>withAnnotation(Relation.class));
		Set<Field> allFields = ReflectionUtils.getAllFields(target, filter);

		// Fields of EntityDescriptor
		List<FieldDescriptor> fields = new ArrayList<FieldDescriptor>();

		for (Field field : allFields) {
			FieldDescriptor fd = null;
			if (field.isAnnotationPresent(Property.class)){
				fd = readAnnotationProperty(field);
			} else if (field.isAnnotationPresent(Relation.class)){
				fd = readAnnotationRelation(field);
			}
			fields.add(fd);
		}

		// Sort
		Collections.sort(fields);

		// Put in entity
		for (FieldDescriptor fieldDescriptor : fields) {
			entity.addField(fieldDescriptor);
		}
		logger.trace("END - {} .addFields", this.getClass().getSimpleName());
	}

	/**
	 *
	 * @param field
	 * @return
	 */
	private FieldDescriptor readAnnotationRelation(Field field) {
		RelationDescriptor fd= new RelationDescriptor();
		Relation relation = field.getAnnotation(Relation.class);

		fd.setName(field.getName());
		fd.setIndex(relation.sortIndex());
		fd.setType(relation.type());
		fd.setTarget(relation.target());

		if (null == relation.target()){
			// TODO read by reflection
			field.getType();
		}

		fd.setDescription(relation.description());
		fd.setRequired(relation.required());
		fd.setSearchable(relation.searchable());
		fd.setVisibleInRelation(relation.visibleInRelation());
		fd.setVisibleInSearchResult(relation.visibleInSearchResult());
		fd.setHtmlValidations(relation.htmlValidations());
		String label = StringUtils.isNotBlank(relation.label()) ? relation.label() : StringUtils.capitalize(field.getName());
		fd.setLabel(label);
		return fd;
	}

	/**
	 *
	 * @param field
	 * @return
	 */
	private FieldDescriptor readAnnotationProperty(Field field) {
		PropertyDescriptor fd = new PropertyDescriptor();
		Property property = field.getAnnotation(Property.class);

		fd.setName(field.getName());
		fd.setIndex(property.sortIndex());
		fd.setDescription(property.description());
		fd.setRequired(property.required());
		fd.setSearchable(property.searchable());
		fd.setVisibleInRelation(property.visibleInRelation());
		fd.setVisibleInSearchResult(property.visibleInSearchResult());
		fd.setHtmlValidations(property.htmlValidations());

		String label = StringUtils.isNotBlank(property.label()) ? property.label() : StringUtils.capitalize(field.getName());
		fd.setLabel(label);
		fd.setType(property.type());
		return fd;
	}

}
