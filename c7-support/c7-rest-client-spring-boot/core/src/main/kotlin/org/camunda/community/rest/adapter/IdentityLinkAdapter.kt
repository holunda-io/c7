package org.camunda.community.rest.adapter

import org.camunda.bpm.engine.task.IdentityLink
import org.camunda.bpm.engine.task.IdentityLinkType
import org.camunda.community.rest.client.model.IdentityLinkDto

/**
 * Implementation of identity link delegating to a simple bean.
 */
class IdentityLinkAdapter(private val identityLinkBean: IdentityLinkBean) : IdentityLink {
  override fun getId(): String? = identityLinkBean.id
  override fun getType(): String = identityLinkBean.type
  override fun getTaskId(): String = identityLinkBean.taskId
  override fun getProcessDefId(): String? = identityLinkBean.processDefinitionId
  override fun getTenantId(): String? = identityLinkBean.tenantId

  override fun getUserId(): String? {
    return if (identityLinkBean is UserLinkBean) {
      identityLinkBean.userId
    } else {
      null
    }
  }

  override fun getGroupId(): String? {
    return if (identityLinkBean is GroupLinkBean) {
      identityLinkBean.groupId
    } else {
      null
    }
  }

}

/**
 * Abstract link.
 */
abstract class IdentityLinkBean(
  open val id: String?,
  open val type: String,
  open val taskId: String,
  open val processDefinitionId: String?,
  open val tenantId: String?
) {
  companion object {
    /**
     * Constructs identity link bean from DTO.
     */
    fun fromDto(taskId: String, dto: IdentityLinkDto): IdentityLinkBean {
      return when (dto.type) {
        "candidate" -> {
          if (dto.groupId != null) {
            GroupLinkBean(
              groupId = dto.groupId!!,
              taskId = taskId,
            )
          } else if (dto.userId != null) {
            CandidateUserLinkBean(
              userId = dto.userId!!,
              taskId = taskId,
            )
          } else {
            throw IllegalArgumentException("Wrong candidate link received.")
          }
        }
        "assignee" -> {
          AssigneeLinkBean(
            userId = dto.userId!!,
            taskId = taskId
          )
        }
        "owner" -> {
          OwnerLinkBean(
            userId = dto.userId!!,
            taskId = taskId
          )
        }
        else -> throw IllegalArgumentException("Unsupported identity link type: ${dto.type}")
      }
    }
  }
}

/**
 * Group link.
 */
data class GroupLinkBean(
  val groupId: String,
  override val taskId: String,
  override val id: String? = null,
  override val processDefinitionId: String? = null,
  override val tenantId: String? = null
) : IdentityLinkBean(
  id = id,
  taskId = taskId,
  processDefinitionId = processDefinitionId,
  tenantId = tenantId,
  type = IdentityLinkType.CANDIDATE
)

/**
 * Abstract user link.
 */
abstract class UserLinkBean(
  open val userId: String,
  override val id: String?,
  override val taskId: String,
  override val processDefinitionId: String?,
  override val tenantId: String?,
  override val type: String
) : IdentityLinkBean(
  id = id,
  taskId = taskId,
  processDefinitionId = processDefinitionId,
  tenantId = tenantId,
  type = type
)

/**
 * Candidate user link.
 */
data class CandidateUserLinkBean(
  override val userId: String,
  override val taskId: String,
  override val id: String? = null,
  override val processDefinitionId: String? = null,
  override val tenantId: String? = null
) : UserLinkBean(
  id = id,
  userId = userId,
  taskId = taskId,
  processDefinitionId = processDefinitionId,
  tenantId = tenantId,
  type = IdentityLinkType.CANDIDATE
)

/**
 * Assignee link.
 */
data class AssigneeLinkBean(
  override val userId: String,
  override val taskId: String,
  override val id: String? = null,
  override val processDefinitionId: String? = null,
  override val tenantId: String? = null
) : UserLinkBean(
  id = id,
  userId = userId,
  taskId = taskId,
  processDefinitionId = processDefinitionId,
  tenantId = tenantId,
  type = IdentityLinkType.ASSIGNEE
)

/**
 * Owner link.
 */
data class OwnerLinkBean(
  override val userId: String,
  override val taskId: String,
  override val id: String? = null,
  override val processDefinitionId: String? = null,
  override val tenantId: String? = null
) : UserLinkBean(
  id = id,
  userId = userId,
  taskId = taskId,
  processDefinitionId = processDefinitionId,
  tenantId = tenantId,
  type = IdentityLinkType.OWNER
)
