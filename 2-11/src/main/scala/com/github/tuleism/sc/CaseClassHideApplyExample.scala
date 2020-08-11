package com.github.tuleism.sc

object CaseClassHideApplyExample {
  final case class Email private (value: String) {
    private def copy(): Unit = ()
  }

  object Email {
    private def apply(value: String): Email = ???

    def fromString(v: String): Option[Email] =
      NaiveEmailRegex.findFirstIn(v).filter(_ == v).map(_ => new Email(v))
  }
}
