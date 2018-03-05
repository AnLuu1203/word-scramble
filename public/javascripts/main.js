$(document).keydown(function(e) {
	var shuffleWord = $("#shuffle_word").text();

	if (e.keyCode === 13) { // Enter key
		var answer = $("#input_word").text();
		submitAnswer(answer);
	} else if (e.keyCode === 8) { // Backspace key
		var inputWord = $("#input_word").text();
		// remove last char
		$("#input_word").text(inputWord.slice(0, -1));
		// add last char in input into shuffle word
		$("#shuffle_word").text(shuffleWord + inputWord.slice(-1));
	} else {
		var char = String.fromCharCode(e.keyCode).toLowerCase();

		if (shuffleWord.indexOf(char) !== -1) {
			var currentWord = $("#input_word").text();
			$("#input_word").text(currentWord + char);
			$("#shuffle_word").text(removeChar(shuffleWord, char));
		}
	}
});

function removeChar(str, char) {
	var index = str.indexOf(char);
	if (index === -1) {
		return str;
	}
	return str.slice(0, index) + str.slice(index + 1, str.length);
}

function submitAnswer(answer) {
	if (answer.length < 3) {
		alert('Input at least 3 characters');
		return
	}

	if (isCorrectTypedWord(answer)) {
		$("#input_word").addClass("alert-danger");
		setTimeout(function() {
			$("#input_word").removeClass("alert-danger");
		}, 1500);
	} else {
		$.ajax({
			method: "GET",
			url: "/validate",
			data: {
				answer: answer
			},
			success: function(res) {
				console.log(res);
				if (res.valid) {
					$("#input_word").addClass("alert-success");
					$("#correct_input").append("<p>" + res.answer + "</p>")
					setTimeout(function() {
						$("#input_word").removeClass("alert-success");
					}, 1500);
				} else {
					$("#input_word").addClass("alert-danger");
					setTimeout(function() {
						$("#input_word").removeClass("alert-danger");
					}, 1500);
				}
			},
			error: function(res) {
				alert('Something went wrong!!');
			}
		});
	}
}

function isCorrectTypedWord(answer) {
	var selector = $("#correct_input p");
	for (i = 0; i < selector.length; i++) {
		if (selector[i].innerText === answer)
			return true;
	}

	return false;
}
