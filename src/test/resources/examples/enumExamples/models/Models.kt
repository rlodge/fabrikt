package examples.enumExamples.models

import com.fasterxml.jackson.`annotation`.JsonProperty
import com.fasterxml.jackson.`annotation`.JsonValue
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map

public enum class ContentType(
    @JsonValue
    public val `value`: String,
) {
    APPLICATION_JSON("application/json"),
    APPLICATION_X_SOME_TYPE_JSON("application/x.some-type+json"),
    APPLICATION_X_SOME_OTHER_TYPE_JSON_VERSION_2("application/x.some-other-type+json;version=2"),
    ;

    public companion object {
        private val mapping: Map<String, ContentType> = values().associateBy(ContentType::value)

        public fun fromValue(`value`: String): ContentType? = mapping[value]
    }
}

public data class EnumHolder(
    @param:JsonProperty("array_of_enums")
    @get:JsonProperty("array_of_enums")
    public val arrayOfEnums: List<EnumHolderArrayOfEnums>? = null,
    @param:JsonProperty("inlined_enum")
    @get:JsonProperty("inlined_enum")
    public val inlinedEnum: EnumHolderInlinedEnum? = null,
    @param:JsonProperty("inlined_extensible_enum")
    @get:JsonProperty("inlined_extensible_enum")
    public val inlinedExtensibleEnum: EnumHolderInlinedExtensibleEnum? = null,
    @param:JsonProperty("enum_ref")
    @get:JsonProperty("enum_ref")
    public val enumRef: EnumObject? = null,
    @param:JsonProperty("extensible_enum_ref")
    @get:JsonProperty("extensible_enum_ref")
    public val extensibleEnumRef: ExtensibleEnumObject? = null,
    @param:JsonProperty("list_enums")
    @get:JsonProperty("list_enums")
    public val listEnums: List<ContentType>? = null,
)

public enum class EnumHolderArrayOfEnums(
    @JsonValue
    public val `value`: String,
) {
    ARRAY_ENUM_ONE("array_enum_one"),
    ARRAY_ENUM_TWO("array_enum_two"),
    ;

    public companion object {
        private val mapping: Map<String, EnumHolderArrayOfEnums> =
            values().associateBy(EnumHolderArrayOfEnums::value)

        public fun fromValue(`value`: String): EnumHolderArrayOfEnums? = mapping[value]
    }
}

public enum class EnumHolderInlinedEnum(
    @JsonValue
    public val `value`: String,
) {
    INLINED_ONE("inlined_one"),
    INLINED_TWO("inlined_two"),
    INLINED_THREE("inlined_three"),
    ;

    public companion object {
        private val mapping: Map<String, EnumHolderInlinedEnum> =
            values().associateBy(EnumHolderInlinedEnum::value)

        public fun fromValue(`value`: String): EnumHolderInlinedEnum? = mapping[value]
    }
}

public enum class EnumHolderInlinedExtensibleEnum(
    @JsonValue
    public val `value`: String,
) {
    INLINED_ONE("inlined_one"),
    INLINED_TWO("inlined_two"),
    INLINED_THREE("inlined_three"),
    ;

    public companion object {
        private val mapping: Map<String, EnumHolderInlinedExtensibleEnum> =
            values().associateBy(EnumHolderInlinedExtensibleEnum::value)

        public fun fromValue(`value`: String): EnumHolderInlinedExtensibleEnum? = mapping[value]
    }
}

public enum class EnumObject(
    @JsonValue
    public val `value`: String,
) {
    ONE("one"),
    TWO("two"),
    THREE("three"),
    ;

    public companion object {
        private val mapping: Map<String, EnumObject> = values().associateBy(EnumObject::value)

        public fun fromValue(`value`: String): EnumObject? = mapping[value]
    }
}

public enum class ExtensibleEnumObject(
    @JsonValue
    public val `value`: String,
) {
    ACTIVE("active"),
    INACTIVE("inactive"),
    ;

    public companion object {
        private val mapping: Map<String, ExtensibleEnumObject> =
            values().associateBy(ExtensibleEnumObject::value)

        public fun fromValue(`value`: String): ExtensibleEnumObject? = mapping[value]
    }
}
