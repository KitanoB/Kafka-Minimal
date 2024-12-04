# Kafka Minimal Project Setup with Spring Boot

This project demonstrates a minimal configuration to set up and use **Apache Kafka** with **Spring Boot**. It is designed for developers seeking a basic understanding of Kafka, its purpose, and how to integrate it into their applications.

---

## What is Apache Kafka?

Apache Kafka is a distributed event-streaming platform designed for high-performance data pipelines, stream processing, and data integration. Kafka is widely used for building real-time data pipelines and streaming applications, ensuring durability and fault-tolerance.

### Key Kafka Components:
1. **Producers**: Applications or services that send messages to Kafka topics.
2. **Consumers**: Applications or services that read messages from Kafka topics.
3. **Topics**: Categories to which messages are sent.
4. **Zookeeper**: Manages Kafka's configuration, leader election, and health monitoring.

---

## Purpose of This Project

This project highlights:
- **Minimal Kafka configuration**: A basic setup using `docker-compose` for Kafka and Zookeeper.
- **Spring Boot integration**: Using Spring Kafka to send and receive serialized objects (`Person`).
- **Practical Example**: Sends and receives `Person` objects through Kafka topics, showcasing both producer and consumer behavior.

---

## Project Architecture

### Key Components:
1. **DTO (Data Transfer Object)**:
   - Class `Person`: Represents the data model exchanged through Kafka.

2. **Sender**:
   - **KafkaSender**: Implements the `ISender` interface to send `Person` objects to specific topics.

3. **Receiver**:
   - **KafkaReceiver**: Implements the `IReceiver` interface to listen to Kafka topics and process messages using `IProcess`.

4. **Configurations**:
   - **KafkaSenderConfig**: Configures Kafka producer.
   - **KafkaReceiverConfig**: Configures Kafka consumer.
   - **KafkaTopicConfig**: Manages Kafka topics.

5. **Docker Compose**:
   - Sets up Zookeeper, Kafka, and Kafka Manager services for a local development environment.

6. **Main Application**:
   - Sends `Person` objects to topics `Tuto1` and `Tuto2` in a loop.

```lua
    +-------------------+       +--------------------+

                     +----------------+
                     |  Kafka Manager |
                     |   (localhost)  |
                     +----------------+
                              |
                              v
  +-------------------+       +--------------------+
  |                   |       |                    |
  |     Zookeeper     | <---- |      Kafka         |
  |                   |       |    (Broker)        |
  +-------------------+       +--------------------+
                                       |
                                       v
  +-------------------+       +--------------------+
  |                   |       |                    |
  | KafkaSender (ISender)    KafkaReceiver (IReceiver)
  | Spring Boot App   |       | Spring Boot App    |
  |  - Sends Person   |       |  - Listens to      |
  |    to Topics      |       |    Topics          |
  +-------------------+       +--------------------+
                                       |
                                       v
                             +--------------------+
                             | Kafka Topics       |
                             |  - Tuto1           |
                             |  - Tuto2           |
                             +--------------------+

```

---

## Setting Up Kafka on macOS

### Prerequisites:
1. **Docker Desktop**: Download and install [Docker Desktop](https://www.docker.com/products/docker-desktop/).
2. Verify Docker installation:
   ```bash
   docker --version
   docker-compose --version
   ```

Oui, ils auront besoin du fichier `docker-compose.yml` pour exécuter la commande `docker-compose up`. Voici une version mise à jour de la section avec une mention explicite du fichier :

---

### Steps:
1. Clone the project repository:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. Locate the `docker-compose.yml` file:
   - The file is located in the [DOCS](DOCS/docker-compose.yml) directory of the project.
   - Ensure you are in the same directory as `docker-compose.yml` before running the next command.

3. Start Kafka, Zookeeper, and Kafka Manager:
   ```bash
   docker-compose up
   ```

<img src="DOCS/dockercompose.png" width="800"  alt="hexagone"/>

3. Open Kafka Manager:
    - Navigate to `http://localhost:9000` in your browser.
    - Add a new cluster with:
        - **Cluster Name**: `kafka1`
        - **Zookeeper Host**: `zookeeper:2181`

---

## Running the Project

1. Start the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

2. Kafka Sender Behavior:
    - The application sends `Person` objects to `Tuto1` and `Tuto2` topics every 3 seconds.

3. Kafka Receiver Behavior:
    - Listens to `Tuto1` and `Tuto2` topics.
    - Logs messages to the console in the following format:
      ```
      Message Received: info=Tuto1Info, person={firstName:"Jean", lastName:"DUPOND", age:10}
      ```

---

## Expected Results

1. Kafka Manager:
    - Navigate to `http://localhost:9000`.
    - Under `kafka1`, verify that `Tuto1` and `Tuto2` topics exist and are receiving messages.

2. Console Output:
    - The logs in your terminal will display the sent and received messages.

3. Message Flow:
    - **Producer**: Sends `Person` objects with incremental ages to `Tuto1` and `Tuto2`.
    - **Consumer**: Receives these messages and logs them.

---

## Example Console Output

```
Message To Send : topicName=Tuto1, person={firstName:"Jean", lastName:"DUPOND", age:10}
Message Received: info=Tuto1Info, person={firstName:"Jean", lastName:"DUPOND", age:10}
Message To Send : topicName=Tuto2, person={firstName:"Pierre", lastName:"DURAND", age:20}
Message Received: info=Tuto2Info, person={firstName:"Pierre", lastName:"DURAND", age:20}
```

---

## Additional Notes

- **Customizing Kafka Address**: Update `application.properties` or environment variables for `kafka.bootstrapAddress` to point to your Kafka broker.
- **Docker Volumes**: Modify the `docker-compose.yml` if you want persistent storage for Kafka data.
- **Error Handling**: Add custom error handlers in `KafkaSender` and `KafkaReceiver` for production-grade resilience.

---

## Troubleshooting

1. **Cannot connect to Kafka**:
    - Ensure `docker-compose up` is running.
    - Verify `kafka.bootstrapAddress` in your configuration.

2. **Topics not visible in Kafka Manager**:
    - Confirm Zookeeper and Kafka are running.
    - Double-check the Zookeeper host address in Kafka Manager.

---

## Conclusion
Please check the original [article](https://dimbo.developpez.com/tutoriels/java/kafka/installation-kafka-et-execution-premier-programme-java/) for more details.

