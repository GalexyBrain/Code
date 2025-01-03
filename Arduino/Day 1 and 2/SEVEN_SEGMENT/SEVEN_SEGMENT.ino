#include "SevSeg.h"
SevSeg sevseg; 

void setup(){
    byte numDigits = 1;
    byte digitPins[] = {};
    byte segmentPins[] = {13,12,11,10,9,8,7};
    bool resistorsOnSegments = true;

    byte hardwareConfig = COMMON_CATHODE; 
    sevseg.begin(hardwareConfig, numDigits, digitPins, segmentPins, resistorsOnSegments);
    
}

void loop(){
    for(int i = 0; i < 10; i++){
        sevseg.setNumber(i, i%2);
        delay(1000);
        sevseg.refreshDisplay(); 
        }
}
