Feature: API Test Automation

  @API
  Scenario Outline: GET an user list, create user entity's and verify it
    Given Config URL: 'https://reqres.in'
    When Create user entity's using request '/api/users?page=2'
    Then User is present by first name: '<firstName>'

    Examples:
      | firstName |
      | Eve       |
      | Charles   |
      | Tracey    |
