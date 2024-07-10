package fr.simplex_software.quarkus.test_case.tests;

import io.quarkus.test.junit.*;
import org.junit.jupiter.api.*;
import org.slf4j.*;
import org.testcontainers.containers.*;
import org.testcontainers.containers.startupcheck.*;
import org.testcontainers.utility.*;

import java.time.*;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class HostResourcePostmanIT
{
  private static final Logger LOG = LoggerFactory.getLogger(HostResourcePostmanIT.class);

  private static final GenericContainer<?> postman = new GenericContainer<>("postman/newman")
    .withNetworkMode("host")
    .withCopyFileToContainer(MountableFile.forClasspathResource("postman/test.postman_collection.json"),
      "/etc/newman/test.postman_collection.json")
    .withStartupCheckStrategy(new OneShotStartupCheckStrategy().withTimeout(Duration.ofSeconds(10)));

  @Test
  public void run()
  {
    postman.withCommand("run", "test.postman_collection.json",
      "--global-var base_uri=localhost:8081");
    postman.start();
    LOG.info(postman.getLogs());
    assertThat(postman.getCurrentContainerInfo().getState().getExitCodeLong()).isZero();
    postman.stop();
  }
}
