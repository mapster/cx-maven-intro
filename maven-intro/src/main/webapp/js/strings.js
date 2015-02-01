var MyStrings = MyStrings || {};

(function(ns, $) {
    ns.rows = 0;

    var addResultRow = function(servicePath) {
        ns.rows += 1;
        var id = ns.rows;
        var row = $("<tr>");
        row.append('<td>'+"rest/oppg4/"+servicePath+'</td>')
        row.append('<td><input size="25" id="input'+id+'"></td>');
        row.append('<td id="result'+id+'">');
        row.append('<td><button id="submit'+id+'">Submit</button></td');

        $("#result-table").append(row);
        $("#submit"+id).click(function() {
            $.ajax({
                url: "rest/oppg4/"+servicePath,
                type: "GET",
                data: {input: $("#input"+id).val()},
                success: function(response) {
                    $("#result"+id).text(response);
                },
                error: function() {
                    $("#result"+id).text("Feil: ingen tjeneste tilgjengelig.");
                }
            });
        });
    };
    ns.addResultRow = addResultRow;
}(MyStrings, jQuery));