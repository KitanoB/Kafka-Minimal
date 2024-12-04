package com.kitano.kafka;

import com.kitano.kafka.dto.Person;
import com.kitano.kafka.sender.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaApplication implements CommandLineRunner {

	@Autowired
	private ISender sender; // Instance for sending messages

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

	/**
	 * Allows to send messages to the topics Tuto1 and Tuto2
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		int ageTuto1 = 10; //
		int ageTuto2 = 20; //
		while (true) { // initialize an infinite loop
			Person personTuto1 = new Person("Jean", "DUPOND", ageTuto1); // Creation of an object for Tuto1
			sender.send("Tuto1", personTuto1); // Send the object to the topic "Tuto1"
			Thread.sleep(3000); // Wait 3s
			Person personTuto2 = new Person("Pierre","DURAND",ageTuto2); // Création of an object for Tuto2
			sender.send("Tuto2", personTuto2);  // Send the object to the topic "Tuto2"
			Thread.sleep(3000); // Wait 3s
			ageTuto1++; // Incrementation of the text
			ageTuto2++; // Incrementation of the text
		}

	}
}