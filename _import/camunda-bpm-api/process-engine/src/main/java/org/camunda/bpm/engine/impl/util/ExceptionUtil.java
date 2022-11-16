package org.camunda.bpm.engine.impl.util;

import org.camunda.bpm.engine.impl.__NotImplemented;

import java.sql.SQLException;

/**
 * Referenced from {@link org.camunda.bpm.engine.impl.errorcode.ExceptionCodeProvider}
 * Real implementation has dependencies to parts of persistence.
 */
public class ExceptionUtil {
  public static boolean checkDeadlockException(SQLException sqlException) {
    throw __NotImplemented.EXCEPTION;
  }

  public static boolean checkForeignKeyConstraintViolation(SQLException sqlException, boolean b) {
    throw __NotImplemented.EXCEPTION;
  }

  public static boolean checkValueTooLongException(SQLException sqlException) {
    throw __NotImplemented.EXCEPTION;
  }
}
