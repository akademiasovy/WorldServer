package sk.akademiasovy.world.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import sk.akademiasovy.world.db.MySQL;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        writeCitiesIntoFile(country+".txt", list);

        return result;
    }

    private void writeCitiesIntoFile(String s, List<String> list) {
        try{
           File file=new File(s);
            FileWriter fw=new FileWriter(file);
            for(String city:list){
                fw.write(city+"  ");
            }
            fw.flush();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @POST
    @Path("/population")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPopulation(City city) {

         String population=new MySQL().getPopulation(city.name);
         String ret= "showPopulation({\"population\":\""+population+"\"})";
        System.out.println(city.name+" > > > "+ret);
        return ret;
    }

    public static class City{
        @JsonProperty("name")
        public String name;
    }
}
