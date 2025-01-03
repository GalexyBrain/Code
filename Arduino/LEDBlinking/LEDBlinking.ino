int value1;
#define relay 7
const int buzzer = 13;
void setup() {
  Serial.begin(9600);
  pinMode(relay, OUTPUT);
  digitalWrite(relay, LOW);
}
void loop() {
  Serial.print("Reading");
  while (Serial.available() == 0)
    ;
  value1 = Serial.read();
  Serial.print(value1);
  if (value1 == 0) {
    Serial.print("Unlocking");
    digitalWrite(relay, LOW);
    delay(1000);
  }
  if (value1 == 1) {
    digitalWrite(relay, HIGH);
    Serial.print("Locking");
    delay(3000);
    digitalWrite(relay, LOW);
  }
}
