List(6, 1, 2, 3, 4).partition(x => x % 2 == 0)
List(6, 1, 2, 3, 4).span(x => x % 2 == 0)

val l = List(1, 2, 3, 4, 5, 6,7,8,9)

def remoteIndexElement(list: List[Int], index: Int) = {

  def remove(curlList: List[Int], n: Int): List[Int] = {
    if (n > list.length) {
      curlList
    } else {
      remove(list(n - 1) :: curlList, n + n)
    }
  }

  remove(Nil, index)
}
remoteIndexElement(l, 4)

def usingZip(l:List[Int], index:Int) = {
  l.zipWithIndex.filterNot { x =>
    (x._2 + 1) % index == 0
  }.map(_._1)
}
usingZip(l, 4)