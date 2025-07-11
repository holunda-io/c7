## Why should I use this?

Imagine you integrate your Camunda Engine into a larger application landscape.
In doing so the inter-system communication becomes important and questions on
communication styles and patterns arise. In the world of self-contained systems,
the asynchronous communication with messages is wide adopted. This library helps
you to solve integration problems around correlation of messages with processes.

## How to start?

### Install Dependency

First install the extension using the corresponding ingress adapter (in this example we use Spring Cloud Stream for connecting with Kafka):

```xml

<dependencies>
  <!-- Core -->
  <dependency>
    <groupId>io.holunda</groupId>
    <artifactId>camunda-bpm-correlate-spring-boot-starter</artifactId>
  </dependency>

  <!-- Cloud Stream Ingress -->
  <dependency>
    <groupId>io.holunda</groupId>
    <artifactId>camunda-bpm-correlate-spring-cloud-stream</artifactId>
  </dependency>

  <!-- Kafka Binding -->
  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream-binder-kafka</artifactId>
  </dependency>

</dependencies>
```

### Configuration

Please add the configuration of the extension:

```yaml

correlate:
  enabled: true
  channels:
    my-kafka-channel:
      enabled: true
      type: stream
      beanName: special-name
  batch:
    mode: all # default fail_first -> 'all' will correlate one message after another, resulting in ignoring the order of receiving
    query:    # query scheduler
      pollInitialDelay: PT10S
      pollInterval: PT6S
    cleanup:  # cleanup of expired messages
      pollInitialDelay: PT1M
      pollInterval: PT1M
  message:
    timeToLiveAsString: PT10S # errors during TTL seconds after receiving are ignored
    payloadEncoding: jackson  # our bytes are actually JSON written by Jackson.
  persistence:
    messageMaxRetries: 5 # default 100 -> will try to deliver 5 times at most
    messageFetchPageSize: 100 # default 100
  retry:
    retryMaxBackoffMinutes: 5 # default 180 -> maximum 5 minutes between retries
    retryBackoffBase: 2.0 # value in minutes default 2.0 -> base in the power of retry to calculate the next retry

```

Now configure your basic Spring Cloud Streams Kafka configuration to looks like this (or similar).
Pay attention to the name of the function definition and the bindings' in channels. It results from the
value of `correlate.channels.<channel-nam>.beanName` and accordingly is part of the expression to
bind the parameter of the binding (`special-name-in-0`).

```yaml

spring:
  cloud:
    stream:
      function:
        definition: special-name
        bindings:
          special-name-in-0: correlate-ingress-binding      
      bindings:
        correlate-ingress-binding:
          content-type: application/json
          destination: ${KAFKA_TOPIC_CORRELATE_INGRES:correlate-ingress}
          binder: correlate-ingress-binder
          group: ${KAFKA_GROUP_ID}
      binders:
        correlate-ingress-binder:
          type: kafka
          defaultCandidate: false
          inheritEnvironment: false
          environment:
            spring:
              kafka:
                consumer:
                  key-deserializer: org.apache.kafka.common.serialization.ByteArrayDeserializer
                  value-deserializer: org.apache.kafka.common.serialization.ByteArrayDeserializer
              cloud:
                stream:
                  kafka:
                    binder:
                      autoCreateTopics: false
                      autoAddPartitions: false
                      brokers: ${KAFKA_BOOTSTRAP_SERVER_HOST:localhost}:${KAFKA_BOOTSTRAP_SERVER_PORT:9092}
                      configuration:
                        security.protocol: ${KAFKA_SECURITY_PROTOCOL_OVERRIDE:PLAINTEXT}

```
