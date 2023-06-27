package pl.edu.wat.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.edu.wat.control.HashService;
import pl.edu.wat.model.HashDTO;

@Path("/hash")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HashController {

    @Inject
    private HashService hashService;

    @GET
    public Collection<HashDTO> getAllHashes() {
        return hashService.getAll();
    }

    @POST
    public Response saveHash(@NotNull @Valid final HashDTO dto) {
        hashService.save(dto);

        return Response.ok().build();
    }

    @DELETE
    public Response deleteHash(@QueryParam("hash128") final String hash128,
                               @QueryParam("hash256") final String hash256,
                               @QueryParam("hash512") final String hash512) {
        final HashDTO dto = new HashDTO(hash128, hash256, hash512);
        hashService.remove(dto);

        return Response.ok().build();
    }
}
