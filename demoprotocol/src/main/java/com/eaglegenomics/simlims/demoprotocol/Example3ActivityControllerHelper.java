package com.eaglegenomics.simlims.demoprotocol;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eaglegenomics.simlims.core.ActivityData;
import com.eaglegenomics.simlims.spring.AbstractActivityControllerHelper;

/**
 * Copyright (C) 2009 The Genome Analysis Center, Norwich, UK.
 * <p>
 * A demonstration activity controller.
 * 
 * @author Richard Holland
 * @since 0.0.1
 */
public class Example3ActivityControllerHelper extends
		AbstractActivityControllerHelper {
	protected static final Log log = LogFactory
			.getLog(Example3ActivityControllerHelper.class);

	public Properties convertDataModelToActivityProperties(
			Collection<ActivityData> inputData, Object dataModel) {
		Properties props = new Properties();
		// Only one input.
		props.setProperty(Example3Activity.FINAL_APPEND_STR_KEY,
				((Example3DataModel) dataModel).getMessage());
		return props;
	}

	public String convertActivityDataToDisplayName(ActivityData activityData) {
		try {
			return activityData.getDataReference().resolve().toString();
		} catch (IOException e) {
			if (log.isDebugEnabled()) {
				log.debug("Could not resolve data reference", e);
			}
			return activityData.getUniqueId() + " (could not resolve)";
		}
	}

	public Object createDataModel() {
		return new Example3DataModel();
	}

	class Example3DataModel {
		private String message = "Another message";

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}
}
