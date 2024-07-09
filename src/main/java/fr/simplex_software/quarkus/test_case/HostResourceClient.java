package fr.simplex_software.quarkus.test_case;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.*;

import java.io.*;

@Path("host")
@RegisterRestClient(configKey = "base_uri")
public interface HostResourceClient
{
  @GET
  public String host() throws IOException;
}
