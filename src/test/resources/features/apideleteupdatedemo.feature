Feature: API Test Automation

  @API
  Scenario Outline: Test API UPDATE/DELETE functions, create user then modify then delete
    Given Config URL: 'https://reqres.in'
    When Create a user with name: '<createUserName>' job: '<userJob>' using request: '/api/users'
    Then Update existing user using request: '/api/users/'
    And Delete user using request: '/api/users/'
    Then Verify that user is deleted using request: '/api/user/'

    Examples:
      | createUserName | userJob  |
      | Bob            | Gardener |
      | Joe            | Cook     |
      | Greg           | Cop      |