/**
 * 
 */
 
 if(document.getElementById("welcome-msg") != null){
    let msg = document.getElementById("welcome-msg").value;
    alert(msg);
}
 
let tableBody = document.querySelector(".gameboard tbody")

for(let x = 0; x < 20; x++){
	
	let tr = document.createElement("tr");
	
	for(y = 0; y < 10; y++){
		
		let td = document.createElement("td");
		tr.appendChild(td);
	}
	 tableBody.appendChild(tr);
}


if(screen.width < 576){
let tds= document.getElementsByTagName("td");

for(let i = 0; i < tds.length; i++){
	tds[i].style.height = "20px";
	
}

}