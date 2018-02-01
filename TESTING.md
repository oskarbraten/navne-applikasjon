DAT153: Obligatory assignment 2
# Test Plan

## Test OnInstallBehaviour

__Description:__
Test that when the app is first installed, it will prompt the user for a name, then check that assigned name is correctly displayed.

__Type:__
Activity/UI

__Pre-conditions:__
Cleared default shared preferences.

__Expected result:__
The app prompts the user for a name and when entered displays it correctly in the main menu (MainActivity).


## Test ChangeUserName

__Desciption:__
Test that displayed username is changed when adjusted in settings.

__Type:__
UI

__Pre-conditions:__
None.

__Expected result:__
That the username is changed, and updated in the main menu (MainActivity).

## Test ChangeUserPicture

__Desciption:__
Test that displayed userpicture is changed when adjusted in settings and saved to application internal storage.

__Type:__
UI

__Pre-conditions:__
None.

__Expected result:__
That the profilepicture is changed and saved to app internal storage.

## Test AddPerson

__Description:__
Test that the user is able to add a person, name them, add a picture and that this is saved to the app and correctly displayed in the gallery.

__Type:__
UI

__Pre-conditions:__
None.

__Expected result:__
That user is able to add person to app.

## Test Gallery

__Description:__
Test that the user is able to see all the pictures, and that when a picture is clicked it displays the correct name to the picture.

__Type:__
UI

__Pre-conditions:__
None.

__Expected result:__
That all pictures are displayed and shows the correct names.

## Test LearningMode

__Description:__
Test that when learningmode is started that when the correct name is inputed it confirms that it was correct and that points are awarded, and that when a wrong string is entered it says that it was wrong and points removed. Also check that the same picture is not displayed twice in a row, unless there is only a single picture. 

__Type:__
UI

__Pre-conditions:__
None.

__Expected result:__
That the correct name is confirmed as correct and rewarded and that a wrong string is rejected as wrong and punished. That the same picture won't be shown twice in a row.

## Test HighscoreUpdate

__Description:__
Check that when a new highscore is reached in learning mode, that it will properly replace the old highscore, and that a lower score than a highscore doesn't affect the displayed highscore in main activity.

__Type:__
UI

__Pre-conditions:__
None.

__Expected result:__
That a new highscore is saved and displayed in main activity, and that any lower score will be discarded
