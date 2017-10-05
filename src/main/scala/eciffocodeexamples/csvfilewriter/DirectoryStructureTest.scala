import eciffocodeexamples.csvfilewriter.DirectoryStructure1
import org.scalatest.FunSuite

class DirectoryStructureTest extends FunSuite {
  test("shall create passed folder sturcture") {
    val list = "/var/reactore/webapps/debians/zipfiles"
    val result = DirectoryStructure1.buildPaths(list.split("/").toList.tail)
    assert(result == list)
  }
}