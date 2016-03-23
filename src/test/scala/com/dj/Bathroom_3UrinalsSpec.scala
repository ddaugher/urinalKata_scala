package com.dj

import org.scalatest._

class Bathroom_3UrinalsSpec extends FlatSpec with Matchers {
  "Bathroom" should "return position 3 when 3 urinals exist and all urinals are available" in {
    val bathroom: Bathroom = new Bathroom(3)

    3 should ===(bathroom.numberOfUrinals)

    bathroom.getUrinalByPosition(3).get should ===(bathroom.nextAvailable.get)
  }

  "Bathroom" should "return position 1 when 3 urinals exist and position 3 is occupied" in {
    val bathroom: Bathroom = new Bathroom(3)
    bathroom.getUrinalByPosition(3).get.status = Occupied

    3 should ===(bathroom.numberOfUrinals)

    bathroom.getUrinalByPosition(1).get should ===(bathroom.nextAvailable.get)
  }

  "Bathroom" should "return position 3 when 3 urinals exist and position 2 is occupied" in {
    val bathroom: Bathroom = new Bathroom(3)
    bathroom.getUrinalByPosition(2).get.status = Occupied

    3 should ===(bathroom.numberOfUrinals)

    bathroom.getUrinalByPosition(3).get should ===(bathroom.nextAvailable.get)
  }

  "Bathroom" should "return position 3 when 3 urinals exist and position 1 is occupied" in {
    val bathroom: Bathroom = new Bathroom(3)
    bathroom.getUrinalByPosition(1).get.status = Occupied

    3 should ===(bathroom.numberOfUrinals)

    bathroom.getUrinalByPosition(3).get should ===(bathroom.nextAvailable.get)
  }

  "Bathroom" should "return position 1 when 3 urinals exist and position 2 and 3 occupied" in {
    val bathroom: Bathroom = new Bathroom(3)
    bathroom.getUrinalByPosition(2).get.status = Occupied
    bathroom.getUrinalByPosition(3).get.status = Occupied

    3 should ===(bathroom.numberOfUrinals)

    bathroom.getUrinalByPosition(1).get should ===(bathroom.nextAvailable.get)
  }

  "Bathroom" should "return position 3 when 3 urinals exist and position 1 and 2 occupied" in {
    val bathroom: Bathroom = new Bathroom(3)
    bathroom.getUrinalByPosition(1).get.status = Occupied
    bathroom.getUrinalByPosition(2).get.status = Occupied

    3 should ===(bathroom.numberOfUrinals)

    bathroom.getUrinalByPosition(3).get should ===(bathroom.nextAvailable.get)
  }

  "Bathroom" should "return None when 3 urinals exist and all are occupied" in {
    val bathroom: Bathroom = new Bathroom(3)
    bathroom.getUrinalByPosition(1).get.status = Occupied
    bathroom.getUrinalByPosition(2).get.status = Occupied
    bathroom.getUrinalByPosition(3).get.status = Occupied

    3 should ===(bathroom.numberOfUrinals)

    None should ===(bathroom.nextAvailable)
  }
}
