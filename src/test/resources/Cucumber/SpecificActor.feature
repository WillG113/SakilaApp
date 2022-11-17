Feature: Find Actor by ID
  Scenario Outline: As a user, I would like to load the information of a specific actor when I search for their ID
    Given A user wants to find information about an actor by their ID
    When they search the <ID>
    Then the program responds with the actor firstname "<answer>"

    Examples:
      | ID          | answer |
      | 7           | 	GRACE         |
      | 91          | 	CHRISTOPHER   |
      | 196         |   BELA          |