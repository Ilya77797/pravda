package pravda.vm.asm

import pravda.vm.Data.Primitive
import pravda.vm.{Data, Opcodes}

sealed trait Operation {
  def mnemonic: String
}

object Operation {

  import Opcodes._

  sealed abstract class ParametrizedOperation(val mnemonic: String) extends Operation

  // Virtual operations (which aren't included to bytecode)
  case object Nop                         extends ParametrizedOperation("")
  final case class Comment(value: String) extends ParametrizedOperation("")
  final case class Label(name: String)    extends ParametrizedOperation("")

  // TODO add meta to parser
  final case class Meta(meta: pravda.vm.Meta)        extends ParametrizedOperation("")
  final case class Push(d: Data)                     extends ParametrizedOperation("push")
  final case class New(d: Data)                      extends ParametrizedOperation("new")
  final case class Jump(name: Option[String])        extends ParametrizedOperation("jump")
  final case class JumpI(name: Option[String])       extends ParametrizedOperation("jumpi")
  final case class Call(name: Option[String])        extends ParametrizedOperation("call")
  final case class StructMut(key: Option[Primitive]) extends ParametrizedOperation("struct_mut")
  final case class StructGet(key: Option[Primitive]) extends ParametrizedOperation("struct_get")

  final case class Orphan(opcode: Int, mnemonic: String) extends Operation

//  final val STRUCT_GET_STATIC = 0x23
//  final val STRUCT_MUT_STATIC = 0x26

  val Orphans: Seq[Operation.Orphan] = Seq(
    Orphan(STOP, "stop"),
    Orphan(RET, "ret"),
    Orphan(PCALL, "pcall"),
    Orphan(LCALL, "lcall"),
    Orphan(POP, "pop"),
    Orphan(DUPN, "dupn"),
    Orphan(DUP, "dup"),
    Orphan(SWAPN, "swapn"),
    Orphan(SWAP, "swap"),
    Orphan(ARRAY_GET, "array_get"),
    Orphan(ARRAY_MUT, "array_mut"),
    Orphan(PRIMITIVE_PUT, "primitive_put"),
    Orphan(PRIMITIVE_GET, "primitive_get"),
    Orphan(SPUT, "sput"),
    Orphan(SGET, "sget"),
    Orphan(SDROP, "sdrop"),
    Orphan(SEXIST, "sexist"),
    Orphan(ADD, "add"),
    Orphan(MUL, "mul"),
    Orphan(DIV, "div"),
    Orphan(MOD, "mod"),
    Orphan(LT, "lt"),
    Orphan(GT, "gt"),
    Orphan(NOT, "not"),
    Orphan(AND, "and"),
    Orphan(OR, "or"),
    Orphan(XOR, "xor"),
    Orphan(EQ, "eq"),
    Orphan(CAST, "cast"),
    Orphan(CONCAT, "concat"),
    Orphan(SLICE, "slice"),
    Orphan(FROM, "from"),
    Orphan(PADDR, "paddr"),
    Orphan(PCREATE, "pcreate"),
    Orphan(PUPDATE, "pupdate"),
    Orphan(TRANSFER, "transfer"),
    Orphan(PTRANSFER, "ptransfer")
  )

  /**
    * Orphan operation by opcode.
    * @see [[pravda.vm.Opcodes]]
    */
  val operationByCode: Map[Int, Operation] = Orphans
    .map(o => o.opcode -> o)
    .toMap

  /**
    * Alias to [[operationByCode]]
    */
  def apply(opcode: Int): Operation =
    operationByCode(opcode)
}
