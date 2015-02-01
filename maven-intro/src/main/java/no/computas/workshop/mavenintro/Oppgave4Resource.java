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
        String result = "sett resultat i denne strengen";

        //
        //  Legg til kode under her.
        //
        ////////////////////////////

            //her

        ////////////////////////////
        //
        //  Legg til kode over her.
        //
        return result;
    }

    @GET
    @Path("result2")
    public String getResultTwo(@QueryParam("input") String input) {
        String result = "sett resultat i denne strengen";

        //
        //  Legg til kode under her.
        //
        ////////////////////////////

            //her

        ////////////////////////////
        //
        //  Legg til kode over her.
        //
        return result;
    }

    @GET
    @Path("result3")
    public String getResultThree(@QueryParam("input") String input) {
        String result = "sett resultat i denne strengen";

        //
        //  Legg til kode under her.
        //
        ////////////////////////////

            //her

        ////////////////////////////
        //
        //  Legg til kode over her.
        //
        return result;
    }
}
