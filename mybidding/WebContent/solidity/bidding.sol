//SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.6.10;

contract bidding {
    address payable public creator;

    /* 최고 입찰자 */
    string h1Title;
    string h1Name;
    uint public h1Bid;
    address payable h1arrd;
    
    string h2Title;
    string h2Name;
    uint public h2Bid;
	address payable h2arrd;

    constructor() public{
        creator = msg.sender;
    }
    
    event recentVoter(
        string rtitle,
        string rName,
        uint rAmount
    );

    event highestVoter(
        string htitle,
        string hName,
        uint hAmount
    );
    
    function list() public view {
        
    }

    function bid1(string memory _title, string memory _name) public payable {
        if (msg.value > h1Bid) {     
	        if(!h1arrd.send(h1Bid)){
	            revert();
	        }
            h1Title = _title;
            h1Name = _name;
            h1Bid = msg.value;
            h1arrd = msg.sender;
            emit highestVoter(_title, _name, msg.value);
        }else{
            if(!msg.sender.send(msg.value)){
	            revert();
	        }
        }
        emit recentVoter(_title, _name, msg.value);
    }
    
    function bid2(string memory _title, string memory _name) public payable {
        if (msg.value > h2Bid) {
            if(!h2arrd.send(h2Bid)){
	            revert();
	        }
            h2Title = _title;
            h2Name = _name;
            h2Bid = msg.value;
            h2arrd = msg.sender;
            emit highestVoter(_title, _name, msg.value);
        }else{
            if(!msg.sender.send(msg.value)){
	            revert();
	        }
        }
        emit recentVoter(_title, _name, msg.value);
    }

    function getVoter() public view returns(string memory, string memory, uint) {
        return (h1Title, h1Name, h1Bid);
    }
}
