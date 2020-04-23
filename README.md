# FileNameCleaner
Command line program which removes both '()' and '[]' brackets from filenames.

Some example strings of which the program will work on...

"Red Hot Chilli Peppers (Officiale Music Video) - Zephyr Song (Official Music Video)"
<br>
<b>Result:</b> Red Hot Chilli Peppers - Zephyr Song

"Red Hot Chilli (Officiale Music Video) Peppers (Officiale Music Video) - Zephyr Song (Official Music Video)"
<br>
<b>Result:</b> Red Hot Chilli Peppers - Zephyr Song

"Eminem - Stan (()Official Video ())([](!()())())"
<br>
<b>Result:</b> Eminem - Stan

"Rihanna - Disturbia (Official Video) (Out Now!)[Hola]"
<br>
<b>Result:</b> - Rihanna - Disturbia

<a href="fileCleaner.exe" download>Click to Download</a>

<h1>Usage</h1>

<p>Open the command line in the same directory of the fileCleaner application and type 'filecleaner path/to/folder'</p>
<p>The program will proceed to remove all brackets from all the filenames in the directory</p>
