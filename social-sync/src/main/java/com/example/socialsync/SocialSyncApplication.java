package com.example.socialsync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@SpringBootApplication
public class SocialSyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialSyncApplication.class, args);

		try {
			// Initialize JAXBContext with your class
			JAXBContext jaxbContext = JAXBContext.newInstance(SocialSyncApplication.class);

			// Now you can use this context for marshalling and unmarshalling
			// For example, you can create a marshaller and marshal an object
			// Marshaller marshaller = jaxbContext.createMarshaller();
			// marshaller.marshal(yourObject, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
