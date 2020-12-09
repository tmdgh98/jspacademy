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
					      "anonymous": false,
					      "inputs": [
					        {
					          "indexed": false,
					          "internalType": "string",
					          "name": "name",
					          "type": "string"
					        },
					        {
					          "indexed": false,
					          "internalType": "string",
					          "name": "message",
					          "type": "string"
					        },
					        {
					          "indexed": false,
					          "internalType": "uint256",
					          "name": "timeStamp",
					          "type": "uint256"
					        }
					      ],
					      "name": "chat",
					      "type": "event"
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
					          "name": "_msg",
					          "type": "string"
					        }
					      ],
					      "name": "setChat",
					      "outputs": [],
					      "stateMutability": "nonpayable",
					      "type": "function"
					    },
					    {
					      "inputs": [],
					      "name": "getChat",
					      "outputs": [
					        {
					          "internalType": "string",
					          "name": "",
					          "type": "string"
					        },
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
					      "type": "function",
					      "constant": true
					    }
					  ]);
					App.contract.options.address = "0xF2F6b4e111e8017174ef338feF46b4858de81d44";

	},
	setChat : function(){
		$('#button').on('click',function(){
			contract.method.setChat($('#name').val(), $('#chatting').val());
			$("msg").append($("<p />").html($('#name').val()+" "+$('#chatting').val()));		
		})
	},
	getChat: function(){
		contract.method.chat().watch((err, res) => {
			//res출력
			contract.method.getChat().then(function(data){
				console.log(data);
			});
		});
	}
}

$(function(){
	App.initWeb3();
})