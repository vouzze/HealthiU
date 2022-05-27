function connect() {
    const socket = new SockJS('/chat-messaging');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log("connected: " + frame);
        stompClient.subscribe('/chat/messages', function (response) {
            const data = JSON.parse(response.body);
            draw("left", data.content);
        });
    });
}

function draw(side, text) {
    console.log("drawing...");
    const $message = $($('.message_template').clone().html());
    $message.addClass(side).find('.text').html(text);
    $('.messages').append($message);

    return setTimeout(function () {
        return $message.addClass('appeared');
    }, 0);

}

function disconnect() {
    stompClient.disconnect();
}

// function sendMessage() {
//     // $.ajax({
//     //     type : 'GET',
//     //     url : "/patient/myMealPlan",
//     // });
//     var senderId = [[${senderId}]].toString();
//     console.log(senderId)
//     stompClient.send("/app/message", {}, JSON.stringify({
//         'content': $("#message_input_value").val(),
//         'senderId': senderId
//     }));
//
// }