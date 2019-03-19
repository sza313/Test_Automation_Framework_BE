Feature: API Test Automation

  @API
  Scenario Outline: Test API GET calls
    Given Config URL: 'https://reqres.in'
    When Get a single user with request: '/api/users/' and user ID: '<userID>'
    Then Verify a single user by first name: '<firstName>' and last name: '<lastName>'
    When Get a single resource with request: '/api/unknown/2'
    Then Verify a single resource by first name: fuchsia rose
    When Test a collection of users with request: '/api/users?page=1'
    Then The collection contain the user name: '<userName>'
    When Test a collection of resources with request: '/api/unknown'
    Then The collection contain the resource name: '<resourceName>'
    Given Test user not found by ID: 23 with request: '/api/users/'
    Given Test a single resource not found by ID: 23 with request: 'api/unknown/'
    Given Test delayed response with delay time: 2 with request: 'api/users?delay='

    Examples:
      | userName | resourceName | userID | firstName | lastName |
      | George   | cerulean     | 7      | Michael   | Lawson   |
      | Janet    | fuchsia rose | 8      | Lindsay   | Fergusson |
      | Emma     | true red     | 9      | Tobias    | Funke    |

  @API
  Scenario Outline: Test API POST functions
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

  @API
  Scenario Outline: Test API UPDATE/DELETE functions
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

  @API
  Scenario Outline: Create user entity's and verify it
    Given Config URL: 'https://reqres.in'
    When Create user entity's using request '/api/users?page=2'
    Then User is present by first name: '<firstName>'

    Examples:
      | firstName |
      | Eve       |
      | Charles   |
      | Tracey    |
