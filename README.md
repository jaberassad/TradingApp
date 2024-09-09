# Trading App

## Overview 📝

This project involves using the Jave Framework Springboot to handle serverside requests such as share trading as well as user authentification. On the front end, a JavaScript framework is used to display all relevant information dynamically, ensuring a smooth and interactive user experience.

## Features 

- ** User Authentication 🔑: Secure login and signup functionality using Spring Boot, ensuring user data is protected.
- ** Share Trading 📈: Allows users to buy and sell shares in real-time, with server-side logic handling transactions.
- ** Data Display 📊: Dynamically presents relevant trading information using a JavaScript framework for a responsive and user-friendly experience.
- ** Transaction History 🧾: Displays a detailed history of all completed transactions for users to review past activities.
- ** MarketStock API Integration 🔌: Fetches live stock data directly from the MarketStock API to provide users with up-to-date market trends and stock prices.
- ** Real-Time Updates ⏱️: Keeps users updated with the latest stock prices and portfolio value using real-time data.
- ** Error Handling ⚠️: Robust error handling and user-friendly messages to guide users in case of incorrect inputs or failed transactions.
- ** REST API Integration 🔌: Supports RESTful services for managing trading requests, authentication, and fetching data from external sources.
- -** AWS EC2 Deployment ☁️: The React frontend, Spring Boot API, and database are all deployed using AWS EC2, ensuring scalability and availability.

## Getting Started 🚀

### Prerequisites 📦
- JDK 17
- Node JS
- Maven
- Node.js & npm

### Installation 🔧

1. **Clone the Repository** 

   ```bash
   git clone git clone https://github.com/jaberassad/TradingApp.git
   cd TradingApp
   

2. **Run the SpringBoot App**
   ```bash
   java -jar trading-app-backend/target/Trading-App-0.0.1-SNAPSHOT.jar

   Note: If you encounter an error indicating that port 8081 is already in use, you can resolve this issue by checking which process is using the port and terminating it. Use the following commands:
      ```bash
      lsof -i :8081

   Note: Copy the PID that is returned:
      ```bash
      kill -9 <PID>
   

### Neural Network Architecture

The neural network takes as input an array representing the user's drawing. Below is a summary of the architecture:
1. **Input Layer**:
   - Size: 784 units (flattened 28x28 pixel images)
   - **Normalization**: The input images are normalized to have values between 0 and 1.

2. **Dense Layer 1**:
   - Units: 300
   - Activation Function: ReLU

3. **Dense Layer 2**:
   - Units: 60
   - Activation Function: ReLU

4. **Dense Layer 3**:
   - Units: 50
   - Activation Function: ReLU

5. **Dense Layer 4**:
   - Units: 40
   - Activation Function: ReLU

6. **Output Layer** 🎯:
   - Units: Number of categories (equal to the number of classes in the dataset)
   - Activation Function: Softmax

### Model Training 📚

- **Optimizer**: Adam 
- **Loss Function**: Categorical Cross-Entropy
- **Metrics**: Accuracy
- **Epochs**: 10
- **Batch Size**: 32
- **Validation Split**: 0.2

The model is trained on 80% of the data while the rest is used for evaluation to reduce bias and have an accurate estimate on its performance.

For more details on the training process, you can refer to the `training.py` script.
