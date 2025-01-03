//  Variables and Keywords

int a ;// Here a is variable int is a datatype(integer) and " ; " must be put at the end of every line
int age = 21 ; // Here we are assigning a variable to a constant
float len = 20.69 ; // float is another datatype(decimal point or fraction)
const int pin = 2;
int pins[]={1,2,3}; // array if numbers
char Name = "Amshu"; // this is a string of characters(String datatype)
#define pi 3.14 // You can also define constants in this manner
/* There are a few words that cannot be used as variables.
 * Example, int ,if ,else ,while ,for ,etc
 * these are called Keywords,they can be recognised as they come in coloured text
 */

//  Operators
/* Operators are symbols or words used in programming to perform specific operations on data.
 * They can be used for arithmetic, comparison, logical operations, and more.
 * Example +, -, >, /, *, <, <=, >=, !=, etc
 */


//  Libraries
#include<stdio.h> // To include any Library we write #include<libraryName.h>


//  Conditional statements
if (marks>90)   //if (condition)  ...if condition is true then execute the following statements
{
  printf("Grade A");
}
else if(marks >80)    //if first condition is not satisfied then check condition 2,if true then execute following statements
{
  printf("Grade B");
}
else    // if none of the conditions are true then execute the following statements
{
  printf("Grade C");
}

switch(case)
{
case 1: i = 1+3;
case 2: printf(i);
...
default:printf("invalid input);
}


//  Loops
while(condition)    // As long as the condition is true the statemets under it will execute continuously one after the other
{
  //expressions or tasks
  
}

for( initialise; condition; increment/decrement)  // Initialise a variable; check a condition on it; update the variable after one itiration
{
  //expressions or tasks
  
}


// Functions
void functionName(a,b)  // a,b are parameters,which are not mandatory.Any number of parameters can be added depending on requirements
{
  // any task you want to execute whenever function is called
}
int function2() 
{
  // any task you want to execute whenever function is called,that has to return a value
  return thing; //It returns a integer value
}
// Calling these functions is shown in void loop() below


void setup()    //It is a default function present in arduino IDE
{
  // put your setup code here, to run once(Initialising):
  
  pinMode(pin, OUTPUT); // initialize digital or analog pin  as an output/Input.
  Serial.begin(9600);// Initialize serial communication at 9600 baud.(To use Sereial monitor)
  
}
void loop()   //It is also a default function present in arduino IDE
{
  // put your main code here, to run repeatedly:
  functionName(6, 9); // This calls the function,meaning it runs all the lines present in that function
  int x=function2();  // This returns the value of thing ,hence it has to be stored in a variable

  // Some arduino specific inbuilt funtions
  digitalWrite(pin);    // Sets a pin HIGH or LOW for digital output
  digitalRead(pin);   // Reads the state (HIGH or LOW) of a digital pin
  analogRead(pin);    // Reads an analog voltage on an analog pin,Example 0-255 for 0v to 5v
  analogWrite(pin);   // Outputs a voltage on a pin for analog pin
  /* Note: Some digital pins have PWM(Pulse Width Modulation) denoted by ~
   * It is a technique used to control the average voltage output of a digital signal 
   * by varying the duration of its ON and OFF states,
   * effectively simulating an analog voltage level.
   */

   //Mapping
   rotation=map(0,255,0,360)  // Here 0=0' and 255=360' and values inbetween will be mapped accordingly

   // Printing on serial monitor
   Serial.print("name");
   Serial.print(age);
   Serial.println("Hello");


   delay(1000); //this gives us time delay,here 1000 is in milliseconds,hence this gives us 1 second delay
   delayMicroseconds(100);  //Gives 100 micro sec delay

}
