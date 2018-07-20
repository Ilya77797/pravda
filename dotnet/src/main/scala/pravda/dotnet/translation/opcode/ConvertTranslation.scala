package pravda.dotnet.translation.opcode
import pravda.dotnet.data.TablesData._
import pravda.dotnet.parsers.CIL
import pravda.dotnet.parsers.CIL._
import pravda.dotnet.translation.data.{MethodTranslationCtx, TranslationError, UnknownOpcode}
import pravda.vm.Data
import pravda.vm.asm.Operation

object ConvertTranslation extends OneToManyTranslatorOnlyAsm {
  override def asmOpsOne(op: CIL.Op,
                         stackOffsetO: Option[Int],
                         ctx: MethodTranslationCtx): Either[TranslationError, List[Operation]] = op match {

    case Call(MemberRefData(TypeRefData(_, "Convert", "System"), "ToBoolean", _)) => Right(cast(Data.Type.Boolean))
    case Call(MemberRefData(TypeRefData(_, "Convert", "System"), "ToChar", _)) => Right(cast(Data.Type.Int16))
    case Call(MemberRefData(TypeRefData(_, "Convert", "System"), "ToDouble", _)) => Right(cast(Data.Type.Number))
    case Call(MemberRefData(TypeRefData(_, "Convert", "System"), "ToInt16", _)) => Right(cast(Data.Type.Int16))
    case Call(MemberRefData(TypeRefData(_, "Convert", "System"), "ToInt32", _)) => Right(cast(Data.Type.Int32))
    case Call(MemberRefData(TypeRefData(_, "Convert", "System"), "ToString", _)) => Right(cast(Data.Type.Utf8))
    case Call(MemberRefData(TypeRefData(_, "Convert", "System"), "ToByte", _))   => Right(cast(Data.Type.Int8))
    case _                                                                       => Left(UnknownOpcode)
  }
}