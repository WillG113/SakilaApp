Feature: This will find an actor by their ID
  As a user, I would like to load the information of a specific actor when I search their ID

  Scenario Outline: Find a actor by their ID
    Given A user wants to find information about an actor by their ID
    When they search the <ID>
    Then the program responds with the actor firstname "<answer>"

    Examples:
      | ID          | answer |
      | 7           | 	GRACE         |
      | 91          | 	CHRISTOPHER   |
      | 196         |   BELA          |