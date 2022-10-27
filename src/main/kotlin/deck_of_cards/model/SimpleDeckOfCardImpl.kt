package deck_of_cards.model

class SimpleDeckOfCardImpl : DeckOfCard {
    private val list = ArrayList<Card>()


    override fun deckOfCard(): List<Card> {
        //for spade cards

        for (cardType in CardType.values()){
            for (i in 1..13) {
                list.add(Card(i, cardType))
            }
        }

        return list
    }
}