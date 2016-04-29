Meta:

Narrative:
As a system
I want to provide 2-phase registration
So that I can limit toxic registrations

Scenario: draft account should be persisted properly
Given visitor <email> intends to register with the following information:
|first_name|last_name|
|awesome  |visitor |
When visitor <email> registers
Then visitor <email> draft account is persisted with the following information:
|first_name|last_name|
|awesome  |visitor |
And visitor <email> gets the following token <token> to confirm registration
  
Examples:
|<email>            |
|awesome@visitor.com|
