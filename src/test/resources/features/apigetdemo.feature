Feature: API Test Automation

  @API
  Scenario Outline: Test API GET requests, verify single users and multiple users and user lists
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
      | userName | resourceName | userID | firstName | lastName  |
      | George   | cerulean     | 7      | Michael   | Lawson    |
      | Janet    | fuchsia rose | 8      | Lindsay   | Fergusson |
      | Emma     | true red     | 9      | Tobias    | Funke     |