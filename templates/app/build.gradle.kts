val awsSpringCloudVersion: String by project

dependencies {
    implementation("io.awspring.cloud:spring-cloud-aws-messaging:$awsSpringCloudVersion")
    implementation("io.awspring.cloud:spring-cloud-starter-aws:$awsSpringCloudVersion")
}
