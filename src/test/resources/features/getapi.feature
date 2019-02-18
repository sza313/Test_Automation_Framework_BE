Feature: Testing API GET

  @Sample
  Scenario: Sample API scenario
    Given Config URL: 'https://reqres.in'
    Given Test a single user name with request: '/api/users/2' and first name: Janet last name: 'Weaver'