package pravda.cli.languages

package impl

import com.google.protobuf.ByteString
import pravda.forth.{Compiler => ForthCompiler}
import pravda.vm.asm.Assembler
import pravda.dotnet.{Translator => DotnetTranslator, FileParser => DotnetParser}

import scala.concurrent.{ExecutionContext, Future}

final class CompilersLanguageImpl(implicit executionContext: ExecutionContext) extends CompilersLanguage[Future] {

  def asm(source: String): Future[Either[String, ByteString]] = Future {
    Assembler()
      .compile(source)
      .map(a => ByteString.copyFrom(a))
  }

  def disasm(source: ByteString): Future[String] = Future {
    Assembler()
      .decompile(source)
      .map { case (no, op) => "%06X:\t%s".format(no, op.toAsm) }
      .mkString("\n")
  }

  def forth(source: String): Future[Either[String, ByteString]] = Future {
    ForthCompiler().compileToByteString(source)
  }

  def dotnet(source: ByteString): Future[Either[String, ByteString]] = Future {
    DotnetParser.parsePe(source.toByteArray).map {
      case (_, cilData, methods, signatures) =>
        val ops = DotnetTranslator.translate(methods, cilData, signatures)
        ByteString.copyFrom(Assembler().compile(ops))
    }
  }
}