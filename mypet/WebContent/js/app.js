App = {
	web3Provider : null,
	contrat : null,
	//펫정보 화면
	init : async function init(){
				$.getJSON('./PetList', function(data) {
					var petsRow = $('#petsRow');
					var petTemplate = $('#petTemplate');
					for(i=0; i<data.length; i++){
						petTemplate.find('.panel-title').text(data[i].name);
						petTemplate.find('img').attr('src',data[i].picture);
						petTemplate.find('.pet-breed').text(data[i].breed);
						petTemplate.find('.pet-age').text(data[i].age);
						petTemplate.find('.pet-location').text(data[i].location);
						petTemplate.find('.btn-adopt').attr('data-id', data[i].id);
						//petTemplate.find('.btn-adopt').data('id', data[i].id);
						petsRow.append(petTemplate.html());
					}
					return App.initWeb3();
				});
	},
	//메타마스크 Provider 연결
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
	//컨트렉트 연결
	initContract : function(){
				   		App.contract = new web3.eth.Contract([
    {
      "constant": true,
      "inputs": [
        {
          "internalType": "uint256",
          "name": "",
          "type": "uint256"
        }
      ],
      "name": "adopters",
      "outputs": [
        {
          "internalType": "address",
          "name": "",
          "type": "address"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    },
    {
      "constant": false,
      "inputs": [
        {
          "internalType": "uint256",
          "name": "petId",
          "type": "uint256"
        }
      ],
      "name": "adopt",
      "outputs": [
        {
          "internalType": "uint256",
          "name": "",
          "type": "uint256"
        }
      ],
      "payable": true,
      "stateMutability": "payable",
      "type": "function"
    },
    {
      "constant": true,
      "inputs": [],
      "name": "getAdopters",
      "outputs": [
        {
          "internalType": "address[16]",
          "name": "",
          "type": "address[16]"
        }
      ],
      "payable": false,
      "stateMutability": "view",
      "type": "function"
    }
  ]); //abi
						App.contract.options.address = "0x15F6700265a796Ce38A8c861F5332467b88feE5b"; //address
						
						App.bindEvents();
						return App.markAdopted();
	},
	//입양버튼 클릭시 분양 비용을 전송
	bindEvents : function(){ 
		$(document).on('click','.btn-adopt',App.handleAdopt);
	},
	//분양여부 마크표시
	markAdopted : function(){
		App.contract.methods.getAdopters().call().then(function(adopters){
			for(var i=0; i<adopters.length; i++){
				console.log(adopters[i]);
				if(adopters[i] !== '0x0000000000000000000000000000000000000000'){
					$('.panel-pet').eq(i).find('button').text('Success').attr('disabled',true);
				}
			}
		});
	 },
	//초기화면 펫들의 분양여부 표시
	handleAdopt : function(event){ 
		event.preventDefault();
		
		var petId = parseInt($(event.target).data('id'));
		var amount = prompt("보내실 금액을 입력해주세요.");
		
		web3.eth.getAccounts(function(error, accounts){
			var account = accounts[0];
			
			return App.contract.methods.adopt(petId)
					  .send({from:account, value:web3.utils.toWei(amount,"ether")})
					  .then(function(result){
						return App.markAdopted();
					});
		})	
	}
}

$(function(){
	App.init()
})