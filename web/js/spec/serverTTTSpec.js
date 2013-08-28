describe("TicTacToe GUI", function(){
	var $box, gui;

	beforeEach(function(){
		$box = affix(".box#00");
		gui = new TicTacToeGUI();
		gui.listen();
	});

	it("calls send move when box is clicked", function(){
		$box.click();

		expect($box).toHandleWith('click', gui.sendMove);
	});

	describe("View", function(){
		var $box00, $box01, $box02, $box10, $box11, $box12, $box20, $box21, $box22;
		var jsonResponse;
		var view;

		beforeEach(function(){
			$box00 = affix(".box#00");
			$box01 = affix(".box#01");
			$box02 = affix(".box#02");
			$box10 = affix(".box#10");
			$box11 = affix(".box#11");
			$box12 = affix(".box#12");
			$box20 = affix(".box#20");
			$box20 = affix(".box#21");
			$box22 = affix(".box#22");

			view = new View();

			jsonResponse = {"state":[["X","",""],["","",""],["","",""]]};
		});

		describe("update", function(){
			it("changes DOM for JSON received", function(){
				expect($box00).toContainText("");

				view.update(jsonResponse);

				expect($("#00")).toContainText("X");
			});
		});
	});
});
