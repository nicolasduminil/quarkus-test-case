package fr.simplex_software.quarkus.test_case;

import jakarta.enterprise.context.*;
import jakarta.ws.rs.*;

import java.io.*;
import java.net.*;

@Path("host")
@ApplicationScoped
public class HostResource
{
  private static final String FMT = "*** My IP address is %s";

  @GET
  public String host() throws IOException
  {
    return String.format(FMT, InetAddress.getLocalHost().getHostAddress());
  }
}
