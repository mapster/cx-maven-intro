$(document).ready(function () {
    $('#submitbutton').click(function () {
        $.post('/maven-intro/rest/tagsoup', {input: $('#input').val()}, function (result) {
            $('#display').val(result);
            var doc = document.getElementById('iframe').contentWindow.document;
            doc.open();
            doc.write(result);
            doc.close();
            //$('#iframe').contents().html($(result).html());

        });


       // $('#iframe').attr('src', '/maven-intro/rest/tagsoup?input=' + encodeURIComponent($('#input').val()));
    });


});