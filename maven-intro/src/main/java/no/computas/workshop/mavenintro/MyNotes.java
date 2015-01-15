package no.computas.workshop.mavenintro;

//import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Enkel notatblokk.
 */
@Path("notes")
public class MyNotes {

    private static List<String> notes = new LinkedList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getNotes() {
        String s = new String();
        for(String note: notes) {
            s += note + "\n";
        }
        return s;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNote(String note) {
        notes.add(note);
    }
}
