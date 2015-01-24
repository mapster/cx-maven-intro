package no.computas.workshop.mavenintro;

import java.util.List;

/**
 * Created by mapster on 24.01.15.
 */
public class Notes {
    public String[] notes;

    public Notes() {
    }

    public Notes(List<String> notes) {
        this.notes = notes.toArray(new String[notes.size()]);
    }
}
