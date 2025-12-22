package org.camunda.community.process_test_coverage.report;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Martin Schimak <martin.schimak@plexiti.com>
 */
public class ClassLocationURLTest {

  @Test
  public void test_urlFromCodeSource_JavaLangStringClass() {
    URL url = ClassLocationURL.urlFromCodeSource(String.class);
    assertThat(url).isNull();
  }

  @Test
  public void test_urlFromCodeSource_OrgJunitTestClass() {
    URL url = ClassLocationURL.urlFromCodeSource(Test.class);
    assertThat(url).isNotNull();
    assertThat(url.toExternalForm()).startsWith("file:");
    assertThat(url.toExternalForm()).endsWith(".jar");
  }

  @Test
  public void test_urlFromCodeSource_ThisClass() {
    URL url = ClassLocationURL.urlFromCodeSource(ClassLocationURLTest.class);
    assertThat(url).isNotNull();
    assertThat(url.toExternalForm().startsWith("file:")).isTrue();
    assertThat(url.toExternalForm().endsWith(".jar")).isFalse();
    assertThat(url.toExternalForm().endsWith("/")).isTrue();
  }

  @Test
  public void test_urlFromResource_JavaLangStringClass() {
    URL url = ClassLocationURL.urlFromResource(String.class);
    assertThat(url).isNotNull();
//    doesn't work in Java 11: Assert.assertTrue(url.toExternalForm().startsWith("file:"));
//    doesn't work in Java 11: Assert.assertTrue(url.toExternalForm().endsWith(".jar"));
  }

  @Test
  public void test_urlFromResource_OrgJunitTestClass() {
    URL url = ClassLocationURL.urlFromResource(Test.class);
    assertThat(url).isNotNull();
    assertThat(url.toExternalForm().startsWith("file:")).isTrue();
    assertThat(url.toExternalForm().endsWith(".jar")).isTrue();
  }

  @Test
  public void test_urlFromResource_ThisClass() {
    URL url = ClassLocationURL.urlFromResource(ClassLocationURLTest.class);
    assertThat(url).isNotNull();
    assertThat(url.toExternalForm().startsWith("file:")).isTrue();
    assertThat(url.toExternalForm().endsWith(".jar")).isFalse();
    assertThat(url.toExternalForm().endsWith("/")).isTrue();
  }

  @Test
  public void test_locationFor_JavaLangStringClass() {
    URL url = ClassLocationURL.locationFor(String.class);
    assertThat(url).isNotNull();
//    doesn't work in Java 11: Assert.assertTrue(url.toExternalForm().startsWith("file:"));
//    doesn't work in Java 11: Assert.assertTrue(url.toExternalForm().endsWith(".jar"));
  }

  @Test
  public void test_locationFor_OrgJunitTestClass() {
    URL url = ClassLocationURL.locationFor(Test.class);
    assertThat(url).isNotNull();
    assertThat(url.toExternalForm().startsWith("file:")).isTrue();
    assertThat(url.toExternalForm().endsWith(".jar")).isTrue();
  }

  @Test
  public void test_locationFor_ThisClass() {
    URL url = ClassLocationURL.locationFor(ClassLocationURLTest.class);
    assertThat(url).isNotNull();
    assertThat(url.toExternalForm().startsWith("file:")).isTrue();
    assertThat(url.toExternalForm().endsWith(".jar")).isFalse();
    assertThat(url.toExternalForm().endsWith("/")).isTrue();
  }

  @Test
  @Disabled // doesn't work in Java 11
  public void test_fileFor_JavaLangStringClass() {
    File file  = ClassLocationURL.fileFor(String.class);
    assertThat(file).isNotNull();
    assertThat(file.getAbsolutePath().endsWith(".jar")).isTrue();
    assertThat(file.isFile()).isTrue();
  }

  @Test
  public void test_fileFor_OrgJunitTestClass() {
    File file = ClassLocationURL.fileFor(Test.class);
    assertThat(file).isNotNull();
    assertThat(file.getAbsolutePath().endsWith(".jar")).isTrue();
    assertThat(file.isFile()).isTrue();
  }

  @Test
  public void test_fileFor_ThisClass() {
    File file = ClassLocationURL.fileFor(ClassLocationURLTest.class);
    assertThat(file).isNotNull();
    assertThat(file.getAbsolutePath().endsWith(".jar")).isFalse();
    assertThat(file.isDirectory()).isTrue();
  }

}
