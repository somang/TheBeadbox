# The Beadbox : Vibrotactile Composition Softare / Notation System

## :sparkles: What's new? :sparkles:
:boom: Aug 07-10 ParaPanAm Event: Accessible Innovation Showcase 2015

![alt text](https://github.com/somang/TheBeadbox/blob/master/TheBeadbox/resources/Screenshot.jpg "Screenshot")

## Synopsis
Vibrotactile as an art genre, the Beadbox aims to support the artists who wants to create a vibrotactile art composition of their own. Similar to music/movie editors, it uses timeline user interface but using a unique notation system. The Beadbox has its default setup for the Emoti-Chair but further it will support other output devices. (http://www.ryerson.ca/psychology/emotichair.html)

## Classes
All classes are commented by developers including:
- File I/O : Vidi file structure protocol uses MIDI message base but has unique way to interpret
- Java GUI classes : Forms, Components, Swing!
- ASIO outputs : Audio outputs to each channel, speakers

## Libraries
- This requires (and includes) Java Asio library (jasiohost) and ASIO driver.
- ASIO4ALL is suggested.

## Installation, prerequisites.
Java 1.8.x

## Motivation or Background
A Vibrotactile system needs four essential components to be used.
* Frequency Control
* Intensity (Amplitude) Level Control
* Timing (Time, duration) Control
* Spatial Information (Where does this output contactor positioned)

The Beadbox provides intuitive notation system, intuitive user interface, and allows its users to control over the essential components to create their own vibrotactile art composition.

### Research
There were 30 participants for the user study to evaluate the Beadbox.
Study procedure includes prequestionnaire, breif demo, and a 30 minute composition session.
Also recorded the users behaviour and audio, post questionnaire.

The data collected were analyzed to answer the research questions:

1. How can a novice user who does not have any previous education in computer programming or music, can create a vibrotactile composition?

2. What is the impact of using a dedicated software for vibrotactile composition?

3. Will the dedicated software be viable to the users?

4. What is the protocol for the vibrotactile data?

5. What is the usability of the vibrotactile composition tool for allowing users to control over the frequency, intensity, timing, and spatial information?



# Future works (To do)
- Undo & Redo 
- Virtual Input instrumental/device/window UI (i.e. virtual Vibrochord, Computer Keyboard mapped, etc)
- Import wav files, Export multi-track audio file

## Contributors
Somang Nam :v:
Albert Aikins-Mensah :v:

Project started since Sept. 28th 2014



## License

The MIT License (MIT)

Copyright (c) <year> <copyright holders>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
