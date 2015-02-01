package no.computas.workshop.mavenintro;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;

/**
 * Enkel notatblokk.
 */
@Path("/notes")
public class MyNotes {

    private static List<String> notes = new LinkedList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getNotes() {
        String s = new String();
        for(String note: notes) {
            if (!s.isEmpty()) {
                s += "\n";
            }
            s += note;
        }
        return s;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Notes getNotesAsJson() {
        return new Notes(notes);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNote(String note) {
        notes.add(note);
    }
}
