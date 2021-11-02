/**
 * 
 */
 	
 	let audio = null;
 	const URL  = location.origin + "/webtris/sound";

	if(document.getElementById("playSound") !== null){
	document.querySelector(".sound").innerText = "Sound:Off";
	audio = new Audio(URL)
	audio.play();
  	}
  	

	function stopStartAudio(){
		
	let audioText =	document.querySelector(".sound").innerText;
	
	if(audioText === "Sound:On" && audio === null){
	document.querySelector(".sound").innerText = "Sound:Off"
	audio = new Audio(URL)
	audio.play();
	}
	else if(audioText === "Sound:On" && audio !== null){
	document.querySelector(".sound").innerText = "Sound:Off"
	audio.play();
	}
	else if(audioText === "Sound:Off"){
	document.querySelector(".sound").innerText = "Sound:On"
	audio.pause();
	}
		
	}
	
