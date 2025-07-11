---
title: Camunda BPM Correlate Overview
---

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

## Main features

* Ingress Adapters:
    * Spring Cloud Streams (e.g. Kafka Streams, Rabbit MQ, Azure Event Hubs, AWS SQS, AWS SNS, Solace PubSub+, Google PubSub)
    * Axon Framework (Axon Event Bus)
* Inbox pattern on message receiving
* MetaData extractors:
    * Message based (Headers)
    * Channel based (Properties)
* Persisting Message Accepting Adapter
* Message Persistence
    * In-Memory
    * MyBatis (using the same DB as Camunda Platform 7)
* Asynchronous batch processor running correlation on schedule
    * Batch modes: all, fail-first
* Variety of error handling strategies on mismatched correlation (ignore, drop, retry)
* Configurable timings, retry strategies and many other parameters
* Message Buffering (TTL)
* Message Expiry
* Camunda Cockpit Plugin to display the content of the inbox table

## External References

* [Camunda Community Summit 2022 Talk](https://page.camunda.com/ccs2022-correlatingmessages?hsLang=en)
