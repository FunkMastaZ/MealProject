////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Abstract Class Instruction
//  TODO
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

data class Instruction(val stepNumber: Int,
                       val instructionText: String,
                       val taskType: String? = null,
                       val taskProperty: Int? = null) : InstructionItemInterface  // This class extends this interface
{
    override fun task() {
        if (taskType == null) return
        return // Do nothing for now TODO fix this later
    }

    override val prop: Int? = null
//        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}