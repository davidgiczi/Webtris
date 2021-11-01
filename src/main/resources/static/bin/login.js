/**
 * 
 */
 
 
function regist(){

	let name = prompt("Type your nickname for the game:");
	
	if(name !== null){
	document.getElementById("reg-name").value = name;
	document.getElementById("reg-form").submit();
	}
}