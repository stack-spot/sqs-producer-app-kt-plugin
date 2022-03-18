val awsSpringCloudVersion: String by project

dependencies {
    implementation("io.awspring.cloud:spring-cloud-aws-messaging:$awsSpringCloudVersion")
    implementation("io.awspring.cloud:spring-cloud-starter-aws:$awsSpringCloudVersion")
    runtimeOnly("com.amazonaws:aws-java-sdk-sts:1.12.179")
}
