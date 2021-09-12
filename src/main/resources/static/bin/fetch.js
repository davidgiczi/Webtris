/**
 * 
 */
 
 
 let fecthOptions = {
	
	method: 'GET',
	mode: 'cors',
	cache: 'no-cache'
};

async function getInitData(){

const response = await fetch('/webtris/start', fecthOptions);
const data = await response.json();

return data;
}


getInitData().then(data =>{
	
	displayActualShape(data);
	console.log(data);
	displayNextShape(data);
	displayScore(data);
	
});


function displayActualShape(data){
	
	for(let i = 0; i < data.actualShape.shapePositions.length; i++){

		document.getElementById(data.actualShape.shapePositions[i])
		.style.backgroundColor = data.actualShape.shapeColor;
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