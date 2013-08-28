$(document).ready(function(){
	var view = new View();
	var gui = new TicTacToeGUI(view);
	gui.listen();
});

function TicTacToeGUI(view){
	this.view = view;
	var gui = this;

	this.listen = function(){
		var $box = $(".box");
		$box.on('click', gui.sendMove);
	};

	this.sendMove = function(event){
		var gameId = $("#game_id").html();
		var position = event.target.id;

		$.get("/game/update", {game: gameId, move: position, player: "X"})
		.done(function(data) {
 			gui.view.update(data);
		});
	};
}

function View(){
    var self = this;

	this.update = function(json){
		var jsonObj = JSON.parse(json);
		var state = jsonObj['state'];
		var boxDiv;

		for(var row=0; row<3; row++){
			for(var col=0; col<3; col++){
				var rowString = row.toString();
				var colString = col.toString();
				boxDiv = document.getElementById(rowString.concat(colString));
				boxDiv.innerHTML = state[row][col];
			}
		}

        self.applyStyles();
	};

    this.applyStyles = function(){
        $(".box").each(function(){
            if($(this).text() === "X"){
                $(this).addClass("green");
            }
            else if($(this).text() === "O"){
                $(this).addClass("pink");
            }
        });
    };
}