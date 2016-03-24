package com.dj

class Urinal {
  var leftNeighbor: Option[Urinal] = None
  var rightNeighbor: Option[Urinal] = None
  var position: Int = 0
  var status: UrinalStatus = Available

  def this(p: Int) = {
    this()
    position = p
  }

  def isAvailable: Boolean = {
    if (this.isOccupied) return false

    if (isFirstPositionAndAvailable) return true

    if (isLastPositionAndAvailable) return true

    if (isMiddlePositionAndAvailable) return false

    true
  }

  private def isOccupied: Boolean = {
    this.status == Occupied
  }

  def haveLeftNeighbor: Boolean = {
    leftNeighbor.isDefined
  }

  private def doesNot(u: Boolean):Boolean = { !(u) }

  def haveRightNeighbor: Boolean = {
    rightNeighbor.isDefined
  }

  private def noLeftOrOccupied: Boolean = {
     doesNot(haveLeftNeighbor) || leftNeighbor.get.isOccupied
  }

  private def noRightOrOccupied: Boolean = {
    doesNot(haveRightNeighbor) || rightNeighbor.get.isOccupied
  }

  private def isMiddlePositionAndAvailable: Boolean = {
    return noLeftOrOccupied || noRightOrOccupied
  }

  private def isFirstPositionAndAvailable: Boolean = {
    doesNot(haveRightNeighbor) && available
  }

  private def isLastPositionAndAvailable: Boolean = {
    doesNot(haveLeftNeighbor) && available
  }

  private def available: Boolean = {
    status == Available
  }

}
