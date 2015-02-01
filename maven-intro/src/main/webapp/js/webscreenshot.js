$(document).ready(function () {
    $('#input').bind('keypress', function(event) {
        if(event.which == 13) {
            event.preventDefault();
            webscreenshot();
        }
    });

    $('#submitbutton').click(webscreenshot);

    function webscreenshot() {
        var button = $(this);

        var div = $('<div></div>').addClass('screenshot');
        var input = $('#input').val();
        if (!input.match(/^https?:\/\//)) {
            input = 'http://' + input;
        }
        var heading = $('<p></p>').text(input).addClass('url');
        var img = $('<img />').attr('src', 'images/loading.gif').addClass('image');

        div.append(heading);
        div.append(img);

        $('#displayimage').prepend(div);

        $.ajax({
            type: 'POST',
            url: '/maven-intro/rest/webscreenshot',
            dataType: 'text',
            data: {url: input},
            success: function (result) {
                img.load(function () {
                    img.css({width: '100%'});
                    img.click(function () {
                        window.open($(this).attr('src'), input, '');
                        return false;
                    });
                });
                img.attr('src', '/maven-intro/rest/webscreenshot?filename=' + encodeURIComponent(result));
            },
            error: function (a, b, c) {
                img.css({width: '100%'});
                img.attr('src', 'images/error_button.png');
            }
        });


    }
});