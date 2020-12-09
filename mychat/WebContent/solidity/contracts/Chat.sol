//SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.6.10;

contract Chat {
    string name;
    string message;
    uint timeStamp;
	
	event chat(
	    string name,
	    string message,
	    uint timeStamp
	);
	function setChat(string memory _name, string memory _msg) public {
	    name = _name;
	    message = _msg;
	    timeStamp = now;
	    emit chat(_name, _msg, now);
	}
	function getChat() public view returns(string memory, string memory, uint){
	    return(name, message, timeStamp);
	}
}
