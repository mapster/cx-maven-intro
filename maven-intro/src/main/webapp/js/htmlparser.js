$(document).ready(function () {
    $('#submitbutton').click(function () {
        $.post('/maven-intro/rest/htmlparser', {input: $('#input').val()}, function (result) {
            $('#display').val(result);
            var doc = document.getElementById('iframe').contentWindow.document;
            doc.open();
            doc.write(result);
            doc.close();
        });
    });

    $('#clearbutton').click(function () {
        $('#input').val('');
    });
});