package com.github.tuleism.sc

object CaseClassHideApplyExample {

  final case class Email private (value: String)

  object Email {
    def fromString(v: String): Option[Email] =
      NaiveEmailRegex.findFirstIn(v).filter(_ == v).map(_ => new Email(v))
  }
}
