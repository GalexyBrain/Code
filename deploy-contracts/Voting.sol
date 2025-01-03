//SPDX-License-Identifier:MIT
pragma solidity ^0.8.0;
contract Voting {
    address payable public owner;
    mapping(uint => address) voter;
    mapping(uint=>bool) valid;
     uint[] public check;
    constructor(){
    voter[123]=0x5B38Da6a701c568545dCfcB03FcB875f56beddC4;
    voter[234]=0x4B20993Bc481177ec7E8f571ceCaE8A9e22C02db;
    voter[345]=0x78731D3Ca6b7E34aC0F824c42a7cC18A495cabaB;
    voter[456]=0x617F2E2fD72FD9D5503197092aC168c91465E7f2;
    voter[567]=0x17F6AD8Ef982297579C203069C1DbfFE4348c372;
    check=[123,234,345,456,567];
    valid[123]=true;
    valid[234]=true;
    valid[345]=true;
    valid[456]=true;
    valid[567]=true;    
    }

    function choose(address payable abc) public {
        require(abc==0xAb8483F64d9C6d1EcF9b849Ae677dD3315835cb2,"Enter Valid Address");
        owner=abc;
    }

    function eligible(address cde) public payable returns (bool){
        uint i=0;
        bool flag=false;
        for(i=0;i<check.length;i++){
            if(cde==voter[check[i]]){
                if(valid[check[i]]==true){
                flag=true;
                break;
                }
            }
            
        }
              require(flag==true,"Not eligible");
            return true;

    }
    function vote() payable public returns (bool) {
        uint i=0;
        bool flag=false;
        for(i=0;i<check.length;i++){
            if(msg.sender==voter[check[i]]){
                if(valid[check[i]]==true){
                flag=true;
                break;
                }
            }
            
        }

        if(flag==true){
            owner.transfer(msg.value);
            valid[check[i]]=false;
            return true;

        }
        require(flag==true,"Vote unsuccessful");
        
    }

}