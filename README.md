# QuestGame

  GUI application made with JavaFX and Scenebuilder that allows users to answer questions from video games in order to gain tokens. They can also create new quests for other users by spending their tokens.
  
  For data persistence I used PostgreSQL and Gradle for dependencies.
  
  As packages for what I had time to implement I created:
    -Controller -> controllers for all the pages that are needed for the application
    -Repository -> interfaces for each model and DB repository that implements them
    -Service -> service for communication between controllers and repos
    -Domain -> entities
    
  The app is pretty basic, I only got to work a few hours on it but it is still a work in progress project.
