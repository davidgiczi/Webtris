/**
 * 
 */
 let speed = 500;
 let playingGame;
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

document.querySelector(".left-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
	
	options.unshift("left");
	
		}	
} );


document.querySelector(".right-btn").addEventListener("click", function(){

let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
			
	options.unshift("right");
	
	}
} );

document.querySelector(".rotate-btn").addEventListener("click", function(){
	
let startBtnText = document.querySelector(".btn-start-stop").innerText;

	if(startBtnText === "Stop") {
	
	options.unshift("rotate");
		}	
} );

document.querySelector(".speed-btn").addEventListener("click", function(){
	
		let btnText = this.innerText;
		let startBtnText = document.querySelector(".btn-start-stop").innerText;
		
		switch(btnText){
		case "Speed: 1":
		clearInterval(playingGame);
		speed = 400;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		this.innerText = "Speed: 2";
		break;
		case "Speed: 2":
		clearInterval(playingGame);
		speed = 300;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		this.innerText = "Speed: 3";
		break;
		case "Speed: 3":
		clearInterval(playingGame);
		speed = 200;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		this.innerText = "Speed: 4";
		break;
		case "Speed: 4":
		clearInterval(playingGame);
		speed = 100;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		this.innerText = "Speed: 5";
		break;
		case "Speed: 5":
		clearInterval(playingGame);
		speed = 500;
		startBtnText === "Stop" ? playingGame = setInterval(displayData, speed) : "";
		this.innerText = "Speed: 1";
}
	});

function displayData(){
	
	options.push("play");
	
	sendOption().then(data => {clearDeletedPositions(data); return data})
				.then(data => {displayActualShape(data); return data})
				.then(data => {
					
						if(data.nextShape !== null){
		
						clearNextShapeDisplayer()
						displayNextShape(data)}
						
						});
				
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
	


