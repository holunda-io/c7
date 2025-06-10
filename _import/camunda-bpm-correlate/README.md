# Camunda BPM Correlate

*Solution for correlation of events with processes.*

## Why should you use it?

Imagine you integrate your Camunda process engine into a larger application landscape. 
In doing so the inter-system communication becomes important and questions on 
communication styles and patterns arise. In the world of self-contained systems, 
the asynchronous communication with messages is wide adopted. This library helps 
you to solve integration problems around correlation of messages with processes.

## Main Features

* Ingress adapters for:
    * Spring Cloud Streams (e.g. Kafka Streams, Rabbit MQ, Azure Event Hubs, AWS SQS, AWS SNS, Solace PubSub+, Google PubSub)
    * Axon Framework (Axon Event Bus)
* Inbox pattern on message receiving
* Message storage in the Camunda Platform 7 database
    * MyBatis repository 
* Asynchronous scheduled batch-mode correlation
* Variety of error handling modes on mismatched correlation
* Configurable timings, retry strategies and many other parameters
