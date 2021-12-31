/**
 * 
 */
 
 /**
 * 
 */
 let speed = 500;
 let instructions = ["step"];
 let dataStore = [null, null, null, null];
 let playingGame;
 
document.addEventListener("keydown", pressButton);

function pressButton(e) {
	
	let startBtnText = document.querySelector(".btn-start-stop").innerText;
	
    e = e || window.event;

	if (e.keyCode === 37 && startBtnText === "Stop" && dataStore[0] === null) {
		
		instructions.unshift("left");
		dataStore[0] = sendOption("left");
		
	}
	else if (e.keyCode === 38 && startBtnText === "Stop" && dataStore[1] === null) {
     
		instructions.unshift("rotate");
		dataStore[1] = sendOption("rotate");
		
	}
	else if (e.keyCode === 39 && startBtnText === "Stop" && dataStore[2] === null) {
		
		instructions.unshift("right");
		dataStore[2] = sendOption("right");
	
	}
	else if (e.keyCode === 40 && startBtnText === "Stop") {
		
		sendOption("fallDown").then(data => {
		
			refreshDisplayer(data);
			displayActualShape(data);
		});
		
	}
 	
}
 
 let fecthOptions = {
	
	method: 'GET',
	mode: 'cors',
	cache: 'no-cache'
};

getInitData().then(data =>{
	
	displayActualShape(data);
	displayNextShape(data);
	displayScore(data);
});

async function sendOption(request){
const response = await fetch('/webtris/' + request, fecthOptions);
const data = await response.json();

return data;
}

document.querySelector(".left-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;
	
	if(startBtnText === "Stop") {
	
		if(dataStore[0] === null){
		instructions.unshift("left");
		dataStore[0] = sendOption("left");
		}
		}	
} );

document.querySelector(".rotate-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
		
		if(dataStore[1] === null){
			instructions.unshift("rotate");
			dataStore[1] = sendOption("rotate");
		}
		
		}	
} );

document.querySelector(".right-btn").addEventListener("click", function(){
	
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
	
		if(dataStore[2] === null){
			instructions.unshift("right");
			dataStore[2] = sendOption("right");
		}
		
	}
} );

document.querySelector(".fall-down-btn").addEventListener("click", function(){
	
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
		
			sendOption("fallDown").then(data => {
		
			refreshDisplayer(data);
			displayActualShape(data);
		});
	}
});

 function growSpeed(){
 
	
		let speedText = document.querySelector(".speed").innerText;
		let startBtnText = document.querySelector(".btn-start-stop").innerText;
		
		switch(speedText){
		case "Speed:1":
		clearInterval(playingGame);
		speed = 400;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		document.querySelector(".speed").innerText = "Speed:2";
		break;
		case "Speed:2":
		clearInterval(playingGame);
		speed = 300;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		document.querySelector(".speed").innerText = "Speed:3";
		break;
		case "Speed:3":
		clearInterval(playingGame);
		speed = 200;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		document.querySelector(".speed").innerText = "Speed:4";
		break;
		case "Speed:4":
		clearInterval(playingGame);
		speed = 100;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		document.querySelector(".speed").innerText = "Speed:5";
		break;
		case "Speed:5":
		clearInterval(playingGame);
		speed = 500;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		document.querySelector(".speed").innerText = "Speed:1";
}
	}

function stepNext(instruction){
	
		if(dataStore[3] === null && instruction === "step"){
			dataStore[3] = sendOption("step");
			instructions.push("step");
		}
	}
	
function stepLeft(){

	dataStore[0].then(data => {
			
			refreshDisplayer(data);
			displayActualShape(data);
		}	
			)
}

function stepRight(){
	
	dataStore[2].then(data => {
			
			refreshDisplayer(data);
			displayActualShape(data);

		}	
			)
}

function rotate(){

dataStore[1].then(data => {
		
			refreshDisplayer(data);
			displayActualShape(data);
		}	
			)
}

function stepDown(){

	dataStore[3].then(data => {
		
	if(data.notPlaying){
	location.href = location.origin + "/webtris/save";
	return;
	}
	
	if(data.actualShape !== null){
	
		if(data.gameOver){
			endOfTheGame(data);
		}
		else {
			refreshDisplayer(data);
			displayActualShape(data);
			displayScore(data);
		
		
			if(data.nextShape !== null){
				
				clearNextShapeDisplayer();
				displayNextShape(data);
				
			}
		
			if(data.shapeStore !== null){
						
				clearNextShapeDisplayer();
				displayNextShape(data);
				clearDisplayer();
				displayShapeStore(data);
		}
			}	
				}
					}		
			)	
}

function displayData(){
		
		let instruction = instructions.shift();
		
		stepNext(instruction);
		
		if(dataStore[0] !== null && instruction === "left"){
			
			stepLeft();
			
		}
		else if(dataStore[1] !== null && instruction === "rotate"){
			
			rotate();
			
		}
		else if(dataStore[2] !== null && instruction === "right"){
			
			stepRight();
			
		}
		else if (dataStore[3] !== null && instruction === "step"){
		
			stepDown();
	}
	
			clearDataStore();				
}


function endOfTheGame(data){
		
		clearInterval(playingGame);
		clearDisplayer();
		clearNextShapeDisplayer();
		let startBtn = document.querySelector(".btn-start-stop");
		startBtn.innerText = "Start";
		startBtn.classList.remove("btn-danger");
		startBtn.classList.add("btn-success");
		alert("Game Over! Next one?\nYour actual score is: " + data.score +".");
		getInitData().then(data =>{
	
	displayActualShape(data);
	displayNextShape(data);
	displayScore(data);
});
}

function clearDataStore(){
	for(let i = 0; i< dataStore.length; i++){
				dataStore[i] = null;
			}
}

document.querySelector(".btn-start-stop").addEventListener("click", function(){
	
	let btnText = this.innerText;
	
	if("Start" === btnText){
		
	playingGame= setInterval(displayData, speed);
	
	}
	else if("Stop" === btnText){
		
		clearInterval(playingGame);
	}
	
});

async function getInitData(){

const response = await fetch('/webtris/start', fecthOptions);
const data = await response.json();

return data;
}


function displayActualShape(data){
	
	for(let i = 0; i < data.actualShape.shapePositions.length; i++){

		document.getElementById(data.actualShape.shapePositions[i])
		.style.backgroundColor = data.actualShape.shapeColor;
	}
}

function clearNextShapeDisplayer(){
	
	let nextShapeCells = document.querySelectorAll(".nextCell");

	for(let i = 0; i < nextShapeCells.length; i++){
		nextShapeCells[i].style.backgroundColor = "";
	}

}

function clearDisplayer(){
	for(let i = 0; i < 200; i++){
		document.getElementById(i).style.backgroundColor = "";
	}
}

function refreshDisplayer(data){
			
	for(let i = 0; i < 200; i++){
		if(!data.displayedPositions.includes(i)){
			document.getElementById(i).style.backgroundColor = "";
		}
	}
}

function displayNextShape(data){
	
	for(let i = 0; i < data.nextShape.shapePositions.length; i++){

		document.getElementById(data.nextShape.shapePositions[i] + "-next")
		.style.backgroundColor = data.nextShape.shapeColor;
	}
}

function displayScore(data){
	document.querySelector(".score-value").innerHTML = data.score;
	}
	

function displayShapeStore(data){
	
	for(let i = 0; i < data.shapeStore.length; i++){
		for(let j = 0; j < data.shapeStore[i].shapePositions.length; j++){
		document.getElementById(data.shapeStore[i].shapePositions[j])
		.style.backgroundColor = data.shapeStore[i].shapeColor;
		}
	}
}