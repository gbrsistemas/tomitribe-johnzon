package br.com.gbrsistemas.crvirtual.usuario;

import br.com.gbrsistemas.crvirtual.Resultado;
import org.apache.johnzon.jaxrs.JohnzonProvider;
import org.apache.ziplock.maven.Mvn;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.impl.base.spec.WebArchiveImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class UsuarioServiceTest {
    protected static String SERVICE_ROOT;

    @ArquillianResource
    private URL base;

    protected Client client;
    protected WebTarget target;


    static {
        SERVICE_ROOT = "usuarios";
    }

    @Before
    public void before() {
        this.client = ClientBuilder.newClient().register(JohnzonProvider.class);
        this.target = getWebTarget(SERVICE_ROOT);
    }

    protected WebTarget getWebTarget(final String serviceRoot) {
        WebTarget target = client.target(base.toExternalForm() + "/api/" + serviceRoot);
        return target;
    }

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        final WebArchiveImpl war = (WebArchiveImpl) Mvn.war();
        return war;
    }

    @Test
    public void testUsuarios() {
        final ParameterizedType pt = new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] { Usuario.class };
            }

            @Override
            public Type getRawType() {
                return Resultado.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };

        Response response = target.request().accept(MediaType.APPLICATION_JSON).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Resultado<Usuario> usuarios = response.readEntity(new GenericType<Resultado<Usuario>>(pt));
        assertNotNull(usuarios);
    }

    @After
    public void after() {
        this.client.close();
    }
}