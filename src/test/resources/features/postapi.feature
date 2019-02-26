Feature: Testing API POST

  @Sample
  Scenario: Testing the API POST endpoints
    Given Config URL: 'https://reqres.in'
    Given Create a user with name: Hudak Szabi job: Boss using request: '/api/users'
    Given Register user with email: testFotLife@gangsta.com password: itsASecretToEveryone using request: '/api/register'
    Given Unsuccessful user registration with email: notgood@stuff.com using request: '/api/register'
    Given Login successful with email: testFotLife@gangsta.com password: itsASecretToEveryone using request '/api/login'
    Given Login unsuccessful with email: testFotLife@gangsta.com using request: '/api/login'