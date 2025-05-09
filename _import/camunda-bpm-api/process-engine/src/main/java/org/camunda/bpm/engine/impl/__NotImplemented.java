package org.camunda.bpm.engine.impl;

/**
 * Contains a dummy exception to make compiler happy and notify the user of the misuse of this library.
 */
public interface __NotImplemented {
  UnsupportedOperationException EXCEPTION = new UnsupportedOperationException(
    "This is a fake implementation provided by camunda-bpm-api. Please use the real engine instead.");
}
