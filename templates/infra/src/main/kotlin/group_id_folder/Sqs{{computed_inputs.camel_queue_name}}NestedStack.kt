package {{project_group_id}}

import org.cdk8s.plus22.EnvValue
import software.amazon.awscdk.NestedStack
import software.amazon.awscdk.services.sqs.Queue
import software.constructs.Construct

class Sqs{{computed_inputs.camel_queue_name}}NestedStack(scope: Construct, id: String, stage: Stage) : NestedStack(scope, id) {
    val queue: Queue = Queue.Builder.create(this, "{{inputs.sqs_producer_queue_name}}")
        .queueName("{{inputs.sqs_producer_queue_name}}")
        .build()

    init {
        Manifests.env["SQS_QUEUE_PRODUCE"] = EnvValue.fromValue(queue.queueName)
        Manifests.env["CLOUD_AWS_REGION_STATIC"] = EnvValue.fromValue(stage.cloud.account.region)
    }
}