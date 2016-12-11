jQuery(document).ready(function($) {
    if (isVerified() == "true") {
        $.ajax({url: "/showResultsUser", success: function(result) {
            var body = document.getElementById("resultbody");
            for (i = 0; i < result.length; i++) {
                var m = result[i];
                var tr = document.createElement("tr");
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                var td4 = document.createElement("td");
                var td5 = document.createElement("td");
                var td6 = document.createElement("td");
                var t1 = document.createTextNode(m['name']);
                td1.appendChild(t1);
                var t2 = document.createTextNode(m['passed']);
                td2.appendChild(t2);
                var t3 = document.createTextNode(m['score']);
                td3.appendChild(t3);
                var t4 = document.createTextNode(m['examstart']);
                td4.appendChild(t4);
                var t5 = document.createTextNode(m['examend']);
                td5.appendChild(t5);
                var t6 = document.createTextNode(m['examclassif']);
                td6.appendChild(t6);
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);
                tr.appendChild(td6);
                body.appendChild(tr);
            }
        }});
    }
    else {
        $.ajax({
            url: "/showResultsAdmin", success: function (result) {
                var body = document.getElementById("resultbody");
                for (i = 0; i < result.length; i++) {
                    var m = result[i];
                    var tr = document.createElement("tr");
                    var td1 = document.createElement("td");
                    var td2 = document.createElement("td");
                    var td3 = document.createElement("td");
                    var td4 = document.createElement("td");
                    var td5 = document.createElement("td");
                    var td6 = document.createElement("td");
                    var t1 = document.createTextNode(m['name']);
                    td1.appendChild(t1);
                    var t2 = document.createTextNode(m['passed']);
                    td2.appendChild(t2);
                    var t3 = document.createTextNode(m['score']);
                    td3.appendChild(t3);
                    var t4 = document.createTextNode(m['examstart']);
                    td4.appendChild(t4);
                    var t5 = document.createTextNode(m['examend']);
                    td5.appendChild(t5);
                    var t6 = document.createTextNode(m['examclassif']);
                    td6.appendChild(t6);
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);
                    tr.appendChild(td5);
                    tr.appendChild(td6);
                    body.appendChild(tr);
                }

            }
        });
    }
    function isVerified() {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", "/isVerified", false);
        xmlHttp.send(null);
        return (xmlHttp.responseText);
    }
});