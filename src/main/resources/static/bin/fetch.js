/**
 * 
 */
 
 
 let fecthOptions = {
	
	method: 'GET',
	mode: 'cors',
	cache: 'no-cache'
};

document.querySelector(".left-btn").addEventListener("click", function(){
	
	goLeft().then(data => {
		
		clearDeletedPositions(data);
		displayActualShape(data);
	}
);
	
} );

document.querySelector(".right-btn").addEventListener("click", function(){
	
	goRight().then(data => {
		
		clearDeletedPositions(data);
		displayActualShape(data);
	}
);

} );


async function getInitData(){

const response = await fetch('/webtris/start', fecthOptions);
const data = await response.json();

return data;
}


getInitData().then(data =>{
	
	displayActualShape(data);
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
	
function clearDeletedPositions(data){
	
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
