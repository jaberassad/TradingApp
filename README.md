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

3. **Run the React App**
   ```bash
   cd trading-app-frontend
   npm start
   

### 2. **Usage 📚**

   Provide details on how to use the application, including examples or commands for common actions.
   
   Once the application is running, you can access the following features:
   
   - **User Authentication**: Navigate to `/login` or `/signup` to access user authentication features.
   - **Share Trading**: Go to the `/` page to start buying or selling shares as well as look at charts for different stocks.
   - **Transaction History**: View your transaction history on the `/profile` page.

