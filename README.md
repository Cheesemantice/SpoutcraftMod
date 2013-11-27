![Spoutcraft](https://dl.dropboxusercontent.com/u/37060654/Images/Spoutcraft/spoutcraft.png)
===========
Spoutcraft is a mod for Forge that has the ability to add more features to Minecraft through an easy-to-use implementation-free API.

## Team
[![Zidane](https://secure.gravatar.com/avatar/3b8d6171c3f15daf35328a4f04c83de9?s=48)](https://github.com/Zidane "Zidane, Lead Developer")
[![Grinch](https://secure.gravatar.com/avatar/19d97d07c8797464aa8b7e2e0481da78?s=48)](https://github.com/Grinch "Grinch, Developer")
[![Dockter](https://secure.gravatar.com/avatar/532e7ce3830bfb47b22c241d45e63cc9?s=48)](https://github.com/mcsnetworks "Dockter, Developer")

## Clone
If you are using Git, use this command to clone the project: `git clone git@github.com:AlmuraDev/SpoutcraftMod.git`

## Setup
__Note:__ If you do not have [Gradle](http://www.gradle.org) installed you can use the included gradlew files included with the project in place of 'gradle' in the following commands. If you are using Unix/OS X then use 'gradlew', if you are using Windows then use 'gradlew.bat'.

__For [Eclipse](http://www.eclipse.org)__<br>
1. Run `gradle setupDevWorkspace eclipse`<br>
2. Import SpoutcraftMod as an existing project inside Eclipse.<br>
3. Get to work!

__For [IntelliJ](http://www.jetbrains.com/idea/)__<br>
1. Run `gradle setupDevWorkspace ideaModule`<br>
2. Import SpoutcraftMod as a module inside IntelliJ.<br>
3. Get to work!

## Build
__Note:__ If you do not have [Gradle](http://www.gradle.org) installed you can use the included gradlew files included with the project in place of 'gradle' in the following commands. If you are using Unix/OS X then use 'gradlew', if you are using Windows then use 'gradlew.bat'.

Run `gradle build`

This will produce a JAR file in SpoutcraftMod/build/libs.

## FAQ
__Why does the JAR that I built not have the changes I made?__<br>
>Gradle doesn't re-compile unless changes to the code are detected. This causes it to reuse the resources and code again when building the JAR. If you need to force it to make these changes you can go into the build folder in the root of SpoutcraftMod and delete the 'classes' and 'resources' folders, then build it again.

>Do NOT delete the build folder itself as this will prevent building all together. 'gradle clean' will also delete the build folder and should be avoided.