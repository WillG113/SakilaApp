Feature: Pagination
  Scenario: if a user scrolls to the bottom of a div, load more data (aka pagination)
  As a user, if I scroll to the bottom of the div I would like the application to load additional values.

  Given A user had loaded the index page of the application
  When the user reaches the bottom of the page
  Then additional values will be loaded and added to the page
