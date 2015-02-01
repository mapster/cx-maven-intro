$(document).ready(function () {
    post();
    var typingTimer;
    var doneTypingInterval = 500;

    $('#input').keyup(function () {
        clearTimeout(typingTimer);
        typingTimer = setTimeout(post, doneTypingInterval);
    });

    $('#input').keydown(function () {
        clearTimeout(typingTimer);
    });

    $('#clearbutton').click(function () {
        $('#input').val('');
    });

    function post() {
        $.post('/maven-intro/rest/htmlparser', {input: $('#input').val()}, function (result) {
            $('#display').val(result);
            var doc = document.getElementById('iframe').contentWindow.document;
            doc.open();
            doc.write(result);
            doc.close();
        });
    }
});