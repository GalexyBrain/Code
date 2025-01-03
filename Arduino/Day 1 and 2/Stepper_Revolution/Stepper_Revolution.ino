// Arduino stepper motor control code
//for one revolution its range is 0 to 2048 steps

#include <Stepper.h> // Include the header file

// change this to the number of steps on your motor
#define STEPS 32

// create an instance of the stepper class using the steps and pins
Stepper stepper(STEPS, 8, 10, 9, 11);

int val = 0;

void setup() {
  Serial.begin(9600);
  stepper.setSpeed(500);

}


void loop()
{
   stepper.step(2048);
   delay(1000);
   stepper.step(-2048);
   delay(1000);
}
