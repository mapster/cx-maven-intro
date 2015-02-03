package no.computas.workshop.mavenintro;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 * Created by mapster on 01.02.15.
 */
@Path("oppg4")
public class Oppgave4Resource {

    @GET
    @Path("result1")
    public String getResultOne(@QueryParam("input") String input) {
        String result = input.toUpperCase();
        return result;
    }

    @GET
    @Path("result2")
    public String getResultTwo(@QueryParam("input") String input) {
        String result = "result2: input="+input;

        ////////////////////////////
        //  Legg til kode under her.
        ////////////////////////////

        return result;
    }

    @GET
    @Path("result3")
    public String getResultThree(@QueryParam("input") String input) {
        String result = "result3: input="+input;

        ////////////////////////////
        //  Legg til kode under her.
        ////////////////////////////

        return result;
    }

    @GET
    @Path("result4")
    public String getResultFour(@QueryParam("input") String input) {
        String result = "result4: input="+input;

        ////////////////////////////
        //  Legg til kode under her.
        ////////////////////////////

        return result;
    }

    @GET
    @Path("result5")
    public String getResultFive(@QueryParam("input") String input) {
        String result = "result5: input="+input;

        ////////////////////////////
        //  Legg til kode under her.
        ////////////////////////////

        return result;
    }

    @GET
    @Path("result6")
    public String getResultSix(@QueryParam("input") String input) {
        String result = "result6: input="+input;

        ////////////////////////////
        //  Legg til kode under her.
        ////////////////////////////

        return result;
    }

    @GET
    @Path("result7")
    public String getResultSeven(@QueryParam("input") String input) {
        String result = "result7: input="+input;

        ////////////////////////////
        //  Legg til kode under her.
        ////////////////////////////

        return result;
    }

    @GET
    @Path("result8")
    public String getResultEight(@QueryParam("input") String input) {
        String result = "result8: input="+input;

        ////////////////////////////
        //  Legg til kode under her.
        ////////////////////////////

        return result;
    }
}
