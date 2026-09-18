# Messenger

A microservice-based messaging system that supports multiple message agents for sending messages across different platforms.

The system allows you to implement and integrate your own agent for a specific messaging or SMS platform.

## Architecture

The project follows a microservice architecture where each message-sending platform can be implemented as an independent agent.

![Messenger Architecture](docs/Messanger-architecture.png)

## Modules

### Client

Contains the models and clients required to communicate with the messaging services.

### Gateway

The entry point to the system.

Responsibilities include:

* Receiving client requests
* Routing requests to the appropriate microservice
* Handling resilience and fallback mechanisms

### Distributor

Responsible for determining how and where a message should be sent.

It manages:

* Messages
* Senders
* Sending strategies
* Message routing

### Telegram Agent

An independent message agent responsible for sending messages through Telegram.

The agent-based architecture makes it possible to add other platforms without changing the core messaging logic.

For example:

```text
Telegram Agent
SMS Agent
Email Agent
WhatsApp Agent
Push Notification Agent
...
```

## Adding a New Agent

One of the main goals of this project is to make it easy to add support for a new messaging platform.

You can implement your own agent for a specific messaging or SMS platform.

For example:

```text
                Distributor
                     │
          ┌──────────┼──────────┐
          │          │          │
          ▼          ▼          ▼
       Telegram     SMS       Email
        Agent      Agent      Agent
```

Each agent can be implemented and deployed independently.

## Message Flow

A typical message flow looks like this:

```text
Client
  │
  │ Send Message
  ▼
Gateway
  │
  │ Route Request
  ▼
Distributor
  │
  │ Select Agent
  ▼
Message Agent
  │
  │ Send Message
  ▼
External Platform
```

## Technologies

The project is built using modern Java and Spring technologies.

* Java
* Spring Boot
* Spring Cloud
* Spring Cloud Gateway
* Spring Data JPA
* OpenFeign
* Liquibase
* PostgreSQL
* Docker
* Kubernetes

## Project Structure

```text
Messenger
│
├── client
│
├── gateway
│
├── distributor
│
└── telegram-agent
```

## Running the Project

### Prerequisites

Make sure the following are installed:

* Java 25
* Maven
* Docker

### Build

Clone the repository and build the project:

```bash
git clone <repository-url>

cd Messenger

mvn clean install
```

Each microservice can then be started independently.

## Docker

Each microservice is designed to have its own Docker image.

Example:

```bash
docker build -t messenger-gateway ./gateway
docker build -t messenger-distributor ./distributor
docker build -t messenger-telegram-agent ./telegram-agent
```

## Kubernetes

The project is also intended to be deployed in a Kubernetes environment.

Each microservice can run as an independent Kubernetes Deployment and communicate with other services through Kubernetes Services.

```text
                    Kubernetes Cluster
┌──────────────────────────────────────────────────────┐
│                                                      │
│   ┌─────────┐       ┌─────────────┐                  │
│   │ Gateway │ ────► │ Distributor │                  │
│   └─────────┘       └──────┬──────┘                  │
│                             │                         │
│                     ┌───────┴────────┐                │
│                     ▼                ▼                │
│              ┌─────────────┐  ┌─────────────┐        │
│              │   Telegram  │  │     SMS     │        │
│              │    Agent    │  │    Agent    │        │
│              └─────────────┘  └─────────────┘        │
│                                                      │
└──────────────────────────────────────────────────────┘
```

## Example Request

Send a message through the API:

```http
POST /api/v1/messages
Content-Type: application/json
```

```json
{
  "receiverIdentifier": "receiver-id",
  "sendingStrategy": "TELEGRAM",
  "content": "Hello from Messenger!"
}
```

The Distributor determines which agent should handle the request.

## Goals

This project is mainly focused on exploring and demonstrating:

* Microservice architecture
* Clean service boundaries
* Message routing
* Extensible agent-based architecture
* Inter-service communication
* Resilience patterns
* Docker containerization
* Kubernetes deployment

## Future Improvements

Possible future improvements include:

* Adding SMS Agent
* Adding Email Agent
* Adding WhatsApp Agent
* Kafka-based asynchronous messaging
* Service discovery
* Centralized configuration
* Distributed tracing
* Authentication and authorization
* Monitoring and observability

## License

This project is licensed under the MIT License.
