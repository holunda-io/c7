---
title: Overview
---

# Holunda C7: Camunda 7 Open Source Libraries

Holunda C7 is a comprehensive collection of open-source libraries and tools designed to enhance and extend Camunda Platform 7. With Camunda 7 reaching its
end-of-life in October 2025, this repository serves as a centralized hub for maintaining critical components that were previously released independently. Our
mission is to provide ongoing support for enterprise customers utilizing Camunda 7 Enterprise Edition, which will continue to receive Long-Term Support (LTS)
for years to come. By consolidating these libraries in one place, we ensure consistent maintenance, compatibility, and documentation across the entire
ecosystem.

The repository encompasses a wide range of functionalities organized into support, operations, and testing categories. Each library addresses specific needs
within the Camunda 7 environment, from improved process data handling and message correlation to enhanced testing capabilities and REST API clients. Whether
you're managing complex workflows, optimizing operations, or ensuring quality through comprehensive testing, Holunda C7 provides the tools necessary to maximize
your Camunda 7 investment. As the platform transitions to end-of-life, this repository becomes increasingly valuable for organizations committed to maintaining
and extending their Camunda 7 implementations with enterprise-grade reliability and performance.

This user guide gives you insight of features, scenarios and application examples of the included components and libraries.

# Modules

Here is the overview of currently supported modules:

## Support

Includes libraries built as improvements to Camunda 7 engine itself.

<div class="grid cards" markdown>

-   :material-clock-fast:{ .lg .middle } __camunda-bpm-correlate__

    ---

    Solution for correlation of messages with processes running in Camunda 7

    [:octicons-arrow-right-24: Reference](user-guide/camunda-bpm-correlate/index.md)

-   :fontawesome-brands-markdown:{ .lg .middle } __camunda-bpm-data__

    ---

    Beautiful process data handling for Camunda 7.

    [:octicons-arrow-right-24: Reference](user-guide/camunda-bpm-data/index.md)

-   :material-format-font:{ .lg .middle } __camunda-bpm-spring-boot-deployment__

    ---

    Take back control on artifact deployment in Camunda 7

    [:octicons-arrow-right-24: Reference](user-guide/c7-deployment/index.md)

-   :material-scale-balance:{ .lg .middle } __camunda-platform-7-custom-batch__

    ---

    Perform custom batch operations on the shoulders of the Camunda 7 Job executor

    [:octicons-arrow-right-24: Reference](#)

-   :material-scale-balance:{ .lg .middle } __c7-rest-client-spring-boot__

    ---

    Full-featured client for Camunda 7 REST API providing implementation of Camunda Java API

    [:octicons-arrow-right-24: Reference](user-guide/c7-rest-client-spring-boot/index.md)

-   :material-clock-fast:{ .lg .middle } __camunda-api__

    ---

    API layer of Camunda 7 Java API

    [:octicons-arrow-right-24: Reference](user-guide/c7-api/index.md)

-   :fontawesome-brands-markdown:{ .lg .middle } __c7-commons-immutables__

    ---

    Immutables implementation of Camunda 7 Java data objects

    [:octicons-arrow-right-24: Reference](user-guide/c7-immutables/index.md)


</div>


## Operations

Includes extensions used for improved operations of Camunda 7.

<div class="grid cards" markdown>

-   :material-format-font:{ .lg .middle } __camunda-platform-7-autologin__

    ---

    Camunda 7 WebApp Autologin

    [:octicons-arrow-right-24: Reference](user-guide/c7-webapp-autologin/index.md)

-   :material-scale-balance:{ .lg .middle } __camunda-admin-process-registry__

    ---

    Process Registry for inline-defined one-function adin processes

    [:octicons-arrow-right-24: Reference](user-guide/c7-admin-process-registry/index.md)

</div>

## Testing

Includes features for testing.

<div class="grid cards" markdown>

-   :material-clock-fast:{ .lg .middle } __camunda-bpm-jgiven__

    ---

    Solution for correlation of messages with processes running in Camunda 7

    [:octicons-arrow-right-24: Reference](user-guide/c7-jgiven/index.md)

-   :fontawesome-brands-markdown:{ .lg .middle } __c7-mockito__

    ---

    Simplify mocking and stubbung for process testing in Camunda 7

    [:octicons-arrow-right-24: Reference](user-guide/c7-mockito/index.md)

-   :material-format-font:{ .lg .middle } __c7-process-test-coverage__

    ---

    Visualise test process paths and ensure your process model coverage ratio

    [:octicons-arrow-right-24: Reference](user-guide/c7-process-test-coverage/index.md)


</div>
