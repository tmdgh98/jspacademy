App= {
	web3Provider:null,
	contract:{},
	bidList : ["경매물품1","경매물품2"],
	init:async function() {
		const table = document.getElementById('table1');
		for(let i=0;i< App.bidList.length; i++){
			const row = table.insertRow();
			const cell1 = row.insertCell(0);
			const cell2 = row.insertCell(1);
			const cell3 = row.insertCell(2);
			
			cell1.innerHTML = App.bidList[i];
			cell2.innerHTML = "name";
			cell3.innerHTML = "0";
			
			$("#title").append($("<option />").html(App.bidList[i]));
		}
		
		//const web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));
		
			
		return await App.initWeb3();
	},
	initWeb3: async function() {
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
	initContract: function(){
		//contract 생성
		App.contract = new web3.eth.Contract(abi);
		App.contract.options.address = "0x21c770efF663532b27012273334BD00FFF8Dd450";
		
		App.makeReceiver();
		App.bindEvents();
		App.highbid();
		App.recentbid();
	},
	makeReceiver:function(){
		web3.eth.getAccounts(function(error, accounts){
			if(error){
					console.log(error);
					return;
			}
			for(el of accounts){
				$("#receiver").append($("<option />").html(el));
			}
		})
	},
	//입찰버튼
	bindEvents:function(){
		$("#savebutton").on("click",App.setBid);
	}, 
	
	//event
	highbid : function(){
		App.contract.events.highestVoter({},function(err,res){
			console.log("res",res);
			if(!err){
				let name = res.returnValues.hName;
				let amount = res.returnValues.hAmount;
				let title = res.returnValues.htitle;
				let table = $("#table1").find("tr");
				for(row of table){
					console.log($(row).find("td").eq(0).html(),title);
					if($(row).find("td").eq(0).html()==title){
						$(row).find("td").eq(1).html(name);
						$(row).find("td").eq(2).html(amount);
					}
				}
			}
			else console.log("err",err);
		})
	}, 
	//event
	recentbid : function(){
		App.contract.events.recentVoter({},function(err,res){
			console.log("res",res);
			if(!err){
				$("#rTitle").html(res.returnValues.rtitle);
				$("#rFname").html(res.returnValues.rName);
				$("#rAmount").html(res.returnValues.rAmount);				
			}
			else console.log("err",err);
		})
	}, 
	//버튼 이벤트 핸들러
	setBid: function(){
		//contract bid1함수 호출
		//set Chat참조
		//버튼 이벤트 핸들러
		var title = $("#title").val();
		var name = $("#name").val();
		var bid = $("#bid").val();
		var account = $("#receiver").val();
		
		if(title=="경매물품1"){
			
			App.contract.methods.bid1(title,name)
					  .send({from:account, value:bid})
					  .then(function(result){console.log(result)});
		}else{
			
			App.contract.methods.bid2(title,name)
					  .send({from:account, value:bid})
					  .then(function(result){console.log(result)});
		}
	}
	
}
$(function(){
	App.init();
	console.log();
})