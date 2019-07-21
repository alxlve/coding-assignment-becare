var stompClient = null;

function setConnectionStatus(connected) {
    document.getElementById('connect-button').disabled = connected;
    document.getElementById('disconnect-button').disabled = !connected;
    document.getElementById('pipeline-status-collection').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('pipeline-status-collection').innerHTML = '';
}

function connect() {
    var socket = new SockJS('/api/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function () {
        setConnectionStatus(true);

        stompClient.subscribe('/topic/pipeline', function (message) {
            addPipelineStatus(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
        stompClient = null;
    }

    setConnectionStatus(false);
}


function addPipelineStatus(pipelineStatus) {
    var pipelineStatusCollectionElement = document.getElementById('pipeline-status-collection');

    var pipelineStatusElement = document.createElement('p');
    pipelineStatusElement.appendChild(document.createTextNode(pipelineStatus.timestamp + ' — ' + pipelineStatus.message));

    pipelineStatusCollectionElement.appendChild(pipelineStatusElement);
}
