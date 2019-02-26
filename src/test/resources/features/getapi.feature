Feature: Testing API GET

  @Sample
  Scenario: Testing the API GET endpoints
    Given Config URL: 'https://reqres.in'
    Given Test a single user by first name: Janet and last name: Weaver with request: '/api/users/2'
    Given Test a single resource by name: fuchsia rose with request: '/api/unknown/2'
    Given Test a list of users by ID's: 4, 5, 6 with request: '/api/users?page=2'
    Given Test a list of resources by name: cerulean, fuchsia rose, true red with request: '/api/unknown'
    Given Test user not found by ID: '23' with request: '/api/users/'
    Given Test a single resource not found by ID: 23 with request: 'api/unknown/'
    Given Test delayed response with delay time: 2 and request: 'api/users?delay='
