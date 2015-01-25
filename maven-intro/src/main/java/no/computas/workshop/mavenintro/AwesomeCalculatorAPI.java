package no.computas.workshop.mavenintro;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

// copyright Awesome Inc 2002 do not steal
//

@Path("calculator")
public class AwesomeCalculatorAPI {

    public final static double pi = 3.14;
           final static double E = 2.71828;

    @Path("add/{a}/{b}")
    @GET @Produces(MediaType.TEXT_PLAIN)
    public String Add(@PathParam("a") double a, @PathParam("b") double b) {

        try{
            double expected = 10.0;
            double actual = adder(5, 5);

            if(actual != expected) throw new Exception();
        } catch (Exception ex)
        {
            return "Sorry, basic arithmetic is no longer valid!";
        }

        return new String(String.valueOf(adder(a, b)));
    }

    double adder  (double a, double B)
    {
        if(a != a)  return 0;

        return a + B;
    }
}
