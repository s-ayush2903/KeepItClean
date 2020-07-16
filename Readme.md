[![pipeline status](https://gitlab.com/s-ayush2903/KeepItClean/badges/develop/pipeline.svg)](https://gitlab.com/s-ayush2903/KeepItClean/-/commits/develop)

### KeepItClean

An awesome implementation of MVI and Clean Architecture. The current project
makes use of all the latest depedencies and android tools. The project is
completely written in kotlin, cherry on the ice is that the gradle files too are
writtend using DSLs that make this project up to date till now. The project
follows complete Test Driven Development. A star thing in this TDD is that the
testing is performed even before the Presentation Layer(UI components) are
prepared. The project is stale nowadays due to multiple engagements but will be
continued soon

- If you are looking to submit patches, then please do note that reviewing
  practices are quite strict, so sometimes you may have to add appetize url of
  the apk in the PR when demanded or needed.

## Deploying to Appetize.io

Follow these steps to deploy your app to appetize.io:-

1. Get an API token from here: https://appetize.io/docs#request-api-token.
2. Create a CI/CD variable for api token named "APPETIZE_API".  
   Follow this guide to learn how to add CI/CD variables to your gitlab
   repository:
   https://docs.gitlab.com/ee/ci/variables/#creating-a-custom-environment-variable
3. Run the following command once to upload the app.  
   `curl https://API_TOKEN@api.appetize.io/v1/apps -F "file=@path_of_file_to_be_uploaded.apk" -F "platform=android"`  
   Replace API_TOKEN with the api token you got in step 1.  
   Replace file_to_upload.apk with your apk file.
4. Command in step 3 will return a response. Note the public key from your
   response and add a CI/CD varible named "APPETIZE_KEY" and enter this public
   key as value.  
   Make sure to make both the varibales protected and make your branch protected
   too. Follow this guide:
   https://docs.gitlab.com/ee/user/project/protected_branches.html#configuring-protected-branches

   This is a one time setup, subsequent changes you make in your repository will
   be reflected in your link you got in the response automatically.

### Info regarding the branch(deepenCore)

- This branch is all about deepening the core module functionality
- Means in the commits made in this branch i've added more functionalities like
  DataChannelManager and hence deepened the core module
- More specifically, in this branch, i've written classes that manage state,i.e,
  StateManagement oriented

**State Management**

- Consider a scenario where a user clicks an _interactive component_ of the app
- (This is the current _initial state_ of the app)
- A corresponding output/change is then reflected on the screen
- Technically speaking, that reflected change is termed as a _state change_
- (State here has got no special meaning, it is just what it is normally)
- The process that occurs down the architecture lane behind the scenes to cause
  the change reflected on the screen is handled by _stateManagement_
