package br.com.camel.poc.tests;

import java.io.IOException;
import java.io.InputStream;

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
	public void testCase_01() {

		try {
			getMockEndpoint("{{camel.poc.project.jms.endpoint.out}}")
					.expectedMessageCount(1);

			template.sendBody("{{camel.poc.project.jms.endpoint.in}}", getFileAsStream());
			assertMockEndpointsSatisfied();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}
	}

	public String getFileAsStream() {

		StringBuilder builder = new StringBuilder();
		InputStream inputStream = null;
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream("messages/input-message-01.xml");
			int content;
			while ((content = inputStream.read()) != -1) {
				builder.append((char) content);
			}

		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		return builder.toString();
	}
}
