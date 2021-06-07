# Marvel Android Test

[![N|Solid](https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Marvel_Logo.svg/800px-Marvel_Logo.svg.png)](https://developer.marvel.com/)

Android app that shows a list of Marvel comic characters obtained from the web https://developer.marvel.com/.

- The application is developed with MVVM architecture.
- Using coroutines for network calls
- Common classes for Fragments and Viewmodels, which manage navigation, errors, data retrieval and implemented functionalities that are allowed to be overwritten for any customization
- Test implementation in UseCase, Providers and ViewModels
- Libraries used:
	1. **Dagger 2** for data injection
	2. **Retrofit** for network calls
	3. **Lifecycle** to use LiveData objects
	4. **Glide** for the treatment of images
	5. **Mockito-Nhaarman** for test implementation
	6. **Navigation** to control navigation
	7. **AppCompat** elements
- Enviorements: Debug and Release

### Resqueriments

        minSdkVersion 21
        targetSdkVersion 30

### Steps

- Download the project and open it with Android Studio
- For debug tests, an API_KEY and a PRIVATE_KEY are supplied by default
- For use in release you must add your credentials in the file ../configuration/dependencies.gradle: 
            release: [
                    apyKey        : "\"put me here\"",
                    privateKey    : "\"put me here\""
            ]

- Select your BuildVariant
- Build and Run app

### Some functionalities

- Presentation screen with animation.
- Loading new characters when reaching the end of the list, with custom loading for the fragment
- Obtaining the search bar by dragging the listing downwards, being in the highest part of it.
- Live search of the elements by typing on the search bar.
- Error control and application closure when not configuring credentials.
