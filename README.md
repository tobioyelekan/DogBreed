# DogBreed App

This project showcases modern Android app development with clear architectural guidance. It includes popular tools, libraries, and testing frameworks to promote a scalable and maintainable architecture.

The app is modularised by both **layer** and **feature**

Resource: https://developer.android.com/topic/modularization/patterns

## Getting Started
1. Android Studio -> File -> New -> From Version control -> Git
2. Enter https://github.com/tobioyelekan/DogBreed.git into the URL field and press the Clone button

## Environment requirement
To build this project, you require:

* From Android Studio Giraffe upward
* Gradle 8.0
* Kotlin 1.9.20
* Android Gradle Plugin 8.1.0

## Features 🎨
The dogBreed App is a simple application that presents a list of all dog breeds, their subbreeds and you're able to add a breed as a favorite.
This data is fetched from https://dog.ceo/dog-api/ API

The app has a few screens located in multiple feature modules:

* All breeds screen - displays list of all dog breeds
* Breed details screen - displays information about the selected breed
* Subbreed screen - displays images of a dog subbreed
* Favourites screen - display list of favorite breeds

<p float="left">
<img alt="image showing all breeds" src="https://raw.githubusercontent.com/tobioyelekan/DogBreed/master/screenshots/all_breeds.png">
<img alt="image showing breed details" src="https://raw.githubusercontent.com/tobioyelekan/DogBreed/master/screenshots/breed_details.png">
<img alt="image showing subbreeds" src="https://raw.githubusercontent.com/tobioyelekan/DogBreed/master/screenshots/subreeds.png">
<img alt="image showing all favorite breeds" src="https://raw.githubusercontent.com/tobioyelekan/DogBreed/master/screenshots/favorite_breeds.png">
</p>

## Tech Stack
* [Compose](https://developer.android.com/jetpack/compose) - modern toolkit for building native Android UI

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Presenter for persisting view state across config changes

* [Coroutine](https://developer.android.com/kotlin/coroutines) - recommended way to execute asynchronous code, main safe!, and eliminates call back hell code

* [Room](https://developer.android.com/topic/libraries/architecture/room) - provides abstraction layer over SQLite

* [Retrofit](https://square.github.io/retrofit/) - type safe http client and supports coroutines out of the box.

* [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - Kotlin JSON Parser

* [Coil](https://github.com/coil-kt/coil) - load and display image url

* [Turbine](https://github.com/cashapp/turbine) - testing kotlin flow

* [mockk](https://mockk.io/) - mocking library for kotlin used in test

* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - for dependency injection


## License
```
MIT License

Copyright (c) 2020 Tobiloba Oyelekan

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```