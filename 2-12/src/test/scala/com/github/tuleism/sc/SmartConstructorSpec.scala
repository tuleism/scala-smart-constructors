package com.github.tuleism.sc

import org.scalatest.OptionValues
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpecLike

class SmartConstructorSpec extends AnyWordSpecLike with Matchers with OptionValues {
  "Smart Constructor 2.12" should {
    "TraitExample" in {
      import TraitExample.Email
      val email = Email.fromString(exampleEmail).value // apply now return Option
      email.value mustBe exampleEmail // access ok

      // allowed
      assertCompiles(
        """
          |Email.fromString(exampleEmail)
          |""".stripMargin
      )

      // public constructor
      assertDoesNotCompile(
        """
          |new Email(exampleEmail)
          |""".stripMargin
      )
      // extends trait
      assertDoesNotCompile(
        """
          |new Email {
          |  override def value: String = exampleEmail
          |}
          |""".stripMargin
      )
      // companion's apply()
      assertDoesNotCompile(
        """
          |Email(exampleEmail)
          |""".stripMargin
      )
      // case class's copy()
      assertDoesNotCompile(
        """
          |email.copy(value = exampleEmail)
          |""".stripMargin
      )
      // case class's unapply()
      assertDoesNotCompile(
        """
          |email match { case Email(value) => value }
          |""".stripMargin
      )
    }

    "CaseClassHideApplyExample" in {
      import CaseClassHideApplyExample.Email
      val email = Email.fromString(exampleEmail).value // apply now return Option
      email.value mustBe exampleEmail // access ok

      // allowed
      assertCompiles(
        """
          |Email.fromString(exampleEmail)
          |""".stripMargin
      )

      // case class's unapply()
      assertCompiles(
        """
          |email match { case Email(value) => value }
          |""".stripMargin
      )

      // public constructor
      assertDoesNotCompile(
        """
          |new Email(exampleEmail)
          |""".stripMargin
      )
      // extends trait
      assertDoesNotCompile(
        """
          |new Email {
          |  override def value: String = exampleEmail
          |}
          |""".stripMargin
      )
      // companion's apply()
      assertDoesNotCompile(
        """
          |Email(exampleEmail)
          |""".stripMargin
      )
      // case class's copy()
      assertDoesNotCompile(
        """
          |email.copy(value = exampleEmail)
          |""".stripMargin
      )
    }

    "CaseClassOverrideApplyExample" in {
      import CaseClassOverrideApplyExample.Email
      val email = Email(exampleEmail).value // apply now return Option
      email.value mustBe exampleEmail // access ok

      // case class's unapply()
      assertCompiles(
        """
          |email match { case Email(value) => value }
          |""".stripMargin
      )
      // companion's apply()
      assertCompiles(
        """
          |Email(exampleEmail)
          |""".stripMargin
      )
      // public constructor
      assertDoesNotCompile(
        """
          |new Email(exampleEmail)
          |""".stripMargin
      )
      // extends trait
      assertDoesNotCompile(
        """
          |new Email {
          |  override def value: String = exampleEmail
          |}
          |""".stripMargin
      )
      // case class's copy()
      assertDoesNotCompile(
        """
          |email.copy(value = exampleEmail)
          |""".stripMargin
      )
    }
  }
}
