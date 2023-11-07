package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.feature.home.model.Professional
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class ProfessionalSerializationTest {

    @Test
    fun testJsonDeserialization() {
        val json =
            """
                [
                  {
                    "id": 1,
                    "email": "professional1@example.com",
                    "firstName": "John",
                    "lastName": "Doe",
                    "coachType": "Life Coach",
                    "priceNumber": 100,
                    "priceCurrency": "USD",
                    "profileImageUrl": "image_url_1",
                    "description": "Experienced life coach",
                    "rating": "5.00",
                    "availability": [
                      {
                        "date": "2023-10-31",
                        "from": "08:00:00",
                        "to": "12:00:00"
                      },
                      {
                        "date": "2023-10-31",
                        "from": "08:00:00",
                        "to": "12:00:00"
                      }
                    ]
                  },
                  {
                    "id": 7,
                    "email": "professional121321@example.com",
                    "firstName": "John",
                    "lastName": "Doe",
                    "coachType": "Life Coach",
                    "priceNumber": 100,
                    "priceCurrency": "USD",
                    "profileImageUrl": "image_url_1",
                    "description": "Experienced life coach"
                  }
                ]
            """.trimIndent()

        val professionals = Json.decodeFromString<List<Professional>>(json)
        assertEquals(2, professionals.size)
        assertEquals(1, professionals[0].id)
    }

}
