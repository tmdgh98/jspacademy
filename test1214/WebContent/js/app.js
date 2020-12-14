App={
	web3Provider : null,
	contract : null,
	init : async function(){},
	initWeb3 : async function() {
				    if(window.ethereum) {
					      App.web3Provider = window.ethereum;
					      try {  
					        await window.ethereum.enable();
					      } catch(error) {
					        console.error("Used denied account access");
					      }
				    } else if(window.web3) {
				      	App.web3Provider = window.web3.currentProvider;
				    } else {
				      	App.web3Provider = new Web3.providers.HttpProvider("http://127.0.0.1:7545");
				    }
				    web3 = new Web3(App.web3Provider);
					return App.initContract();
	},
	initContract : function(){
				    App.contract = new web3.eth.Contract([
															{
																"inputs": [
																	{
																		"internalType": "string",
																		"name": "_name",
																		"type": "string"
																	}
																],
																"name": "getProduce",
																"outputs": [
																	{
																		"internalType": "string",
																		"name": "",
																		"type": "string"
																	},
																	{
																		"internalType": "uint256",
																		"name": "",
																		"type": "uint256"
																	}
																],
																"stateMutability": "view",
																"type": "function"
															},
															{
																"inputs": [
																	{
																		"internalType": "string",
																		"name": "",
																		"type": "string"
																	}
																],
																"name": "map",
																"outputs": [
																	{
																		"internalType": "string",
																		"name": "proloc",
																		"type": "string"
																	},
																	{
																		"internalType": "uint256",
																		"name": "pronumber",
																		"type": "uint256"
																	}
																],
																"stateMutability": "view",
																"type": "function"
															},
															{
																"inputs": [
																	{
																		"internalType": "string",
																		"name": "_name",
																		"type": "string"
																	},
																	{
																		"internalType": "string",
																		"name": "_loc",
																		"type": "string"
																	},
																	{
																		"internalType": "uint256",
																		"name": "_num",
																		"type": "uint256"
																	}
																],
																"name": "setProduce",
																"outputs": [],
																"stateMutability": "nonpayable",
																"type": "function"
															}
														]);
					App.contract.options.address = "0x69E481C84CE2289224187cb924c6dc453a302376";
					
					App.bindEvents();
	},
	bindEvents : function(){ 
		$(document).on('click','#getPro',App.getPro);
		$(document).on('click','#setPro',App.setPro);
	},
	getPro : function(){
		let proname = $("#proname").val();
		
		web3.eth.getAccounts(function(error, accounts){
			if(error){
				console.log(error);
				return;
			}
			var account = accounts[0];
			
			return App.contract.methods.getProduce(proname)
									  .call()
									  .then(function(result){
											console.log(result)
											$("#proloc").val(result[0]);
											$("#pronumber").val(result[1]);
										});
		})
	},
	setPro : function(){
		let proname = $("#proname").val();
		let proloc = $("#proloc").val();
		let pronumber = Number($("#pronumber").val());
		
		web3.eth.getAccounts(function(error, accounts){
			if(error){
				console.log(error);
				return;
			}
			var account = accounts[0];
			
			return App.contract.methods.setProduce(proname,proloc,pronumber)
									  .send({from:account})
									  .then(function(result){
											console.log(result)
											$("#proname").val("");
											$("#proloc").val("");
											$("#pronumber").val("");
										});
		})	
		
	}
}

$(function(){
	App.initWeb3();
})
	