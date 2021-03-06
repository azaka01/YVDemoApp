# Master Detail Application

A Demo App to consume an API service `Randomuser.me API` and display data

## About

The Demo App is written in Kotlin and requires minimum Android API 21+.

Developed using Android Studio 4.2 Beta 6 with Gradle Wrapper `6.7.1` and AGP `4.2.0-beta06`

## Usage

It's a basic implementation of two screens 

    Master screen with 500 user pictures and names. Uses `seed` value `yv` in API call
    Detail screen with user picture and details 

There's a pull to refresh on the main screen in case an error occurs and data needs to be refetched.



![Demo](https://user-images.githubusercontent.com/1098487/111902517-c3ee3480-8a35-11eb-95e2-540f5fd77a8a.gif)

## Architecture

The app is implemented using a clean architecture approach 

Data, Domain and App (Presentation) layers each have their own modules with separation of concerns

Domain layer is the inner-most with commonly shared models, interfaces and use cases

Data layer has the network and repository classes

The app (presentation) layer has the ViewModels and UI

These Android Architecture components are used - MVVM, LiveData, Navigation, new View Binding

## Dependencies

All dependencies are referenced in `common\dependencies.gradle` 

DI - I chose the new Hilt library as it's easier to integrate than Dagger and still supports the required features

Image Loading - Coil is a new Kotlin-first image loading library with good performance

RxJava2 - for network calls

Unit tests - Mockito and Mockk

## Unit tests and code coverage

Classes with significant business logic have unit tests - `UsersViewModel` and `UserResponseMapper`

DemoAppCoverage1.png![image](https://user-images.githubusercontent.com/1098487/111901857-51c82080-8a32-11eb-8343-18c5073ad0de.png)
VMCoverage.png![image](https://user-images.githubusercontent.com/1098487/111901862-61476980-8a32-11eb-8c82-ed3cb2040c02.png)
MapperCoverage.png![image](https://user-images.githubusercontent.com/1098487/111901863-64daf080-8a32-11eb-97aa-8e56c76862f6.png)


## Improvements and Modifications

DB caching can improve data persistence. This would live inside the `data` module.

Use Coroutines with Flow instead of RxJava


