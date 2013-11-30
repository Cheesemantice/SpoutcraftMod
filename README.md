[![Spoutcraft](https://dl.dropboxusercontent.com/u/37060654/Images/Spoutcraft/spoutcraft.png)](https://github.com/Spoutcraft)
===========
Spoutcraft is a mod for Forge that has the ability to add more features to Minecraft through an easy-to-use implementation-free API.

## Team
[![Zidane](https://secure.gravatar.com/avatar/3b8d6171c3f15daf35328a4f04c83de9?s=48)](https://github.com/Zidane "Zidane, Lead Developer")
[![Grinch](https://secure.gravatar.com/avatar/19d97d07c8797464aa8b7e2e0481da78?s=48)](https://github.com/Grinch "Grinch, Developer")
[![Dockter](https://secure.gravatar.com/avatar/532e7ce3830bfb47b22c241d45e63cc9?s=48)](https://github.com/mcsnetworks "Dockter, Developer")

## Clone
If you are using Git, use this command to clone the project: `git clone git@github.com:AlmuraDev/SpoutcraftMod.git`

## Setup
__Note:__ If you do not have [Gradle](http://www.gradle.org) installed you can use the gradlew files included with the project in place of 'gradle' in the following command(s). If you are using Git Bash, Unix or OS X then use 'gradlew'. If you are using Windows then use 'gradlew.bat'.

__For [Eclipse](http://www.eclipse.org)__<br>
1. Run `gradle setupDevWorkspace eclipse`<br>
2. Import SpoutcraftMod as an existing project inside Eclipse.<br>
3. Go to 'Run > Run Configurations'<br>
4. Right-click 'Java Application' and select 'New'<br>
5. Set the current project.<br>
6. Set the name as "Client" and apply the information for Client below.<br>
7. Repeat step 5 and 6, then set the name as "Server" and apply the information for Server below.<br>

__For [IntelliJ](http://www.jetbrains.com/idea/)__<br>
1. Run `gradle setupDevWorkspace ideaModule`<br>
2. Import SpoutcraftMod as a module inside IntelliJ.<br>
3. Go to 'Run > Edit Configurations'<br>
4. Click the green + button and select 'Application'<br>
5. Set the name as "Client" and apply the information for Client below.<br>
6. Repeat step 4 and set the name as "Server" and apply the information for Server below.<br>
 
__Client__
```
Main class: net.minecraft.launchwrapper.Launch
VM options: -Xmx1G -Xms512M -Dfml.ignoreInvalidMinecraftCertificates=true -Djava.library.path="../../build/natives"
Program arguments: --version 1.6 --tweakClass cpw.mods.fml.common.launcher.FMLTweaker --username Username
Working directory: Path/to/SpoutcraftMod/run/client
Use classpath of module: SpoutcraftMod (IntelliJ-only)
```

__Server__
```
Main class: cpw.mods.fml.relauncher.ServerLaunchWrapper
VM options: -Xmx1G -Xms512M -Dfml.ignoreInvalidMinecraftCertificates=true -Djava.library.path="../../build/natives"
Working directory: Path/to/SpoutcraftMod/run/server
Use classpath of module: SpoutcraftMod (IntelliJ-only)
```

__Note:__ The working directories for both Client and Server will need to be created manually in the root of SpoutcraftMod's directory. Otherwise you'll get a few errors and will not be able to run it this way.

## Build
__Note:__ If you do not have [Gradle](http://www.gradle.org) installed you can use the gradlew files included with the project in place of 'gradle' in the following command(s). If you are using Git Bash, Unix or OS X then use 'gradlew'. If you are using Windows then use 'gradlew.bat'.

Run `gradle build`

This will produce a compiled JAR file for SpoutcraftMod in `SpoutcraftMod/build/libs`.

## FAQ
__Why does the JAR that I built not have the changes I made?__<br>
>Gradle doesn't re-compile unless changes to the code are detected. This causes it to reuse the resources and code again when building the JAR. If you need to force it to make these changes you can go into the build folder in the root of SpoutcraftMod and delete the 'classes' and 'resources' folders, then build it again.

>Do NOT delete the build folder itself as this will prevent building all together. 'gradle clean' will also delete the build folder and should be avoided.

__How do I apply the license header to the java files I've added?__
>By default, when you run 'gradle build' it will apply the license header to java files that are missing it or do not have the correct header. You can also run 'gradle licenseFormatMain' which will apply the license without building.

__Why do I get `javac: source release 1.7 requires target release 1.7` in IntelliJ when running the client configuration?__
>Sometimes another project can mess with the settings in IntelliJ. Fixing this is relatively easily.

>1. Go to 'File > Settings'<br>
>2. Click the dropdown for 'Compiler' on the left-hand side and select 'Java Compiler'.<br>
>3. Select SpoutcraftMod and set the 'Target bytecode version' as '1.7'.<br>
>4. Click Apply and OK and try running it again.<br>