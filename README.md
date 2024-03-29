

# 3d-Ad Template : Ad Templates with 3d parallax effect

[![APP](https://img.shields.io/badge/APP-1.0.3-E0234E.svg?style=for-the-badge)](https://android-arsenal.com/api?level=24)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=24) ![Hilt](https://img.shields.io/badge/Hilt-2.44-FFCA28?style=for-the-badge)

> Ad Template build using json and android ConstraintLayout.



![](images/3d-ad-github.png)


## [Download it on Google Play here](https://play.google.com/store/apps/details?id=in.junkielabs.adsmeta)



## Feature Roadmap
- 2023: Support of GLTF 3D objects.
- 2023: Ad Editor for android.


## Goals
- A very good UI.
- Use of android sensors for 3d effect.
- Why [ConstraintLayout](#why-constraintlayout) ?

<!-- - [Use Cases](https://github.com/JunkieLabs/3d-ad-template/wiki/Use-Cases) -->

## Features:

Whole template functionality is implemented inside domain folder, where SensorProvider used for listing available sensors and SensorPacketProvider for get packets .

* Realtime **sensor rotation** into ads 3d effect.
* Usage of android Data binding.
* MVVM Architecture + Clean architecture.
* Dynamic Usage of Constraint Layout, Coil Image Load etc.
* Theming in M3 for Light and Dark.



## Tech Stack

This project takes advantage of best practices of common libraryies and tools in android.

* [Kotlin](https://kotlinlang.org/)  
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - for background operations  
* [Hilt](https://dagger.dev/hilt/) - for dependency injection  
* [Coil](https://github.com/coil-kt/coil) - image loading library
* [Jetpack libraries](https://developer.android.com/jetpack):
   * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - in-app navigation
   * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
   * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way



## Why ConstraintLayout
- Using ConstraintLayout we can build complex design without need of nested layouts.
- Due single layer, the constraint layout gives better performance. [See more](https://android-developers.googleblog.com/2017/08/understanding-performance-benefits-of.html)  
- For more special use case read this [blog]( https://bignerdranch.com/blog/constraintlayout-flow-simple-grid-building-without-nested-layouts/)

<br>

## Show your love :heart: by giving a :star: on this project.

<br>

<br>






# Open Source Credits



- Thanks [Sushil Kumar](https://github.com/sushilgitter) for helping in the app development.
- [3d Parallax Effect](https://proandroiddev.com/parallax-effect-with-sensormanager-using-jetpack-compose-a735a2f5811b) Blog by Suraj Sau.
- Thank you to everyone who tried out this app and opened issues, suggested features, provided translations, or tested debug builds for me


# License

[![License](https://img.shields.io/:license-apache%202.0-blue.svg?style=for-the-badge)](LICENSE)

