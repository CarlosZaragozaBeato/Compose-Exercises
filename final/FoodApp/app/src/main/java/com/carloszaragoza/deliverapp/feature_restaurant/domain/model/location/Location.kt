package com.carloszaragoza.deliverapp.feature_restaurant.domain.model.location

class Location(
    val id: String,
    val city:String,
    val country: String,
    val categoriesId: List<String>,
    val ubication:String
){
    companion object {
        val listOfObjects:List<Location> = listOf(
            Location(
                id = "l1",
                categoriesId = listOf(
                    "c1",
                    "c2",
                    "c3",
                    "c4",
                    "c5",
                    "c6",
                    "c7",
                    "c8"
                ),
                city = "Shibuya",
                country = "Japan",
                ubication = "150m"
            ),
            Location(
                id = "l2",
                categoriesId = listOf(
                    "c1",
                    "c2"
                ),
                city = "Kyoto",
                country = "Japan",
                ubication = "200m"
                ),
            Location(
                id = "l3",
                categoriesId = listOf(
                    "c2",
                    "c3"
                ),
                city = "Osaka",
                country = "Japan",
                ubication = "500m"
            ),
            Location(
                id = "l4",
                categoriesId = listOf(
                    "c1",
                    "c2",
                    "c6"
                ),
                city = "Tokyo",
                country = "Japan",
                ubication = "100m"
            ),
            Location(
                id = "l5",
                categoriesId = listOf(
                    "c1",
                    "c2",
                    "c5"
                ),
                city = "Sapporo",
                country = "Japan",
                ubication = "600m"
            )
        )
    }
}
