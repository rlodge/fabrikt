package examples.enumPolymorphicDiscriminator.models

import com.fasterxml.jackson.`annotation`.JsonProperty
import com.fasterxml.jackson.`annotation`.JsonSubTypes
import com.fasterxml.jackson.`annotation`.JsonTypeInfo
import com.fasterxml.jackson.`annotation`.JsonValue
import javax.validation.Valid
import javax.validation.constraints.NotNull
import kotlin.String
import kotlin.collections.Map

public data class ConcreteImplOne(
    @param:JsonProperty("some_prop")
    @get:JsonProperty("some_prop")
    public val someProp: String? = null,
) : PolymorphicEnumDiscriminator() {
    @get:JsonProperty("some_enum")
    @get:NotNull
    public override val someEnum: EnumDiscriminator = EnumDiscriminator.OBJ_ONE_ONLY
}

public class ConcreteImplThree() : PolymorphicEnumDiscriminator() {
    @get:JsonProperty("some_enum")
    @get:NotNull
    public override val someEnum: EnumDiscriminator = EnumDiscriminator.OBJ_THREE
}

public data class ConcreteImplTwo(
    @param:JsonProperty("some_enum")
    @get:JsonProperty("some_enum")
    @get:NotNull
    public override val someEnum: EnumDiscriminator,
    @param:JsonProperty("some_prop")
    @get:JsonProperty("some_prop")
    public val someProp: String? = null,
) : PolymorphicEnumDiscriminator()

public enum class EnumDiscriminator(
    @JsonValue
    public val `value`: String,
) {
    OBJ_ONE_ONLY("obj_one_only"),
    OBJ_TWO_FIRST("obj_two_first"),
    OBJ_TWO_SECOND("obj_two_second"),
    OBJ_THREE("obj_three"),
    ;

    public companion object {
        private val mapping: Map<String, EnumDiscriminator> =
            values().associateBy(EnumDiscriminator::value)

        public fun fromValue(`value`: String): EnumDiscriminator? = mapping[value]
    }
}

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "some_enum",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(
        value = ConcreteImplOne::class,
        name =
        "obj_one_only"
    ),
    JsonSubTypes.Type(
        value = ConcreteImplTwo::class,
        name =
        "obj_two_first"
    ),
    JsonSubTypes.Type(
        value = ConcreteImplTwo::class,
        name =
        "obj_two_second"
    ),
    JsonSubTypes.Type(value = ConcreteImplThree::class, name = "obj_three")
)
public sealed class PolymorphicEnumDiscriminator() {
    public abstract val someEnum: EnumDiscriminator
}

public data class Wrapper(
    @param:JsonProperty("polymorph")
    @get:JsonProperty("polymorph")
    @get:Valid
    public val polymorph: PolymorphicEnumDiscriminator? = null,
)
