package com.hulkdx.findprofessional.core.navigation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json

object NavigationScreenSerializer : KSerializer<NavigationScreen> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("NavigationScreen", PrimitiveKind.STRING)

    private lateinit var navigationJson: Json

    fun setJson(json: Json) {
        navigationJson = json
    }

    override fun serialize(
        encoder: Encoder,
        value: NavigationScreen
    ) {
        val jsonString = navigationJson.encodeToString(
            PolymorphicSerializer(NavigationScreen::class),
            value,
        )
        encoder.encodeString(jsonString)
    }

    override fun deserialize(decoder: Decoder): NavigationScreen {
        val jsonString = decoder.decodeString()
        return navigationJson.decodeFromString(
            PolymorphicSerializer(NavigationScreen::class),
            jsonString,
        )
    }
}
