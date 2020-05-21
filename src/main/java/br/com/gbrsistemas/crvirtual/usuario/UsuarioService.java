package br.com.gbrsistemas.crvirtual.usuario;

import br.com.gbrsistemas.crvirtual.Resultado;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("usuarios")
public class UsuarioService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarios() {
        List<Usuario> users = new ArrayList<>();
        users.add(new Usuario(1, "Rafael Guimarães"));
        users.add(new Usuario(2, "Ivan Junckes Filho"));

        Resultado<Usuario> userResult = new Resultado<>();
        userResult.setItens(users);
        userResult.setTotal(Long.valueOf(users.size()));
        return Response.ok(userResult).build();
    }
}
