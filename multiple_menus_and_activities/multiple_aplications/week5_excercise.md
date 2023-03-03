#1
Create a new mapping project in Android Studio. This should be separate from your work from last week with GPS and permissions, and should just contain a simple OSMDroid map, as for Topic 3, Exercise 2. Ensure you add OSMDroid as a dependency in your build.gradle and create a layout containing a map - again, see Topic 3, Exercise 2.
#2
Use the code snippets and guidance above to get the example (allowing the user to change the map style from a separate activity) working.
3# 
Add a third activity to allow the user to enter a latitude and longitude and set the map to this location. To do this:
- Add a further menu option to your app from above labelled "Set Location".
- Create a third activity to allow the user to enter a latitude and longitude. Give it an XML layout file with two EditTexts, one for latitude and one for longitude, and a button. When the button is clicked, the latitude and longitude should be read from the EditTexts and sent back to the main activity in a Bundle in an Intent. Hint: you can store more than one item in a bundle using code such as: val bundle = bundleOf("key1" to value1, "key2" to value2)
- Create a new launcher object to handle receiving the result from the third activity, and use this launcher to launch the third activity when the user selects the "Set Location" menu option. In the launcher's lambda, set the latitude and longitude of the map to the contents of the Bundle from the Intent.