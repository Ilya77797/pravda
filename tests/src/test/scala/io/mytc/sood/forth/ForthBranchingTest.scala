package io.mytc.sood.forth

import org.scalatest._
import ForthTestUtils._

class ForthBranchingTest extends FlatSpec with Matchers {
  "if" must "execute block if true is on top of the stack" in {
    assert(
      runTransaction[Int]("""
      0 0
      eq
      if 5 then
    """) == Right(
        List(5)
      ))
  }

  "if" must "not execute block if false is on top of the stack" in {
    assert(
      runTransaction[Int]("""
      0 1
      eq
      if 5 then
    """) == Right(
        List()
      ))
  }
}
