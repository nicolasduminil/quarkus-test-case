package fr.simplex_software.quarkus.test_case;

import jakarta.enterprise.context.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.*;
import jakarta.ws.rs.core.*;

import java.net.*;
import java.nio.charset.*;
import java.time.*;
import java.time.format.*;

@ApplicationScoped
@Path("time")
public class TimeResource
{
  private static final String FMT = "d MMM uuuu, HH:mm:ss XXX z";

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public void getCurrentDateAndTime(@Suspended AsyncResponse ar)
  {
    ar.resume(Response.ok().entity(ZonedDateTime.now().format(DateTimeFormatter.ofPattern(FMT)
      .withZone(ZoneId.systemDefault()))).build());
  }

  @GET
  @Path("{zoneId}")
  @Produces(MediaType.TEXT_PLAIN)
  @Consumes(MediaType.TEXT_PLAIN)
  public void getCurrentDateAndTimeAtZone(@PathParam("zoneId") String zoneId,  @Suspended AsyncResponse ar)
  {
    String decodedZoneId = URLDecoder.decode(zoneId, StandardCharsets.UTF_8);
    ar.resume(Response.ok().entity(ZonedDateTime.now(ZoneId.of(decodedZoneId)).format(DateTimeFormatter
      .ofPattern(FMT).withZone(ZoneId.of(decodedZoneId)))).build());
  }

  @POST
  public void postCurrentDateAndTime(@Suspended AsyncResponse ar)
  {
    ar.resume(Response.ok().entity(ZonedDateTime.now().format(DateTimeFormatter.ofPattern(FMT)
      .withZone(ZoneId.systemDefault()))).build());
  }
}
