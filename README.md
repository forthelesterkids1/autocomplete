# autocomplete

Created this project for fun a few years ago to recreate some functionality I saw in Xcode. Created it to see what would 
be involved in a possible solution. I wrote both the iOS and Android version in about a weekend, and looking back there
are definately some areas that could be modified.

The main piece of the feature can been see in the AutoCompleteMatcher class. I am basically moving through a string and seeing if the chars match (in order) the character set passed in. After matching I highlight the matching characters in the string and show it in a list.

After a rewrite and a polish I think you could turn this into a nice search text highlighter.

Android

Autocomplete/app/src/main/java/com/autocomplete/util/AutoCompleteMatcher.java

iOS

autocomplete_ios/Util/AutocompleteMatcher.m

Have fun
