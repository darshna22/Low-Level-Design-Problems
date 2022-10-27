package deck_of_cards.model

import deck_of_cards.model.Card
import deck_of_cards.model.CardType
import deck_of_cards.model.DeckOfCard

class TwoDeckOfCardsImpl : DeckOfCard {
    private val list = ArrayList<Card>()
    
    override fun deckOfCard(): List<Card> {
        //for spade cards

        for (cardType in CardType.values()){
            for (i in 1..13) {
                list.add(Card(i, cardType))
            }
        }

        for (cardType in CardType.values()){
            for (i in 1..13) {
                list.add(Card(i, cardType))
            }
        }
        return list
    }
}