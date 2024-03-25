# Android-Movies-App

# Project Structure

## Setup

The initial setup of your Android project is crucial for ensuring that all components work together seamlessly, especially when relying on external services like the TMDb API. This setup step includes setting up your API key as a variable within your local development environment in a secure and accessible manner for your application. Below, you'll find detailed instructions on how to accomplish this setup.

### General Purpose

The purpose of this setup section is to explain how to securely configure and store your TMDb API key in your Android project. This is important for several reasons:

- **Security**: Keeping your API key secure prevents unauthorized use that could lead to quota violations or misuse under your account.
- **Flexibility**: By storing the API key in your local configuration, you can easily switch between different keys (e.g., development, production) without changing the codebase.
- **Maintainability**: Centralizing the API key in one place makes it easier to update or change it without searching through your project's code.

### How to Configure Your API Key

1. **Obtain an API Key**: First, you need to obtain an API key from TMDb by registering for an account and requesting an API key through their website.

2. **Local Properties File**: In your Android project, locate the `local.properties` file. This file is not tracked by version control by default (and it shouldn't be), making it a good place to store sensitive information like your API key.

3. **Add Your API Key**: Open `local.properties` and add your TMDb API key as follows:

    ```
    TMDB_API_ACCESS_KEY="your_api_key_here"
    ```

    Replace `your_api_key_here` with the actual API key you obtained from TMDb.

4. **Access Your API Key in the Project**: In your project, you can access this API key through the `BuildConfig` object, which is generated during the build process. For example, in your network module or wherever you set up your Retrofit instance, you can use `BuildConfig.TMDB_API_ACCESS_KEY` to retrieve the API key.

### Running the Project

With the API key configured, you're now ready to run your project. The API key will be included in all requests to the TMDb API, authorizing your application to fetch data. Ensure that your Retrofit configuration and network module are set up to use the API key as shown in the previous sections.

Remember, the `local.properties` file should never be committed to your version control system to keep your API key secure.






## Network

### Network Module
The `NetworkModule` object provides Retrofit and OkHttpClient instances configured for communicating with the TMDb API. It's set up with Hilt to allow for dependency injection across the application.


### Data Models

The project defines several data models to parse responses from the TMDb API:

- `MovieDetails`: Represents detailed information about a movie.
- `MovieResponse`: Serves as a general response model for listing movies, used across different API endpoints (Popular, Top Rated, Now Playing, and Upcoming).
- `Genre`: Represents a movie genre.


### Api Interface
The `TmdbApiService` interface defines methods for fetching movies from various TMDb API endpoints:

- `getMovieDetails`: Fetches detailed information about a specific movie.
- `getPopularMovies`: Retrieves a list of popular movies.
- `getNowPlayingMovies`: Retrieves a list of movies currently playing in theaters.
- `getUpcomingMovies`: Retrieves a list of upcoming movies.
- `getTopRatedMovies`: Retrieves a list of top-rated movies.

***Consuming the API in a ViewModel***

Below is an example of how to consume the TMDb API in a ViewModel using Hilt for dependency injection:

```kotlin
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val tmdbApiService: TmdbApiService
) : ViewModel() {

    private val _movieDetails = MutableLiveData<MovieDetails>()
    val movieDetails: LiveData<MovieDetails> = _movieDetails

    fun fetchMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val response = tmdbApiService.getMovieDetails(movieId, "Bearer ${BuildConfig.TMDB_API_ACCESS_KEY}", "en-US").execute()
                if (response.isSuccessful && response.body() != null) {
                    _movieDetails.postValue(response.body())
                } else {
                    // Handle API error
                }
            } catch (e: Exception) {
                // Handle network error
            }
        }
    }
}