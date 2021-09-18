/**
 * 
 */
 

 
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

document.querySelector(".left-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
	
	goLeft().then(data => {
	
	clearDeletedPositions(data);
	displayActualShape(data);
	
	}
);
}	
} );

document.querySelector(".right-btn").addEventListener("click", function(){

let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {	

	goRight().then(data => {
	
	clearDeletedPositions(data);
	displayActualShape(data);
		
	}
);
}
} );

document.querySelector(".rotate-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
	
	rotate().then(data => {
	
	clearDeletedPositions(data);
	displayActualShape(data);
	
	}
);
}	
} );

document.querySelector(".down-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {

	fallDown().then(data => {
	
	clearDeletedPositions(data);
	displayActualShape(data);
	
	});

	}
	
} );

function displayGame(){
	
	playGame().then(data =>{
	
	clearDeletedPositions(data);
	displayActualShape(data);
	
	if(data.nextShape !== null){
		
		clearNextShapeDisplayer();
		displayNextShape(data);
	}
	
	});
	
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


async function displayActualShape(data){
	
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
	
async function clearDeletedPositions(data){
	
	for(let i = 0; i < data.deletedPositions.length; i++){
		document.getElementById(data.deletedPositions[i]).style.backgroundColor = "";
	}
}
	
async function goLeft(){

const response = await fetch('/webtris/left', fecthOptions);
const data = await response.json();

return data;
}

async function goRight(){

const response = await fetch('/webtris/right', fecthOptions);
const data = await response.json();

return data;
}

async function rotate(){

const response = await fetch('/webtris/rotate', fecthOptions);
const data = await response.json();

return data;
}

async function fallDown(){

const response = await fetch('/webtris/fallDown', fecthOptions);
const data = await response.json();

return data;
}

async function playGame(){

const response = await fetch('/webtris/play', fecthOptions);
const data = await response.json();

return data;
}


