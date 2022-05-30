import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  apiUrl: string = 'http://localhost:8080/';
  operand: string = "";
  operator: string = "";
  show: string = "";


  constructor(private http: HttpClient) {}

  public calc(data: { operand: string; operator: string }) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'});
    let options = { headers: headers };
    this.http.post(this.apiUrl + "calc", data, options)
      .subscribe((JSON:any)  => {
        const response = JSON.message;
        if(response == "c") { // input c or ce
          console.log("Deleted");
          this.operand = this.operator = this.show = "";
        } else if(response == "Cannot divide by zero") {
          this.operand = this.operator = "";
          this.show = "Cannot divide by zero";
        }
        else if (data.operator == "=" || data.operator.length > 1) { // input "=" or unary
          this.operand = this.show = response;
        } else {
          this.operand = "";
          this.show = this.operand + " " + data.operator;
          if(data.operand == "/") {
            this.show = response;
          }
          console.log(response);
          this.operator = ""
        }
      })
  }

  // input number
  inputOperand(operand: string): void {
    if(this.operator != "") return;
    if(operand == "delete") {
      this.operand = this.operand.substring(0,this.operand.length - 1);
      this.show = this.operand + " " + this.operator;
      return;
    }
    if(operand == '-' ) // handle the "-" sign
      this.operand = (this.operand[0] === '-')? this.operand.substring(1): operand + this.operand;
    else
      this.operand += operand; // add input to operand
    // show on the screen
    this.show = this.operand + " " + this.operator;
  }

  // input operator ( + , * , / , - , ...)
  inputOperator(operator: string): void {

    console.log("operand  : "+ this.operand +"  " + "  operator" + this.operator);
    this.operator = operator;
    this.calc({ operand: this.operand, operator: this.operator });
  }


}






