import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing.Validation
import argonaut.{DecodeJson, Parse}

/**
 * Created by dimberman on 9/17/15.
 */
class JsonDocumentExtractor {
  def extractLine(input: String):Option[HGClassificationDocument] = {
    var s = input.stripMargin
    if(s(s.length-1)==']') s = s.init
    if(s(s.length-1) == ',') s = s.init
    val a = Parse.decodeValidation[HGClassificationDocument](s).toOption
    a
  }

  implicit def HGDocumentDecodeJson: DecodeJson[HGClassificationDocument] =
    DecodeJson(c => for {
      id <- (c --\ "_id").jdecode[String]
      document <- (c --\ "d").jdecode[String]
    } yield HGClassificationDocument(id, document))


}
