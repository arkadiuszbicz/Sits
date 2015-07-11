/**
 * Scala Installation Tool Schell
 */
package pl.abksystem.sits

trait Command  {
  def run() = this
  def rollback() = this
  def test() = this
  def finalise() = this
}

object Sits {
  def toSits(sits:Sits, comm : Command): Sits = {
    new Sits() {
      override val comms : List[Command] = comm :: sits.comms
    }
  }

}

trait Sits {
   def comms : List[Command]
}

trait Copy extends Sits {
  import Sits._
  def from : String
  def to : String

  class CopyCommand extends Command {
  }

  def copy(from:String, to:String) : Sits = {
    toSits(this, new CopyCommand())
  }
}
