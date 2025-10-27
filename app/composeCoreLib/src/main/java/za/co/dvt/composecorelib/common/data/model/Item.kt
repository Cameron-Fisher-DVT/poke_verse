package za.co.dvt.composecorelib.common.data.model

class Item private constructor(
    val id: String,
    val title: String,
    val subTitle: String,
    val rating: String,
    val description: String,
    val imageUrl: String
) {
    private constructor(builder: Builder) : this(builder.id, builder.title, builder.subTitle, builder.rating, builder.description, builder.imageUrl)

    class Builder {
        var id = ""
        var title = ""
        var subTitle = ""
        var rating = ""
        var description = ""
        var imageUrl = ""

        fun id(id: String) = apply { this.id = id }
        fun title(title: String) = apply { this.title = title }
        fun subTitle(subTitle: String) = apply { this.subTitle = subTitle }
        fun rating(rating: String) = apply { this.rating = rating }
        fun description(description: String) = apply { this.description = description }
        fun imageUrl(imageUrl: String) = apply { this.imageUrl = imageUrl }
        fun build(): Item = Item(this)
    }
}