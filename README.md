# TenPinBowling

This project allows to read a file and get a bowling game scores


## Installation Pre requisites
    - java from 8 to above to be able to execute java commands
    - maven 3 in classpath to be able to execute mvn commands


## Attached files

In the resources folder there is a sample.txt file
which contains the result of several players used for unit tests


## Installation Instructions

- download project
- open a command line
- change to project directory
- run the command: mvn verify 
- run the command: 
  java -jar target/consoleApp-1.0.0-jar-with-dependencies.jar fileFullPath
  replacing 'fileFullPath' by the full path of the scores file


## Known Bugs
    - Only handles input with 10 frames
    - If there is a FOUL in the score it is shown as a 0

