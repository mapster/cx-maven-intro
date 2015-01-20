$(document).ready(function () {
    console.log("loool");


    $('#submitbutton').click(function () {
        console.log("lol");
        /*$.ajax({
         url: '/maven-intro/rest/ghostdriver',
         data: {},
         success: function (data) {
         //$('#screenshot').attr('src', '/screenshots/test.png');
         console.log("COOL");

         var lol = $('<img src="data:image/png;base64,' + data + '" />');
         $('#displayimage').append(lol);
         },
         error: function(e, f, g) {
         console.log(e); console.log(f); console.log(g);
         },
         type:'GET',
         dataType: "image/png",
         processData: false
         });*/

        var button = $(this);
        button.attr('disabled', true);

        var div = $('<div></div>').css({width: '600px', border: '2px solid black', 'margin-bottom': '10px'});

        var input = document.getElementById("input").value

        var heading = $('<h1></h1>').text(input);
        var img = $('<img />').addClass('screenshot').attr('src', 'images/loading.gif').css({display: 'table', margin: '0 auto'});

        div.append(heading);
        div.append(img);

        $('#displayimage').prepend(div);

        var xhr = new XMLHttpRequest();
        xhr.open('GET', '/maven-intro/rest/ghostdriver?url=' + encodeURIComponent(input), true);
        //xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");

        xhr.responseType = 'arraybuffer';

        xhr.onload = function (e) {
            if (this.status == 200) {
                var uInt8Array = new Uint8Array(this.response);
                var i = uInt8Array.length;
                var binaryString = new Array(i);
                while (i--) {
                    binaryString[i] = String.fromCharCode(uInt8Array[i]);
                }
                var data = binaryString.join('');

                var base64 = window.btoa(data);

                //document.getElementById("screenshot").src = "data:image/png;base64," + base64;
                img.attr('src', 'data:image/png;base64,' + base64);
                img.css({width: '100%'});
                img.click(function(){
                    console.log('clickety');
                    window.open($(this).attr('src'), input, '');
                    return false;
                });
            }
            button.attr('disabled', false);


        };

        xhr.send();



        //var lol = $('<img />').attr('src', '/maven-intro/rest/ghostdriver');
        //$('#displayimage').append(lol);
    })
});