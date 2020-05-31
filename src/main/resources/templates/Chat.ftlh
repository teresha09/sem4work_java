<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div>
    <label>
        <input type="text" placeholder="Write room id!" id="id">
    </label>
    <div id="join">
        <button>join</button>
    </div>
</div>
<div id="rooms">
</div>
<script
        src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script>
    let webSocket = new SockJS("http://localhost:80/chat");
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    webSocket.onmessage = function (data) {
        let message = JSON.parse(data.data).payload
        let messagesAttr = "[data-room-id-messages=" + message.roomId + "]";
        let messages = $(messagesAttr).toArray()[0];
        messages.innerHTML +=
            "<div class=\"message light\">" +
            "<div class=\"name\">" + message.name + "</div>" +
            "<div class=\"text\">" + message.text + "</div>" +
            "<br>" +
            "</div>";
    };

    let roomId = $("#id");
    let join = $("#join");
    let rooms = $("#rooms");
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    join.click(function () {
        let id = roomId.val();
        roomId.val("");
        webSocket.send(JSON.stringify({
            header: "room",
            payload: {
                id: id
            }, beforeSend: function( xhr ) {
                xhr.setRequestHeader(header, token);
            }
        }));
        rooms.append(
            "<div class=\"room\">" +
            "<div class=\"title\"> " + id + "</div>" +
            "<div class=\"messages\" data-room-id-messages=\"" + id + "\">" +
            "</div>" +
            "<div class=\"input-wrapper\">" +
            "<label>" +
            "<input type=\"text\" class=\"text\" placeholder=\"Message..\" data-room-id-text=\"" + id + "\">" +
            "</label>" +
            "<div class=\"send\"><button><i  data-room-id=\"" + id + "\">Send</button></i></div>" +
            "</div>" +
            "</div>"
        );
    });

    rooms.click(function (event) {
        let roomId = event.target.dataset.roomId;
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        if (roomId) {
            let textAttr = "[data-room-id-text=" + roomId + "]";
            let messagesAttr = "[data-room-id-messages=" + roomId + "]";
            let messages = $(messagesAttr).toArray()[0];
            let text = $(textAttr).toArray()[0].value;
            webSocket.send(JSON.stringify({
                header: "message",
                payload: {
                    text: text,
                    roomId: roomId
                }, beforeSend: function( xhr ) {
                    xhr.setRequestHeader(header, token);
                }
            }));
            messages.innerHTML +=
                "<div class=\"message blue\">" +
                "<div class=\"text\">" + text + "</div>" +
                "<br>" +
                "</div>";
        }
    });
</script>
</body>
</html>