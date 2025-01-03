const int ProxSensor=2;
int inputVal = 0;
//It is active LOW
void setup() 
{                
  pinMode(13, OUTPUT);          // Pin 13 has an LED connected on most Arduino boards:  
  pinMode(ProxSensor,INPUT);    //Pin 2 is connected to the output of proximity sensor
  Serial.begin(9600);
}

void loop() 
{
  if(digitalRead(ProxSensor)==LOW)      //Check the sensor output
  {
    digitalWrite(13, HIGH);   // set the LED on
  }
  else
  {
    digitalWrite(13, LOW);    // set the LED off
  }
inputVal = digitalRead(ProxSensor);
Serial.println(inputVal);
delay(10);              // wait for a second
}
