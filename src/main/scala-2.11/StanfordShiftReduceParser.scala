/**
 * Created by dimberman on 10/23/15.
 */

import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import scala.collection.JavaConversions._


class StanfordShiftReduceParser {
  val p = new Properties()
  p.put("annotators", "tokenize ssplit pos parse lemma ")
  p.put("parse.model", "englishSR.beam.ser.gz")
  val corenlp = new StanfordCoreNLP(p)
  var i = 0

  def parseSentences(text:String) = {
    println("parsing sentence")
    val annotation = new Annotation(text)
    corenlp.annotate(annotation)
    val sentences = annotation.get(classOf[SentencesAnnotation]).toList
    sentences.foreach(s =>{ println(i);
      i = i+ 1})
    sentences.map(_.toString)
  }


}
