### Android Uber Clone
---

**Important** - *This application relies on Parse which has since been shut down. PR's welcome from anyone who wants to help get this application working again*

**Description:**
A simple Android Uber App Clone using Parse. Select whether you are a rider or a driver. Riders can request rides which are show to drivers. Drivers can view the riders locations and select which rider they would like to pick up. Once selected, navigation is started on their phone. 

**Dependencies:**
MongoDB, Heroku, and Parse

**Code:**
Well documented Code. There are 5 Activities to be aware of:

*MainActivity.Java* - the first page of the app. User determines if they want to be a rider or a driver. From here they are redirected to their respective activities. 

*StarterApplication.Java* - sets up Parse with our application ID and our server.

*YourLocation.Java* - This activity is for RIDERS! Once the app has determined you are a rider you are redirected to this page. This is a Google Maps Activity that shows the user their location. From this page they are able to request and cancel an UBER.

*ViewRequests.Java* - This is the Activity for DRIVERS! This activity allows drivers to see a listview showing the closest rides. Drivers can click on the listview to see an individual ride which will take them to the ViewRiderLocation Activity.

*ViewRiderLocation.Java* - This is the activity where the drivers can see their location and their riders location on a map. If the locations are acceptable to the driver they can accept the ride which will open google maps and navigate them to their rider.
