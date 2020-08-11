package com.github.tuleism.sc

object CaseClassOverrideApplyExample {

  final case class Email private (value: String)

  object Email {
    def apply(v: String): Option[Email] =
      NaiveEmailRegex.findFirstIn(v).filter(_ == v).map(_ => new Email(v))
  }
}
