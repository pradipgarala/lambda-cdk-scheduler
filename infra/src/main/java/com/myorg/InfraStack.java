package com.myorg;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.events.Rule;
import software.amazon.awscdk.services.events.Schedule;
import software.amazon.awscdk.services.events.targets.LambdaFunction;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class InfraStack extends Stack {
    public InfraStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public InfraStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Function function = Function.Builder.create(this, "hello-world-lambda")
                .handler("org.example.HelloWorldLambda::handleRequest")
                .runtime(Runtime.JAVA_21)
                .memorySize(128)
                .timeout(Duration.seconds(20))
                .functionName("hello-world-lambda")
                .code(Code.fromAsset("../assets/functions.jar"))
                .build();

        Rule rule = Rule.Builder.create(this, "hello-world-lambda-scheduler")
                .ruleName("hello-world-lambda-scheduler")
                .schedule(Schedule.rate(Duration.minutes(1)))
                .build();

        rule.addTarget(new LambdaFunction(function));

    }
}
