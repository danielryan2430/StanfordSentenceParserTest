import scala.io.Source

/**
 * Created by dimberman on 10/23/15.
 */
object TimingTester {
  def main(args: Array[String]) = {
    val fileIterator = Source.fromInputStream(getClass.getResourceAsStream("smallerUncompressedDocs.json")).getLines()
    val smallFileIterator = Source.fromInputStream(getClass.getResourceAsStream("reallySmallList.json")).getLines()

    val originalParser = new StanfordPCFGParser
    var time = getTime
    val extractor = new JsonDocumentExtractor
    val sentences = fileIterator.map(extractor.extractLine).flatten.map(_.document).toList
    val aFewSentences =  smallFileIterator.map(extractor.extractLine).flatten.map(_.document)
//    sentences.foreach(originalParser.parseSentence)
//    time = time - getTime
//    println("PCFG parser took " + time.asInstanceOf[Double] / 1000 + "seconds for 5000 documents to " + originalParser.i + "sentences")

    val srParser = new StanfordShiftReduceParser
    time = getTime()
    var dropped = 0
    var groupedSentences = List[String]()

    for (i <- 0 to sentences.length/1000){
        groupedSentences = groupedSentences :+ sentences.drop(i * 1000).foldLeft("")((b,a) => b + " " + a)
    }
    sentences.foreach(srParser.parseSentences)
    time = getTime - time
    println("SR parser took " + time.asInstanceOf[Double] / 1000 + "seconds for 5000 documents to " + srParser.i + "sentences")
  }

  def getTime() = java.lang.System.currentTimeMillis()

}

