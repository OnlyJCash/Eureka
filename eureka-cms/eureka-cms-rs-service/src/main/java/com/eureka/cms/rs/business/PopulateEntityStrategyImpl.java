/**
 *
 */
package com.eureka.cms.rs.business;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.eureka.cms.core.common.FieldType;
import com.eureka.cms.core.config.bean.entity.EntityDescriptor;
import com.eureka.cms.core.config.bean.entity.FieldDescriptor;
import com.eureka.cms.core.config.bean.entity.RelationDescriptor;
import com.eureka.cms.core.data.model.EurekaUser;
import com.eureka.cms.core.data.model.Identifier;
import com.eureka.cms.core.data.model.EurekaModel;
import com.eureka.cms.core.data.model.Audit;
import com.eureka.cms.core.service.cfg.ConfigurationService;
import com.eureka.cms.core.service.data.EntityService;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author mmazzilli
 *
 */
public class PopulateEntityStrategyImpl implements PopulateEntityStrategy {

	private static final Logger logger = LoggerFactory.getLogger(PopulateEntityStrategyImpl.class);

	@Autowired
	private ConfigurationService configurationService;
	@Autowired
	private EntityService entityService;


	/* (non-Javadoc)
	 * @see com.eureka.cms.rs.business.PopulateEntityStrategy#populate(java.lang.String, java.lang.String, com.google.gson.JsonObject)
	 */
	@Override
	public Identifier populateAndSave(String entity, JsonObject object, EurekaUser loggedUser) {
		EntityDescriptor entityDescr = configurationService.getEntity(entity);

		Gson gson = new GsonBuilder().addDeserializationExclusionStrategy(new ExclusionRelationStrategy(entityDescr)).create();

		// Deserialize bean
		Identifier record = gson.fromJson(object, entityDescr.getTarget());

		for(Entry<String, FieldDescriptor> entry : entityDescr.getFields().entrySet()){
			if (FieldType.RELATION.equals(entry.getValue().getFieldType())){
				fillRelation(object, record, entry);
			}
		}

		// TODO Thrown Event
		if (entityDescr.isHasMetadata()){
			EurekaModel recordWithMetadata = (EurekaModel) record;
			Date now = new Date();
			if (null == recordWithMetadata.getAudit() || null == recordWithMetadata.getAudit().getId()){
				recordWithMetadata.setAudit(new Audit());
				recordWithMetadata.getAudit().setCreatedBy(loggedUser);
				recordWithMetadata.getAudit().setClassName(entityDescr.getTarget().getCanonicalName());
				recordWithMetadata.getAudit().setCreationTime(now);
			}
			recordWithMetadata.getAudit().setLastModificationTime(now);
			recordWithMetadata.getAudit().setModifiedBy(loggedUser);
		}
		entityService.saveOrUpdate(record, loggedUser);
		return record;
	}


	/**
	 *
	 * @param object
	 * @param record
	 * @param entry
	 *
	 */
	private void fillRelation(JsonObject object, Identifier record, Entry<String, FieldDescriptor> entry) {
		RelationDescriptor relation = (RelationDescriptor) entry.getValue();

		EntityDescriptor relatedEntityDescr = configurationService.getEntityByClass(relation.getTarget());

		switch (relation.getType()) {
		case MANY_TO_ONE:
			JsonObject joRelatad = object.getAsJsonObject(relation.getName());
			Optional<Object> oRelatedEntity = entityService.getById(relatedEntityDescr.getName(), joRelatad.get("id").getAsLong());
			if (oRelatedEntity.isPresent()){
				try {
					PropertyUtils.setProperty(record, relation.getName(), oRelatedEntity.get());
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					logger.error(e.getMessage(), e);
				}
			}
			break;
		case MANY_TO_MANY:
			JsonArray joArrayRelated = object.getAsJsonArray(relation.getName());
			if (!joArrayRelated.isJsonNull()){
				List<Long> ids = Lists.newArrayList();
				for (Iterator<JsonElement> iterator  = joArrayRelated.iterator(); iterator.hasNext();) {
					JsonElement element =  iterator.next();
					ids.add(element.getAsJsonObject().get("id").getAsLong());
				}
				List<? extends Identifier> relatedEntities = entityService.findAllWithIds(relatedEntityDescr.getName(), ids);
				try {
					// TODO By Reflaction List|Set
					PropertyUtils.setProperty(record, relation.getName(), Sets.newHashSet(relatedEntities));
				} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
					logger.error(e.getMessage(), e);
				}
			}
			break;
		default:
			break;
		}
	}

}
