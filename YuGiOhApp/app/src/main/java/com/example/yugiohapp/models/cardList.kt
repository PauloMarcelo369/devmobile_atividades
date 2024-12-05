package com.example.yugiohapp.models

import com.example.yugiohapp.R


val cardList = listOf(
    Card(
        id = 1,
        cardName = "Dark Magician",
        cardType = "Monster",
        cardImageRes = R.drawable.darkmagician,
        cardDescription = "The ultimate wizard in terms of attack and defense.",
        cardStars = 7,
        isFavorite = false
    ),
    Card(
        id = 2,
        cardName = "Blue-Eyes White Dragon",
        cardType = "Monster",
        cardImageRes = R.drawable.blue_eyes,
        cardDescription = "This legendary dragon is a powerful engine of destruction.",
        cardStars = 8,
        isFavorite = true
    ),
    Card(
        id = 3,
        cardName = "Red-Eyes Black Dragon",
        cardType = "Monster",
        cardImageRes = R.drawable.red_eyes,
        cardDescription = "A ferocious dragon with a deadly attack.",
        cardStars = 7,
        isFavorite = false
    ),
    Card(
        id = 4,
        cardName = "Summoned Skull",
        cardType = "Monster",
        cardImageRes = R.drawable.summoned_skull,
        cardDescription = "A fiend with dark powers for confusing the enemy.",
        cardStars = 6,
        isFavorite = true
    ),
    Card(
        id = 5,
        cardName = "Dark Hole",
        cardType = "Spell",
        cardImageRes = R.drawable.dark_hole,
        cardDescription = "Destroy all monsters on the field.",
        cardStars = 0,
        isFavorite = false
    ),
    Card(
        id = 6,
        cardName = "Mirror Force",
        cardType = "Trap",
        cardImageRes = R.drawable.mirror_force,
        cardDescription = "Destroy all Attack Position monsters your opponent controls when attacked.",
        cardStars = 0,
        isFavorite = false
    ),
    Card(
        id = 7,
        cardName = "Monster Reborn",
        cardType = "Spell",
        cardImageRes = R.drawable.monster_reborn,
        cardDescription = "Target 1 monster in either GY; Special Summon it.",
        cardStars = 0,
        isFavorite = true
    ),
    Card(
        id = 8,
        cardName = "Blue-Eyes Ultimate Dragon",
        cardType = "Fusion Monster",
        cardImageRes = R.drawable.blue_eyes_ultimate_dragon,
        cardDescription = "Fusion of three Blue-Eyes White Dragons.",
        cardStars = 12,
        isFavorite = false
    ),
    Card(
        id = 9,
        cardName = "Exodia the Forbidden One",
        cardType = "Monster",
        cardImageRes = R.drawable.exodia,
        cardDescription = "If you have all five pieces of Exodia, you win the duel.",
        cardStars = 3,
        isFavorite = true
    ),
    Card(
        id = 10,
        cardName = "Pot of Greed",
        cardType = "Spell",
        cardImageRes = R.drawable.pot_of_greed,
        cardDescription = "Draw 2 cards from your deck.",
        cardStars = 0,
        isFavorite = true
    ),
    Card(
        id = 11,
        cardName = "Raigeki",
        cardType = "Spell",
        cardImageRes = R.drawable.raigeki,
        cardDescription = "Destroy all monsters your opponent controls.",
        cardStars = 0,
        isFavorite = false
    ),
    Card(
        id = 12,
        cardName = "Slifer the Sky Dragon",
        cardType = "Monster",
        cardImageRes = R.drawable.slifer_the_sky_dragon,
        cardDescription = "A divine beast with unknown power.",
        cardStars = 10,
        isFavorite = false
    ),
    Card(
        id = 13,
        cardName = "Obelisk the Tormentor",
        cardType = "Monster",
        cardImageRes = R.drawable.obelisk_the_tormentor,
        cardDescription = "An Egyptian God card with immense strength.",
        cardStars = 10,
        isFavorite = true
    ),
    Card(
        id = 14,
        cardName = "The Winged Dragon of Ra",
        cardType = "Monster",
        cardImageRes = R.drawable.winged_dragon_of_ra,
        cardDescription = "A divine beast that requires a high tribute.",
        cardStars = 10,
        isFavorite = true
    ),
    Card(
        id = 15,
        cardName = "Time Wizard",
        cardType = "Monster",
        cardImageRes = R.drawable.time_wizard,
        cardDescription = "Can manipulate time to destroy all your opponent's monsters.",
        cardStars = 2,
        isFavorite = false
    )
)
