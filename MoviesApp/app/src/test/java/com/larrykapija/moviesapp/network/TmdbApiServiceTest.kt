
import com.larrykapija.moviesapp.network.api.TmdbApiService
import com.larrykapija.moviesapp.BuildConfig
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TmdbApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: TmdbApiService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TmdbApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getMovieDetails returns data successfully`() {
        val mockResponseJson = """{
      "id": 278,
      "title": "The Shawshank Redemption",
      "overview": "Framed in the 1940s for the double murder...",
      "poster_path": "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
      "backdrop_path": "/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg",
      "vote_average": 8.7,
      "release_date": "1994-09-23",
      "runtime": 142,
      "genres": [{"id": 18, "name": "Drama"}, {"id": 80, "name": "Crime"}],
      "adult": false,
      "original_language": "en",
      "original_title": "The Shawshank Redemption",
      "popularity": 132.266,
      "video": false,
      "vote_count": 25837
    }""".trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponseJson).setResponseCode(200))

        val response = service.getMovieDetails(movieId = 278).execute()

        assertNotNull(response.body())
        assertEquals(278, response.body()?.id)
        assertEquals("The Shawshank Redemption", response.body()?.title)
    }

    @Test
    fun `getPopularMovies returns data successfully`() {
        val mockResponseJson = """{
          "page": 1,
          "results": [
            {
              "adult": false,
              "backdrop_path": "/1XDDXPXGiI8id7MrUxK36ke7gkX.jpg",
              "genre_ids": [28, 12, 16, 35, 10751],
              "id": 1011985,
              "original_language": "en",
              "original_title": "Kung Fu Panda 4",
              "overview": "Po is gearing up to become the spiritual leader of his Valley of Peace...",
              "popularity": 5263.595,
              "poster_path": "/wkfG7DaExmcVsGLR4kLouMwxeT5.jpg",
              "release_date": "2024-03-02",
              "title": "Kung Fu Panda 4",
              "video": false,
              "vote_average": 6.916,
              "vote_count": 286
            }
          ],
          "total_pages": 43188,
          "total_results": 863758
        }""".trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponseJson).setResponseCode(200))

        val response = service.getPopularMovies().execute()

        assertNotNull(response.body())
        assertEquals(1, response.body()?.page)
        assertEquals(1011985, response.body()?.results?.get(0)?.id)
        assertEquals("Kung Fu Panda 4", response.body()?.results?.get(0)?.title)
    }

    @Test
    fun `getNowPlayingMovies returns data successfully`() {
        val mockResponseJson = """
            {
              "dates": {
                "maximum": "2024-03-27",
                "minimum": "2024-02-14"
              },
              "page": 1,
              "results": [
                {
                  "adult": false,
                  "backdrop_path": "/1XDDXPXGiI8id7MrUxK36ke7gkX.jpg",
                  "genre_ids": [28, 12, 16, 35, 10751],
                  "id": 1011985,
                  "original_language": "en",
                  "original_title": "Kung Fu Panda 4",
                  "overview": "Po is gearing up to become the spiritual leader of his Valley of Peace...",
                  "popularity": 5263.595,
                  "poster_path": "/wkfG7DaExmcVsGLR4kLouMwxeT5.jpg",
                  "release_date": "2024-03-02",
                  "title": "Kung Fu Panda 4",
                  "video": false,
                  "vote_average": 6.929,
                  "vote_count": 283
                }
              ],
              "total_pages": 203,
              "total_results": 4050
            }
        """.trimIndent()
        mockWebServer.enqueue(MockResponse().setBody(mockResponseJson).setResponseCode(200))

        val response = service.getNowPlayingMovies().execute()

        assertNotNull(response.body())
        assertEquals(1, response.body()?.page)
        assertEquals(1011985, response.body()?.results?.get(0)?.id)
        assertEquals("Kung Fu Panda 4", response.body()?.results?.get(0)?.title)
    }

    @Test
    fun `getUpcomingMovies returns data successfully`() {
        val mockResponseJson = """
    {
      "dates": {
        "maximum": "2024-04-17",
        "minimum": "2024-03-27"
      },
      "page": 1,
      "results": [
        {
          "adult": false,
          "backdrop_path": "/1XDDXPXGiI8id7MrUxK36ke7gkX.jpg",
          "genre_ids": [28, 12, 16, 35, 10751],
          "id": 1011985,
          "original_language": "en",
          "original_title": "Kung Fu Panda 4",
          "overview": "Po is gearing up to become the spiritual leader...",
          "popularity": 5263.595,
          "poster_path": "/wkfG7DaExmcVsGLR4kLouMwxeT5.jpg",
          "release_date": "2024-03-02",
          "title": "Kung Fu Panda 4",
          "video": false,
          "vote_average": 6.916,
          "vote_count": 286
        }
      ],
      "total_pages": 43,
      "total_results": 852
    }
    """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponseJson).setResponseCode(200))

        val response = service.getUpcomingMovies().execute()

        assertNotNull(response.body())
        assertEquals(1, response.body()?.page)
        assertEquals(1011985, response.body()?.results?.get(0)?.id)
        assertEquals("Kung Fu Panda 4", response.body()?.results?.get(0)?.title)
    }

    @Test
    fun `getTopRatedMovies returns data successfully`() {
        val mockResponseJson = """
    {
      "page": 1,
      "results": [
        {
          "adult": false,
          "backdrop_path": "/kXfqcdQKsToO0OUXHcrrNCHDBzO.jpg",
          "genre_ids": [18, 80],
          "id": 278,
          "original_language": "en",
          "original_title": "The Shawshank Redemption",
          "overview": "Framed in the 1940s for the double murder of his wife and her lover...",
          "popularity": 132.266,
          "poster_path": "/9cqNxx0GxF0bflZmeSMuL5tnGzr.jpg",
          "release_date": "1994-09-23",
          "title": "The Shawshank Redemption",
          "video": false,
          "vote_average": 8.704,
          "vote_count": 25837
        }
      ],
      "total_pages": 463,
      "total_results": 9254
    }
    """.trimIndent()

        mockWebServer.enqueue(MockResponse().setBody(mockResponseJson).setResponseCode(200))

        val response = service.getTopRatedMovies().execute()

        assertNotNull(response.body())
        assertEquals(1, response.body()?.page)
        assertEquals(278, response.body()?.results?.get(0)?.id)
        assertEquals("The Shawshank Redemption", response.body()?.results?.get(0)?.title)
    }

}
