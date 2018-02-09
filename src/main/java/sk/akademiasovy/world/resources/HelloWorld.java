package sk.akademiasovy.world.resources;

import netscape.javascript.JSObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by host on 9.2.2018.
 */

@Path("/hello")
public class HelloWorld {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getGreeting(){
        return "Hello. I am server.";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/about")
    public String about(){
        return "{\"firstname\":\"Ivan\", \"lastname\":\"Hrozny\",\"age\": \"28\"}";

    }
}
