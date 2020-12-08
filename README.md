# Final Project PART 1 (Individual Assignment) - Distributed System - NET3204

## How To Run / Setup

1) `git clone https://github.com/JAhimaz/TCPScalaApp-DS-Assignment.git`

2) Open `SERVER` folder and open a **Powershell** or **Command Prompt** window and type
   `sbt "-Djava.security.policy=no.policy"`
   Followed by
   `run`
   Wait until it prompts `Press Any Key To Shutdown Server`.
3) Open `CLIENT` folder and open a **Powershell** or **Command Prompt** window and type
   `sbt`
   Followed by
   `run`
4) You may use any number of Clients
5) `0` in the `CLIENT` to exit and press **Any Key** to exit the `SERVER`.

## Requirements

* [SBT](https://www.scala-sbt.org/download.html)

## Application Features

List of features the application is capable of achieving:

* Storage Method: DERBY SQL
* Retrieve Items from Database Over A Network using TCP
