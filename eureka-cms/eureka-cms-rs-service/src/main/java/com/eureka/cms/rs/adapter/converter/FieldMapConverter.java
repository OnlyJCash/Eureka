package com.eureka.cms.rs.adapter.converter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eureka.cms.core.config.bean.entity.FieldDescriptor;
import com.eureka.cms.rs.adapter.bean.cfg.entity.FieldBean;

public class FieldMapConverter implements CustomConverter, MapperAware {

	private static final Logger logger = LoggerFactory.getLogger(FieldMapConverter.class);

	private Mapper mapper;

	@Override
	@SuppressWarnings("unchecked")
	public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
		if (sourceFieldValue == null){
			return null;
		}
		logger.debug("START - FieldMapConverter.convert source: {} in destination {}", sourceClass, destinationClass);
		Map<String, FieldDescriptor> source = (LinkedHashMap<String, FieldDescriptor>) sourceFieldValue;
		Map<String, FieldBean> destination = new LinkedHashMap<String, FieldBean>(source.size());
		for(Entry<String, FieldDescriptor> entry : source.entrySet()){
			FieldBean fieldBean = mapper.map(entry.getValue(), FieldBean.class);
			destination.put(entry.getKey(), fieldBean);
		}
		logger.debug("END - FieldMapConverter.convert source: {} in destination {}", sourceClass, destinationClass);
		return destination;
	}

	@Override
	public void setMapper(Mapper mapper) {
		this.mapper = mapper;
	}

}
