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

import java.io.FileNotFoundException

import org.scalatest.FunSuite

import scala.io.Source

/**
  * Created by koke on 10-Sep-16.
  */
class Converter$Test extends FunSuite {

  test("all features with values") {

    val  source="src/test/resources/test01.csv"
    val  dst="src/test/resources/result01.csv"

    val args: Array[String]=Array(source,dst)

    Converter.main(args);


    val line=  Source.fromFile(dst).getLines().take(1).mkString
    assert("1 1:23 2:-0.5 3:7" == line)
  }


  test("file contains NAs") {

    val  source="src/test/resources/testNA.csv"
    val  dst="src/test/resources/resultNA.csv"

    val args: Array[String]=Array(source,dst)

    Converter.main(args);

    val line=  Source.fromFile(dst).getLines().take(1).mkString
    assert("0 2:1.2" == line)
  }


  test("file contains zeros") {

    val  source="src/test/resources/test0s.csv"
    val  dst="src/test/resources/result0s.csv"

    val args: Array[String]=Array(source,dst)

    Converter.main(args);

    val line=  Source.fromFile(dst).getLines().take(1).mkString
    assert("1 2:-0.5 3:4" == line)
  }

  test("file contains empty values") {

    val  source="src/test/resources/testEmpty.csv"
    val  dst="src/test/resources/resultEmpty.csv"

    val args: Array[String]=Array(source,dst)

    Converter.main(args);

    val line=  Source.fromFile(dst).getLines().take(1).mkString
    assert("1 1:23 3:7 6:5" == line)
  }


 }
