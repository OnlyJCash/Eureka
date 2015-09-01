package com.eureka.commons.loader;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.eureka.commons.loader.bean.Bean1;
import com.google.common.annotations.VisibleForTesting;

public class TestAnnotationDiscoveryEngineImpl {

	@Test
	public void testDiscover_success() {
		AnnotationDiscoveryEngineImpl adeImpl = new AnnotationDiscoveryEngineImpl();
		Set<Class<?>> classes = adeImpl.discover(VisibleForTesting.class, Bean1.class.getPackage().getName());
		Assert.assertEquals(1, classes.size());
		Assert.assertTrue(classes.iterator().next().isAssignableFrom(Bean1.class));
	}
}
