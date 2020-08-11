package com.github.tuleism

import scala.util.matching.Regex

package object sc {
  val NaiveEmailRegex: Regex = """^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$""".r
  val exampleEmail: String   = "test@gmail.com"
}
