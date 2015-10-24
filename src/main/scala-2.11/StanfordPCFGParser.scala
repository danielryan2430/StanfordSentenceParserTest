import java.util.Properties

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation
import edu.stanford.nlp.pipeline.{Annotation, StanfordCoreNLP}
import scala.collection.JavaConversions._


/**
 * Created by dimberman on 10/23/15.
 */
class StanfordPCFGParser {
  val props = new Properties()
  props.put("annotators", "tokenize, ssplit, pos, lemma")
  val pipeline = new StanfordCoreNLP(props)
  var i = 0

  def parseSentence(doc:String ):List[String] = {
    val tokens = new Annotation(doc)
    pipeline.annotate(tokens)
    val sentences = tokens.get(classOf[SentencesAnnotation]).toList
    sentences.foreach(s =>{  println(i * 1000);
      i = i+ 1})
    sentences.map(_.toString)
  }
}
