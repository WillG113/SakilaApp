Feature: Order Process

  Scenario: As a user, I would like to purchase a single film from the application
    Given I have a film in my basket that is in stock
    When I click the checkout button
    Then The order will be processed
    And the stock will be lowered for the given item