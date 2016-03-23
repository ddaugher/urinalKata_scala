package com.dj

import org.scalatest._

class UrinalSpec extends FlatSpec with Matchers {
  "Urinal" should "default to Available status when created" in {
    val urinal: Urinal = new Urinal

    Available should === (urinal.status)
  }

  "Urinal" should "return properly assigned position when requested" in {
    val urinal: Urinal = new Urinal
    urinal.position = 1

    1 should === (urinal.position)
  }

  "Urinal" should "be unavailable when status is occupied" in {
    val urinal: Urinal = new Urinal
    urinal.status = Occupied

    false should === (urinal.isAvailable)
  }

  "Urinal" should "not have a left neighbor by default" in {
    val urinal: Urinal = new Urinal

    false should === (urinal.hasLeftNeighbor)
  }

  "Urinal" should "not have a right neighbor by default" in {
    val urinal: Urinal = new Urinal

    false should === (urinal.hasRightNeighbor)
  }

  "Urinal" should "have left neighbor when created" in {
    val urinal: Urinal = new Urinal(2)
    urinal.leftNeighbor = Option(new Urinal(1))

    true should === (urinal.hasLeftNeighbor)
  }

  "Urinal" should "have right neighbor when created" in {
    val urinal: Urinal = new Urinal(1)
    urinal.rightNeighbor = Option(new Urinal(2))

    true should === (urinal.hasRightNeighbor)
  }

  "Urinal" should "be considered occupied when an available urinal, with an occupied left and right neighbor exists" in {
    val middle: Urinal = new Urinal(1)

    var left: Urinal = new Urinal(2)
    left.status = Occupied
    middle.leftNeighbor = Option(left)

    var right: Urinal = new Urinal(3)
    right.status = Occupied
    middle.rightNeighbor = Option(right)

    false should === (middle.isAvailable)
  }

  "Urinal" should "be considered available when an available urinal, in first position, with an non-existent left neighbor and occupied right neighbor" in {
    val urinal: Urinal = new Urinal

    var right: Urinal = new Urinal
    right.status = Occupied
    urinal.rightNeighbor = Option(right)

    true should === (urinal.isAvailable)
  }

  "Urinal" should "be considered available when an available urinal, in last position, with an occupied left neighbor and non-existent right neighbor" in {
    val urinal: Urinal = new Urinal

    var left: Urinal = new Urinal
    left.status = Occupied
    urinal.leftNeighbor = Option(left)

    true should === (urinal.isAvailable)
  }

}
