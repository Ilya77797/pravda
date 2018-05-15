package io.mytc.sood.forth

import io.mytc.sood.vm.Vm
import java.nio.ByteBuffer

import com.google.protobuf.ByteString
import io.mytc.sood.vm.state._
import io.mytc.sood.vm.serialization._

import scala.collection.mutable

object ForthTestUtils {

  trait StackItem[T] {
    def get(item: Data): T
  }

  object StackItem {

    implicit val intStackItem: StackItem[Int] =
      (item: Data) => ByteBuffer.wrap(item.toByteArray).getInt

    implicit val floatStackItem: StackItem[Double] =
      (item: Data) => ByteBuffer.wrap(item.toByteArray).getDouble

    implicit val byteStringStackItem: StackItem[Data] =
      (item: Data) => item

    implicit val boolStackItem: StackItem[Boolean] =
      (item: ByteString) => if ((ByteBuffer.wrap(item.toByteArray).get & 0xFF) == 1) true else false
  }

  def runTransaction[T](code: String)(implicit stackItem: StackItem[T]): Either[String, List[T]] = {
    Compiler().compile(code, useStdLib = true) match {
      case Left(err) ⇒ Left(err)
      case Right(code) ⇒
        val emptyState = new Environment {
          override def getProgram(address: Address): Option[Program] = None
        }
        val stack = Vm.runRaw(ByteString.copyFrom(code), ByteString.EMPTY, emptyState).stack
        Right(stack.map(stackItem.get).toList)
    }
  }

  def runProgram[T](code: String)(implicit stackItem: StackItem[T]): Either[String, (List[T], Map[Address, T])] = {
    val programAddress = ByteString.copyFrom(Array[Byte](1, 2, 3))

    Compiler().compile(code, useStdLib = true) match {
      case Left(err) ⇒ Left(err)
      case Right(c) ⇒
        val programStorageMap = mutable.Map[Address, Data](int32ToAddress(10) -> int32ToData(0))
        val programStorage = new Storage {
          override def get(key: Address): Option[Data] = programStorageMap.get(key)
          override def put(key: Address, value: Data): Unit = programStorageMap.put(key, value)
          override def delete(key: Address): Unit = programStorageMap.remove(key)
        }

        val stateWithAccount = new Environment {
          override def getProgram(address: Address): Option[Program] =
            if (address == programAddress) {
              Some(new Program {
                override def storage: Storage = programStorage
                override def code: ByteBuffer = ByteBuffer.wrap(c)
              })
            } else {
              None
            }
        }
        val stack = Vm.runProgram(programAddress, Memory.empty, ByteString.EMPTY, stateWithAccount).stack
        Right(
          (
            stack.map(stackItem.get).toList,
            programStorageMap.mapValues(stackItem.get).toMap
          ))
    }
  }
}
