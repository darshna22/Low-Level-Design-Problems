package LLD_Vending_Machine.product

class ProductInventory {
    private val products = mutableMapOf<String, Product>()

    fun addProduct(product: Product) {
        products[product.code] = product
    }

    fun getProduct(code: String): Product? {
        return products[code]
    }


    //update product quantity
    fun updateProductQuantity(code: String) {
        products[code]?.let {
            if (it.quantity > 0) it.quantity--
        }
    }

    //show all products
    fun showAllProducts() {
        products.forEach { (code, product) ->

            println("Product Code: $code,Product Name:${product.name},Product Price: ${product.price},Product Quantity: ${product.quantity}")

        }
    }

    fun getOutOfStockProducts(): List<Product> =
        products.filter { it.value.quantity == 0 }.values.toList()
}