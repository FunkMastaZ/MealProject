fun main(args: Array<String>) {
    var rawToast = """Toast Recipe
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

//    var toastIngredientList = rawRecipeIntoIngredientList(rawToast)
//    var pbjIngredientList = rawRecipeIntoIngredientList(rawPBJ)
//    println("This is the toastIngredientList:\n\t" + toastIngredientList)
//    println("This is the pbjIngredientList:\n\t" + pbjIngredientList)
//
//    var toastInstructionList = rawRecipeIntoInstructionList(rawToast)
//    var pbjInstructionList = rawRecipeIntoInstructionList(rawPBJ)
//    println("This is the toastInstructionList:\n\t" + toastInstructionList)
//    println("This is the pbjInstructionList:\n\t" + pbjInstructionList)
    println(recipeBuilder(rawToast))
    println(recipeBuilder(rawPBJ))
}


