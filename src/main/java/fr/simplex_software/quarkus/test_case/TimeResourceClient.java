package fr.simplex_software.quarkus.test_case;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.rest.client.inject.*;

import java.util.concurrent.*;

@Path("time")
@RegisterRestClient(configKey = "base_uri")
public interface TimeResourceClient
{
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  CompletionStage<Response> getCurrentDateAndTime();
  @GET
  @Path("{zoneId}")
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  CompletionStage<Response> getCurrentDateAndTimeAtZone(@PathParam("zoneId") String zoneId);
  @POST
  CompletionStage<Response> postCurrentDateAndTime();
}
