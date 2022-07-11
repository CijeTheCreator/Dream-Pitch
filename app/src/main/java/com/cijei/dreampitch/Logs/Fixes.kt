package com.cijei.dreampitch.Logs

//Passing data from fragment to fragment or activity to activity
//From Activity to Fragment
/*
To move from one activity to another, we have to use an intent
create an Intent object with its first parameter being the current class, eg @MainActivity
the second parameter should be the destination class, ie MainActivity
Before moving, create a bundle object, this is after you must have made what you are loading Parcelable
using putParcelable on the bundle, add a key and put the parcelable in the bundle.

on the intent, call putExtras and then add the bundle that was just created !a mistake I kept making
if the activity has/had fragments, you have to read the intent in the activity class, then set the arguments
for the fragment class

 ie @the activity intent.extras
 Fragment.arguments = intent.extras

 @The fragment now, you'll read the stuff you put with the key that you used to put it. Ez
 arguments.get(key)
 */


//Why the supportFragmentManager was not working
/*
I forgot to call the commit method on the transaction after attempting to change the fragment.
See remove fragments @line21
 */