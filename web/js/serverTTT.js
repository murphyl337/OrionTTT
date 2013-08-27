$(document).ready(function(){
	var view = new View();
	view.listen();
});

function View(){
	var view = this;

	this.listen = function(){
		var $box = $(".box");
		$box.on('click', view.sendMove);
	};

	this.sendMove = function(event){
		var gameId = document.getElementById("game_id").innerHTML;
		var position = event.target.id;

		$.get("/game/update", { game: gameId, move: position, player: "X" })
		.done(function(data) {
 			alert("Data Loaded: " + data);
		});
	};
}