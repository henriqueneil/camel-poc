package br.com.camel.poc.routes;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class PocRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// Default error handling which just put the message in the queue
		errorHandler(deadLetterChannel("{{camel.poc.project.jms.endpoint.dlq}}"));
		
		from("{{camel.poc.project.jms.endpoint.in}}").routeId("camel-poc-route")
			.setHeader("createDate", simple("${date:now:dd/MM/yyyy HH:mm:ss}"))
			.log(LoggingLevel.INFO, "${body}")
			.to("xslt:xslt/transformation.xslt")
			.log(LoggingLevel.INFO, "${body}")
			.to("{{camel.poc.project.jms.endpoint.out}}");
	}
}
