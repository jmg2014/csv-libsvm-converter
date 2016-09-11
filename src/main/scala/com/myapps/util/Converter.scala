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

import java.io.{File, FileNotFoundException, IOException, PrintWriter}

import scala.io.Source

object Converter {

  def main(args: Array[String]): Unit = {

    val t0 = System.nanoTime()

    try {

      val filename: String = args(0)
      val writer = new PrintWriter(new File(args(1)))

      for (line <- Source.fromFile(filename).getLines().drop(1)) {


        val last: String = line.split(",").last

        //Writing the label
        writer.write(last)

        //Writing all but last one
        line.split(",").init.drop(1).zipWithIndex.foreach {
          case ("0", count) =>
          case ("NA", count) =>
          case ("", count) =>
          case elem@(_, count) => writer.write(" " + (count + 1) + ":" + elem._1)

        }

        writer.write("\n")
      }

      writer.close()

    } catch {
      case ex: FileNotFoundException => println("Couldn't find that file.")
      case ex: IOException => println("Had an IOException trying to read that file.")
      case ex: ArrayIndexOutOfBoundsException => println("Not enough arguments.")
    }

    val t1 = System.nanoTime()
    val seconds = (t1 - t0) / 1000000000.0
    println("Elapsed time: " + seconds + "s")

  }

}
