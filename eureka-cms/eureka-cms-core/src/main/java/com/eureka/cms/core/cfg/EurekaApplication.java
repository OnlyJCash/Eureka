package com.eureka.cms.core.cfg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.Assert;

import com.eureka.cms.core.cfg.bean.ProjectDescriptor;
import com.eureka.cms.core.cfg.bean.TagDescriptor;
import com.eureka.cms.core.common.Constants;
import com.eureka.cms.core.data.model.EurekaUser;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
/**
 * Use ApplicationBuilder to build this class configuration.
 *
 * @author mmazzilli
 *
 */
public class EurekaApplication implements Serializable {

	private static final long serialVersionUID = 4406618716891077040L;

	private ProjectDescriptor project;
	private Map<String, TagDescriptor> tags;
	private List<String> modelPackages;
	private List<BootApplication> bootApplications;

	private EurekaApplication(ProjectDescriptor project,
			Map<String, TagDescriptor> tags, List<String> modelPackages,
			List<BootApplication> bootApplications) {

		this.project = project;
		this.tags = tags;
		this.modelPackages = modelPackages;
		this.bootApplications = bootApplications;
	}

	/**
	 * @return the project
	 */
	public ProjectDescriptor getProject() {
		return project;
	}

	/**
	 * @return the tags
	 */
	public Map<String, TagDescriptor> getTags() {
		return tags;
	}

	/**
	 * @return the modelPackages
	 */
	public List<String> getModelPackages() {
		return modelPackages;
	}

	/**
	 * @return the applicationBoots
	 */
	public List<BootApplication> getBootApplications() {
		return bootApplications;
	}

	/**
	 *
	 * @author michele.mazzilli
	 *
	 */
	public static class ApplicationBuilder {
		private String name;
		private String description;
		private List<Locale> localeAllowed;
		private Locale defaultLocale;
		private List<String> modelPackages;
		private List<BootApplication> bootApplications;
		private HashMap<String, TagDescriptor> tags;

		public ApplicationBuilder() {
		}

		/**
		 *
		 * @return
		 */
		public EurekaApplication build(){
			Assert.hasText(name, "Application Name must be set!");

			if (null != defaultLocale){
				Assert.notEmpty(localeAllowed, "Locale allowed must be set with default locale set!");
				Assert.isTrue(localeAllowed.contains(defaultLocale), "Locale allowed must contain a default locale!");
			}

			Assert.notEmpty(modelPackages, "Model Packages must be set!");

			ProjectDescriptor project = new ProjectDescriptor();
			project.setLabel(name);
			project.setName(name.toLowerCase());
			project.setDescription(description);
			if (null != localeAllowed){
				project.setLocaleAllowed(Sets.newHashSet(localeAllowed));
			}
			project.setDefaultLocale(defaultLocale);

			return new EurekaApplication(project, tags, modelPackages, bootApplications);
		}

		public ApplicationBuilder name(String applicationName){
			this.name = applicationName;
			return this;
		}

		public ApplicationBuilder description(String description){
			this.description = description;
			return this;
		}

		public ApplicationBuilder locales(Locale... locales){
			if (null == this.localeAllowed)
				this.localeAllowed = Lists.newArrayList();
			this.localeAllowed.addAll(Lists.newArrayList(locales));
			return this;
		}

		public ApplicationBuilder defaultLocale(Locale locale){
			this.defaultLocale = locale;
			return this;
		}

		public ApplicationBuilder bindModelPackages(String... modelPackageName){
			if ( null == this.modelPackages)
				this.modelPackages = Lists.newLinkedList();
			this.modelPackages.addAll(Lists.newArrayList(modelPackageName));
			return this;
		}

		public ApplicationBuilder bindBootApplications(BootApplication...applicationBoots){
			if ( null == this.bootApplications)
				this.bootApplications = Lists.newLinkedList();
			this.bootApplications.addAll(Lists.newArrayList(applicationBoots));
			return this;
		}

		public ApplicationBuilder withTag(String name, String label){
			if ( null == this.tags)
				this.tags = Maps.<String, TagDescriptor>newHashMap();
			this.tags.put(name, new TagDescriptor(name, label));
			return this;
		}

		/**
		 * Bind Eureka CMS Entities
		 *
		 * @return ApplicationBuilder
		 */
		public ApplicationBuilder bindEurekaEntities(){
			bindModelPackages(EurekaUser.class.getPackage().getName());
			withTag(Constants.TAG_EUREKA_ENTITIES_MANAGEMENT_NAME, "CMS Entities Management");
			return this;
		}

		/**
		 * Bind Eureka Application Boot
		 *
		 * @return ApplicationBuilder
		 */
		public ApplicationBuilder bindEurekaBootApplication(){
			if (null == this.bootApplications){
				this.bootApplications = Lists.newLinkedList();
			}
			this.bootApplications.add(0, new EurekaBootApplication());
			return this;
		}
	}
}
