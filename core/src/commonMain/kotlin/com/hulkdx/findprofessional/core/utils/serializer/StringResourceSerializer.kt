package com.hulkdx.findprofessional.core.utils.serializer

import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.allStringResources
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.jetbrains.compose.resources.StringResource

object StringResourceSerializer : KSerializer<StringResource> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "StringResource",
        kind = PrimitiveKind.STRING,
    )

    override fun serialize(encoder: Encoder, value: StringResource) {
        encoder.encodeString(value.key)
    }

    override fun deserialize(decoder: Decoder): StringResource {
        val key = decoder.decodeString()
        return Res.allStringResources[key]
            ?: throw IllegalArgumentException("StringResource not found for key: $key")
    }
}
