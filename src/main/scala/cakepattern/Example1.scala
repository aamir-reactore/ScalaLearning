/*trait RosterFacadeComponent {

  def rosterRepo: RosterRepository

  def rosterEmployeeRepo: RosterEmployeeRepository


  trait RosterFacade extends RosterFacadeComponent {

    override lazy val rosterRepo: RosterRepository = ImplRosterRepository
    override lazy val rosterEmployeeRepo: RosterEmployeeRepository = ImplRosterEmployeeRepository
  }

  abstract class RosterEmployeeRepository
    extends RelationalRepository[RosterEmployeeTable, RosterEmployee](TableQuery[RosterEmployeeTable]) {
    self: RosterFacadeComponent =>

  }
  object ImplRosterEmployeeRepository extends RosterEmployeeRepository with RosterFacade

  //Test cases

  import org.scalatest.mockito.MockitoSugar

  trait MockedRosterFacade extends RosterFacadeComponent with MockitoSugar {
    override lazy val rosterRepo: RosterRepository = mock[RosterRepository]
    override lazy val rosterEmployeeRepo: RosterEmployeeRepository = mock[RosterEmployeeRepository]
  }

  //testfile.scala
  class RosterEmployeeApiTest extends WordSpec with MockitoSugar with ScalaFutures with Matchers {
    "Roster Employee Repository Test" must {

      "throw validation exception if roster doesn't exist for passed rosterId" in {
        when(RosterEmployeeApiTest1.rosterRepo.filter(any(classOf[(BaseTable[_]) => Rep[Boolean]]), any[SysParams])
        (any(classOf[CanBeQueryCondition[Rep[Boolean]]]))).thenReturn(Future.successful(Nil))
        val result = RosterEmployeeApiTest1.createRosterEmployees(LocalDate.parse("2016-02-02"), 1L, 1L, Nil, prs)
        result.failed.futureValue.isInstanceOf[ValidationException]
      }
    }
  }

  object RosterEmployeeApiTest1 extends RosterEmployeeRepository with MockedRosterFacade
   {
   override def getRosterEmployees(rosterId: Long, prs: SysParams) = Future.successful(rosterEmp
   }
  */
