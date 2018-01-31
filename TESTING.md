DAT153: Obligatory assignment 1 &amp; 2
# Test Plan
D: Description

R: Expected results

#### Test OnInstallBehaviour.

Test that when the app is first installed, it will prompt the user for a name, then check that assigned name is correctly displayed.

Expected result is that the app prompts user for name and displays it correctly

#### Test ChangeUserName

D: Test that displayed username is changed when adjusted in settings.

R: That the username is changed

#### Test ChangeUserPicture

D: Test that displayed userpicture is changed when adjusted in settings and saved to application internal storage.

R: That the profilepicture is changed and saved to app internal storage.

#### Test AddPerson

D: Test that the user is able to add a person, name them, add a picture and that this is saved to the app and correctly displayed in the galery.

R: That user is able to add person to app.

#### Test Gallery

D: Test that the user is able to see all the pictures, and that when a picture is clicked it displays the correct name to the picture.

R: That all pictures are displayed and shows the correct names.

#### Test LearningMode

D: Test that when learningmode is started that when the correct name is inputed it confirms that it was correct, and that when a wrong string is entered it says that it was wrong. Also check that the same picture is not displayed twice in a row, unless there is only a single picture. 

R: That the correct name is confirmed as correct and that a wrong string is rejected as wrong. That the same picture won't be shown twice in a row.
