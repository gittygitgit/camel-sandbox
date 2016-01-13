package com.notatracer.examples;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;

public class PageExtractorRoutes extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:page_extractor")
        .setHeader(Exchange.HTTP_URI, body())
        .log("Extracting content from: '${body}'")
        .to("http:extractor")
        .unmarshal().tidyMarkup()
        .log("Html from: '${body}'")
        .split(xpath("//body//p/text()"), new SplitterAggregationStrategy("(?s).*[A-Za-z0-9].*"))
        .log("Text chunk: '${body}'.")
        .end();
	}

}
