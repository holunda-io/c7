package io.holunda.commons.immutables.task;

import org.camunda.bpm.engine.task.IdentityLinkType;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum IdentityLinkTypeEnum {
  ASSIGNEE(IdentityLinkType.ASSIGNEE),
  CANDIDATE(IdentityLinkType.CANDIDATE),
  OWNER(IdentityLinkType.OWNER),
  ;

  public String getType() {
    return type;
  }

  private final String type;

  public static final Set<String> TYPES = Stream.of(IdentityLinkTypeEnum.values())
    .map(IdentityLinkTypeEnum::getType)
    .collect(Collectors.toSet());

  IdentityLinkTypeEnum(String type) {
    this.type = type;
  }
}
