package top

import java.io.{InputStream, OutputStream, OutputStreamWriter, Writer}

import com.amazonaws.services.lambda.runtime.Context
import play.api.libs.json.{JsObject, Json}

import scalaz.\/

/*
  Simple API Gateway lambda to generate an MP3 using Polly and store it in S3.
 */
object Text2Mp3 {
  case class QueryParameters(id: String, voice: String, text: String)

  implicit val parametersReads = Json.reads[QueryParameters]

  def handler(in: InputStream, out: OutputStream, context: Context): Unit = {
    val logger = context.getLogger

    // Expect a GUID, a voice, & the text as query parameters
    val input = parseInput(in)

    logger.log(s"input: $input")

    val params = input.flatMap(getParams)

    logger.log(s"params: $params")

    val response = buildResponse(200, "OK")

    withWriter(new OutputStreamWriter(out), writer => {
      logger.log(s"response: $response")
      writer.write(response)
    })
  }

  private def parseInput(in: InputStream): Throwable \/ JsObject =
    \/.fromTryCatchNonFatal(Json.parse(in).as[JsObject])


  private def getParams(obj: JsObject): Throwable \/ QueryParameters =
    \/.fromTryCatchNonFatal((obj \ "queryStringParameters").as[QueryParameters])

  private def buildResponse(status: Int, body: String): String =
    Json.stringify(Json.obj("statusCode" -> status, "body" -> body))

  // Ensure the Writer gets closed
  private def withWriter[T <: Writer](writer: T, f: Writer => Unit): Unit = try {
    f(writer)
  } finally {
    writer.close()
  }
}
