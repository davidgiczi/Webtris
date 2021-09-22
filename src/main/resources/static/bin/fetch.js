/**
 * 
 */
 
 let options = [];
 
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

async function sendOption(){
const response = await fetch('/webtris/' + options.shift(), fecthOptions);
const data = await response.json();

return data;
}

function doNextOption(){
	
	let nextOption;
	
	if(options.length !== 0){
		
		nextOption = options.shift();
		
		switch(nextOption){
			
			case "rotate":
			document.querySelector(".rotate-btn").click();
			break;
			case "right":
			document.querySelector(".right-btn").click();
			break;
			case "left":
			document.querySelector(".left-btn").click();
			break;
			case "fallDown":
			document.querySelector(".down-btn").click();
			break;
			case "play":
			displayGame();	
		}		
	}
}

document.querySelector(".left-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
	
	options.push("left");
	
	sendOption().then(data => {
	
	clearDeletedPositions(data);
	displayActualShape(data);
	
	}
).then(doNextOption());
}	
} );


document.querySelector(".right-btn").addEventListener("click", function(){

let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
			
	options.push("right");
	
	sendOption().then(data => {
	
	clearDeletedPositions(data);
	displayActualShape(data);
		
	}
).then(doNextOption());
}
} );

document.querySelector(".rotate-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
	
	options.push("rotate");
	
	sendOption().then(data => {
	
	clearDeletedPositions(data);
	displayActualShape(data);
	
	}
).then(doNextOption());
}	
} );

document.querySelector(".down-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
	
	options.push("fallDown");
	
	sendOption().then(data => {
	
	clearDeletedPositions(data);
	displayActualShape(data);
	
	}
).then(doNextOption());
	}
} );

async function displayGame(){
	
	options.push("play");
	
	sendOption().then(data =>{
		
	clearDeletedPositions(data);
	displayActualShape(data);
	
	if(data.nextShape !== null){
		
		clearNextShapeDisplayer();
		displayNextShape(data);
	}
	
	}
	).then(doNextOption());
}

let playingGame;

document.querySelector(".btn-start-stop").addEventListener("click", function(){
	
	let btnText = this.innerText;
	
	if("Start" === btnText){
		
	playingGame= setInterval(displayGame, 500);
	
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

function displayNextShape(data){
	
	for(let i = 0; i < data.nextShape.shapePositions.length; i++){

		document.getElementById(data.nextShape.shapePositions[i] + "-next")
		.style.backgroundColor = data.nextShape.shapeColor;
	}
}

function displayScore(data){
	document.querySelector(".score-value").innerHTML = data.score;
	}
	
function clearDeletedPositions(data){
	
	for(let i = 0; i < data.deletedPositions.length; i++){
		document.getElementById(data.deletedPositions[i]).style.backgroundColor = "";
	}
}
	



