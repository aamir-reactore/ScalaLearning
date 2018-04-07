package paginate

import slick.lifted.Query

object PaginateUtilities extends App {

  def listPagination[T](pageNumber: Int, pageSize: Int, list: List[T]): List[T] = {
    if (pageNumber != 0 && pageSize != 0) {
      val startIndex = (pageNumber - 1) * pageSize
      val endIndex = startIndex + pageSize
      // val indexes = (startIndex to endIndex).toList
      val result = list.slice(startIndex, endIndex)
      result.take(pageSize)
    } else {
      list
    }

  }

  def paginateQuery[P, Q](query: Query[P, Q, Seq],
                          pageNumber: Option[Int], pageSize: Option[Int]) = {
    if (pageNumber.isDefined && pageSize.isDefined)
      query.drop((pageNumber.get - 1) * pageSize.get).take(pageSize.get)
    else
      query
  }

}