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

  def hasLeftNeighbor: Boolean = {
    leftNeighbor.isDefined
  }

  def hasRightNeighbor: Boolean = {
    rightNeighbor.isDefined
  }

  private def noLeft: Boolean = {
    leftNeighbor.isEmpty || leftNeighbor.get.isOccupied
  }

  private def noRight: Boolean = {
    rightNeighbor.isEmpty || rightNeighbor.get.isOccupied
  }

  private def isMiddlePositionAndAvailable: Boolean = {
    return noLeft || noRight
  }

  private def isFirstPositionAndAvailable: Boolean = {
    !hasRightNeighbor && available
  }

  private def isLastPositionAndAvailable: Boolean = {
    !hasLeftNeighbor && available
  }

  private def available: Boolean = {
    status == Available
  }

}
