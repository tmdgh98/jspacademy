App = {
   web3Provider: null, //값
   
   contract: {
      
   },
   
   bidList : ["경매물품1", "경매물품2"],
   
   init: async function() {
      const table = document.getElementById('table1');
      for(let i = 0; i < App.bidList.length; i++) {
         const row = table.insertRow();
         const cell1 = row.insertCell(0);
         const cell2 = row.insertCell(1);
         const cell3 = row.insertCell(2);
         
         cell1.innerHTML = App.bidList[i];
         cell2.innerHTML = "name";
         cell3.innerHTML = "0";
         $("#title").append($("<option>").html(App.bidList[i]));
      }
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

    return await App.initContract();
   },
   
   initContract: function() { //abi 
      //컨트랙트 생성
      App.contract = new web3.eth.Contract(abi); 
      App.contract.options.address = "0xb4C5AEbe4Fb5DA02638f94A8EE67dF04Ce0eb0AD"; //돈보낼주소
      
      App.bindEvents();
      App.highbid();
      App.recentbid();
   },
   
   bindEvents: function() { //입찰 버튼 이벤트 
      $("#savebutton").on('click', App.setBid);
   },
   
   highbid: function() { //event
   //getChat참조해서 highestVoter이벤트 구독
   /*
   이벤트가 발생하면 감지해서 콜백함수 실행
   결과값 두번째 인수로 들어옴
   */
      App.contract.events.highestVoter({}, function(err, res) {
         console.log(res);
         
         let i = 0 ;
         
         if(err){
            console.log(i);
            return;
         }
         if(res.returnValues.htitle == "경매물품1") {
             i = 1; 
         }
         else if(res.returnValues.htitle == "경매물품2") {
            i = 2;
         }
         
         const table = document.getElementById('table1');
         const row = table.rows[i];
         const cell2 = row.cells[1];
         const cell3= row.cells[2];
         
         cell2.innerHTML = res.returnValues.hName;
         cell3.innerHTML = res.returnValues.hAmount;
      })
   },
   
   recentbid: function() { //event
      //recentVoter 이벤트 구독
      App.contract.events.recentVoter({}, function(err, res) {
         console.log(res);
       if(!err) {
         document.getElementById('rTitle').innerText = res.returnValues.rtitle;
         document.getElementById('rFname').innerText = res.returnValues.rName;
         document.getElementById('rAmount').innerText = res.returnValues.rAmount;
      }
      else console.log(err);
      });
   },   //event
   
   setBid: function() { //버튼 이벤트 핸들러
      //bid1 함수 호출
      //setChat 참조
      event.preventDefault();
      
      var name = $('#name').val(); //인코딩 
      var bid = parseInt($('#bid').val()); 
      var title = $('#title').val();
      
      web3.eth.getAccounts(function(error, accounts) {
         if(error) {
            console.log(error);
            return;
         }
         var account = accounts[0]; //여우 계좌
         
         return App.contract.methods.bid1(title, name) //bid
            .send({from: account, value: bid}) //msg
            .then(function(result){console.log(result)}); 
      })
   }
}

$(function() {
   App.init();
});