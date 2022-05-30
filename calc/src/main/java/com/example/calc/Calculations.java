package com.example.calc;

public class Calculations {

    public String operand;
    public String operator;

    public Calculations() {
        this.operand = "";
        this.operator = "";
    }

    public String calc(Calculations data) {
        if (data.operator.equals("c")) {
            this.operand = "";
            this.operator = "";
            System.out.println("DELETED");
            return "c";
        }


        double dataOperand = 1;
        try {
            dataOperand = Double.parseDouble(data.operand);

        } catch (Exception e) {
            System.out.println("catch error" + e);
        }

//
//        // first operand incoming

        // unary operations
        switch (data.operator) {
            case "squared":
                return (dataOperand * dataOperand) + "";

            case "root":
                return Math.sqrt(dataOperand) + "";

            case "1/x":
                return (1 / dataOperand) + "";
        }
        // is not unary operator
        if(this.operand.isEmpty()) {
            this.operand = data.operand;
            this.operator = data.operator;
            return this.operand;
        }

        double operand = Double.parseDouble(this.operand);
        // binary operations
        switch (this.operator) {
            case "+":
                this.operand = (operand + dataOperand) + "";
                break;
            case "-":
                this.operand = (operand - dataOperand) + "";
                break;
            case "*":
                this.operand = (operand * dataOperand) + "";
                break;
            case "/":
                if (data.operand.equals("0")) {
                    System.out.println("Cannot divide by zero");
                    this.operand = "";
                    this.operator = "";
                    return "Cannot divide by zero";
                }
                this.operand = (operand / dataOperand) + "";
                break;

            case "%":
                this.operand = (operand % (int) dataOperand) + "";
                break;
        }


        String temp = this.operand;
        if(data.operator.equals("="))
            this.operator = "";
        else
            this.operator = data.operator;

        System.out.println("operand: "+this.operand +"  operator : "+this.operator);
        return temp;
}


}
