jQuery(document).ready(function($){
    $.ajax({url: "/getAll", success: function(result){
        var radioInput;
        for (var i = 0, keys = Object.keys(result), ii = keys.length; i < ii; i++) {
            console.log('key : ' + keys[i] + ' val : ' + result[keys[i]]);
            var fileName = keys[i];
            var div = document.getElementById("questions");
            var p = document.createElement("p");
            var ul = document.createElement("ul");
            ul.setAttribute('name', "ans");
            p.appendChild(document.createTextNode(fileName));
            div.appendChild(p);
            if (result[keys[i]] != '') {
                var list = result[keys[i]];
                for (j = 0; j < list.length; j++) {
                    var label = document.createElement("label");
                    radioInput = document.createElement('input');
                    radioInput.setAttribute('type', 'radio');
                    radioInput.setAttribute('name', list[j]);
                    label.appendChild(radioInput);
                    label.innerHTML += list[j];
                    ul.appendChild(label);
                }
                div.appendChild(ul);
            }
        }
    }});
});