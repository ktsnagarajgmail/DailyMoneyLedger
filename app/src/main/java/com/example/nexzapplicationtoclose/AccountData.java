package com.example.nexzapplicationtoclose;


public class AccountData {
    String type, amount, desc;

    public String getType() { return type; }
    public void setType(String type) {  this.type = type;   }

    public String getAmount() {  return amount;  }
    public void setAmount(String amount) {  this.amount = amount;  }

    public String getDesc() {  return desc;  }
    public void setDesc(String desc) {  this.desc = desc;    }

    AccountData(String type, String amount, String desc){
        this.type = type;
        this.amount = amount;
        this.desc = desc;
    }
}
