# Github Trending
An android app that shows trending repositories and developers, fetching
results from <https://github-trending-api.now.sh>

## Project Structure
The project consists of two modules
- **app**  
  The Android app with screens for trending repos and devs
- **libTrendingGithub**  
  A java-library that implements the API

### app

#### UI

The app has two activities -
- Trending Lists  
  This has a bottom-bar based navigation, and has 3 fragments
  - Trending Repositories
  - Trending Developers
  - Languages
- Repository Details  
  This is opened when clicking a repository in the list for details


#### Libraries Used
- **Android Jetpack**  
  RecyclerView, CardView, Fragments, BottomNaviation
- **Glide** (image rendering)

#### Test Setup

There are tests for UI as well as for ViewModels
For testing please run

```sh
./gradlew connectedCheck
```

### libTrendingGithub

#### Libraries Used
- **OkHttp**
- **Moshi** (JSON de/serialisation)
- **Reotrofit**

#### Test Setup
There are unit tests with 100% coverage. '

To run tests use

```sh
./gradlew :libTrendingGithub:test
```

We have both real (flaky) tests and tests with OkHttp's MockWebServer
The `assets` folder contains sample responses used in mock tests.

Gradle configuration creates a `test.jar` file too that can be
used in consuming modules (like the app) to test against the mock
server and responses.
