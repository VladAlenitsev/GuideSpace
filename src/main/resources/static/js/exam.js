jQuery(document).ready(function($) {
    var submit = $('#checkAnswers');
    var submit2 = $('#start');
    submit2.css("display","none");
    $.ajax({
        url: "/getAllQuestions", success: function (result) {
            if (jQuery.isEmptyObject(result)) {
                $("#questions").css("display", "block");
                var div = document.getElementById("questions");
                var p = document.createElement("h4");
                p.innerHTML = "Ühtegi eksamit pole hetkel avatud";
                p.setAttribute("style","color:red");
                div.appendChild(p);
            }
            else {
                submit2.css("display", "block");
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
                            var label = document.createElement("p");
                            radioInput = document.createElement('input');
                            radioInput.setAttribute('type', 'checkbox');
                            radioInput.setAttribute('name', list[j]);
                            label.appendChild(radioInput);
                            label.innerHTML += list[j];
                            var lineBr = document.createElement("br");
                            ul.appendChild(label);
                            ul.appendChild(lineBr);
                        }
                        div.appendChild(ul);
                    }
                }
            }
        }
    });
    $("#questions").css("display","none");
    $("#checkAnswers").css("display","none");
    submit.click(function () {
        sendAnswers();
    });
    function sendAnswers(){
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
            url: '/userAnsw'

        }).done(function (data, textStatus, jqXHR) {
            $.notify(data, "success");
            setTimeout(function(){window.location = "/";}, 3000);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Failed");
        });

    }
    var Timer;
    var TotalSeconds;
    var myVar;
    $.ajax({
        url: "/time", success: function (result) {
            myVar = result;
        }
    });
    submit2.click(function () {
        $("#questions").css("display","inline-block");
        $("#checkAnswers").css("display","block");
        submit2.css("display","none");
        CreateTimer(myVar);
    });
    function CreateTimer(Time){
        TotalSeconds = Time;
        UpdateTimer();
        Tick();
    }
    function Tick() {
        TotalSeconds -= 1;
        if(TotalSeconds ==-1){
            alert("Time Up");
            sendAnswers();
        }
        else{
            UpdateTimer();
            setTimeout(function(){Tick();}, 1000);
        }
    }
    function UpdateTimer() {
        if (TotalSeconds > 60) {
            var rounded = Math.floor(TotalSeconds/60);
            document.getElementById("showTime").innerHTML = "Time Left: "+rounded.toString() + ":" + (TotalSeconds-(rounded*60)).toString();
        } else {
            document.getElementById("showTime").innerHTML = "Time Left: "+TotalSeconds.toString();
        }
    }
});