package org.camunda.bpm.dmn.engine.impl;

import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.dmn.engine.delegate.DmnDecisionEvaluationListener;
import org.camunda.bpm.dmn.engine.delegate.DmnDecisionTableEvaluationListener;
import org.camunda.bpm.dmn.engine.spi.DmnEngineMetricCollector;

import java.util.List;

/**
 * Referenced from the {@link DmnEngineConfiguration}.
 */
public class DefaultDmnEngineConfiguration extends DmnEngineConfiguration {
  @Override
  public DmnEngineMetricCollector getEngineMetricCollector() {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public void setEngineMetricCollector(DmnEngineMetricCollector engineMetricCollector) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public DmnEngineConfiguration engineMetricCollector(DmnEngineMetricCollector engineMetricCollector) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public List<DmnDecisionTableEvaluationListener> getCustomPreDecisionTableEvaluationListeners() {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public void setCustomPreDecisionTableEvaluationListeners(List<DmnDecisionTableEvaluationListener> decisionTableEvaluationListeners) {

  }

  @Override
  public DmnEngineConfiguration customPreDecisionTableEvaluationListeners(
    List<DmnDecisionTableEvaluationListener> decisionTableEvaluationListeners) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public List<DmnDecisionTableEvaluationListener> getCustomPostDecisionTableEvaluationListeners() {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public void setCustomPostDecisionTableEvaluationListeners(List<DmnDecisionTableEvaluationListener> decisionTableEvaluationListeners) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public DmnEngineConfiguration customPostDecisionTableEvaluationListeners(
    List<DmnDecisionTableEvaluationListener> decisionTableEvaluationListeners) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public List<DmnDecisionEvaluationListener> getCustomPreDecisionEvaluationListeners() {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public void setCustomPreDecisionEvaluationListeners(List<DmnDecisionEvaluationListener> decisionEvaluationListeners) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public DmnEngineConfiguration customPreDecisionEvaluationListeners(List<DmnDecisionEvaluationListener> decisionEvaluationListeners) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public List<DmnDecisionEvaluationListener> getCustomPostDecisionEvaluationListeners() {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public void setCustomPostDecisionEvaluationListeners(List<DmnDecisionEvaluationListener> decisionEvaluationListeners) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public DmnEngineConfiguration customPostDecisionEvaluationListeners(List<DmnDecisionEvaluationListener> decisionEvaluationListeners) {
    throw __NotImplemented.EXCEPTION;
  }

  @Override
  public DmnEngine buildEngine() {
    throw __NotImplemented.EXCEPTION;
  }
}
