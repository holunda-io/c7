package io.holunda.camunda.bpm.correlate.persist.impl

import io.holunda.camunda.bpm.correlate.persist.MessageEntity
import io.holunda.camunda.bpm.correlate.persist.MessageEntity.Companion.FAR_FUTURE
import java.time.Instant
import java.util.UUID
import org.apache.ibatis.jdbc.ScriptRunner
import org.apache.ibatis.mapping.Environment
import org.apache.ibatis.session.Configuration
import org.apache.ibatis.session.SqlSessionFactoryBuilder
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory
import org.assertj.core.api.Assertions.assertThat
import org.h2.jdbcx.JdbcDataSource
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MyBatisMessageRepositoryIT {

  private lateinit var repository: MyBatisMessageRepository
  private lateinit var dataSource: JdbcDataSource

  @BeforeEach
  fun setUp() {
    dataSource = JdbcDataSource().apply {
      setURL("jdbc:h2:mem:${UUID.randomUUID()};DB_CLOSE_DELAY=-1")
      user = "sa"
    }
    dataSource.connection.use { connection ->
      val runner = ScriptRunner(connection)
      runner.setStopOnError(true)
      runner.setLogWriter(null)
      listOf("/schema.sql", "/some-messages.sql").forEach { resource ->
        requireNotNull(javaClass.getResourceAsStream(resource)).bufferedReader().use(runner::runScript)
      }
    }
    val configuration = Configuration(Environment("test", JdbcTransactionFactory(), dataSource)).apply {
      addMapper(MyBatisMessageMapper::class.java)
    }
    repository = MyBatisMessageRepository(SqlSessionFactoryBuilder().build(configuration))
  }

  @AfterEach
  fun tearDown() {
    dataSource.connection.use { connection ->
      connection.createStatement().use { it.execute("SHUTDOWN") }
    }
  }

  companion object {
    val MESSAGES_IDS_BY_INSERTED_ASC = listOf("4711", "4716", "4712", "4713", "4714", "4715")
    val MESSAGES_IDS_BY_INSERTED_ASC_FAULTS = listOf("4713", "4714", "4715")
  }

  @Test
  fun `selects one by id`() {

    val message = repository.findByIdOrNull("4711")
    assertThat(message).isNotNull.apply {
      extracting( MessageEntity::id.name ).isEqualTo("4711")
      extracting( MessageEntity::payloadTypeName.name ).isEqualTo("MyType")
      extracting( MessageEntity::payloadTypeNamespace.name ).isEqualTo("io.holixon.namespace")
      extracting( MessageEntity::payloadEncoding.name ).isEqualTo("JACKSON")
    }

    val nothing = repository.findByIdOrNull("does-not-exist")
    assertThat(nothing).isNull()

  }

  @Test
  fun `count by status`() {
    val now = Instant.parse("2022-09-01T10:00:00Z")

    val count = repository.countByStatus(3, now, FAR_FUTURE)
    assertThat(count.total).isEqualTo(6)
    assertThat(count.error).isEqualTo(3)
    assertThat(count.retrying).isEqualTo(1)
    assertThat(count.inProgress).isEqualTo(2)
    assertThat(count.maxRetriesReached).isEqualTo(1)
    assertThat(count.paused).isEqualTo(2)
  }

  @Test
  fun `select paged in correct order`() {
    val messages = repository.findAll(0, 100);
    assertThat(messages.map { it.id }).containsExactlyElementsOf(MESSAGES_IDS_BY_INSERTED_ASC)
  }

  @Test
  fun `select light in correct order`() {
    val messages = repository.findAllLight(0, 100, false);
    assertThat(messages.map { it.id }).containsExactlyElementsOf(MESSAGES_IDS_BY_INSERTED_ASC)
  }

  @Test
  fun `select light faults in correct order`() {
    val messages = repository.findAllLight(0, 100, true);
    assertThat(messages.map { it.id }).containsExactlyElementsOf(MESSAGES_IDS_BY_INSERTED_ASC_FAULTS)
  }

  @Test
  fun `select second page in correct order`() {
    val messages = repository.findAll(1, 3)
    assertThat(messages.map { it.id }).containsExactlyElementsOf(MESSAGES_IDS_BY_INSERTED_ASC.subList(3, MESSAGES_IDS_BY_INSERTED_ASC.size))
  }

  @Test
  fun `select light second page in correct order`() {
    val messages = repository.findAllLight(1, 3, false)
    assertThat(messages.map { it.id }).containsExactlyElementsOf(MESSAGES_IDS_BY_INSERTED_ASC.subList(3, MESSAGES_IDS_BY_INSERTED_ASC.size))
  }

  @Test
  fun `select light faults second page in correct order`() {
    val messages = repository.findAllLight(1, 2, true);
    assertThat(messages.map { it.id }).containsExactlyElementsOf(MESSAGES_IDS_BY_INSERTED_ASC_FAULTS.subList(2, MESSAGES_IDS_BY_INSERTED_ASC_FAULTS.size))
  }
}
