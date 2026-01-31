package org.camunda.community.rest.exception

import org.camunda.bpm.engine.ProcessEngineException

/**
 * Exception thrown by accessing remote Camunda engine.
 * @constructor Creates the exception.
 * @param message message of the exception.
 * @param cause cause.
 */
class RemoteProcessEngineException(
  override val message: String,
  override val cause: Throwable? = null
) : ProcessEngineException(message, cause)
