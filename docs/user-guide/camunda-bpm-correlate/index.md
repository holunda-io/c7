## Motivation

Correlation is about targeting a running workflow (for example running inside the Camunda Platform 7)
containing the state update by an external system. Inside the Camunda Platform it is important that the
message subscription is present at the time of correlation, otherwise the correlation is mismatched.

If you are building a distributed system using the Camunda Platform 7 as a part of it, you should not
make assumptions or assertions regarding the speed of processing of components, message ordering,
message delivery or timings. To make sure that the correlation is not dependent on all those assumptions,
the usage of inbox pattern to store the message locally and then deliver it timely on schedule is a good
practise.

## Solution Architecture

The library provides a core that is responsible for accepting the message, storing it into persistence storage
and processing it scheduled. If any errors occur during the correlation, these are handled by one of the
pre-configured error strategies, like retry, ignore or drop...

In addition, there are a set of several ingress adapters to support different communication technologies.


## Supported features

* Ingress Adapters:
  * Spring Cloud Kafka
  * Axon Framework
* MetaData extractors:
  * Message based (Headers)
  * Channel based (Properties)
* Persisting Message Accepting Adapter
* Message Persistence
  * In-Memory
  * MyBatis (using the same DB as Camunda Platform 7)
* Batch processor running on schedule
  * Batch modes: all, fail-first
* Correlation error strategies: ignore, drop, retry
* Message Buffering (TTL)
* Message Expiry
* Camunda Cockpit Plugin to display the content of the inbox table

## Concepts and Components

* [Ingress Adapter](ingress.md)
* [Message Acceptor](message-acceptor.md)
* [Message Persistence](message-persistence.md)
* [Scheduled Processing](scheduled-processing.md)
* [Metrics](metrics.md)

## Examples

* [Kafka Example](examples.md#spring-cloud-streams-with-kafka)
* [Axon Example](examples.md#axon-events-aka-using-camunda-platform-7-as-microservice-orchestrator)

## References

* [Camunda Community Summit 2022 Talk](https://page.camunda.com/ccs2022-correlatingmessages?hsLang=en)
