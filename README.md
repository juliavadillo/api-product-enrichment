Product Enrichment API
This project is a Spring Boot REST API that simulates an AI-powered product enrichment service.
It receives basic product data via a POST request and returns a detailed product description, along with the original product information.

Features
POST /products – Accepts a product name and features, then generates a description.

Stores enriched products in-memory for quick retrieval.

Demonstrates clean architecture with Controller, Service, and DTO layers.

AI functionality is mocked to simulate the generation process without external API calls.

Example Request

{
  "name": "Wireless Mouse",
  "features": ["ergonomic", "bluetooth", "rechargeable"]
}

Example Response

{
  "name": "Wireless Mouse",
  "features": ["ergonomic", "bluetooth", "rechargeable"],
  "description": "An ergonomic wireless mouse with Bluetooth connectivity and a long-lasting rechargeable battery."
}
Tech Stack

Java 17

Spring Boot 3

Maven

In-memory storage (Java Collections)

