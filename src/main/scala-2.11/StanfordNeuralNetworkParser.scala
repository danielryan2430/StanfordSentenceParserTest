import java.io.StringReader

import edu.stanford.nlp.ling.HasWord
import edu.stanford.nlp.parser.nndep.DependencyParser
import edu.stanford.nlp.process.DocumentPreprocessor
import edu.stanford.nlp.tagger.maxent.MaxentTagger
import edu.stanford.nlp.trees.GrammaticalStructure

/**
 * Created by dimberman on 10/23/15.
 */
class StanfordNeuralNetworkParser {
  val modelPath:String = DependencyParser.DEFAULT_MODEL
  val taggerPath:String = "edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger"
  val tagger:MaxentTagger = new MaxentTagger(taggerPath)
  var i = 0

  val parser:DependencyParser = DependencyParser.loadFromModelFile(modelPath)
    def parseTest(doc:String):List[String] = {
      val tokenizer:DocumentPreprocessor = new DocumentPreprocessor(new StringReader(doc))
      val iter = tokenizer.iterator()
      var ret = List[String]()
      while(iter.hasNext){
        val curr = iter.next()
//        println("tokens = "  + curr)
        val tagged = tagger.tagSentence(curr)
        val gs = parser.predict(tagged)
        i = i+ 1
//        println(gs.)
        ret = ret :+ gs.toString
      }
      ret
    }
}
