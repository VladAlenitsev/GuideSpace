jQuery(document).ready(function($) {
    var submit = $('#addQuestion');
    $.ajax({
        url: "/getAll", success: function (result) {
            var radioInput;
            for (var i = 0, keys = Object.keys(result), ii = keys.length; i < ii; i++) {
                console.log('key : ' + keys[i] + ' val : ' + result[keys[i]]);
                var fileName = keys[i];
                var div = document.getElementById("questions");
                var p = document.createElement("h4");
                var ul = document.createElement("ul");
                ul.setAttribute('name', keys[i]);
                p.appendChild(document.createTextNode(fileName));
                ul.appendChild(p);
                if (result[keys[i]] != '') {
                    var list = result[keys[i]];
                    for (j = 0; j < list.length; j++) {
                        var label = document.createElement("label");
                        radioInput = document.createElement('input');
                        radioInput.setAttribute('type', 'checkbox');
                        radioInput.setAttribute('name', list[j]);
                        label.appendChild(radioInput);
                        label.innerHTML += list[j];
                        ul.appendChild(label);
                    }
                    div.appendChild(ul);
                }
            }
        }
    });

    submit.click(function () {
        var map = {};
        var x = document.getElementById("questions").querySelectorAll("ul");
        for (s = 0; s < x.length; s++) {
            var list = [];
            var boxes = x[s].querySelectorAll("input");
            for (z = 0; z < boxes.length; z++) {
                if(boxes[z].checked) {
                    list.push( boxes[z].getAttribute("name"));
                }
            }
            map[x[s].getAttribute("name")] = list;
        }
        var cookie = JSON.parse($.cookie('CSRF'));
        $.ajax({
            data: JSON.stringify(map),
            headers: {'X-CSRF-TOKEN': cookie.csrf},
            timeout: 5000,
            contentType: "application/json",
            type: 'POST',
            url: '/listTest'

        }).done(function (data, textStatus, jqXHR) {
            alert(data);

        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });
    });
});