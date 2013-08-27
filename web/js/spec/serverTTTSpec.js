describe("View", function(){
	var $box, view;

	beforeEach(function(){
		$box = affix(".box#0");
		view = new View();
		view.listen();
	});

	it("calls send move when box is clicked", function(){
		$box.click();

		expect($box).toHandleWith('click', view.sendMove);
	});
});