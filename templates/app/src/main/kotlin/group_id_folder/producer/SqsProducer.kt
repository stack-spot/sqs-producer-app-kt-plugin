package {{project_group_id}}.producer

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.amazonaws.services.sqs.model.SendMessageResult
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SqsProducer(
    private val awsSQS: AmazonSQS,
    @Value("\${sqs.queue.produce}") private val queueUrl: String
) {

    fun produce(data: String): SendMessageResult {
        return awsSQS.sendMessage(SendMessageRequest(queueUrl, data))
    }

    fun fifoProduce(data: String, messageGroupId: String): SendMessageResult {
        return awsSQS.sendMessage(SendMessageRequest(queueUrl, data).withMessageGroupId(messageGroupId))
    }
}
