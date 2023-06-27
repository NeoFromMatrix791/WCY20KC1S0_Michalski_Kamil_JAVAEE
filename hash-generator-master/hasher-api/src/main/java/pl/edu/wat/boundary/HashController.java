package pl.edu.wat.boundary;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.script.ScriptException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.wat.model.HashResult;
import pl.edu.wat.service.HashService;

@Stateless
@Path("/hash")
public class HashController {

    @EJB
    private HashService hashService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateHash(@NotNull @QueryParam("input") final String input) {
        try {
            final HashResult result = hashService.generateHash(input);
            return Response.ok(result).build();
        } catch (ScriptException | NoSuchMethodException | FileNotFoundException | URISyntaxException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
