# Song Recorder
----------
### Software architecture and design project by:
[![](https://avatars1.githubusercontent.com/u/8987819?v=3&s=150)](https://github.com/DajanaS "Dajana Stojchevska") [![](https://avatars0.githubusercontent.com/u/18077884?v=3&s=150)](https://github.com/mtoshevska "Martina Toshevska") <br />
### Check out our promo video [here](https://www.youtube.com/watch?v=0DImwW_DQJQ)
## 1. Description
Our mobile application SongRecorder is a music application used for fun intended for anyone who likes music. Users can record music, record themselves singing or record anything else, get songs information and play their saved music files. <br />

## 2. Guide
Right after starting the application, the Home Screen is shown. This is the main screen in the application because here the user can see the main functionalities of the application and respectively choose one option. The first possible option is to `Start recording`. Another one is to `View files` which are already recorded and saved on the phone. Last one is, of course, to `Exit` the application. <br />
<img src="https://github.com/mtoshevska/SongRecorder/blob/master/Screenshots/1.png?raw=true" alt="Home Screen" height="500px"/> <br />
Once the user clicks the button `Start recording`, this music application connects to the microphone of the phone (previously when application is being installed user must allow its usage) and this is how it records the sound. Now, once it has started recording, the user only one option - to `Stop\Pause` the recording. The timer is on as the recording is happening. <br />
<img src="https://github.com/mtoshevska/SongRecorder/blob/master/Screenshots/2.png?raw=true" alt="Recording" height="500px"/> <br />
By clicking `Stop/Pause` the timer stops counting. Another screen is shown where user has several options. If the user had intention just to pause the recording, now they can `Continue`, so the timer would just continue counting where it has previously stopped. Second option is to discard this recording (here the user is asked if they are sure to do it) because if agreed, the recording will be lost. Last option is to save the recorded sound on the phone. <br />
<img src="https://github.com/mtoshevska/SongRecorder/blob/master/Screenshots/3.png?raw=true" alt="Paused" height="500px"/> <br />
By clicking the option to `Save` the recorded sound, the user now has to write its name in order the application to know exactly which information (for which song) to pull and respectively save the song on the phone along with those information. <br />
<img src="https://github.com/mtoshevska/SongRecorder/blob/master/Screenshots/4.png?raw=true" alt="Writing title" height="500px"/> <br />
The information for that song are taken from a database that we made on our own and filled with few popular songs. In order the application to be able to pull those information, the user's phone must be connected to the Internet. Otherwise, the application will not be able to pull the information and save the song with it. The user gets notified (warned) if this happens. <br />
<img src="https://github.com/mtoshevska/SongRecorder/blob/master/Screenshots/8.png?raw=true" alt="Pulling information" height="500px"/> <br />
If everything is okay, meaning the user successfully is connected to the Internet and the song is found in the database, the user is asked to choose if they are sure they want to save the song locally in the phone storage. <br />
<img src="https://github.com/mtoshevska/SongRecorder/blob/master/Screenshots/5.png?raw=true" alt="Saving song" height="500px"/> <br />
Of course, it is possible the user to enter wrong song name, or the song is not popular according to our criteria, so it does not exist in our database. In that case, a warning notification is shown, saying that the song does not exist. The user can choose to try again, or save it just like that with the name they want, without additional pulled information. <br />
<img src="https://github.com/mtoshevska/SongRecorder/blob/master/Screenshots/6.png?raw=true" alt="Try again" height="500px"/> <br />
All the recorded files are saved (kept) locally on phone. By clicking `View files` option, the user can see all their recorded sounds in one place and choose any of it to play. All the information pulled from database are shown here. <br />
<img src="https://github.com/mtoshevska/SongRecorder/blob/master/Screenshots/7.png?raw=true" alt="View files" height="500px"/> <br />
These are the main functionalities of our music application. The application is designed in such a way that is has flexible design, so it could be upgraded easily. If it gets high rating in future, it is possible to add more functionalities to it.
