package com.example.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContextProvider implements ApplicationContextAware {

	private static ApplicationContext appContext;

	@Override
	public void setApplicationContext(final ApplicationContext ac) throws BeansException {
		appContext = ac;
	}

	public static ApplicationContext getApplicationContext() {
		return appContext;
	}

}
