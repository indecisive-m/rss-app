package com.example.rss_app.data.models

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlOtherAttributes
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("rss")
data class RssResponse(
    @XmlOtherAttributes
    val version: String? = null,
    @XmlElement(true)
    val channel: RssChannel? = null
)






