[![pipeline status](https://gitlab.com/s-ayush2903/KeepItClean/badges/develop/pipeline.svg)](https://gitlab.com/s-ayush2903/KeepItClean/-/commits/develop)

### KeepItClean
An awesome implementation of MVI and Clean Architecture. The current project makes use of all the latest depedencies and android tools. The project is completely written in kotlin, cherry on the ice is that the gradle files too are writtend using DSLs that make this project up to date till now.
The project follows complete Test Driven Development. A star thing in this TDD is that the testing is performed even before the Presentation Layer(UI components) are prepared. The project is stale nowadays due to multiple engagements but will be continued soon

### Info regarding the branch(deepenCore)

* This branch is all about deepening the core module functionality
* Means in the commits made in this branch i've added more functionalities like DataChannelManager and hence deepened the core module
* More specifically, in this branch, i've written classes that manage state,i.e, StateManagement oriented

**State Management**
* Consider a scenario where a user clicks an _interactive component_ of the app
* (This is the current _initial state_ of the app)
* A corresponding output/change is then reflected on the screen
* Technically speaking, that reflected change is termed as a _state change_
* (State here has got no special meaning, it is just what it is normally)
* The process that occurs down the architecture lane behind the scenes to cause the change reflected on the screen is handled by _stateManagement_
 
