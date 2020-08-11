package com.github.tuleism.sc

object TraitExample {
  sealed trait Email {
    def value: String
  }

  object Email {
    def fromString(v: String): Option[Email] =
      NaiveEmailRegex
        .findFirstIn(v)
        .filter(_ == v)
        .map { _ =>
          new Email {
            override def value: String = v
          }
        }
  }
}
