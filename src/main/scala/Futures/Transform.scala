/*
*  val futureResult = someOperaton
* finalResult.transform(_ =>
      true,
      ex => connectorUtilities.handleFailure(getConnectorName, getLocalFilePath, fileNames, ex)
    )
  }
* */