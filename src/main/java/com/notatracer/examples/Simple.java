package com.notatracer.examples;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

public class Simple {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		final DefaultCamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new PageExtractorRoutes());
		camelContext.start();

		ProducerTemplate template = camelContext.createProducerTemplate();
		String result = template.requestBody("direct:page_extractor",
				"http://www.w3.org", String.class);
		System.out.printf("Extracted: '%s'.n", result);

		Thread.sleep(10000);
		camelContext.stop();
	}

}
