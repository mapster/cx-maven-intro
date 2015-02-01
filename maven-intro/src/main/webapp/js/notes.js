var MyNotes = MyNotes || {};

(function(ns, $) {
    var setNoteList = function (data) {
        var notes = data.notes;
        var list = $("#note-list");
        list.empty();

        var i;
        for (i = 0; i < notes.length; i++) {
            list.append("<li>"+notes[i]+"</li>")
        }
    };

    var updateNotes = function() {
        $.get("rest/notes", null, setNoteList, "json");
    };

    var addNote = function(note) {
        $.ajax({
            type:   "POST",
            url:    "rest/notes",
            contentType: "text/plain",
            data: note,
            success: updateNotes
        });
//        $.post("rest/notes", note, updateNotes, "text");
    };

    ns.updateNotes = updateNotes;
    ns.addNote = addNote;
}(MyNotes, jQuery));