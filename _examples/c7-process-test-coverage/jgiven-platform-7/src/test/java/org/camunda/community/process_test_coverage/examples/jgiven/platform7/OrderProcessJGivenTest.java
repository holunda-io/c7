package org.camunda.community.process_test_coverage.examples.jgiven.platform7;

import com.tngtech.jgiven.annotation.ScenarioState;
import com.tngtech.jgiven.junit5.ScenarioTest;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.assertions.bpmn.AbstractAssertions;
import org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests;
import org.camunda.bpm.engine.variable.impl.VariableMapImpl;
import org.camunda.community.process_test_coverage.junit5.platform7.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@Deployment(resources = "order-process.bpmn")
public class OrderProcessJGivenTest extends ScenarioTest<OrderProcessStage, OrderProcessStage, OrderProcessStage> {

    @RegisterExtension
    private static final ProcessEngineCoverageExtension extension = ProcessEngineCoverageExtension.Companion.builder().build();

    @ScenarioState
    private final ProcessEngine processEngine = extension.getProcessEngine();

    @BeforeAll
    public static void reset() {
        // Process engine is cached in the thread and therefore the engine from earlier tests would be used
        AbstractAssertions.reset();
    }

    @Test
    public void shouldExecuteHappyPath() {

        given()
            .process_is_started()
            .and().process_waits_in("Task_ProcessOrder")
        .when()
            .task_is_completed_with_variables(new VariableMapImpl(BpmnAwareTests.withVariables("orderOk", true)))
            .and().process_waits_in("Task_DeliverOrder")
            .and().task_is_completed_with_variables(new VariableMapImpl())
        .then()
            .process_is_finished()
            .process_has_passed("Event_OrderProcessed");
    }

    @Test
    public void shouldCancelOrder() {
        given()
            .process_is_started()
            .and().process_waits_in("Task_ProcessOrder")
        .when()
            .task_is_completed_with_variables(new VariableMapImpl(BpmnAwareTests.withVariables("orderOk", false)))
        .then()
            .process_is_finished().process_has_passed("Event_OrderCancelled");
    }

}
