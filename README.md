# Flopp

### Screens :

The App has 2 screens

1. A list screen which displays cards with few text and dynamic image, loaded from URL.
   Here two pages of 10 items each are displayed(pagination).
   If the api call fails for first page, local data is shown if available.   
   When first page is fetched again(requires new app session, reload UX not implemented) the local data is cleared.
   Every time next page is loaded it is saved to local db.

2. A detail screen with image and more text. 
   The data is fetched from API if it fails attempts to load data from db of available.
   Whenever data is loaded, it is added to db and replaces old enrty for same data.
   Currently no cache invalidation strategy has been implemented.
   
   
### API Implementation :

The api layer is implemented using retrofit.
The reponse is mocked with the help of an interceptor named `MockResponseInterceptor`.
This interceptor returns 2 pages for the list call(20 distinct items, 10 per each page)
As for the detail call the same response is returned.


### Models :

There are 3 distinct types of models in the app. 
1. Api
2. Db
3. Localized

The Api model represents the data retirned from the Api end points.
The class named has suffix `ApiItem` eg: `ListingApiItem`

The Db models are represents the data stored in Db. 
The class named has suffix `Entity` eg: `ListingEntity`

The Localized models are represents the data that is formatted and ready for the views to consume. 
The names are not suffixed properly as of now. The two classes in this category are : `ListingListItem` & `DetailedListing` 


### Testing :

The app will attempt to fetch data from api and if it fails attempt to fallback to local data.
To cause the fallback to local data, turn off internet.

If local data is not available either, a retry button will be shown. 
Clicking the button and will trigger to attempt the process again.

There are some stub methods return for the conditions that ought to be tested, few simple tests are also written.





   
   
   
   
   
   
   
   
   
