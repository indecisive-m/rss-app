package com.example.rss_app.data.models

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName(
    "channel"
)
data class RssChannel(
    @XmlElement(true)
    val title: String? = null,
    @XmlElement(true)
    val description: String? = null,
    @XmlElement(true)
    val link: String? = null,
    @XmlElement(true)
    val language: String? = null,
    @XmlElement(true)
    val item: List<RssItem> = emptyList()

)