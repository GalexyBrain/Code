
// Include Libraries
#include <LiquidCrystal.h>
#include <Servo.h>


// Pin Definitions
#define LCD_PIN_RS	7
#define LCD_PIN_E	6
#define LCD_PIN_DB4	2
#define LCD_PIN_DB5	3
#define LCD_PIN_DB6	4
#define LCD_PIN_DB7	5
#define PUSHBUTTON_1	8
#define PUSHBUTTON_2	9
#define PUSHBUTTON_3	10
#define PUSHBUTTON_RESET	11
#define SERVO9G_PIN_SIG	12



const int servo9gRestPosition   = 0;  //Starting position
const int servo9gTargetPosition = 150; //Position when event is detected
const int Password[4] = {1,1,2,3};
int userPassword[4], n = 0;
int attempts = 0;
// object initialization
LiquidCrystal lcd(LCD_PIN_RS,LCD_PIN_E,LCD_PIN_DB4,LCD_PIN_DB5,LCD_PIN_DB6,LCD_PIN_DB7);
Servo servo9g;

char pass = "";

// Setup the essentials for your circuit to work. It runs first every time your circuit is powered with electricity.
void setup() 
{
    // Setup Serial which is useful for debugging
    // Use the Serial Monitor to view printed messages
    Serial.begin(9600);
    while (!Serial) ; // wait for serial port to connect. Needed for native USB
    Serial.println("start");
    
    // set up the LCD's number of columns and rows
    lcd.begin(16, 2);
    pinMode(PUSHBUTTON_1, INPUT);
    pinMode(PUSHBUTTON_2, INPUT);
    pinMode(PUSHBUTTON_3, INPUT);
    pinMode(PUSHBUTTON_RESET, INPUT);
    servo9g.attach(SERVO9G_PIN_SIG);    
}

// Main logic of your circuit. It defines the interaction between the components you selected. After setup, it runs over and over again, in an eternal loop.
void loop() 
{
    if(digitalRead(PUSHBUTTON_1) ==  HIGH){
      userPassword[n] = 1;
      pass[n] = '*';
      n += 1;
      delay(15);
    }
    if(digitalRead(PUSHBUTTON_2) ==  HIGH){
      userPassword[n] = 2;
      pass[n] = '*';
      n += 1;
      delay(15);
    }
    if(digitalRead(PUSHBUTTON_3) ==  HIGH){
      userPassword[n] = 3;
      pass[n] = '*';
      n += 1;
      delay(15);
    }
    if(digitalRead(PUSHBUTTON_RESET) == HIGH){
      for (int i = 0; i < n; i++) {
          userPassword[i] = 0; 
          pass[i] = '\0';       
        }
        n = 0;
        delay(15);
    }
    if(n == 3){
      if (n == 4) {
        // Check if the entered password matches the correct password
        bool passwordMatch = true;
        for (int i = 0; i < n; i++) {
            if (userPassword[i] != Password[i]) {
                passwordMatch = false;
                break;
            }
        }
      if (passwordMatch) {
          welcomeUser();
      }else if(attempts == 3){
        lock();
      }else{
        attempts += 1;
        for (int i = 0; i < n; i++) {
          userPassword[i] = 0; 
          pass[i] = '\0';
        }
        n = 0;
        delay(15);
      }
    }
    lcd.setCursor(0,0);
    lcd.print(pass);
}

void welcomeUser(){
  lcd.setCursor(0,0);
  lcd.print("Welcome back!");
  for (pos = servo9gRestPosition; pos <= servo9gTargetPosition; pos += 1) { 
    // in steps of 1 degree
    servo9g.write(pos); 
    delay(5);          
  }
  return;
}

void lock(){
  lcd.setCursor(0,0);
  lcd.print("3 WRONG ATTEMPTS");
  lcd.setCursor(1,0);
  lcd.print("LOCKING SYSTEM");
  servo9g.write(servo9gRestPosition); 
  for(int i = 30; i > 0; i--){
    lcd.setCursor(1,14);
    lcd.print(i);
  }
}