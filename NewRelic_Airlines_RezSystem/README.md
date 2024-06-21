# Build Instructions #

Requires installation of Gradle   

Run the command: gradle clean jar  

This will build NewRelic_Airlines_RezSystem-1.0.jar in the directory build\libs

Copy that jar into this directory and rename it to NewRelic_Airlines_RezSystem.jar   

To start the Reservation System run this command
./RezSystemCtl.sh start   

To stop the Reserveration System    
./RezSystemCtl.sh stop   

To check status    
./RezSystemCtl.sh status   
