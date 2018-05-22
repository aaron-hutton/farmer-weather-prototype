                    ﻿_                
 _ __ ___  __ _  __| |_ __ ___   ___ 
| '__/ _ \/ _` |/ _` | '_ ` _ \ / _ \
| | |  __/ (_| | (_| | | | | | |  __/
|_|  \___|\__,_|\__,_|_| |_| |_|\___|
                                    
Running the app


Java 10 is required to run the app. A group19.jar file is provided to facilitate running the app. It is necessary that a folder resources/icons/ containing icons used by the app (contained in the zip file) is present in the directory the .jar is run from.




Platforms


The app runs on all of Windows, Linux and Mac OS. On Windows there are several minor issues with icon resolution and button placement.




Weather data


Weather data for both hourly and daily summaries is taken from the MET Office weather API in the form of JSON, or in some cases calculated from that data.




Location data


The Google Maps library is used to convert text addresses and postcodes into latitude-longitude coordinates in order that the appropriate weather station to take data from can be found.




The maps


The maps are downloaded using the MET Office weather API. In particular, we extract URL information from the information json provided by the API, and from the URLs download visible-range satellite imagery of the UK, as well a rain may overlay. We then crop the images to fit the screen (with the UK square in the centre) and overlay the two images. The satellite imagery is updated every three hours, while the rain map is updated every 15 minutes.
It is worth noting that in our time developing the app there have been (rare) moments when the images served by the MET Office appear somewhat corrupted. In our experience, this is a problem on the server side and is usually resolved at the next image update.


The icons


App icon (tractor) made by Freepik from www.flaticon.com, used and modified with permission. Other icons (weather icons) made by Larysa from http://emske.com, used with permission. GIMP was used to slice and edit icons, as well as to create new ones, which, like the other weather icons, are made available by the author for free unattributed use.




Growing degree days


The application uses the bizee Degree Days API in order to get past degree days numbers, calculated to high accuracy using the Enhanced Met Office Method. The free version used for this prototype only provides data for locations in Cape Cod, but a full release would include data from all over the UK (and the USA, and many other countries).
While on the GDDs tab it is possible to calculate total growing degree days in your location from a given start date to the current day, going back up to around 6 years.
The GDD forecast tab gives you the growing degree days for the next five days calculated from the Met Office maximum and minimum temperature, and using a base temperature of 10C (the usual base), via the formula ((max + min)/2 - base).
