package xlike.processor;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.log4j.Logger;

import xlike.model.Conll;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RelationProcessorMock implements IProcessor {
	private String serviceUrl = null;

	private static Logger log = Logger.getLogger(RelationProcessorMock.class);

	/**
	 * Mock class for Relation Processor.
	 */
	public RelationProcessorMock() {
		Properties properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(
					"xlike_de.properties"));
			serviceUrl = properties.getProperty("parser.url");
		} catch (IOException e) {
			log.error("Error loading parser service url");
			e.printStackTrace();
		}
	}

	@Override
	public void process(Conll conll) {
		log.debug("Processing relations...");
		try {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);

			WebResource service = client.resource(UriBuilder
					.fromUri(serviceUrl).build());

			String xmlStr = conll.toString();

			ClientResponse response = service
					.type(MediaType.TEXT_XML)
					.post(ClientResponse.class,
							"<parse><lang>de</lang><conll>"+ xmlStr +"</conll></parse>");
			if (response.getStatus() == 200) {
				String responseXml = response.getEntity(String.class);
				log.debug("The mock returned: " + responseXml);
				// remove <conll></conll>
				responseXml = responseXml.replace("<conll>", "").replace(
						"</conll>", "");
				conll.parseConllFile(responseXml);
			} else
				log.error("Problems with the relation service");
		} catch (Exception e) {
			// ignore
			log.error("Relation processing mock service not runnig");
		}
	}
}
