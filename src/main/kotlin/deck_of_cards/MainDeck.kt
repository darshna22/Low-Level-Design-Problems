package deck_of_cards

import deck_of_cards.model.SimpleDeckOfCardImpl
import deck_of_cards.model.Card
import kotlin.jvm.JvmStatic

object MainDeck {
    @JvmStatic
    fun main(args: Array<String>) {
         val  deckOfCardList:List<Card> = SimpleDeckOfCardImpl().deckOfCard()
          println("value of deck of card="+deckOfCardList.size)
    }
}