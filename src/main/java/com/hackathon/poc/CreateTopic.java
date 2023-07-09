package com.hackathon.poc;

import java.io.IOException;

import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.Subscription;
import com.google.pubsub.v1.SubscriptionName;
import com.google.pubsub.v1.TopicName;

public class CreateTopic {
	public static void main(String... args) throws Exception {
		// TODO(developer): Replace these variables before running the sample.
		String projectId = "indigo-anchor-392208";
		String topicId = "gmail-topic";
		String subscriptionId = "gmail-topic-sub";

		createPullSubscriptionExample(projectId, subscriptionId, topicId);

	}

	public static void createPullSubscriptionExample(String projectId, String subscriptionId, String topicId)
			throws IOException {
		try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create()) {
			TopicName topicName = TopicName.of(projectId, topicId);
			SubscriptionName subscriptionName = SubscriptionName.of(projectId, subscriptionId);
			// Create a pull subscription with default acknowledgement deadline of 10
			// seconds.
			// Messages not successfully acknowledged within 10 seconds will get resent by
			// the server.
			Subscription subscription = subscriptionAdminClient.createSubscription(subscriptionName, topicName,
					PushConfig.getDefaultInstance(), 10);
			System.out.println("Created pull subscription: " + subscription.getName());
		}
	}
}