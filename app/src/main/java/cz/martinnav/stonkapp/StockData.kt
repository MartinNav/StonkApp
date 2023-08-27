package cz.martinnav.stonkapp

class StockData (val currency:String,
    val symbol:String,
    val quote:Quote)//May not be 100% accurate must modify to comply with reality
data class Quote(
    val high: List<Double>,
    val close: List<Double>,
    val open: List<Double>,
    val low: List<Double>,
)