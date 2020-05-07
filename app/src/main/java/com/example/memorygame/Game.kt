package com.example.focused

class Game {

    val cards: MutableList<Card> = mutableListOf()
    private var indexOfOneAndOnlyFaceUpCard: Int? = null

    init {
        val numbers = mutableListOf(0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5)
        numbers.shuffle()
        numbers.forEach {
            val card = Card(it)
            cards.add(card)
        }
    }

    fun onClicked(index: Int) {
        if (!cards[index].isMatched) {
            if (indexOfOneAndOnlyFaceUpCard != null && indexOfOneAndOnlyFaceUpCard != index) {
                val firstCard = cards[indexOfOneAndOnlyFaceUpCard!!]
                val secondCard = cards[index]
                if (firstCard.id == secondCard.id) {
                    firstCard.isMatched = true
                    secondCard.isMatched = true
                }
                cards[index].isFlipedUp = true
                indexOfOneAndOnlyFaceUpCard = null
            } else {
                for (i in 0..11) {
                    cards[i].isFlipedUp = (i == index)
                }
                indexOfOneAndOnlyFaceUpCard = index
            }
        }
    }

    fun isTheEnd(): Boolean {
        return (cards.filter { !it.isMatched }.count() == 0)
    }
}