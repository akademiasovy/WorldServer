package sk.akademiasovy.world.resources;

import org.hibernate.validator.constraints.NotEmpty;
import sk.akademiasovy.world.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by host on 9.2.2018.
 */

@Path("/world")
public class World {

    @GET
    @Path("/countries")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountries(){
        MySQL mySQL=new MySQL();
       List<String> list= mySQL.getCountries();
        System.out.println(list);
        boolean b= false;
       String result= "func({\"name\":[";
        for(String temp:list){
            if(b==true){
                result+=',';
            }
            else
               b=true;
            result+="\""+temp+"\"";
        }
        result+="]})";

        return result;
    }

    @GET
    @Path("/cities/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCities(@PathParam("country")  String country) {
        System.out.println(country);
        List<String>list= new MySQL().getCities(country);

        boolean b= false;
        String result= "showCities({\"name\":[";
        for(String temp:list){
            if(b==true){
                result+=',';
            }
            else
                b=true;
            result+="\""+temp+"\"";
        }
        result+="]})";

        return result;
    }

}
