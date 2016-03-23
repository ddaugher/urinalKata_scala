package com.dj

class Bathroom {
  var urinals: List[Urinal] = List[Urinal]()

  def this(numberOfUrinals: Int) = {
    this()

    val _isSingle = isSingleStallFacility(numberOfUrinals)

    if (_isSingle) {
      urinals = List(getOrCreate(1))
    } else {
      urinals = createMultiUrinalFacility(numberOfUrinals)
    }

    def _isInMiddle(p: Int): Boolean = { p > 1 && p < numberOfUrinals }

    def linkUrinals(u: Urinal): Urinal = {
      if (u.position == 1) return linkFirst()
      if (_isInMiddle(u.position)) return linkMiddle(u.position)
      linkLast(u.position)
    }

    val link: Urinal => Urinal = (t) => linkUrinals(t)

    urinals.map(link)
  }

  def numberOfUrinals: Int = {
    this.urinals.size
  }

  def getOrCreate(position: Int): Urinal = {
    getUrinalByPosition(position) match {
      case Some(u) => u
      case None => new Urinal(position)
    }
  }

  def createMultiUrinalFacility(num: Int): List[Urinal] = {
    val base: Int => Urinal = (t) => getOrCreate(t)
    List.range(1, num + 1).map(base)
  }

  def isSingleStallFacility(numberOfUrinals: Int): Boolean = {
    1 == numberOfUrinals
  }

  def linkFirst(): Urinal = {
    val u = getOrCreate(1)
    u.rightNeighbor = getUrinalByPosition(2)
    u
  }

  def getUrinalByPosition(p: Int): Option[Urinal] = {
    urinals.filter(_.position == p).headOption
  }


  def linkMiddle(p: Int): Urinal = {
    val u = getOrCreate(p)
    u.leftNeighbor = getUrinalByPosition(p - 1)
    u.rightNeighbor = getUrinalByPosition(p + 1)
    u
  }

  def linkLast(p: Int): Urinal = {
    val u = getOrCreate(p)
    u.leftNeighbor = getUrinalByPosition(p - 1)
    u
  }

  def nextAvailable: Option[Urinal] = {

    if (urinals.isEmpty) return None

    if (urinalExistsWithoutNeighbor) { getUrinalWithNoNeighbor } else { getUrinalWithNeighbor }
  }

  private def getUrinalWithNeighbor = {
    urinals.filter(_.status == Available).lastOption
  }

  private def getUrinalWithNoNeighbor = {
    urinals.filter(_.isAvailable).lastOption
  }

  private def urinalExistsWithoutNeighbor = {
    getUrinalWithNoNeighbor.isDefined
  }
}
