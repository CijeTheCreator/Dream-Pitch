package com.cijei.dreampitch.ui

import androidx.fragment.app.Fragment

class SelectPlayerFragment: Fragment() {
    //Create data classes for players and sets
    //Find out how to read the search widget edit text onType
    //Find out how to filter lists in Kotlin
    //Create the list of players as a tickable list EZ
    //This should'nt be a fragment, from design it should be an activity
    //From the design too, ignore it, use a floating action button to mark completion instead
    //Search bar, add player button, recycler view and finally floating action button to complete it

    //So far, I've learnt to use the EditText widget to the TextWatcher
    //The text watcher replicates javascript's useEffect/useState when you are working with inputs
    //I also learnt how to filter. It seems very straight forward.

    /*
    The plan now?
    Design the layout for this fragment
    (Remember to set it to wrap content because of that problem that was encountered previously)
    What will the layout contain?
    First an editText, why not a searchWidget you must ask?...The fpl implementation does not feel
    like the work of a searchWidget.
    The editText will be hooked to a textWatcher.
    Directly under the edit text, will be a recycler view showing all the players.
    OnTextChange the recycler view's list will be filtered

    Onclick for each of the players (if we get to this point today), it should display its tick icon
    Find out how to add onclick listeners to the viewholders otherwise called holders
     */

    //You cannot have an activity in the manifest with an empty intent filter
    //When comparing characters you must use single quotes (using equals)
    //You can use double quotes when comparing with contains

    /*
    Objectives 07/07/07
    Find out how to attach onClick listeners to each of the players
    The onClick listener should be based on the kind that was used in podplay
    Implement an add player feature
    Implement the second list, the list for choosing players selected. Should I use the Whatsapp style instead of this tick style.
    Implement the onClick listener
     */
}