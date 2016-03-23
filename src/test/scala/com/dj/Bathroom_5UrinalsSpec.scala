package com.dj

import org.scalatest._

class Bathroom_5UrinalsSpec extends FlatSpec with Matchers {
  "Bathroom" should "return position 1 when 5 urinals exist and 2, 3, 5 are occupied" in {
    val bathroom: Bathroom = new Bathroom(5)
    bathroom.getUrinalByPosition(2).get.status = Occupied
    bathroom.getUrinalByPosition(3).get.status = Occupied
    bathroom.getUrinalByPosition(5).get.status = Occupied

    5 should ===(bathroom.numberOfUrinals)

    bathroom.getUrinalByPosition(1).get should ===(bathroom.nextAvailable.get)
  }

  "Bathroom" should "return position 1 when 5 urinals exist and 2, 4, 5 are occupied" in {
    val bathroom: Bathroom = new Bathroom(5)
    bathroom.getUrinalByPosition(2).get.status = Occupied
    bathroom.getUrinalByPosition(4).get.status = Occupied
    bathroom.getUrinalByPosition(5).get.status = Occupied

    5 should ===(bathroom.numberOfUrinals)

    bathroom.getUrinalByPosition(1).get should ===(bathroom.nextAvailable.get)
  }
}
