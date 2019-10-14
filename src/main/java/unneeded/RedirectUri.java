package unneeded;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/get")
public class RedirectUri {

    @GET
    @Path("/get")
    public Response getUsers(@Context UriInfo info) {

        String code = info.getQueryParameters().getFirst("code");

        return Response
                .status(200)
                .entity("code : " + code).build();
    }
}