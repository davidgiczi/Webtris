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
	displayScore(data);

});


function displayActualShape(data){
	
	for(let i = 0; i < data.actualShape.shapePositions.length; i++){

		document.getElementById(data.actualShape.shapePositions[i])
		.style.backgroundColor = data.actualShape.shapeColor;
	}
}

function displayScore(data){
	document.querySelector(".score-value").innerHTML = data.score;
	}