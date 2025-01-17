package ii_collections

fun example1(list: List<Int>) {

    // If a lambda has exactly one parameter, that parameter can be accessed as 'it'
    val positiveNumbers = list.filter { it > 0 }

    val squares = list.map { it * it }
}

fun Shop.getCitiesCustomersAreFrom(): Set<City> {
//    val cities: HashSet<City> = HashSet()
//    customers.forEach{cities.add(it.city)}
    return customers.map{it.city}.toSet()
}

fun Shop.getCustomersFrom(city: City): List<Customer> {
    return customers.filter { it.city == city }
}


