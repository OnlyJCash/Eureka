/**
 *
 */
package com.eureka.cms.rs.adapter.converter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.BooleanUtils;
import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eureka.cms.core.config.bean.entity.EntityDescriptor;
import com.eureka.cms.rs.adapter.bean.cfg.entity.EntityBean;

/**
 * This class converts configuration entities in entity bean for RestService
 *
 * @author mmazzilli
 *
 */
public class EntityMapConverter implements CustomConverter, MapperAware {

	private static final Logger logger = LoggerFactory.getLogger(EntityMapConverter.class);

	private Mapper mapper;

	@Override
	@SuppressWarnings("unchecked")
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null){
			return null;
		}
		logger.debug("START - EntityMapConverter.convert source: {} in destination {}", sourceClass, destinationClass);
		Map<String, EntityDescriptor> source = (Map<String, EntityDescriptor>) sourceFieldValue;
		Map<String, EntityBean> dest = new LinkedHashMap<String, EntityBean>(source.size());
		for(Entry<String, EntityDescriptor> entry : source.entrySet()){
			EntityDescriptor entityDescriptor = entry.getValue();
			if (BooleanUtils.isNotTrue(entityDescriptor.isHidden())){
				EntityBean entityBean = mapper.map(entityDescriptor, EntityBean.class);
				dest.put(entry.getKey(), entityBean);
			}
		}
		logger.debug("END - EntityMapConverter.convert source: {} in destination {}", sourceClass, destinationClass);
		return dest;
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
