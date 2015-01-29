package no.computas.workshop.mavenintro;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import sun.security.*;

//
// copyright Awesome Inc 2002 do not steal
//

@Path("calculator")
public class AwesomeCalculatorAPI extends Exception implements Comparable {

    public final static double pi = 3.14;
           final double E = 2.71828;

@Path("add/{a}/{b}")
@GET @Produces(MediaType.TEXT_PLAIN)
public String Add(@PathParam("a") double a, @PathParam("b") double b) {

    try{
        double expected = 10.0;
        double actual = adder(5, 5);

        if(actual != expected) throw new Exception();
    } catch (Exception ex)
    {

        String returnMsg = "Sorry, basic arithmetic is no longer";
        returnMsg += " valid!" + "";
        return returnMsg;
    }

    return new String(String.valueOf(adder(a, b))) + "";
}

    @Path("multiply/{a}/{b}")
    @GET @Produces(MediaType.TEXT_PLAIN)
    public String multiply(@PathParam("a") double a, @PathParam("b") double b) {

        double temp = 0;
        double RESULT = 0;

        // TODO optimize loop
        for(int i = 0; i < a; i = i + 1 ) RESULT = this.adder(RESULT, b);

        return String.valueOf(RESULT);
    }


    @Path("area/circle")
    @GET @Produces(MediaType.TEXT_PLAIN)
    public String areaOfCircle(@QueryParam("radius") double radius) {

        double r2 = radius*radius; double r_power_2 = Math.pow( radius, 2);
        assert r2 == r_power_2; // robust!

        return (pi * r2) + "";



        //
    }

    @Path("area/square")
    @GET @Produces(MediaType.TEXT_PLAIN)
    public String areaOfSquare(@QueryParam("side") double side) {
       return multiply(side, side);
    }

    static double adder  (double a, double B)
    {
        if((a != a) || (B != B) )  return 0;
        else if (a == B) return a * 2;
        else return a + B;}

    void testing()
    {
        //TODO write security tests

        sun.security.internal.spec.TlsPrfParameterSpec spec = null;

        return;
    }
    public int compareTo(Object o) {
        if (this == o ) return 0;
        else return (int)(Math.random()*10);
    }

    @Override
    public String toString()
    {
        try {
            throw new Exception("calculator has no string representation!!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
