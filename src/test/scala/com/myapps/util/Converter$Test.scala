/*
 * Copyright 2016 Jorge Manrique
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.myapps.util

import org.scalatest.FunSuite

import scala.io.Source
import scala.util.{Failure, Success, Try}

class Converter$Test extends FunSuite {

  test("all features with values") {

    val line: String = callMain("src/test/resources/test01.csv","src/test/resources/result01.csv") match  {
      case Success(value) => value
      case Failure(t) => ""
    }
    assert("1 1:23 2:-0.5 3:7" == line)
  }


  test("file contains NAs") {

    val line: String = callMain("src/test/resources/testNA.csv","src/test/resources/resultNA.csv") match  {
      case Success(value) => value
      case Failure(t) => ""
    }
    assert("0 2:1.2" == line)
  }


  test("file contains zeros") {

    val line: String = callMain("src/test/resources/test0s.csv","src/test/resources/result0s.csv") match  {
      case Success(value) => value
      case Failure(t) => ""
    }
    assert("1 2:-0.5 3:4" == line)
  }

  test("file contains empty values") {

    val line: String = callMain("src/test/resources/testEmpty.csv","src/test/resources/resultEmpty.csv") match  {
      case Success(value) => value
      case Failure(t) => ""
    }
    assert("1 1:23 3:7 6:5" == line)
  }

  test("no file URL") {

    val line: String = callMain("","") match  {
      case Success(value) => value
      case Failure(t) => ""
    }
    assert(line=="")
  }


  def callMain(origin:String,destination:String):Try[String] = {

    Try({

      val args: Array[String] = Array(origin, destination)

      Converter.main(args)

      val line = Source.fromFile(destination).getLines().take(1).mkString
      line
    })
  }
}
