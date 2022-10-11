package examples.okHttpClientMultiMediaType.client

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import examples.okHttpClientMultiMediaType.models.ContentType
import examples.okHttpClientMultiMediaType.models.QueryResult
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import okhttp3.OkHttpClient
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.jvm.Throws

/**
 * The circuit breaker registry should have the proper configuration to correctly action on circuit
 * breaker transitions based on the client exceptions [ApiClientException], [ApiServerException] and
 * [IOException].
 *
 * @see ApiClientException
 * @see ApiServerException
 */
@Suppress("unused")
public class ExamplePath1Service(
    private val circuitBreakerRegistry: CircuitBreakerRegistry,
    objectMapper: ObjectMapper,
    baseUrl: String,
    client: OkHttpClient,
) {
    public var circuitBreakerName: String = "examplePath1Client"

    private val apiClient: ExamplePath1Client = ExamplePath1Client(objectMapper, baseUrl, client)

    @Throws(ApiException::class)
    public fun getExamplePath1(
        explodeListQueryParam: List<String>? = null,
        queryParam2: Int? = null,
        acceptHeader: String = "application/vnd.custom.media+xml",
        additionalHeaders: Map<String, String> = emptyMap(),
    ): ApiResponse<QueryResult> =
        withCircuitBreaker(circuitBreakerRegistry, circuitBreakerName) {
            apiClient.getExamplePath1(explodeListQueryParam, queryParam2, acceptHeader, additionalHeaders)
        }
}

/**
 * The circuit breaker registry should have the proper configuration to correctly action on circuit
 * breaker transitions based on the client exceptions [ApiClientException], [ApiServerException] and
 * [IOException].
 *
 * @see ApiClientException
 * @see ApiServerException
 */
@Suppress("unused")
public class ExamplePath2Service(
    private val circuitBreakerRegistry: CircuitBreakerRegistry,
    objectMapper: ObjectMapper,
    baseUrl: String,
    client: OkHttpClient,
) {
    public var circuitBreakerName: String = "examplePath2Client"

    private val apiClient: ExamplePath2Client = ExamplePath2Client(objectMapper, baseUrl, client)

    @Throws(ApiException::class)
    public fun getExamplePath2(
        explodeListQueryParam: List<String>? = null,
        queryParam2: Int? = null,
        accept: ContentType? = null,
        additionalHeaders: Map<String, String> = emptyMap(),
    ): ApiResponse<QueryResult> =
        withCircuitBreaker(circuitBreakerRegistry, circuitBreakerName) {
            apiClient.getExamplePath2(explodeListQueryParam, queryParam2, accept, additionalHeaders)
        }
}

/**
 * The circuit breaker registry should have the proper configuration to correctly action on circuit
 * breaker transitions based on the client exceptions [ApiClientException], [ApiServerException] and
 * [IOException].
 *
 * @see ApiClientException
 * @see ApiServerException
 */
@Suppress("unused")
public class MultipleResponseSchemasService(
    private val circuitBreakerRegistry: CircuitBreakerRegistry,
    objectMapper: ObjectMapper,
    baseUrl: String,
    client: OkHttpClient,
) {
    public var circuitBreakerName: String = "multipleResponseSchemasClient"

    private val apiClient: MultipleResponseSchemasClient = MultipleResponseSchemasClient(
        objectMapper,
        baseUrl,
        client
    )

    @Throws(ApiException::class)
    public fun getMultipleResponseSchemas(
        accept: ContentType? = null,
        additionalHeaders: Map<String, String> = emptyMap()
    ): ApiResponse<JsonNode> =
        withCircuitBreaker(circuitBreakerRegistry, circuitBreakerName) {
            apiClient.getMultipleResponseSchemas(accept, additionalHeaders)
        }
}
