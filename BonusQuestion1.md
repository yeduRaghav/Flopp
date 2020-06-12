#### 1) No app is complete without the ability to measure our customers engagement. Describe what you would want to measure and how you would report.

There are various tools to help report the events and actions that can be used to measure engagement. 
Firebase Analytics seem promising as far as an Android app is concerned since it is a Google product and they have tight integration with android to help log things likes deep linking which are a crucial part of the engagement. Additionally, it is available for other platforms, and the other online tools they provide such as BigQuery help make more sense of the data we log.  


Assume that the app has just one screen.
A search screen where a list of products is shown by default and user can perform actions on each item.


A few things we can log  :
1. The user launching the App and seeing the screen.
2. The number of unique items in the list the user scrolled/visible to the user in each search result.
3. Every time user searched something
4. Every time user performed an action on the list.

The timestamp must be logged for each event.

From these few events, there is information that might be useful that we can mine, such as :

1. How much time did the user spend on the first/default search result
2. How far does the user scroll in every search result, before they search again?
3. How long does a user spend looking at an item in each result set before an action is performed on that item what is the position of that item in that result set?
4. How many chars does a user enter for each search?

Building on top of this data,  more meaningful data can be uncovered by profiling individual users over time. Eg:

1. What is the average time a particular user spends on a specific category of product they search for?
2. How has the average time changed before the user performs particular action overtime?
3. What is the category a user searches for but they never perform an action on an item in that session?
4. How do these metrics compare between users that can be profiled together based on some other information we have on them such as gender, age locality, etc. 




---
