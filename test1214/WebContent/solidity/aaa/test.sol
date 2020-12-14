//SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.6.10;

contract Test {
    struct produce{
        string proloc;
        uint pronumber;
    }
    
    mapping(string=>produce) public map;
    
    function getProduce(string memory _name) public view returns(string memory, uint){
        produce memory p = map[_name];
        
        return (p.proloc, p.pronumber);
        
    }
	
   function setProduce(string memory _name, string memory _loc, uint _num) public{
       produce memory p;
       p.proloc = _loc;
       p.pronumber = _num;
       map[_name] = p;
   }
}
