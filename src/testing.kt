fun main(args: Array<String>) {

    var rawRecipe = """Toast Recipe
Make delicious toast!
Ingredients
* 1 Slice of bread
Instructions
1. Set the toaster to your desired toast level.
2. Insert bread into toaster.
3. Start toaster.
4. Wait for toaster.
5. Remove toast from toaster
6. Plate and serve."""

    var rawPBJ = """Peanut Butter & Jelly Recipe
Make delicious PB&J
Ingredients
* 2 Slices of bread
* 1 tsbp of Jelly
* 1 tbsp of Peanut Butter
* A plate
Instructions
1. Spread peanut butter on a side of a slice of bread.
2. Spread jelly onto one side of the other slice..
3. Press the slices together, sides with spreads facing inwards
4. Plate and serve."""

    var ingredientQuantity: Float? = null
    var ingredientName: String
    var ingredentyQuanityType: String? = null
    var listOfIngredientItems: MutableList<Ingredient> = mutableListOf()
    var ingredientsTokens: MutableList<List<String>> = mutableListOf()

    var ingredientsAsStrings = rawRecipeAsStrings(rawPBJ) // List of strings

    // Splits all of the ingredient strings into a List of one-word Lists
    // Then creates IngredientListItems from the information
    // For each string that represents an ingredient...
    ingredientsAsStrings.forEach { entry ->
        // Initialize an array to hold each word in each @var entry
        var entryWords: List<String> = emptyList()
        // Split the string on the space character to get one word per entry in List
        entryWords = entry.split(" ")
        // Drop the * character from the list (it's in the first position)
        entryWords = entryWords.drop(1)

        // If the first token, or word, in the sentence is a Float...
        if (entryWords[0].toFloatOrNull() != null) {
            //... then save this as @var ingredientQuantity
            ingredientQuantity = entryWords[0].toFloat()
            //Also, we should get the next word and have it as the quantity type
            ingredentyQuanityType = entryWords[1]
            // Now if we drop these entries from our token list...
            var newList = entryWords.drop(2)
            // Then we can save the rest as the name of the name of the food
            // TODO make a function to remove words like "the" "of" and stuff
            ingredientName = newList.joinToString(" ")
//            println("Ingreditent name:\t" + ingredientName)
        }
        // Else there was no integer quantity given so list the ingredient as name
        else {
            ingredientName = entryWords.joinToString(" ")
            ingredientQuantity = null
            ingredentyQuanityType = null
        }
        // Create the ingredient item entry for each list of tokens
        listOfIngredientItems.add(Ingredient(ingredientName, ingredientQuantity, ingredentyQuanityType))


        // Lastly, add this list of words to our list that holds other lists containing words
//        ingredientsTokens.add(entryWords)
    }
}

//    // If the ingredientTokens list is empty, return to avoid errors
//    if (ingredientsTokens == mutableListOf<List<String>>()) {
//        println("Type match found, ending function")
//        return
//    }

    // For each list of tokens...
//    ingredientsTokens.forEach { tokenList ->
//         If the first token, or word, in the sentence is a Float...
//        if (tokenList[0].toFloatOrNull() != null) {
//            //... then save this as @var ingredientQuantity
//            ingredientQuantity = tokenList[0].toFloat()
//            //Also, we should get the next word and have it as the quantity type
//            ingredentyQuanityType = tokenList[1]
//            // Now if we drop these entries from our token list...
//            var newList = tokenList.drop(2)
//            // Then we can save the rest as the name of the name of the food
//            // TODO make a function to remove words like "the" "of" and stuff
//            ingredientName = newList.joinToString(" ")
////            println("Ingreditent name:\t" + ingredientName)
//        }
//        // Else there was no integer quantity given so list the ingredient as name
//        else {
//            ingredientName = tokenList.joinToString(" ")
//            ingredientQuantity = null
//            ingredentyQuanityType = null
//        }
//        // Create the ingredient item entry for each list of tokens
//        listOfIngredientItems.add(Ingredient(ingredientName, ingredientQuantity, ingredentyQuanityType))
//    } // End for each list of tokens


//    var toastIngredientList = IngredientList(listOfIngredientItems)

//    println("Toast Ingredient List, the actual IngredientList Object:\n\t" + toastIngredientList)

