$(document).ready(function () {
    $('#submitbutton').click(function () {
        var button = $(this);

        var div = $('<div></div>').css({width: '600px', border: '2px solid black', 'margin-bottom': '10px'});
        var input = document.getElementById("input").value
        var heading = $('<p></p>').text(input).css({
            'box-sizing': 'border-box',
            'word-break': 'break-all',
            padding: '10px',
            width: '100%',
            display: 'table',
            margin: '0 auto',
            'border-bottom': '2px solid black'
        });
        var img = $('<img />').addClass('screenshot').attr('src', 'images/loading.gif').css({
            display: 'table',
            margin: '0 auto'
        });

        div.append(heading);
        div.append(img);

        $('#displayimage').prepend(div);

        $.ajax({
            type: 'POST',
            url: '/maven-intro/rest/ghostdriver',
            dataType: 'text',
            data: {url: input},
            beforeSend: function () {
                button.attr('disabled', true);
            },
            success: function (result) {
                img.attr('src', '/maven-intro/rest/ghostdriver?absolutePath=' + encodeURIComponent(result));
                img.css({width: '100%'});
                img.click(function () {
                    window.open($(this).attr('src'), input, '');
                    return false;
                });
                button.attr('disabled', false);


            },
            error: function (a, b, c) {
                console.log(a);
                console.log(b);
                console.log(c);
                img.css({width: '100%'});
                img.attr('src', 'images/error_button.png');
                button.attr('disabled', false);

            }
        });


    })
});