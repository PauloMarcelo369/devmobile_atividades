package com.example.mya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mya.ui.theme.CalculatorTheme


//aqui irei criar toda a parte lógica da minha aplicação



//aqui eu separo todos os meus tokens, para torna a conversão para postFix mais fácil
fun tokenize(expression: String) : List<String>{
    val expressionSize : Int = expression.length
    var i = 0
    val tokenizedList  = mutableListOf<String>()
    while (i in 0..<expressionSize) {
        val digit: Char = expression[i];

        if (digit.isWhitespace()){
            i++
            continue
        }

        if (digit in '0'..'9') {
            var number: String = digit.toString()
            while ((i + 1) < expressionSize && expression[i + 1]  in '0'..'9') {
                number += expression[i + 1]
                i++
            }
            tokenizedList.add(number)
        } else if (isOperator(digit.toString())) {
            tokenizedList.add(digit.toString())
        } else {
            throw IllegalArgumentException("Inválid Expression!");
        }
        i++;
    }
    return tokenizedList
}

fun isOperator(digit : String) : Boolean{
    return digit == "(" || digit == ")" || digit == "+" || digit == "-" || digit == "*"   || digit == "/"
}


//aqui eu checo a precedencia de um operador
fun precedence(operator: String) : Int{
    if (operator == "+" || operator == "-"){
        return 2
    }
    else if (operator == "*" || operator == "/"){
        return 1
    }
    return Int.MAX_VALUE
}


//
fun infixToPostFix(tokens: List<String>) : List<String>{
    val operatorStack  = mutableListOf<String>()
    val postFixedExpression = mutableListOf<String>()
    for (token in tokens){

        if (token == "("){
            operatorStack.add("(")
            continue
        }
        else if (token == ")"){
            while(operatorStack.last() != "("){
                postFixedExpression.add(operatorStack.last())
                operatorStack.removeAt(operatorStack.size - 1)
            }
            operatorStack.removeAt(operatorStack.size - 1)
        }
        else if (!isOperator(token)){
            postFixedExpression.add(token)
        }
        else{
            while (!operatorStack.isEmpty() && precedence(token) >= precedence(operatorStack.last())){
                postFixedExpression.add(operatorStack.last())
                operatorStack.removeAt(operatorStack.size - 1)
            }
            operatorStack.add(token)
        }
    }

    while (!operatorStack.isEmpty()){
        postFixedExpression.add(operatorStack.last())
        operatorStack.removeAt(operatorStack.size - 1)
    }

    return postFixedExpression
}

fun calculate(operator : String, value1 : Double, value2: Double ) : Double{
    when(operator){
        "+" -> return value1 + value2
        "-" -> return value1 - value2
        "*" -> return value1 * value2
        "/" -> return value1 / value2
        else -> return Double.NaN
    }
}

fun calculateValueToExpression(postFixedExpression : List<String>) : Double{
    val valuesStack = mutableListOf<Double>()
    for (token in postFixedExpression){
        if (isOperator(token)){
            val number2 = valuesStack.removeAt(valuesStack.size-1)
            val number1 = valuesStack.removeAt(valuesStack.size-1)
            valuesStack.add(calculate(token, number1, number2))
        }else{
            valuesStack.add(token.toDouble())
        }
    }
    return valuesStack.last()
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                app()

            }
        }
    }
}

fun result(expression : String) : String{
    val tokenizedExpression = tokenize(expression)
    val postFixExpression = infixToPostFix(tokenizedExpression)
    val result = calculateValueToExpression(postFixExpression)
    return result.toString()
}

@Composable
fun app(){
    var expression by remember{ mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){


        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center ){
            Text(text = expression, modifier = Modifier.background(color = Color.Cyan))
            Row () {
                Column {
                    Row () {
                        Button(onClick = {expression += "1"}){  Text("1") }
                        Button(onClick = {expression += "2"}){  Text("2") }
                        Button(onClick = {expression += "3"}){  Text("3") }
                    }
                    Row () {
                        Button(onClick = {expression += "4"}){  Text("4") }
                        Button(onClick = {expression += "5"}){  Text("5") }
                        Button(onClick = {expression += "6"}){  Text("6") }
                    }
                    Row () {
                        Button(onClick = {expression += "7"}){  Text("7") }
                        Button(onClick = {expression += "8"}){  Text("8") }
                        Button(onClick = {expression += "9"}){  Text("9") }
                    }
                }
                Column {
                    Row {
                        Button(onClick = {expression += "+"}){  Text("+") }
                        Button(onClick = {expression += "-"}){  Text("-") }
                        Button(onClick = {expression += "*"}){  Text("*") }
                    }

                    Row {
                        Button(onClick = {expression += "/"}){  Text("/") }
                        Button(onClick = {expression += "("}){  Text("(") }
                        Button(onClick = {expression += ")"}){  Text(")") }
                    }
                    Row {
                        Button(onClick = {expression = result(expression)}) { Text(text = "=") }
                        Button(onClick = {expression = ""}) { Text(text = "x") }
                    }
                }
            }
        }
    }
    
}
