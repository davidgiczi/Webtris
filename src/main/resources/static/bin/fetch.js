/**
 * 
 */
 
 document.querySelector(".btn-success").addEventListener("click", function(){
	
	let text = this.innerText;
	
	if(text === "Start"){
		start();
	}
	
});
 
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

function start(){

getInitData().then(json => console.log(json));

}


