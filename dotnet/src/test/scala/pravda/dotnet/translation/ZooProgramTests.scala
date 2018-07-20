package pravda.dotnet
package translation

import pravda.vm.asm.PravdaAssembler
import utest._

object ZooProgramTests extends TestSuite {

  val tests = Tests {
    'zooProgramTranslation - {
      val Right((_, cilData, methods, signatures)) = parseFile("zoo_program.exe")

      assertWithAsmDiff(
        Translator.translateAsm(methods, cilData, signatures).right.get,
        PravdaAssembler
          .parse("""
            |push null
            |sexist
            |jumpi @methods
            |call @ctor
            |@methods:
            |meta method { int8(-1): "NewZoo", int8(-2): int8(3) }
            |meta method { int8(-1): "TransferZoo", int8(-2): int8(0), int8(0): int8(14), int8(2): int8(3) }
            |meta method { int8(-1): "NewPet", int8(-2): int8(11), int8(0): int8(3) }
            |meta method { int8(4): int8(11), int8(-1): "TransferPet", int8(-2): int8(0), int8(0): int8(14), int8(2): int8(3) }
            |meta method { int8(-1): "BreedPets", int8(-2): int8(11), int8(0): int8(11), int8(2): int8(11) }
            |dup
            |push "NewZoo"
            |eq
            |jumpi @method_NewZoo
            |dup
            |push "TransferZoo"
            |eq
            |jumpi @method_TransferZoo
            |dup
            |push "NewPet"
            |eq
            |jumpi @method_NewPet
            |dup
            |push "TransferPet"
            |eq
            |jumpi @method_TransferPet
            |dup
            |push "BreedPets"
            |eq
            |jumpi @method_BreedPets
            |jump @stop
            |@method_NewZoo:
            |push int32(0)
            |push x5A6F6F546F4F776E6572
            |push "ZooCnt"
            |sget
            |from
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |swap
            |sput
            |pop
            |pop
            |push "ZooCnt"
            |sget
            |push int32(1)
            |add
            |push "ZooCnt"
            |sput
            |push "ZooCnt"
            |sget
            |push int32(1)
            |push int32(-1)
            |mul
            |add
            |push int32(2)
            |swapn
            |pop
            |push int32(1)
            |dupn
            |swap
            |pop
            |swap
            |pop
            |jump @stop
            |@method_TransferZoo:
            |push int32(0)
            |push x5A6F6F546F4F776E6572
            |push int32(4)
            |dupn
            |push x
            |call @storage_get_default
            |from
            |eq
            |push int8(1)
            |cast
            |push int32(2)
            |swapn
            |pop
            |push int32(1)
            |dupn
            |push int8(9)
            |cast
            |not
            |push int8(1)
            |cast
            |push int32(1)
            |eq
            |jumpi @br45
            |push x5A6F6F546F4F776E6572
            |push int32(4)
            |dupn
            |push int32(6)
            |dupn
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |swap
            |sput
            |pop
            |pop
            |@br45:
            |pop
            |pop
            |pop
            |pop
            |jump @stop
            |@method_NewPet:
            |push int32(0)
            |push int32(0)
            |push int32(0)
            |push x5A6F6F546F4F776E6572
            |push int32(6)
            |dupn
            |push x
            |call @storage_get_default
            |from
            |eq
            |push int8(1)
            |cast
            |push int32(4)
            |swapn
            |pop
            |push int32(3)
            |dupn
            |push int8(9)
            |cast
            |not
            |push int8(1)
            |cast
            |push int32(1)
            |eq
            |jumpi @br108
            |push "pet"
            |push "PetId"
            |sget
            |push int8(11)
            |cast
            |concat
            |push int32(3)
            |swapn
            |pop
            |push x506574546F4F776E6572
            |push int32(3)
            |dupn
            |from
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |swap
            |sput
            |pop
            |pop
            |push x5065745369676E6174757265
            |push int32(3)
            |dupn
            |push int32(4)
            |dupn
            |call @func_GenerateSignature
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |swap
            |sput
            |pop
            |pop
            |push "PetId"
            |sget
            |push int32(1)
            |add
            |push "PetId"
            |sput
            |push int32(2)
            |dupn
            |push int32(2)
            |swapn
            |pop
            |jump @br116
            |@br108:
            |push ""
            |push int32(2)
            |swapn
            |pop
            |@br116:
            |push int32(1)
            |dupn
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |jump @stop
            |@method_TransferPet:
            |push int32(0)
            |push x506574546F4F776E6572
            |push int32(4)
            |dupn
            |push x
            |call @storage_get_default
            |from
            |eq
            |push int8(1)
            |cast
            |push int8(9)
            |cast
            |not
            |push int8(1)
            |cast
            |push int32(1)
            |eq
            |jumpi @br47
            |push x5A6F6F546F4F776E6572
            |push int32(5)
            |dupn
            |push x
            |call @storage_get_default
            |push int32(6)
            |dupn
            |eq
            |push int8(1)
            |cast
            |jump @br48
            |push int32(0)
            |@br48:
            |push int32(2)
            |swapn
            |pop
            |push int32(1)
            |dupn
            |push int8(9)
            |cast
            |not
            |push int8(1)
            |cast
            |push int32(1)
            |eq
            |jumpi @br82
            |push x506574546F4F776E6572
            |push int32(4)
            |dupn
            |push int32(7)
            |dupn
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |swap
            |sput
            |pop
            |pop
            |push x506574546F5A6F6F
            |push int32(4)
            |dupn
            |push int32(6)
            |dupn
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |swap
            |sput
            |pop
            |pop
            |@br82:
            |pop
            |pop
            |pop
            |pop
            |pop
            |jump @stop
            |@method_BreedPets:
            |push int32(0)
            |push int32(0)
            |push int32(0)
            |push x506574546F4F776E6572
            |push int32(7)
            |dupn
            |push x
            |call @storage_get_default
            |from
            |eq
            |push int8(1)
            |cast
            |push int8(9)
            |cast
            |not
            |push int8(1)
            |cast
            |push int32(1)
            |eq
            |jumpi @br79
            |push x506574546F4F776E6572
            |push int32(6)
            |dupn
            |push x
            |call @storage_get_default
            |from
            |eq
            |push int8(1)
            |cast
            |push int8(9)
            |cast
            |not
            |push int8(1)
            |cast
            |push int32(1)
            |eq
            |jumpi @br79
            |push x506574546F5A6F6F
            |push int32(7)
            |dupn
            |push int32(-1)
            |call @storage_get_default
            |push x506574546F5A6F6F
            |push int32(7)
            |dupn
            |push int32(-1)
            |call @storage_get_default
            |eq
            |push int8(1)
            |cast
            |jump @br82
            |push int32(0)
            |@br82:
            |push int32(4)
            |swapn
            |pop
            |push int32(3)
            |dupn
            |push int8(9)
            |cast
            |not
            |push int8(1)
            |cast
            |push int32(1)
            |eq
            |jumpi @br159
            |push int32(6)
            |dupn
            |push int32(6)
            |dupn
            |concat
            |push int32(3)
            |swapn
            |pop
            |push x506574546F4F776E6572
            |push int32(3)
            |dupn
            |from
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |swap
            |sput
            |pop
            |pop
            |push x5065745369676E6174757265
            |push int32(3)
            |dupn
            |push x5065745369676E6174757265
            |push int32(9)
            |dupn
            |push int8(14)
            |cast
            |swap
            |concat
            |sget
            |push x5065745369676E6174757265
            |push int32(9)
            |dupn
            |push int8(14)
            |cast
            |swap
            |concat
            |sget
            |swap
            |concat
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |swap
            |sput
            |pop
            |pop
            |push int32(2)
            |dupn
            |push int32(2)
            |swapn
            |pop
            |jump @br168
            |@br159:
            |push ""
            |push int32(2)
            |swapn
            |pop
            |@br168:
            |push int32(1)
            |dupn
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |jump @stop
            |@ctor:
            |push null
            |dup
            |sput
            |push int32(1)
            |push "ZooCnt"
            |sput
            |push int32(1)
            |push "PetId"
            |sput
            |ret
            |@func_GenerateSignature:
            |push int32(0)
            |push int32(0)
            |push int32(0)
            |push int32(0)
            |push int32(10)
            |push int8(1)
            |new_array
            |push int32(5)
            |swapn
            |pop
            |push int32(0)
            |push int32(4)
            |swapn
            |pop
            |jump @br43
            |@br13:
            |push int32(4)
            |dupn
            |push int32(4)
            |dupn
            |push int32(8)
            |dupn
            |push int32(6)
            |dupn
            |push int32(10)
            |dupn
            |length
            |mod
            |array_get
            |push int32(2)
            |div
            |push int8(1)
            |cast
            |swap
            |array_mut
            |push int32(3)
            |dupn
            |push int32(1)
            |add
            |push int32(4)
            |swapn
            |pop
            |@br43:
            |push int32(3)
            |dupn
            |push int32(10)
            |lt
            |push int8(1)
            |cast
            |push int32(3)
            |swapn
            |pop
            |push int32(2)
            |dupn
            |push int32(1)
            |eq
            |jumpi @br13
            |push int32(4)
            |dupn
            |call @array_to_bytes
            |push int32(2)
            |swapn
            |pop
            |push int32(1)
            |dupn
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |swap
            |pop
            |ret
            |@storage_get_default:
            |push int32(2)
            |dupn
            |push int8(14)
            |cast
            |push int32(4)
            |dupn
            |concat
            |sexist
            |jumpi @get_default_if
            |swap
            |pop
            |swap
            |pop
            |ret
            |@get_default_if:
            |pop
            |push int8(14)
            |cast
            |swap
            |concat
            |sget
            |ret
            |@stop:
          """.stripMargin)
          .right
          .get
      )
    }
  }
}