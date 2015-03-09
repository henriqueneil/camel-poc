package br.com.camel.poc.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class PocTest extends CamelBlueprintTestSupport {

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}

	@Override
	public boolean isUseAdviceWith() {
		return Boolean.TRUE;
	}

	@Test
	/**
	 * Success test
	 */
	public void testCase_01() {

		try {
			getMockEndpoint("{{camel.poc.project.jms.endpoint.out}}").expectedMessageCount(1);
			String message = new String(Files.readAllBytes(Paths.get("target/test-classes/messages/input-message-01.xml")));

			template.sendBody("{{camel.poc.project.jms.endpoint.in}}", message);
			assertMockEndpointsSatisfied();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
	
	@Test
	/**
	 * Testing the default error handler
	 */
	public void testCase_02() {
		
		try {
			getMockEndpoint("{{camel.poc.project.jms.endpoint.dlq}}").expectedMessageCount(1);
			String message = new String(Files.readAllBytes(Paths.get("target/test-classes/messages/input-message-02.xml")));

			template.sendBody("{{camel.poc.project.jms.endpoint.in}}", message);
			assertMockEndpointsSatisfied();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
