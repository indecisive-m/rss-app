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

@Serializable
@XmlSerialName("item")
data class RssItem(
    @XmlElement(true)
    val title: String? = null,
    @XmlElement(true)
    val description: String? = null,
    @XmlElement(true)
    val link: String? = null,
    @XmlElement(true)
    val guid: String? = null,
    @XmlElement(true)
    val pubDate: String? = null

)


