Feature: API Test Automation

  @API
  Scenario Outline: Test API POST functions, creating user register user and login with username and password
    Given Config URL: 'https://reqres.in'
    When Create a user with name: '<createUserName>' job: '<userJob>' using request: '/api/users'
    Then Validate created user with name: '<createUserName>' and job: '<userJob>'
    When Register user with email: testForLife@gangsta.com password: itsASecretToEveryone using request: '/api/register'
    Then Validate registered user
    When Unsuccessfully register user with email: notgood@stuff.com using request: '/api/register'
    Then Validate unsuccessful user registration
    When Login with email: test@gmail.com password: password using request '/api/login'
    Then Check that login was successful
    Then Check that login was unsuccessful with email: bademail@gmail.com using request: '/api/login'

    Examples:
      | createUserName | userJob |
      | Joey           | Actor   |
      | Rachel         | Fashion |
      | Ross           | Paleo   |