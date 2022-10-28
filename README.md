# PaintApp

### Translation
[한국어](https://github.com/DavinAn6/PaintApp/blob/main/README.kor.md)

## Description
PaintApp is an application that is similar to MS Paint. The user can draw on a canvas with different colors, erase the canvas, or clear the canvas entirely.

<img alt="PaintApp" src="C:\Users\Davin\MAIN\Study\개발공부\PaintApp\src\main\resources\PaintApp.png"/>

## Functionality
- The canvas size is 650 x 450 pixels
- The side pane contains the following
  - 3 Radio buttons that the user can select from : draw, erase, and circle
  - A textbox in which the user can enter the name of a color to use for subsequent drawing. If the user enters an invalid color, the program will display an alert.
- The bottom pane contains the following
  - Text output that displays the current position of the mouse relative to the drawing pane
  - Text output that indicates how many shapes have been created on the canvas

## Implementation
- This project was built using Maven and JDK 11.
- You can run `PaintApp.java` from inside IntelliJ using the usual run configurations

- Note : If you are using a trackpad instead of a mouse, the application might have trouble recognizing where the mouse is clicked and where it is released. Clicking on where you want your path to start and then start drawing helps fix this issue.