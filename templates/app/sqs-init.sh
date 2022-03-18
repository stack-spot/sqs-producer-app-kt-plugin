#!/bin/bash

export AWS_ACCESS_KEY_ID=FAKE
export AWS_SECRET_ACCESS_KEY=FAKE
export AWS_DEFAULT_REGION=us-east-1
#aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name test.fifo --attributes "FifoQueue=true"
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name "{{inputs.sqs_producer_queue_name}}"
