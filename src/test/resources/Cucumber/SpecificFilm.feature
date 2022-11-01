Feature: This will find information of a specific film
  As a user, I would like to load the information of a specific film when I search it's ID

  Scenario Outline: Find a title of a movie by it's ID
    Given A user wants to find information about a film by it's ID
    When they search <ID>
    Then the program responds with the film name "<answer>"

    Examples:
      | ID          | answer |
      | 7           | AIRPLANE SIERRA     |
      | 91          | BOUND CHEAPER       |
      | 422         | HOLLOW JEOPARDY     |
      | 263         | DURHAM PANKY        |
      | 196         | CRUELTY UNFORGIVEN  |
      | 965         | WATERSHIP FRONTIER  |