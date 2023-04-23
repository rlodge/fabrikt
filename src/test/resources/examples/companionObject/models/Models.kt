package examples.companionObject.models

import com.fasterxml.jackson.`annotation`.JsonProperty
import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import com.fasterxml.jackson.`annotation`.JsonValue
import javax.validation.constraints.NotNull
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.Map

public data class Cat(
    @param:JsonProperty("id")
    @get:JsonProperty("id")
    @get:NotNull
    public override val id: Long,
    @param:JsonProperty("name")
    @get:JsonProperty("name")
    @get:NotNull
    public override val name: String,
    @param:JsonProperty("tag")
    @get:JsonProperty("tag")
    public override val tag: String? = null,
    @param:JsonProperty("hunts")
    @get:JsonProperty("hunts")
    public val hunts: Boolean? = null,
    @param:JsonProperty("age")
    @get:JsonProperty("age")
    public val age: Int? = null,
) : Pet(id, name, tag) {
    @get:JsonProperty("petType")
    @get:NotNull
    public override val petType: String = "Cat"

    public companion object
}

public data class Dog(
    @param:JsonProperty("id")
    @get:JsonProperty("id")
    @get:NotNull
    public override val id: Long,
    @param:JsonProperty("name")
    @get:JsonProperty("name")
    @get:NotNull
    public override val name: String,
    @param:JsonProperty("tag")
    @get:JsonProperty("tag")
    public override val tag: String? = null,
    @param:JsonProperty("bark")
    @get:JsonProperty("bark")
    public val bark: Boolean? = null,
    @param:JsonProperty("breed")
    @get:JsonProperty("breed")
    public val breed: DogBreed? = null,
) : Pet(id, name, tag) {
    @get:JsonProperty("petType")
    @get:NotNull
    public override val petType: String = "Dog"

    public companion object
}

public enum class DogBreed(
    @JsonValue
    public val `value`: String,
) {
    DINGO("Dingo"),
    HUSKY("Husky"),
    RETRIEVER("Retriever"),
    SHEPHERD("Shepherd"),
    ;

    public companion object {
        private val mapping: Map<String, DogBreed> = values().associateBy(DogBreed::value)

        public fun fromValue(`value`: String): DogBreed? = mapping[value]
    }
}

public data class Error(
    @param:JsonProperty("code")
    @get:JsonProperty("code")
    @get:NotNull
    public val code: Int,
    @param:JsonProperty("message")
    @get:JsonProperty("message")
    @get:NotNull
    public val message: String,
) {
    public companion object
}

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "petType",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = Cat::class, name = "Cat"),
    JsonSubTypes.Type(
        value =
        Dog::class,
        name = "Dog"
    )
)
public sealed class Pet(
    public open val id: Long,
    public open val name: String,
    public open val tag: String? = null,
) {
    public abstract val petType: String
}
