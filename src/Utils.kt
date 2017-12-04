////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Utils.kt
//  Helper functions for the project.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// rawRecipeAsStrings()
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun rawRecipeAsStrings(recipeText: String): List<String> {
    return recipeText.split("\n")
}

fun cleanRecipeStringsForIngredients(recipeStrings: List<String>): List<String> {
    var ingredientsFound = false
    var strings = recipeStrings
    // For each line in @var splitText...
    strings.forEach { line ->
        // If the first character of the line is zero and the line signifying the beginning
        //  of the ingredients list was found, then do nothing, leaving the entry in the array
        if (line[0] == '*' && ingredientsFound) return@forEach
        // Else if the line matching "Ingredients" is the active line...
        else if (line == "Ingredients") {
            // Raise the flag to let us know we are in the ingredient's list
            ingredientsFound = true
            strings = strings.minus(line)
        }
        // Else the line is not matching "Ingredients" and does not begin with a star.
        // If the ingredients list has already been processed (i.e. ingredientsFound == true)...
        else if (ingredientsFound) {
            // ...then set the flag to false again to delete further entries...
            ingredientsFound = false
            // ...then delete this entry
            strings = strings.minus(line)
        }
        // Else the line does not begin with a star and we have not entered the list of ingredients..
        else {
            strings = strings.minus(line)
        }
    } // End forEach line
    return strings
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// ingredientStringsToIngredientList()
//
//  Splits all of the ingredient strings into a List of one-word Lists
//  Then creates IngredientListItems from the information
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun ingredientStringsToIngredientList(entryStrings: List<String>) : IngredientList {
    var ingredientName: String
    var ingredientQuantity: Float? = null
    var ingredentyQuanityType: String? = null
    var listOfIngredientItems: MutableList<Ingredient> = mutableListOf()

    //  For each string that represents an ingredient...
    entryStrings.forEach { entry ->
        // Initialize an array to hold each word in each @var entry
        var entryWords: List<String> = emptyList()
        // Split the string on the space character to get one word per entry in List
        entryWords = entry.split(" ")
        // Drop the * character from the list (it's in the first position)
        entryWords = entryWords.drop(1)

        // Gets the properties required to build IngredientObjects
        // If the first token, or word, in the sentence is a Float...
        if (entryWords[0].toFloatOrNull() != null) {
            //... then save this as @var ingredientQuantity
            ingredientQuantity = entryWords[0].toFloat()
            //Also, we should get the next word and have it as the quantity type
            ingredentyQuanityType = entryWords[1]
            // Now if we drop these entries from our token list...
            var newList = entryWords.drop(2)
            // ...then we can save the rest as the name of the name of the food
            // TODO make a function to remove words like "the" "of" and stuff
            ingredientName = newList.joinToString(" ")
        }
        // Else there was no integer quantity given so list the ingredient as name
        else {
            ingredientName = entryWords.joinToString(" ")
            ingredientQuantity = null
            ingredentyQuanityType = null
        }
        // Create the ingredient item entry for each list of tokens amd add this to our list of these objects
        listOfIngredientItems.add(Ingredient(ingredientName, ingredientQuantity, ingredentyQuanityType))
    } // End for each entry in @var ingredientsAsStringss
    // Return the an IngredientList object built from the list of ingredient items
    return IngredientList(listOfIngredientItems)
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// rawRecipeIntoIngredientList()
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun rawRecipeIntoIngredientList(rawRecipe: String) : IngredientList {
    return ingredientStringsToIngredientList(
            cleanRecipeStringsForIngredients(
                    rawRecipeAsStrings(rawRecipe)))
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// instructionsAsStringsToInstructionList()
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun instructionsAsStringsToInstructionList(entryStrings: List<String>): InstructionList {
    var thisStepNumber: Int
    var thisInstructionText: String = String()
    var thisTaskType: String? = null
    var thisTaskProperty: Int? = null
    val regExForStepNumber = Regex("""^\d+\.""")
    val regExForNumber = Regex("""\d+""")
    var listOfInstructions: MutableList<Instruction> = mutableListOf()

    //  For each string that represents an instruction...
    entryStrings.forEach { entry ->
        // Initialize an array to hold each word in each @var entry
        var entryWords: List<String> = emptyList()
        // Split the string on the space character to get one word per entry in List
        entryWords = entry.split(" ")

        // Gets the properties required to build InstructionList
        // If the first token, or word, in @var entry matches the form "[0-9]."
        if (entryWords[0].contains(regExForStepNumber)) {
            //...then save just the number as @var stepNumber
            thisStepNumber = entryWords[0].removeSuffix(".").toInt()
            // Now drop this entry from the list, the first entry
            entryWords = entryWords.drop(1)
            // ...then we can save the rest as the name of the text of the instruction
            thisInstructionText = entryWords.joinToString(" ")
            // ...then build the instruction object and add it to the list
            listOfInstructions.add(Instruction(stepNumber = thisStepNumber, instructionText = thisInstructionText))
        }
        // Else this was not an instruction
        else {
            entryWords.minus(entry)
        }
    } // End for each entry in @var ingredientsAsStringss
    // Return the an IngredientList object built from the list of ingredient items
    return InstructionList(listOfInstructions)
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// rawRecipeIntoInstructionList()
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun rawRecipeIntoInstructionList(rawRecipe: String): InstructionList {
    return  instructionsAsStringsToInstructionList(rawRecipeAsStrings(rawRecipe))
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// recipeBuilder()
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
fun recipeBuilder(thisRawRecipe: String) : Recipe {
    var recipeAsStrings = rawRecipeAsStrings(thisRawRecipe)
    return Recipe(name = rawRecipeAsStrings(thisRawRecipe)[0],
            description = rawRecipeAsStrings(thisRawRecipe)[1],
            ingredients = rawRecipeIntoIngredientList(rawRecipe = thisRawRecipe),
            instructions = rawRecipeIntoInstructionList(rawRecipe = thisRawRecipe)
    )
}
