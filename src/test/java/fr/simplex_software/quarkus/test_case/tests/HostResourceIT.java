package fr.simplex_software.quarkus.test_case.tests;

import fr.simplex_software.quarkus.test_case.*;
import io.quarkus.test.junit.*;
import jakarta.inject.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.rest.client.inject.*;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.assertj.core.api.Assertions.*;

@QuarkusTest
public class HostResourceIT
{
  @Inject
  @RestClient
  HostResourceClient hostResourceClient;
  @Inject
  @ConfigProperty(name = "base_uri/mp-rest/url")
  String baseURI;

  @Test
  public void testHostResource() throws IOException
  {
    String host = hostResourceClient.host();
    assertThat(host).isNotEmpty();
    assertThat(host).startsWith("***");
  }
}
