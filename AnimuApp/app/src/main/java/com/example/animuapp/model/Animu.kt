package com.example.animuapp.model

data class Animu(
    var name:String,
    var type:String,
    var episodes: String,
    var season:String,
    var year:String,
    var status: String,
    var image:String,
    var tags:List<String>,
    var info:String,
    var listImages:List<String>
)
    fun getAnimu():List<Animu>{
        return listOf(
            Animu(
                name = "Neon Genesis Evangelion",
                type = "tv",
                episodes = "26",
                season = "FALL",
                year = "1995",
                status = "finished",
                image = "https://cdn.myanimelist.net/images/anime/1314/108941.jpg",
                tags = listOf(
                    "action",
                    "alien",
                    "drama",
                    "angels",
                    "conspiracy",
                    "gore"),
                info = "Neon Genesis Evangelion (新世紀エヴァンゲリオン, \"Shin Seiki Evangerion\", lit. New Century Evangelion), commonly referred to as Evangelion, is a Japanese anime series, created by Gainax, that began in October 1995. The anime was written and directed by Hideaki Anno, and co-produced by TV Tokyo and Nihon Ad Systems (NAS). " +
                        "It gained international renown and won several animation awards, and was the start of the Neon Genesis Evangelion series.",
                listImages = listOf(
                    "https://images8.alphacoders.com/695/695227.jpg",
                    "https://images5.alphacoders.com/393/thumb-1920-393187.jpg",
                    "https://images3.alphacoders.com/511/thumb-1920-51109.jpg",
                    "https://wall.alphacoders.com/big.php?i=968265&lang=Spanish",
                    "https://wall.alphacoders.com/big.php?i=956390&lang=Spanish"
                )
            ),
            Animu(
                name = "Ping Pong the Animation",
                type = "tv",
                episodes = "11",
                season = "SPRING",
                year = "2014",
                status = "finished",
                image = "https://cdn.myanimelist.net/images/anime/10/58041.jpg",
                tags = listOf(
                    "bullying",
                    "club",
                    "delinquents",
                    "drama",
                    "genius",
                    "japan"),
                info ="Despite being polar opposites, Makoto \"Smile\" Tsukimoto and Yutaka \"Peco\" Hoshino have been best friends since childhood. Although the overly confident Peco strives to be the best ping-pong player in the world, he often skips practice, earning the ire of his fellow teammates on the Katase High School ping-pong team. Meanwhile, Smile—in spite of his innate talent for the sport—cannot help but hold back his full strength when playing against others. " +
                        "Through their mutual love for ping-pong, the two have developed a bond that is seemingly unbreakable.",
                listImages = listOf(
                    "https://images.alphacoders.com/294/thumb-1920-294213.jpg",
                    "https://images5.alphacoders.com/764/764519.jpg",
                    "https://images2.alphacoders.com/749/thumb-1920-749693.jpg",
                    "https://images3.alphacoders.com/788/thumb-1920-788446.jpg",
                    "https://images3.alphacoders.com/788/thumb-1920-788446.jpg",
                    "https://images5.alphacoders.com/393/thumb-1920-393055.png"
                )
            ),
            Animu(
                name = "Shigatsu wa Kimi no Uso",
                type = "tv",
                episodes = "22",
                season = "FALL",
                year = "2014",
                status = "finished",
                image = "https://cdn.myanimelist.net/images/anime/3/67177.jpg",
                tags = listOf(
                    "comedy",
                    "classical music",
                    "disability",
                    "drama",
                    "friendship",
                    "illness"),
                info = "Kousei Arima is a child prodigy known as the \"Human Metronome\" for playing the piano with precision and perfection. Guided by a strict mother and rigorous training, Kousei dominates every competition he enters, earning the admiration of his musical peers and praise from audiences. " +
                        "When his mother suddenly passes away, the subsequent trauma makes him unable to hear the sound of a piano, and he never takes the stage thereafter.",
                listImages = listOf(
                    "https://images8.alphacoders.com/677/thumb-1920-677543.jpg",
                    "https://images2.alphacoders.com/598/thumb-1920-598673.jpg",
                    "https://images3.alphacoders.com/666/666808.jpg",
                    "https://images8.alphacoders.com/679/679710.png",
                    "https://images3.alphacoders.com/588/thumb-1920-588565.jpg",
                    "https://images3.alphacoders.com/640/thumb-1920-640950.jpg",
                    "https://images6.alphacoders.com/794/thumb-1920-794589.jpg",
                    "https://images8.alphacoders.com/665/thumb-1920-665608.jpg",
                    "https://images4.alphacoders.com/668/thumb-1920-668521.jpg",
                    "https://images7.alphacoders.com/677/thumb-1920-677548.jpg",
                    "https://images4.alphacoders.com/680/thumb-1920-680482.jpg",
                    "https://images5.alphacoders.com/636/thumb-1920-636415.jpg"

                )
                ),
            Animu(
                name = "Steins;Gate",
                type = "tv",
                episodes = "22",
                season = "SPRING",
                year = "2011",
                status = "finished",
                image = "https://cdn.myanimelist.net/images/anime/5/73199.jpg",
                tags = listOf(
                    "amnesia",
                    "assassins",
                    "alternate universe",
                    "based on a visual novel",
                    "comedy",
                    "conspiracy"),
                info ="Eccentric scientist Rintarou Okabe has a never-ending thirst for scientific exploration. Together with his ditzy but well-meaning friend Mayuri Shiina and his roommate Itaru Hashida, Rintarou founds the Future Gadget Laboratory in the hopes of creating technological innovations that baffle the human psyche. " +
                        "Despite claims of grandeur, the only notable \"gadget\" the trio have created is a microwave that has the mystifying power to turn bananas into green goo.",
                listImages = listOf(
                    "https://images6.alphacoders.com/471/thumb-1920-471586.png",
                    "https://images6.alphacoders.com/353/353759.png",
                    "https://images7.alphacoders.com/344/344627.jpg",
                    "https://images2.alphacoders.com/224/224760.jpg",
                    "https://images.alphacoders.com/224/224763.jpg",
                    "https://images6.alphacoders.com/345/345038.jpg"
                )
                ),
            Animu(
                name = "Fruits Basket: The Final",
                type = "tv",
                episodes = "13",
                season = "SPRING",
                year = "2021",
                status = "finished",
                image = "https://cdn.myanimelist.net/images/anime/1085/114792.jpg",
                tags = listOf(
                    "animals",
                    "bullying",
                    "comedy",
                    "drama",
                    "fantasy",
                    "gods"),
                info = "Hundreds of years ago, the Chinese Zodiac spirits and their god swore to stay together eternally. United by this promise, the possessed members of the Souma family shall always return to each other under any circumstances. Yet, when these bonds shackle them from freedom, it becomes an undesirable burden—a curse. As head of the clan, Akito is convinced that he shares a special connection with the other Soumas. " +
                        "While he desperately clings to this fantasy, the rest of the family remains isolated and suppressed by the fear of punishment.",
                listImages = listOf(
                    "https://images2.alphacoders.com/100/thumb-1920-1000041.jpg",
                    "https://images7.alphacoders.com/100/1000037.jpg",
                    "https://images2.alphacoders.com/280/280772.jpg",
                    "https://images3.alphacoders.com/723/723539.png",
                    "https://images2.alphacoders.com/100/1002893.png",
                    "https://images2.alphacoders.com/723/thumb-1920-723532.png",
                    "https://images7.alphacoders.com/723/723531.png",
                    "https://images6.alphacoders.com/116/thumb-1920-1160388.jpg"

                )
                ),
            Animu(
                name = "Spy x Family",
                type = "tv",
                episodes = "12",
                season = "SPRING",
                year = "2022",
                status = "ONGOING",
                image = "https://cdn.myanimelist.net/images/anime/1441/122795.jpg",
                tags = listOf(
                    "action",
                    "adoption",
                    "anti-hero",
                    "assassin",
                    "crime",
                    "drama"),
                info = "Corrupt politicians, frenzied nationalists, and other warmongering forces constantly jeopardize the thin veneer of peace between neighboring countries Ostania and Westalis. " +
                        "In spite of their plots, renowned spy and master of disguise \"Twilight\" fulfills dangerous missions one after another in the hope that no child will have to experience the horrors of war.",
                listImages = listOf(
                    "https://images7.alphacoders.com/122/thumb-1920-1229914.jpg",
                    "https://images4.alphacoders.com/118/1187146.jpg",
                    "https://images5.alphacoders.com/122/thumb-1920-1227893.jpg",
                    "https://images7.alphacoders.com/120/thumb-1920-1206753.png",
                    "https://images5.alphacoders.com/122/thumb-1920-1227567.jpg",
                    "https://images.alphacoders.com/122/1227566.jpg",
                    "https://images3.alphacoders.com/122/thumb-1920-1227544.jpg",
                    "https://images3.alphacoders.com/118/thumb-1920-1187119.jpg",
                    "https://images3.alphacoders.com/122/thumb-1920-1223636.png",
                    "https://images6.alphacoders.com/122/thumb-1920-1229069.jpg"
                )

            ),
            Animu(
                name = "86",
                type = "tv",
                episodes = "11",
                season = "SPRING",
                year = "2021",
                status = "finished",
                image = "https://cdn.myanimelist.net/images/anime/1987/117507.jpg",
                tags = listOf(
                    "action",
                    "based on a light novel",
                    "alternative world",
                    "ghost",
                    "guns",
                    "drama"),
                info = "According to the Republic of San Magnolia, their ongoing war against the Giadian Empire has no casualties—however, that is mere propaganda. While the silver-haired Alba of the Republic's eighty-five sectors live safely behind protective walls, those of different appearances are interned in a secret eighty-sixth faction. Known within the military as the Eighty-Six, they are forced to fight against the Empire's autonomous Legion under the command of the Republican \"Handlers.\"",
                listImages = listOf(
                    "https://images7.alphacoders.com/114/thumb-1920-1140591.jpg",
                    "https://images4.alphacoders.com/114/thumb-1920-1140592.jpg",
                    "https://images6.alphacoders.com/100/1006591.jpg",
                    "https://images3.alphacoders.com/120/thumb-1920-1204546.png",
                    "https://images.alphacoders.com/120/thumb-1920-1204554.png"
                ))
        )


}
