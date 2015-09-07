/**
 *
 */
package com.eureka.cms.rs.adapter.converter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eureka.cms.core.config.bean.TagDescriptor;
import com.eureka.cms.rs.adapter.bean.cfg.TagBean;

/**
 * This class converts configuration entities in entity bean for RestService
 *
 * @author mmazzilli
 *
 */
public class TagMapConverter implements CustomConverter, MapperAware {

	private static final Logger logger = LoggerFactory.getLogger(TagMapConverter.class);

	private Mapper mapper;

	@Override
	@SuppressWarnings("unchecked")
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null){
			return null;
		}
		logger.debug("START - TagMapConverter.convert source: {} in destination {}", sourceClass, destinationClass);
		Map<String, TagDescriptor> source = (Map<String, TagDescriptor>) sourceFieldValue;
		Map<String, TagBean> dest = new LinkedHashMap<String, TagBean>(source.size());
		for(Entry<String, TagDescriptor> entry : source.entrySet()){
			TagDescriptor tagDescriptor = entry.getValue();
			TagBean tagBean = mapper.map(tagDescriptor, TagBean.class);
			dest.put(entry.getKey(), tagBean);
		}
		logger.debug("END - TagMapConverter.convert source: {} in destination {}", sourceClass, destinationClass);
		return dest;
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
