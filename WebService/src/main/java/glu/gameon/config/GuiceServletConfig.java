package glu.gameon.config;

import glu.gameon.resources.PdfResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class GuiceServletConfig extends GuiceServletContextListener {

	Logger LOGGER = LoggerFactory.getLogger(GuiceServletConfig.class);

	@Override
	protected Injector getInjector() {
		LOGGER.info("Guice Container Initialized");

		/*
		 * Creating Guice Injector
		 */

		Injector injector = Guice.createInjector(new JerseyServletModule() {

			@Override
			protected void configureServlets() {
				/*
				 * Must configure at least one JAX-RS resource or the server
				 * will fail to start.
				 */

				// Resources injection as Singleton

				bind(PdfResource.class).asEagerSingleton();
				// Services Injection

				// Properties injection

				serve("/*").with(GuiceContainer.class);

			}
		});
		return injector;
	}

}
